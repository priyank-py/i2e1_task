package com.priyank_py.i2e1task.entities;

public class MainResponse {
    private static boolean status;
    private static String message;
    private static DataResponse data;

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        MainResponse.status = status;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        MainResponse.message = message;
    }

    public static DataResponse getData() {
        return data;
    }

    public static void setData(DataResponse data) {
        MainResponse.data = data;
    }
}
