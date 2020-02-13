package l1.map.financial;

import java.util.Map;

public class Init {
    public static void initPersons ( Map<String, FinancialProducts> newMap) {
        newMap.put ( "John", new FinancialProducts ( 100, -1000, 5000, 3000 ) );
        newMap.put ( "Joe", new FinancialProducts ( 0, 0, 0, 0 ) );
        newMap.put ( "Jack", new FinancialProducts ( 10, -1000, 5000, 300000 ) );
        newMap.put ( "Jim", new FinancialProducts ( 0, 0, 0, 0 ) );
    }
}
