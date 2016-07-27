package com.kjoshi.droidrecyclerview.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kjoshi.droidrecyclerview.model.Customer;

import java.util.ArrayList;

/**
 * The CustomerRepo class is a class to perform customer operations.
 *
 * @author Khemchand Joshi
 * @version 1.0
 * @since 2016-06-29
 */
public class CustomerRepo {
    private final static String TAG = CustomerRepo.class.getSimpleName();
    private Context mContext;
    CustomerDbHelper mDbHelper;

    public CustomerRepo(Context _context) {
        this.mContext = _context;
        mDbHelper = CustomerDbHelper.getInstance(_context);
    }

    /**
     * This is the method to add a customer in database.
     *
     * @param Customer parameter used.
     * @return void if there is an exception then exception would be thrown.
     * @throws IOException On input error.
     */
    public void addCustomer(Customer customer) {
        // Create and/or open the database for writing
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(CustomerContract.CustomerTable.COLUMN_CUSTOMER_ID, customer.getCustomerId());
            values.put(CustomerContract.CustomerTable.COLUMN_CUSTOMER_FIRST_NAME, customer.getFirstName());
            values.put(CustomerContract.CustomerTable.COLUMN_CUSTOMER_LAST_NAME, customer.getLastName());
            values.put(CustomerContract.CustomerTable.COLUMN_CUSTOMER_EMAIL, customer.getEmail());
            values.put(CustomerContract.CustomerTable.COLUMN_CUSTOMER_PHONE, customer.getPhone());
            values.put(CustomerContract.CustomerTable.COLUMN_CUSTOMER_COMPANY, customer.getCompany());
            db.insertOrThrow(CustomerContract.CustomerTable.TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }

    }

    /**
     * This is the method to add a customer in database.
     *
     * @param Customer parameter used.
     * @return void if there is an exception then exception would be thrown.
     * @throws IOException On input error.
     */
    public ArrayList<Customer> getAllCustomers() {
        // Create and/or open the database for writing
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {

            Cursor cursor = db.query(CustomerContract.CustomerTable.TABLE_NAME, null, null, null, null, null, null);
            customers = getCustomerListFromCursor(cursor);
            cursor.close();
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }
        return customers;
    }

    /**
     * This is the method to extract values from curser and set in Customer Object.
     *
     * @param Cursor parameter used.
     * @return ArrayList<Customer> or exception if there is an exception then exception would be thrown.
     * @throws IOException On input error.
     */

    private ArrayList<Customer> getCustomerListFromCursor(Cursor cursor) {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    Customer customer = new Customer();
                    customer.setCustomerId(cursor.getString(cursor.getColumnIndexOrThrow(CustomerContract.CustomerTable.COLUMN_CUSTOMER_ID)));
                    customer.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(CustomerContract.CustomerTable.COLUMN_CUSTOMER_FIRST_NAME)));
                    customer.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(CustomerContract.CustomerTable.COLUMN_CUSTOMER_LAST_NAME)));
                    customer.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(CustomerContract.CustomerTable.COLUMN_CUSTOMER_EMAIL)));
                    customer.setCompany(cursor.getString(cursor.getColumnIndexOrThrow(CustomerContract.CustomerTable.COLUMN_CUSTOMER_COMPANY)));
                    customer.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(CustomerContract.CustomerTable.COLUMN_CUSTOMER_PHONE)));
                    //Add Customer in customer list
                    customerList.add(customer);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return customerList;
    }

    /**
     * This is the method to add a customer in database.
     *
     * @param N/A parameter used.
     * @return String as Customer ID or exception if there is an exception then exception would be thrown.
     * @throws IOException On input error.
     */
    public String getLastInsertedCustomerNumber() {
        String customerId = "";
        // Create and/or open the database for writing
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            String[] columnsSelectionQuery = {CustomerContract.CustomerTable.COLUMN_CUSTOMER_ID};


            Cursor cursor = db.query(CustomerContract.CustomerTable.TABLE_NAME, columnsSelectionQuery, null, null, null, null, CustomerContract.CustomerTable.COLUMN_CUSTOMER_ID + " DESC LIMIT 1");
            if (cursor.moveToFirst()) {
                do {
                    customerId = cursor.getString(cursor.getColumnIndex(CustomerContract.CustomerTable.COLUMN_CUSTOMER_ID));
                    // do what ever you want here
                } while (cursor.moveToNext());
            }
            cursor.close();

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }

        return customerId;
    }

}
