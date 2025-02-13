package com.project.Mappers.Enums;

public enum PatTypeEnum {
    ONLINE, OFFLINE, BANK_TRANSACTION;

    public static boolean isValid(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        for (PatTypeEnum type : values()) {
            if (type.name().equalsIgnoreCase(value.trim())) {
                return true;
            }
        }
        return false;
    }
    
}
