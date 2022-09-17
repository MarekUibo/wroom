package com.example.wroom.utils;

/**
 * Constant values used in this application
 *
 * @author Jonathan Rigottier and Kristiina Lindre
 */
public class Constants {
    public static class Audit {
        public static final String DEFAULT_AUDITOR = "SYSTEM";

    }

    public static class Security {
        public static final String AUTHORITY_ADMIN = "ROLE_ADMIN";
        public static final String AUTHORITY_EMPLOYEE_OWNER = "ROLE_OWNER";
        public static final String AUTHORITY_EMPLOYEE_MANAGER = "ROLE_MANAGER";
        public static final String AUTHORITY_EMPLOYEE_SALES_PERSON = "ROLE_SALES_PERSON";
        public static final String AUTHORITY_CUSTOMER = "ROLE_CUSTOMER";
    }
}