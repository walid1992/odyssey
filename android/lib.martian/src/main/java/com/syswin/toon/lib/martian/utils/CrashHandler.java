package com.syswin.toon.lib.martian.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.syswin.toon.lib.martian.app.MartianApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;
import java.util.TreeSet;

/**
 * UncaughtExceptionHandler：线程未捕获异常控制器是用来处理未捕获异常的。
 * 如果程序出现了未捕获异常默认情况下则会出现强行关闭对话框
 * 实现该接口并注册为程序中的默认未捕获异常处理
 * 这样当未捕获异常发生时，就可以做些异常处理操作
 * 例如：收集异常信息，发送错误报告 等。
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private WeakReference<Context> weakContext;
    private SendReports sendReports = null;
    // 使用Properties来保存设备的信息和错误堆栈信息
    private Properties properties = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";
    // 错误报告文件的扩展名
    private static final String CRASH_REPORTER_EXTENSION = ".txt";
    private String path = "";
    private boolean restartAfterCrash;

    private CrashHandler() {
    }

    private static class SingletonHolder {
        static CrashHandler instance = new CrashHandler();
    }

    public static CrashHandler getInstance() {
        return SingletonHolder.instance;
    }

    public void init(Context ctx, String path, boolean restartAfterCrash) {
        weakContext = new WeakReference<>(ctx);
        this.path = path;
        this.restartAfterCrash = restartAfterCrash;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    // 当UncaughtException发生时会转入该函数来处理
    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handleException(ex);
                Looper.prepare();
                Toast.makeText(weakContext.get(), "水滴互助服务器打烊,请稍后再试...", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        SystemClock.sleep(1500);
        if (restartAfterCrash) {
            MartianApp.instance().restart();
        } else {
            MartianApp.instance().exitProgram();
        }
    }

    // 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
    private void handleException(final Throwable ex) {
        if (ex == null || weakContext == null) {
            return;
        }
        // 收集设备信息
        collectCrashDeviceInfo(weakContext.get());
        // 保存崩溃信息
        String crashFileName = saveCrashInfoToFile(ex);
        Log.d(TAG, "crashFileName = " + crashFileName);
        // 发送错误报告到服务器
        sendCrashReportsToServer(weakContext.get());
    }

    // 收集程序崩溃的设备信息
    private void collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                properties.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);
                properties.put(VERSION_CODE, String.valueOf(pi.versionCode));
            }
            //添加渠道号
//            properties.put("ctype", Setting.instance().getCType());
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Error while collect package info", e);
        }
        // 使用反射来收集设备信息.在Build类中包含各种设备信息,
        // 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
        // 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                // 通过设置Accessible属性为true,才能对私有变量进行访问，不然会得到一个IllegalAccessException的异常
                field.setAccessible(true);
                properties.put(field.getName(), String.valueOf(field.get(null)));
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "Error while collect crash info", e);
            }
        }
    }

    // 保存错误信息到文件中
    private String saveCrashInfoToFile(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        // 将此 throwable 及其追踪输出到指定的 PrintWriter
        ex.printStackTrace(printWriter);
        // getCause() 返回此 throwable 的 cause；如果 cause 不存在或未知，则返回 null。
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = info.toString();
        printWriter.close();
        properties.put(STACK_TRACE, result);

        try {
            long timestamp = System.currentTimeMillis();
            String fileName = "crash-" + timestamp + CRASH_REPORTER_EXTENSION;
            fileName = path + File.separator + fileName;
            FileOutputStream trace = new FileOutputStream(new File(fileName));
            properties.store(trace, "");
            trace.flush();
            trace.close();
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an fail occured while writing report file...", e);
        }
        return null;
    }

    // 把错误报告发送给服务器,包含新产生的和以前没发送的.
    private void sendCrashReportsToServer(Context ctx) {
        String[] crFiles = getCrashReportFiles(ctx);
        if (crFiles != null && crFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<>();
            sortedFiles.addAll(Arrays.asList(crFiles));
            for (String fileName : sortedFiles) {
                File cr = new File(ctx.getFilesDir(), fileName);
                postReport(ctx, cr);
                cr.delete();
            }
        }
    }

    // 获取错误报告文件名
    private String[] getCrashReportFiles(Context ctx) {
        File filesDir = ctx.getFilesDir();
        // 实现FilenameFilter接口的类实例可用于过滤器文件名
        FilenameFilter filter = new FilenameFilter() {
            // 测试指定文件是否应该包含在某一文件列表中。
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        // 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录
        return filesDir.list(filter);
    }

    // 报告错误
    private void postReport(Context ctx, File file) {
//        try {
//            FileInputStream fin = new FileInputStream(file);
//            byte[] buffer = new byte[1024];
//            int i;
//            StringBuilder sb = new StringBuilder();
//            while ((i = fin.read(buffer)) != -1) {
//                sb.append(new String(buffer, 0, i));
//            }
//            fin.close();
//            MobclickAgent.reportError(ctx, sb.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    //在程序启动时候, 可以调用该函数来发送以前没有发送的报告
    public void sendPreviousReportsToServer() {
        if (null != sendReports && !sendReports.isAlive()) {
            sendReports.start();
        }
    }

    private final class SendReports extends Thread {

        private Context context;
        private final Object lock = new Object();

        public SendReports(Context context) {
            this.context = context;
        }

        public void run() {
            try {
                synchronized (this.lock) {
                    sendCrashReportsToServer(context);
                }
            } catch (Exception e) {
                Log.e(TAG, "SendReports fail.", e);
            }
        }

    }
}

