package serialization;

import l1.deposits.Deposit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializeDeposit {
    public static void serializeDeposit ( List<Deposit> depositList ) {
        try (ObjectOutputStream oos = new ObjectOutputStream ( new FileOutputStream ( "src/serialization/Deposits.dat" ) )) {
            depositList.forEach ( k -> {
                try {
                    oos.writeObject ( k );
                } catch (IOException e) {
                    e.printStackTrace ( );
                }
            } );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
}
