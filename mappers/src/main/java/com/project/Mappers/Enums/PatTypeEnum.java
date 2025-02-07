package com.project.Mappers.Enums;

public enum PatTypeEnum {
    ONLINE,
    OFFLINE,
    BANK_TRANSACTION;

    public static boolean isValid(String type) {
        if (type == null) {
            return false;
        }
        for (PatTypeEnum payType : values()) {
            if (payType.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
