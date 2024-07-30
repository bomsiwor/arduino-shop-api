package com.arduino.shop_api.exception;

public class ErrorMessage {
    public static String ERROR_SERVER = "Server failed to process";

    public static String DATA_NOT_FOUND = "Data not found";

    public static String DATA_ALREADY_EXISTS = "Data already exists";

    public static String DATA_ALREADY_DELETED = "Data already deleted";

    // Validation Error message
    public static String FIELD_REQUIRED = "Field %s required";

    public static String FIELD_MIN = "Field %s min %d";

    public static String FIELD_MAX = "Field %s max %d";
}
