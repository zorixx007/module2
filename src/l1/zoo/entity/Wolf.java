package l1.zoo.entity;

public class Wolf extends Animal {
    String wolfType;

    public Wolf ( String wolfType , FEEDTYPE feedType) {
        this.wolfType = wolfType;
        super.AnimalFeedType = feedType;
    }

    @Override
    public String getType () {
        return "Wolf";
    }

    @Override
    public String toString () {
        return "Wolf{" +
                "wolfType='" + wolfType + '\'' +
                ", AnimalFeedType=" + AnimalFeedType +
                "} ";
    }
}
