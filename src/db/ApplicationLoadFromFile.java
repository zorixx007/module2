package db;

import db.repository.DBUtils;
import db.service.CustomerService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationLoadFromFile {
    private static void run () {
        Path inputFile = Paths.get ( "src/db/resources/Customers_2020_03_08.csv" );
        try (Connection conn = DBUtils.getConnection ( )) {
            boolean result = CustomerService.uploadCustomersFromFile ( conn , inputFile );
            System.out.println (result );
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }

    }

    public static void main ( String[] args ) {
        ApplicationLoadFromFile.run ( );
    }
}


