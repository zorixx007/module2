package db.repository;


import db.entity.Merchant;
import db.repository.MyConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* Create an application to display a list of all merchants sorted alphabetically in descending order */
public class ListMerchantReport {
    public static void alphabeticOrder(){
        try (Connection conn = MyConnection.getConnection ( )) {
            String sql = "SELECT * FROM merchant order by name desc;";
            Statement stmt = conn.createStatement ( );
            ResultSet rs = stmt.executeQuery ( sql );
            while (rs.next ( )) {
                Merchant current = new Merchant ( rs.getInt ( "id" ),rs.getString ( "name" ),
                        rs.getString ( "bankName" ), rs.getString ( "swift" ), rs.getString ( "account" ),
                        rs.getDouble ( "charge" ), rs.getInt ( "period" ), rs.getDouble ( "minSum" ),
                        rs.getDouble ( "needToSend" ), rs.getDouble ( "sent" ), rs.getDate ( "lastSent" ));
                System.out.println (current );
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace ( );
        }
    }
}
