package serialization;

import l1.deposits.Deposit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DeserializeDeposit {
    public static List<Deposit> deserializeDeposit ( Path filePath ) {
        List<Deposit> depositList = new ArrayList<> ( );
        try (FileInputStream fis = new FileInputStream ( filePath.toFile ( ) );
             ObjectInputStream ois = new ObjectInputStream ( fis )) {
            while (fis.available ( ) > 0) {
                depositList.add ( (Deposit) ois.readObject ( ) );
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace ( );
        }
        return depositList;
    }
}
