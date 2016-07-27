package com.kjoshi.droidrecyclerview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kjoshi.droidrecyclerview.adapter.CustomerListRvAdapter;
import com.kjoshi.droidrecyclerview.controller.CustomerController;
import com.kjoshi.droidrecyclerview.model.Customer;

import java.util.ArrayList;

/**
 * The MainActivity class will be launcher class for this application where it will ada customer in SQLite database.
 *
 * @author Khemchand Joshi
 * @version 1.0
 * @since 2016-06-29
 */

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private FloatingActionButton mAddCustomerFab;
    ProgressDialog mDialog;
    RecyclerView mRecyclerView;
    CustomerListRvAdapter mCustomerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set the toolbar on top
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //Initialize progress dialog
        mDialog = new ProgressDialog(this);
        //Open a add customer button when pressing + icon(Floating action button).
        mAddCustomerFab = (FloatingActionButton) findViewById(R.id.addCustomerFAB);
        mAddCustomerFab.setOnClickListener(addCustomerFABListener);
        //Initialize recycler view, get recycler view layout
        mRecyclerView = (RecyclerView) findViewById(R.id.customerListRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //Load customer data in asynchronous task.
        new LoadCustomerListAsyncTask().execute();

    }

    /**
     * This is the anonymous class for FloatingActionButton Listener.
     */

    private View.OnClickListener addCustomerFABListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //redirect to add a new customer activity when user click on FAB.
            Intent i = new Intent(MainActivity.this, AddCustomerActivity.class);
            startActivity(i);
        }
    };

    /**
     * This is the regular inner class which will load all customer information recycler view.
     */
    private class LoadCustomerListAsyncTask extends AsyncTask<Void, Void, ArrayList<Customer>> {

        @Override
        protected ArrayList<Customer> doInBackground(Void... voids) {
            ArrayList<Customer> customers = new ArrayList<Customer>();
            try {

                CustomerController controller = new CustomerController(MainActivity.this);
                customers = controller.getAllCustomers();
                for (Customer customer : customers) {
                    Log.d(TAG, customer.getCompany());
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            return customers;
        }

        @Override
        protected void onPreExecute() {
            try {
                mDialog = ProgressDialog.show(MainActivity.this, "Please wait ...", "loading customer Data ...", true);
                mDialog.setCancelable(true);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Customer> customers) {
            try {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }


                mCustomerListAdapter = new CustomerListRvAdapter(customers);
                mRecyclerView.setAdapter(mCustomerListAdapter);

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
