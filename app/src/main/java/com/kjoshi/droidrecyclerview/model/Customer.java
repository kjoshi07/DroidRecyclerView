package com.kjoshi.droidrecyclerview.model;

/**
 * The Customer class is a simple POJO class.
 *
 * @author Khemchand Joshi
 * @version 1.0
 * @since 2016-06-29
 */
public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private String email;

    public Customer() {

    }

    public Customer(String _id, String _firstName, String _lastName, String _email, String _phone, String _company) {
        this.customerId = _id;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.email = _email;
        this.company = _company;
        this.phone = _phone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName + ", " + email + ", " + phone + ", " + company;
    }
}
