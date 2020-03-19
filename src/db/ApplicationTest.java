//package db;
//
//import db.entity.Customer;
//import db.entity.Merchant;
//import db.entity.Payment;
//import db.repository.CustomerRepository;
//import db.repository.DBUtils;
//import db.repository.MerchantRepository;
//import db.repository.PaymentRepository;
//import db.service.CustomerService;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//
//public class ApplicationTest {
//
//    private static void run () {
//        try (Connection conn = DBUtils.getConnection ( )) {
//
//            Merchant current = MerchantRepository.getMerchantByID ( conn , 2 );
//            System.out.println ( current );
//
//
//            Customer curr = CustomerRepository.getCustomerByID ( conn , 1 );
//            System.out.println ( curr );
//
//            ArrayList<Payment> customerPayments = PaymentRepository.getPaymentsForCustomer ( conn , curr );
//            System.out.println ( "print payments by customer" );
//            customerPayments.forEach ( item -> System.out.println ( item ) );
//
//            ArrayList<Payment> merchantPayments = PaymentRepository.getPaymentsForMerchant ( conn , current );
//            System.out.println ( "print payments by merchant" );
//            merchantPayments.forEach ( item -> System.out.println ( item ) );
//        } catch (SQLException | IOException e) {
//            e.printStackTrace ( );
//        }
//
//    }
//
//    private static void runTest () {
//        try (Connection conn = DBUtils.getConnection ( )) {
//
//            ArrayList<Payment> bestCustomerTransactions = CustomerService.getBestCustomer ( conn , 3500 );
//            bestCustomerTransactions.forEach ( item -> System.out.println ( item ) );
//
//
//            //            Map<Customer, Long> elementCountMap = allPaymentsForPeriod.stream ()
//
//
//            //            System.out.println ( "print payments " );
//            //            allPaymentsForPeriod.forEach ( item -> System.out.println ( item.getCustomer ().getCustomerId () ) );
//
//
//        } catch (SQLException | IOException e) {
//            e.printStackTrace ( );
//        }
//
//    }
//
//    public static void main ( String[] args ) {
//        ApplicationTest.runTest ( );
//    }
//}
