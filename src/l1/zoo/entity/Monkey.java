package l1.zoo.entity;

public class Monkey extends Animal {
    String monkeyType;

    public Monkey ( String monkeyType , FEEDTYPE feedType) {
        this.monkeyType = monkeyType;
        super.AnimalFeedType = feedType;
    }

    @Override
    public String getType () {
        return "Monkey";
    }
}