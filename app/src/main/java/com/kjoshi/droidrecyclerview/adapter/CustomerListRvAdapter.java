package com.kjoshi.droidrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kjoshi.droidrecyclerview.R;
import com.kjoshi.droidrecyclerview.adapter.holder.CustomerListViewHolder;
import com.kjoshi.droidrecyclerview.model.Customer;

import java.util.ArrayList;

/**
 * Created by Khemchandj on 7/19/2016.
 */
public class CustomerListRvAdapter extends RecyclerView.Adapter<CustomerListViewHolder> {
    private final static String TAG = CustomerListRvAdapter.class.getSimpleName();
    private ArrayList<Customer> customersList = new ArrayList<Customer>();

    public CustomerListRvAdapter(ArrayList<Customer> _customersList) {
        this.customersList = _customersList;
    }

    @Override
    public CustomerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list, parent, false);
        return new CustomerListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomerListViewHolder holder, int position) {
        try {

            Customer customer = customersList.get(position);
            String fullName = customer.getFirstName() + " " + customer.getLastName();
            holder.companyNameTextView.setText(customer.getCompany());
            holder.contactPersonTextView.setText(fullName);
            holder.phoneTextView.setText(customer.getPhone());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public int getItemCount() {

        return customersList.size();
    }
}
