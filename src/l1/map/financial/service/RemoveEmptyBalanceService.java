package l1.map.financial.service;

import l1.map.financial.FinancialProducts;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//If Person doesn’t have any Financial Products, remove the Person from Map

public class RemoveEmptyBalanceService {
    public static void removeEmptyBalancePerson ( Map<String, FinancialProducts> newMap ) {
        Set<String> keySet = new HashSet<> ( );
        for (Map.Entry<String, FinancialProducts> entry : newMap.entrySet ( )) {
            FinancialProducts tmpFinProduct = entry.getValue ( );
            String tmpKey = entry.getKey ( );

            if ( tmpFinProduct.getSavingAccountBalance ( ) == 0
                    && tmpFinProduct.getRetirementPlanAccountBalance ( ) == 0
                    && tmpFinProduct.getDebitAccountBalance ( ) == 0
                    && tmpFinProduct.getCreditCardBalance ( ) == 0 ) {
                keySet.add ( tmpKey );
            }
        }
        newMap.keySet ( ).removeAll ( keySet );
    }
}
