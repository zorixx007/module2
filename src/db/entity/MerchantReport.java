package db.entity;

/*
Create a specific class and method that will show a total sum payed for a merchant with a given
        id (argument). The report should also contain merchant id, title and lastSent info
*/

import java.sql.*;

public class MerchantReport {
    int merchantId;
    String title;
    double totalSum;
    Date lastSent;

    public MerchantReport ( int merchantId , String title , double totalSum , Date lastSent ) {
        this.merchantId = merchantId;
        this.title = title;
        this.totalSum = totalSum;
        this.lastSent = lastSent;
    }

    public MerchantReport () {
    }


    public void setMerchantId ( int merchantId ) {
        this.merchantId = merchantId;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public void setTotalSum ( double totalSum ) {
        this.totalSum = totalSum;
    }

    public void setLastSent ( Date lastSent ) {
        this.lastSent = lastSent;
    }

    @Override
    public String toString () {
        return "MerchantReport{" +
                "merchantId=" + merchantId +
                ", title='" + title + '\'' +
                ", totalSum=" + totalSum +
                ", lastSent=" + lastSent +
                '}';
    }
}
