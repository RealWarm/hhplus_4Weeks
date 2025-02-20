package com.hoonterpark.concertmanager.domain.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PaymentOutBoxEventStatus {
    INIT            (10, "INIT"),
    PENDING         (20, "PENDING"),
    RECEIVED        (30, "RECEIVED"),
    SUCCESS         (40, "SUCCESS"),
    FAIL            (50, "FAIL")
    ;

    private int resultCode;
    private String resultMessage;

    PaymentOutBoxEventStatus(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    private static final Map<Integer, PaymentOutBoxEventStatus> LOOKUP = new HashMap<Integer, PaymentOutBoxEventStatus>();

    static {
        for (PaymentOutBoxEventStatus elem : EnumSet.allOf(PaymentOutBoxEventStatus.class)) {
            LOOKUP.put(elem.getResultCode(), elem);
        }
    }


    public static PaymentOutBoxEventStatus get(int code) {
        return LOOKUP.get(code);
    }

    public static int getResultCode(int code) {
        return get(code).resultCode;
    }

    public static String getResultMessage(int code) {
        return get(code).resultMessage;
    }

}

