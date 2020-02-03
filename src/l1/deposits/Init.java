package l1.deposits;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Init {
    public static List<Deposit> depositList = new ArrayList<> ( );

    public static void initDeposit () {
        depositList.add ( new Deposit ( "Simple" , Date.valueOf ( "2013-09-08" ) , 61 , 2500 , 18 ) );
        depositList.add ( new Deposit ( "MonthCapit" , Date.valueOf ( "2012-02-01" ) , 181 , 10000 , 21 ) );
        depositList.add ( new Deposit ( "Simple" , Date.valueOf ( "2013-11-12" ) , 30 , 5500 , 15.3 ) );
        depositList.add ( new Deposit ( "Barrier" , Date.valueOf ( "2011-12-18" ) , 370 , 43000 , 19.56 ) );
        depositList.add ( new Deposit ( "MonthCapit" , Date.valueOf ( "2013-07-12" ) , 91 , 12000 , 16 ) );
    }

    public static double getPrincipal () {
        double sum = 0;
        //        depositList.forEach ( item -> {
        //            sum += item.getSum ( );
        //        } );

        for (Deposit deposit : depositList) {
            sum += deposit.getSum ( );
        }
        return sum;
    }

    public static double getAverageRate () {
        double avg = 0;
        Iterator<Deposit> iter = depositList.iterator ( );
        while (iter.hasNext ( )) {
            Deposit cur = iter.next ( );
            avg += cur.getInterestRate ( );
        }
        return avg / depositList.size ( );
    }

    public static void printList () {
        depositList.forEach ( item -> {
            System.out.println ( item );
        } );
    }

    //        sort by sum
    public  static Comparator<Deposit> comparatorDate = new Comparator<Deposit> ( ) {
        @Override
        public int compare ( Deposit a , Deposit b ) {
            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;
            if ( a.getStartDate ().after ( b.getStartDate () )  ) {
                return AFTER;
            } else if ( a.getStartDate ().before ( b.getStartDate () ) ) {
                return BEFORE;
            } else {
                return EQUAL;
            }
        }
    };



//    sort by date
    public static Comparator<Deposit> comparatorSum = (Deposit a, Deposit b) -> (int)a.getSum () - (int)b.getSum ();

}

class ComparatorDayLong implements Comparator<Deposit>{
    @Override
    public int compare ( Deposit a , Deposit b ) {
        return a.getDayLong ()- b.getDayLong ();
    }

}