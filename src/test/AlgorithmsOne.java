package test;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmsOne {
    private static void run () {
        int n = 30;
        List<String> out = new ArrayList<> ( );

        for (int i = 1; i <= n; i++) {
            if ( i % 2 == 0 ) {
                if ( out.size ( ) == 0 ) {
                    out.add ( "Fizz" );
                } else {
                    out.add ( ",Fizz" );
                }
            }
            if ( i % 3 == 0 ) {
                out.add ( ",Buzz" );
            }
            if ( i % 5 == 0 ) {
                out.add ( ",Jazz" );
            }
            if ( i % 2 == 0 && i % 3 == 0 ) {
                out.add ( ",Fizz-Buzz" );
            }
        }


        out.forEach ( i -> System.out.print ( i ) );

    }

    public static void main ( String[] args ) {
        AlgorithmsOne.run ( );
    }
}
