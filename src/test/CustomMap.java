package test;

import java.sql.Timestamp;

public class CustomMap {
    String currentKey;
    Timestamp currentTime;
    String currentValue;

    public CustomMap ( String currentKey  , String currentValue ) {
        this.currentKey = currentKey;
        this.currentTime = new Timestamp ( System.currentTimeMillis ( ) );
        this.currentValue = currentValue;
    }

    public String getCurrentKey () {
        return currentKey;
    }

    public Timestamp getCurrentTime () {
        return currentTime;
    }

    public String getCurrentValue () {
        return currentValue;
    }

    @Override
    public String toString () {
        return "CustomMap{" +
                "currentKey='" + currentKey + '\'' +
                ", currentTime=" + currentTime +
                ", currentValue='" + currentValue + '\'' +
                '}';
    }


}
