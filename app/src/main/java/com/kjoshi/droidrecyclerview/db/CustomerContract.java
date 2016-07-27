package com.kjoshi.droidrecyclerview.db;

import android.provider.BaseColumns;

/**
 * The CustomerContract class is a database contract to create all tables and its columns.
 *
 * @author Khemchand Joshi
 * @version 1.0
 * @since 2016-06-29
 */
public final class CustomerContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_CUSTOMERS =
            "CREATE TABLE " + CustomerTable.TABLE_NAME + " (" +
                    CustomerTable._ID + " INTEGER PRIMARY KEY," +
                    CustomerTable.COLUMN_CUSTOMER_ID + TEXT_TYPE + COMMA_SEP +
                    CustomerTable.COLUMN_CUSTOMER_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    CustomerTable.COLUMN_CUSTOMER_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    CustomerTable.COLUMN_CUSTOMER_COMPANY + TEXT_TYPE + COMMA_SEP +
                    CustomerTable.COLUMN_CUSTOMER_EMAIL + TEXT_TYPE + COMMA_SEP +
                    CustomerTable.COLUMN_CUSTOMER_PHONE + TEXT_TYPE +
                    ")";

    public static final String SQL_DELETE_CUSTOMERS =
            "DROP TABLE IF EXISTS " + CustomerTable.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public CustomerContract() {

    }

    /* Inner class that defines the customers table contents */
    public static abstract class CustomerTable implements BaseColumns {
        public static final String TABLE_NAME = "customers";
        public static final String COLUMN_CUSTOMER_ID = "customerId";
        public static final String COLUMN_CUSTOMER_FIRST_NAME = "firstName";
        public static final String COLUMN_CUSTOMER_LAST_NAME = "lastName";
        public static final String COLUMN_CUSTOMER_COMPANY = "company";
        public static final String COLUMN_CUSTOMER_PHONE = "phone";
        public static final String COLUMN_CUSTOMER_EMAIL = "email";
    }
}
