package l1.map.financial.service;

import l1.map.financial.FinancialProducts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//If any Person’s Financial Product has a balance > $20000, remove it from Person’s Financial Products
public class RemoveOverBalancedService {
    public static void removeOverBalanced ( Map<String, FinancialProducts> newMap ) {
        Map<String, FinancialProducts> tmpFinancialMap = new HashMap<> ( );
        Set<String> keySet = new HashSet<> ( );

        //        Iterator iter = newMap.entrySet ( ).iterator ();
        for (Map.Entry<String, FinancialProducts> entry : newMap.entrySet ( )) {
            String tmpKey = entry.getKey ( );
            FinancialProducts tmpFinProduct = entry.getValue ( );
            keySet.add ( tmpKey );
            // Checked if we have balance over 20000 and zeroing it
            if ( tmpFinProduct.getCreditCardBalance ( ) > 20000 ) {
                tmpFinProduct.setCreditCardBalance ( 0 );
            }
            if ( tmpFinProduct.getDebitAccountBalance ( ) > 20000 ) {
                tmpFinProduct.setDebitAccountBalance ( 0 );
            }
            if ( tmpFinProduct.getRetirementPlanAccountBalance ( ) > 20000 ) {
                tmpFinProduct.setRetirementPlanAccountBalance ( 0 );
            }
            if ( tmpFinProduct.getSavingAccountBalance ( ) > 20000 ) {
                tmpFinProduct.setSavingAccountBalance ( 0 );
            }
            tmpFinancialMap.put ( tmpKey , tmpFinProduct );
        }
        newMap.keySet ( ).removeAll ( keySet );
        newMap.putAll ( tmpFinancialMap );
    }
}
