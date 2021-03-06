package l1.deposits;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Deposit implements Comparable<Deposit>, Serializable {
    String depositType;
    Date startDate;
    int dayLong;
    double sum;
    double interestRate;

    public Deposit ( String depositType , Date startDate , int dayLong , double sum , double interestRate ) {
        this.depositType = depositType;
        this.startDate = startDate;
        this.dayLong = dayLong;
        this.sum = sum;
        this.interestRate = interestRate;
    }

    public Deposit () {
    }

    public String getDepositType () {
        return depositType;
    }

    public Date getStartDate () {
        return startDate;
    }

    public int getDayLong () {
        return dayLong;
    }

    public double getSum () {
        return sum;
    }

    public double getInterestRate () {
        return interestRate;
    }

    @Override
    public String toString () {
        DateFormat dateFormat = new SimpleDateFormat ( "dd.MM.yyyy" );
        return "Deposit{" +
                "depositType='" + depositType + '\'' +
                ", startDate=" + dateFormat.format ( startDate ) +
                ", dayLong=" + dayLong +
                ", sum=" + sum +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public int compareTo ( Deposit that ) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        if ( this.interestRate > that.interestRate ) {
            return AFTER;
        } else if ( this.interestRate < that.interestRate ) {
            return BEFORE;
        } else {
            return EQUAL;
        }
    }


    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
        Deposit deposit = (Deposit) o;
        return dayLong == deposit.dayLong &&
                Double.compare ( deposit.sum , sum ) == 0 &&
                Double.compare ( deposit.interestRate , interestRate ) == 0 &&
                depositType.equals ( deposit.depositType ) &&
                startDate.equals ( deposit.startDate );
    }

    @Override
    public int hashCode () {
        return Objects.hash ( depositType , startDate , dayLong , sum , interestRate );
    }
}
