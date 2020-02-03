package l1.linkedAndArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ApplicationArrayAndLinked {
    private static void run () {
        ArrayList<Integer> arrayInt = new ArrayList<> ( );
        LinkedList<Integer> linkedInt = new LinkedList<> ( );
        for (int i = 0; i < 10; i++) {
            arrayInt.add ( i );
            arrayInt.add ( i + 12000 );
            linkedInt.add ( i );
            linkedInt.add ( i + 12000 );
        }
        Collections.sort ( arrayInt );
        Collections.sort ( linkedInt );

        //add 10 elements into ArrayList and calculate time
        long startTime = System.nanoTime ( );
        for (int i = 10; i < 10; i++) {
            arrayInt.add ( i , i );
        }
        long endTime = System.nanoTime ( );
        System.out.println ( "Array list time to insert 10 elements is: " + ( ( endTime - startTime ) ) + " nanoseconds" );


        //add 10 elements into LinkedList and calculate time
        startTime = System.nanoTime ( );
        for (int i = 10; i < 10; i++) {
            linkedInt.add ( i , i );
        }
        endTime = System.nanoTime ( );
        System.out.println ( "Linked list time to insert 10 elements is: " + ( ( endTime - startTime ) ) + " nanoseconds" );


        //add 10000 elements into ArrayList and calculate time
        startTime = System.nanoTime ( );
        for (int i = 10; i < 10000; i++) {
            arrayInt.add ( i , i );
        }
        endTime = System.nanoTime ( );
        System.out.println ( "Array list time to insert 10 000 elements is: " + ( ( endTime - startTime ) / 1000 ) + " microseconds" );


        //add 10000 elements into LinkedList and calculate time
        startTime = System.nanoTime ( );
        for (int i = 10; i < 10000; i++) {
            linkedInt.add ( i , i );
        }
        endTime = System.nanoTime ( );
        System.out.println ( "Linked list time to insert 10 000 elements is: " + ( ( endTime - startTime ) / 1000 ) + " microseconds" );


    }

    public static void main ( String[] args ) {
        ApplicationArrayAndLinked.run ( );
    }
}
