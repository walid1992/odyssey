package com.osmartian.small.app.home.network;

import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.SparseArray;

import com.walid.rxretrofit.interfaces.ICodeVerify;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author   : walid
 * Data     : 2016-09-01  18:53
 * Describe :
 */
public class SeaCodeVerify implements ICodeVerify {

    @IntDef({Code.SUCCESS, Code.TICKET_EXPIRE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Code {
        int SUCCESS = 200;
        int TICKET_EXPIRE = 401;
    }

    private static SparseArray<String> codeValues = new SparseArray<>();

    static {
//        codeValues.append(Code.SYSTEM_ERROR, "参数错误");
//        codeValues.append(Code.SYSTEM_UNRECOGNIZED_ERROR, "未识别错误");
//        codeValues.append(Code.SYSTEM_ERROR, "系统错误");
//        codeValues.append(Code.USER_ACCOUNT_TOKEN_ERROR, "用户权限错误");
//        codeValues.append(Code.USER_ACCOUNT_NO_LOGIN, "登录已失效,请重新登录");
    }

    @Override
    public boolean checkValid(int code) {
        return code == Code.SUCCESS;
    }

    @Override
    public String formatCodeMessage(int code, String message) {
        String codeValue = codeValues.get(code);
        switch (code) {
            case SeaCodeVerify.Code.TICKET_EXPIRE:
//                UserApiService.refreshTicket(new SimpleHttpCallback<UserTicketVo>() {
//                    @Override
//                    public void onNext(UserTicketVo userTicketVo) {
//                        SPUtils.put(R.string.key_tickte, userTicketVo.getTicket());
//                    }
//                });
                break;
            default:
                break;
        }
        if (!TextUtils.isEmpty(codeValue)) {
            return codeValue;
        }
        return message;
    }

}