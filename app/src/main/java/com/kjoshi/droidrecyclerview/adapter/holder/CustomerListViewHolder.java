package com.kjoshi.droidrecyclerview.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kjoshi.droidrecyclerview.R;

/**
 * Created by Khemchandj on 7/19/2016.
 */
public class CustomerListViewHolder extends RecyclerView.ViewHolder {
    public TextView companyNameTextView, contactPersonTextView, phoneTextView;

    public CustomerListViewHolder(View itemView) {
        super(itemView);
        companyNameTextView = (TextView) itemView.findViewById(R.id.companyNameTextView);
        contactPersonTextView = (TextView) itemView.findViewById(R.id.contactPersonTextView);
        phoneTextView = (TextView) itemView.findViewById(R.id.phoneTextView);
    }
}
