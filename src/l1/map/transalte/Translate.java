package l1.map.transalte;

import java.util.HashMap;
import java.util.Map;

public class Translate {
    private static void init ( Map<String, String> dict ) {
        dict.put ( "Save" , "Сохранить" );
        dict.put ( "Remove" , "Удалить" );
        dict.put ( "Cancel" , "Отмена" );
        dict.put ( "Create" , "Создать" );
    }

    public static void main ( String[] args ) {
        Map<String, String> dict = new HashMap<> ( );
        Translate.init ( dict );
        dict.forEach ( ( k , v ) -> System.out.println ( "en: " + k + " -> ru: " + v ) );
    }
}
