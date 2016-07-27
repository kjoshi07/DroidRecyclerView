package com.kjoshi.droidrecyclerview.controller;

import android.content.Context;
import android.util.Log;

import com.kjoshi.droidrecyclerview.db.CustomerRepo;
import com.kjoshi.droidrecyclerview.model.Customer;

import java.util.ArrayList;

/**
 * CustomerController will control all the operation related to Customers whether they are database related or consume API.
 *
 * @author Khemchand Joshi
 * @version 1.0
 * @since 2016-06-30
 */
public class CustomerController {
    private final static String TAG = CustomerController.class.getSimpleName();
    private Context mContext;
    private final static String CUSTOMER_ID_PRE = "CN";

    //Define a constructor to accept Context as argument.
    public CustomerController(Context _context) {
        this.mContext = _context;
    }

    /**
     * This is the method to add a customer in database.
     *
     * @param Customer parameter used.
     * @return true or false as per success or failure of database operation.
     * @throws IOException On input error.
     */
    public boolean addCustomer(Customer customer) {
        try {
            //If customer ID is null then generate a new and set in Customer Object.
            if (customer.getCustomerId() == null) {
                customer.setCustomerId(generateCustomerId());
            }
            //Create a object of Customer repository and call addCustomer method to perform a operation to add a customer in SQLIte.
            CustomerRepo repo = new CustomerRepo(mContext);
            repo.addCustomer(customer);


        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return true;

    }

    /**
     * This is the method to get all the customers available in database..
     *
     * @param N/A parameter used.
     * @return true or false as per success or failure of database operation.
     * @throws IOException On input error.
     */
    public ArrayList<Customer> getAllCustomers() {
        try {
            //Create a object of Customer repository and call addCustomer method to perform a operation to add a customer in SQLIte.
            CustomerRepo repo = new CustomerRepo(mContext);
            return repo.getAllCustomers();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

    }

    /**
     * This is the method to generate a unique customer ID with "AN" and appending a sequential number..
     *
     * @param No parameter used.
     * @return String customerId
     * @throws IOException On input error.
     */
    private String generateCustomerId() {
        String newCustomerNumber = "";
        try {
            CustomerRepo repo = new CustomerRepo(mContext);
            String customerId = repo.getLastInsertedCustomerNumber();
            if (customerId != null && !customerId.isEmpty()) {
                String customerNumber = customerId.substring(2);
                //covert it into int
                int custNo = Integer.parseInt(customerNumber);
                custNo++;
                newCustomerNumber = CUSTOMER_ID_PRE + custNo;
            } else {
                newCustomerNumber = CUSTOMER_ID_PRE + 0001;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return newCustomerNumber;
    }
}
