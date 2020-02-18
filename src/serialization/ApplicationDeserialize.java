package serialization;

import l1.deposits.Deposit;

import java.nio.file.Path;
import java.util.List;

public class ApplicationDeserialize {
    private static void run(){
        Path filePath = Path.of ( "src/serialization/Deposits.dat" );
        List<Deposit> depositList = DeserializeDeposit.deserializeDeposit ( filePath );
        depositList.forEach ( k -> System.out.println (k ) );
    }

    public static void main ( String[] args ) {
        ApplicationDeserialize.run ();
    }
}
