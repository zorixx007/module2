package l1.deposits;

import java.util.Collections;

public class ApplicationDeposit {

    private static void run () {
        Init.initDeposit ( );
        //        sort by inerestRate - overridden compareTo
        Collections.sort ( Init.depositList );
        System.out.println ( "sort by InterestRate" );
        Init.printList ( );
        double total = Init.getPrincipal ( );
        double averageRate = Init.getAverageRate ( );
        System.out.println ( "Total amount: " + total );
        System.out.println ( "Average rate: " + averageRate );

        //        sort by sum
        System.out.println ( "\n sort by Sum" );
        Init.depositList.sort ( Init.comparatorSum );
        Init.printList ( );


        //        sort by date
        System.out.println ( "\n sort  by Date" );
        Init.depositList.sort ( Init.comparatorDate );
        Init.printList ( );

        //        sort by DayLong
        System.out.println ( "\n sort  by dayLong" );
        Collections.sort ( Init.depositList, new ComparatorDayLong() );
        Init.printList ( );

//        remove sum < 10000
        System.out.println ( "\n remove sum < 10000" );
        RemoveService.removeSmall ();
        Init.printList ( );

    }

    public static void main ( String[] args ) {
        ApplicationDeposit.run ( );
    }
}
