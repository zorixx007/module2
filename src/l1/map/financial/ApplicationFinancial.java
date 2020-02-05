package l1.map.financial;

import l1.map.financial.service.RemoveEmptyBalanceService;
import l1.map.financial.service.RemoveOverBalancedService;

import java.util.HashMap;
import java.util.Map;

public class ApplicationFinancial {
    private static void run () {
        Map<String, FinancialProducts> financialMap = new HashMap<> ( );
        Init.initPersons ( financialMap );
        financialMap.forEach ( ( k , v ) -> System.out.println ( k + " has: " + v ) );

        //For each Person show only Financial Products that have balance > $100
        System.out.println ( "\nPrint above 100" );
        financialMap.forEach ( ( k , v ) -> System.out.println ( k + " has: " + v.printAboveHundred ( ) ) );

        //If any Person’s Financial Product has a balance > $20000, remove it from Person’s Financial Products
        System.out.println ( "\nRemove balanced over 20000" );
        RemoveOverBalancedService.removeOverBalanced ( financialMap );
        financialMap.forEach ( ( k , v ) -> System.out.println ( k + " has: " + v ) );

        //If Person doesn’t have any Financial Products, remove the Person from Map
        System.out.println ( "\n Remove person if all balance = 0" );
        RemoveEmptyBalanceService.removeEmptyBalancePerson ( financialMap );
        financialMap.forEach ( ( k , v ) -> System.out.println ( k + " has: " + v ) );
    }

    public static void main ( String[] args ) {
        ApplicationFinancial.run ( );
    }
}
