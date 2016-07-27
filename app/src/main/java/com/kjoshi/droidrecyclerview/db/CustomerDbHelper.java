package com.kjoshi.droidrecyclerview.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The CustomerDbHelper class is a helper class to create and upgradable database which extends SQLiteOpenHelper.
 *
 * @author Khemchand Joshi
 * @version 1.0
 * @since 2016-06-29
 */
public class CustomerDbHelper extends SQLiteOpenHelper {
    private final static String TAG = CustomerDbHelper.class.getSimpleName();
    public final static String DATABASE_NAME = "customer.db";
    public final static int DATABASE_VERSION = 3;
    private static CustomerDbHelper sInstance;

    public static synchronized CustomerDbHelper getInstance(Context _context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new CustomerDbHelper(_context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private CustomerDbHelper(Context _context) {
        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is the method to create tables in database.
     *
     * @param SQLitedDatabase parameters used.
     * @return Nothing.
     * @throws IOException On database operation error.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CustomerContract.SQL_CREATE_CUSTOMERS);

    }

    /**
     * This is the method to create new database if version is changed otherwise do nohting..
     *
     * @param SQLitedDatabase, int as oldVersion and int as new version parameters used.
     * @return Nothing.
     * @throws IOException On database operation error.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(CustomerContract.SQL_DELETE_CUSTOMERS);
            onCreate(db);
        }

    }
}
