package l1.map.financial.service;

import l1.map.financial.FinancialProducts;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//If Person doesn’t have any Financial Products, remove the Person from Map

public class RemoveEmptyBalanceService {
    public static void removeEmptyBalancePerson ( Map<String, FinancialProducts> newMap ) {
        //        Set<String> keySet = new HashSet<> ( );
        //        for (Map.Entry<String, FinancialProducts> entry : newMap.entrySet ( )) {
        //            FinancialProducts tmpFinProduct = entry.getValue ( );
        //            String tmpKey = entry.getKey ( );
        //
        //            if ( tmpFinProduct.getSavingAccountBalance ( ) == 0
        //                    && tmpFinProduct.getRetirementPlanAccountBalance ( ) == 0
        //                    && tmpFinProduct.getDebitAccountBalance ( ) == 0
        //                    && tmpFinProduct.getCreditCardBalance ( ) == 0 ) {
        //                keySet.add ( tmpKey );
        //            }
        //        }
        //        newMap.keySet ( ).removeAll ( keySet );


//                        //        iterator over map,keyset
//                        Iterator<String> iter = newMap.keySet ( ).iterator ( );
//                        while (iter.hasNext ( )) {
//                            String tmpKey = iter.next ( );
//                            FinancialProducts tmpFinProduct = newMap.get ( tmpKey );
//                            if ( tmpFinProduct.getSavingAccountBalance ( ) == 0
//                                    && tmpFinProduct.getRetirementPlanAccountBalance ( ) == 0
//                                    && tmpFinProduct.getDebitAccountBalance ( ) == 0
//                                    && tmpFinProduct.getCreditCardBalance ( ) == 0 ) {
//                                iter.remove ();
//                            }
//                        }

//        //                iterator using EntrySet
//        Iterator<Entry<String, FinancialProducts>> entryIt = newMap.entrySet ( ).iterator ( );
//        while (entryIt.hasNext ( )) {
//            Entry<String, FinancialProducts> entry = entryIt.next ( );
//            FinancialProducts tmpFinProduct = newMap.get ( entry );
//            if ( tmpFinProduct.getSavingAccountBalance ( ) == 0
//                    && tmpFinProduct.getRetirementPlanAccountBalance ( ) == 0
//                    && tmpFinProduct.getDebitAccountBalance ( ) == 0
//                    && tmpFinProduct.getCreditCardBalance ( ) == 0 ) {
//                entryIt.remove ( );
//            }
//        }


                //        try to make set of map
                Set<Entry<String, FinancialProducts>> setOfEntries = newMap.entrySet ( );
                Iterator<Map.Entry<String, FinancialProducts>> iterator = setOfEntries.iterator ( );
                while (iterator.hasNext ( )) {
                    Map.Entry<String, FinancialProducts> entry = iterator.next ( );
                    FinancialProducts tmpFinProduct = entry.getValue ( );
                    if ( tmpFinProduct.getSavingAccountBalance ( ) == 0
                            && tmpFinProduct.getRetirementPlanAccountBalance ( ) == 0
                            && tmpFinProduct.getDebitAccountBalance ( ) == 0
                            && tmpFinProduct.getCreditCardBalance ( ) == 0 ) {
                        iterator.remove ( );
                    }
                }


    }
}
