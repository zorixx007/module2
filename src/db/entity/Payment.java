package db.entity;

import java.sql.Date;

public class Payment {
    int id;
    Date dt;
    int merchantId;
    int customerId;
    String goods;
    double sumPaid;
    double chargePaid;

    public Payment ( int id , Date dt , int merchantId , int customerId ,
                     String goods , double sumPaid , double chargePaid ) {
        this.id = id;
        this.dt = dt;
        this.merchantId = merchantId;
        this.customerId = customerId;
        this.goods = goods;
        this.sumPaid = sumPaid;
        this.chargePaid = chargePaid;
    }

    public Payment () {
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public Date getDt () {
        return dt;
    }

    public void setDt ( Date dt ) {
        this.dt = dt;
    }

    public int getMerchantId () {
        return merchantId;
    }

    public void setMerchantId ( int merchantId ) {
        this.merchantId = merchantId;
    }

    public int getCustomerId () {
        return customerId;
    }

    public void setCustomerId ( int customerId ) {
        this.customerId = customerId;
    }

    public String getGoods () {
        return goods;
    }

    public void setGoods ( String goods ) {
        this.goods = goods;
    }

    public double getSumPaid () {
        return sumPaid;
    }

    public void setSumPaid ( double sumPaid ) {
        this.sumPaid = sumPaid;
    }

    public double getChargePaid () {
        return chargePaid;
    }

    public void setChargePaid ( double chargePaid ) {
        this.chargePaid = chargePaid;
    }
}
