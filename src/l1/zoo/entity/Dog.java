package l1.zoo.entity;

public class Dog extends Animal {
    String dogType;

    public Dog ( String dogType , FEEDTYPE feedType) {
        this.dogType = dogType;
        super.AnimalFeedType = feedType;
    }

    @Override
    public String getType () {
        return "Dog";
    }

    @Override
    public String toString () {
        return "Dog{" +
                "dogType='" + dogType + '\'' +
                ", AnimalFeedType=" + AnimalFeedType +
                "} " ;
    }
}
