package l1.deposits;

public class RemoveService {
    public static void removeSmall () {
        for (int i = 0; i < Init.depositList.size ( ); i++) {
            if ( Init.depositList.get ( i ).getSum ( ) < 10000 ) {
                Init.depositList.remove ( i );
                i--;
            }
        }
    }
}
