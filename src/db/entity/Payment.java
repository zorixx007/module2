package db.entity;

import java.sql.Date;

public class Payment {
    int id;
    Date dt;
    Merchant merchant;
    Customer customer;
    String goods;
    double sumPaid;
    double chargePaid;

    public Payment ( int id , Date dt , Merchant merchant , Customer customer ,
                     String goods , double sumPaid , double chargePaid ) {
        this.id = id;
        this.dt = dt;
        this.merchant = merchant;
        this.customer = customer;
        this.goods = goods;
        this.sumPaid = sumPaid;
        this.chargePaid = chargePaid;
    }

    public Payment ( Date dt , Merchant merchant , Customer customer ,
                     String goods , double sumPaid ) {
        this.dt = dt;
        this.merchant = merchant;
        this.customer = customer;
        this.goods = goods;
        this.sumPaid = sumPaid;
        this.chargePaid =  ( merchant.getCharge ( ) * sumPaid ) / 100 ;
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

    public Merchant getMerchant () {
        return merchant;
    }

    public void setMerchant ( Merchant merchant ) {
        this.merchant = merchant;
    }

    public Customer getCustomer () {
        return customer;
    }

    public void setCustomer ( Customer customer ) {
        this.customer = customer;
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

    @Override
    public String toString () {
        return "Payment{" +
                "id=" + id +
                ", dt=" + dt +
                ", merchantId=" + merchant.getMerchantId ( ) +
                ", customerId=" + customer.getCustomerId ( ) +
                ", goods='" + goods + '\'' +
                ", sumPaid=" + sumPaid +
                ", chargePaid=" + chargePaid +
                '}';
    }
}
