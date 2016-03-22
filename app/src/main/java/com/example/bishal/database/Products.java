package com.example.bishal.database;

/**
 * Created by Bishal on 3/22/2016.
 */
public class Products {

    private int _id;
    private String _productName;

    public Products(){}

    public Products(String productname) {
        this._productName = productname;
    }

    public String getProductname() {
        return _productName;
    }

    public void setProductname(String _productname) {
        this._productName = _productname;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
