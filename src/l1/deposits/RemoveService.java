package l1.deposits;

import java.util.ListIterator;

public class RemoveService {
    public static void removeSmall () {
        //                for (int i = 0; i < Init.depositList.size ( ); i++) {
        //                    if ( Init.depositList.get ( i ).getSum ( ) < 10000 ) {
        //                        Init.depositList.remove ( i );
        //                        i--;
        //                    }
        //                }


//        ListIterator<Deposit> iter = Init.depositList.listIterator ( );
//        while (iter.hasNext ( )) {
//            if ( iter.next ( ).getSum ( ) < 10000 ) {
//                iter.remove ( );
//            }
//        }
        Init.depositList.removeIf ( b -> b.getSum () < 10000 );


    }
}
