package db.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Merchant {
    int merchantId;
    String name;
    String bankName;
    String swift;
    String account;
    double charge;
    int period;
    double minSum;
    double needToSend;
    double sent;
    LocalDate lastSent;

    public Merchant ( int merchantId , String name , String bankName , String swift , String account , double charge ,
                      int period , double minSum , double needToSend , double sent , LocalDate lastSent ) {
        this.merchantId = merchantId;
        this.name = name;
        this.bankName = bankName;
        this.swift = swift;
        this.account = account;
        this.charge = charge;
        this.period = period;
        this.minSum = minSum;
        this.needToSend = needToSend;
        this.sent = sent;
        this.lastSent = lastSent;
    }

    public Merchant () {
    }

    public int getMerchantId () {
        return merchantId;
    }

    public void setMerchantId ( int merchantId ) {
        this.merchantId = merchantId;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getBankName () {
        return bankName;
    }

    public void setBankName ( String bankName ) {
        this.bankName = bankName;
    }

    public String getSwift () {
        return swift;
    }

    public void setSwift ( String swift ) {
        this.swift = swift;
    }

    public String getAccount () {
        return account;
    }

    public void setAccount ( String account ) {
        this.account = account;
    }

    public double getCharge () {
        return charge;
    }

    public void setCharge ( double charge ) {
        this.charge = charge;
    }

    public int getPeriod () {
        return period;
    }

    public void setPeriod ( int period ) {
        this.period = period;
    }

    public double getMinSum () {
        return minSum;
    }

    public void setMinSum ( double minSum ) {
        this.minSum = minSum;
    }

    public double getNeedToSend () {
        return needToSend;
    }

    public void setNeedToSend ( double needToSend ) {
        this.needToSend = needToSend;
    }

    public double getSent () {
        return sent;
    }

    public void setSent ( double sent ) {
        this.sent = sent;
    }

    public LocalDate getLastSent () {
        return lastSent;
    }

    public void setLastSent ( LocalDate lastSent ) {
        this.lastSent = lastSent;
    }

    @Override
    public String toString () {
        return "Merchant{" +
                "merchantId=" + merchantId +
                ", name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ", swift='" + swift + '\'' +
                ", account='" + account + '\'' +
                ", charge=" + charge +
                ", period=" + period +
                ", minSum=" + minSum +
                ", needToSend=" + needToSend +
                ", sent=" + sent +
                ", lastSent=" + lastSent +
                '}';
    }
}




