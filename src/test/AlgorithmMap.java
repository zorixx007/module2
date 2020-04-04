package test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlgorithmMap {
    static List<CustomMap> myList = new ArrayList<> ( );

    static void put ( CustomMap newItem ) {
        if ( newItem != null ) {
            myList.add ( newItem );
        }
    }

    static CustomMap getCustomMap ( String k , Timestamp t ) {
        CustomMap current = null;
        if ( myList.size ( ) < 0 ) {
            return current;
        }
        for (CustomMap item : myList) {
            if ( current == null ) {
                if ( item.getCurrentKey ( ).equals ( k ) && ( item.getCurrentTime ( ).before ( t ) || item.getCurrentTime ( ) == t ) ) {
                    current = item;
                    if ( item.getCurrentTime ( ) == t ) {
                        return current;
                    }
                }
            } else if ( item.getCurrentKey ( ).equals ( k ) && ( item.getCurrentTime ( ).before ( t ) || item.getCurrentTime ( ) == t ) ) {
                if ( item.getCurrentTime ( ) == t ) {
                    return item;
                } else if ( item.getCurrentTime ( ).after ( current.getCurrentTime ( ) ) ) {
                    current = item;
                }
            }
        }
        return current;
    }

    static String get ( String k , Timestamp t ) {
        return getCustomMap ( k , t ).getCurrentValue ( );
    }

    static String get ( String k ) {
        return getCustomMap ( k ).getCurrentValue ( );
    }

    static CustomMap getCustomMap ( String s ) {
        Timestamp t = new Timestamp ( System.currentTimeMillis ( ) );
        return getCustomMap ( s , t );
    }


    private static void run () {
        CustomMap my1 = new CustomMap ( "foo" , "bar" );


        put ( my1 );
        try {
            TimeUnit.MINUTES.sleep ( 3 );
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
        CustomMap my2 = new CustomMap ( "foo" , "bar1" );
        put ( my2 );

        myList.forEach ( item -> System.out.println ( item ) );
        System.out.println ( "getting last" );
        System.out.println ( get ( "foo" ) );
        System.out.println ( "getting 1 older" );
        System.out.println ( get ( "foo" , new Timestamp ( System.currentTimeMillis ( ) - 60 * 1000 ) ) );


    }


    public static void main ( String[] args ) {
        AlgorithmMap.run ( );
    }
}
