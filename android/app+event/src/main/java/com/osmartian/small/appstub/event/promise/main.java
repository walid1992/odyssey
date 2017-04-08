package com.osmartian.small.appstub.event.promise;

public class main {

    public static void main(String[] args) {
        Promise<String, Integer> req = ExampleService.fakefetch(1000);
        req.then(s -> {
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
