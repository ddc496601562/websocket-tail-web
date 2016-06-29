package com.baidu.hina.web.tail.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.util.functions.Func1;

public class StreamUtil {

    public static Observable<String> createDummyStream() {
        return Observable.interval(1000, TimeUnit.MILLISECONDS).map(toMessage());
    }

    static Func1<Long, String> toMessage() {
        return new Func1<Long, String>() {

            @Override
            public String call(Long i) {
                return i
                        + " test info form tial-web-socket service";
            }
        };
    }

}
