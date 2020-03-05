package db.entity;

import java.sql.Date;

public class Customer {
    int customerId;
    String name;
    String address;
    String email;
    String ccNo;
    String ccType;
    Date maturity;

    public Customer ( int customerId , String name , String address ,
                      String email , String ccNo , String ccType , Date maturity ) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.ccNo = ccNo;
        this.ccType = ccType;
        this.maturity = maturity;
    }

    public Customer () {
    }

    public int getCustomerId () {
        return customerId;
    }

    public void setCustomerId ( int customerId ) {
        this.customerId = customerId;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress ( String address ) {
        this.address = address;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getCcNo () {
        return ccNo;
    }

    public void setCcNo ( String ccNo ) {
        this.ccNo = ccNo;
    }

    public String getCcType () {
        return ccType;
    }

    public void setCcType ( String ccType ) {
        this.ccType = ccType;
    }

    public Date getMaturity () {
        return maturity;
    }

    public void setMaturity ( Date maturity ) {
        this.maturity = maturity;
    }
}

