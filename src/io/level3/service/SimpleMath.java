package io.level3.service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SimpleMath {
    public static double result ( String s ) {
        double finalResult = 0;
        ScriptEngine engine = new ScriptEngineManager ( ).getEngineByExtension ( "js" );
        try {
            // Evaluate the expression
            Object resultExpression = engine.eval ( s );
            if ( resultExpression.getClass ( ).getName ( ) == "java.lang.Integer" ) {
                int i = (Integer) resultExpression;
                finalResult = i;
            } else {
                finalResult = (Double) resultExpression;
            }
        } catch (ScriptException e) {
            // Something went wrong
            e.printStackTrace ( );
        }
        return finalResult;
    }


}
