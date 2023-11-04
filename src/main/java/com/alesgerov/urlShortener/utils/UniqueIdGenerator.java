package com.alesgerov.urlShortener.utils;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
public final class UniqueIdGenerator {
    public static Long MACHINE_ID = 2L;
    public static Long DATACENTER_ID = 3L;
    public static Long SEQUENCE_NUMBER = 0L;

    public static long generateId() {
        String stringBuilder = String.valueOf(System.currentTimeMillis()) +
                MACHINE_ID +
                DATACENTER_ID +
                SEQUENCE_NUMBER;

        SEQUENCE_NUMBER++;

        return Long.parseLong(stringBuilder);
    }

}
