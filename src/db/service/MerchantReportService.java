package db.service;

import db.entity.MerchantReport;
import db.repository.MyConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MerchantReportService {


    public static MerchantReport get ( int requestID ) {
        MerchantReport currentReport = new MerchantReport ( );
        try (Connection conn = MyConnection.getConnection ( )) {
            String sql = "SELECT m.id as id, m.name as name, sum(p.sumPaid) as sum, m.lastSent as lastSent  FROM merchant m, payment p";
            sql += " WHERE p.merchantId = m.id AND m.id = '" + requestID + "';";
            Statement stmt = conn.createStatement ( );
            ResultSet rs = stmt.executeQuery ( sql );
            while (rs.next ( )) {
                currentReport.setMerchantId ( rs.getInt ( "id" ) );
                currentReport.setTitle ( rs.getString ( "name" ) );
                currentReport.setTotalSum ( rs.getDouble ( "sum" ) );
                currentReport.setLastSent ( rs.getDate ( "lastSent" ) );
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace ( );
        }

        return currentReport;
    }
}
