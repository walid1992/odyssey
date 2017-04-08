package com.osmartian.small.appstub.event.promise;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author   : walid
 * Data     : 2016-09-06  18:53
 * Describe :
 */

public class ExampleService {

    public static Promise<String, Integer> fakefetch(int ms) {
        return new Promise<>((resolve, reject) -> {
            System.out.println("BEGIN");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("END");
                    resolve.run("walid =>");
                }
            }, ms);
        });
    }

}
