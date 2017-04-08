package com.osmartian.small.appstub.event.promise;

import java.util.Timer;
import java.util.TimerTask;

public class main {

    public static void main(String[] args) {
        Promise<String, Integer> promise = new Promise<>((resolve, reject) -> {
            System.out.println("BEGIN");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("END");
                    resolve.run("walid =>");
                }
            }, 2000);
        });
        promise.then(s -> {
            System.out.println("then 1 : " + s);
            return s + s;
        }).then(s -> {
            System.out.println("then 2 : " + s);
            return s + s;
        }).then(s -> {
            System.out.println("then 3 : " + s);
        }).fail(integer -> System.out.println("main fail"));
    }
}
