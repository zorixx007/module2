package serialization;

import l1.deposits.Init;

public class ApplicationSerialize {
    private static void run () {
        Init.initDeposits ( );
        SerializeDeposit.serializeDeposit ( Init.depositList );
    }

    public static void main ( String[] args ) {
        ApplicationSerialize.run ( );
    }
}
