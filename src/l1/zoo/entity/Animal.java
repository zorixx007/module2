package l1.zoo.entity;

public abstract class Animal {
    FEEDTYPE AnimalFeedType;
    public abstract String getType();

    public FEEDTYPE getAnimalFeedType () {
        return AnimalFeedType;
    }
}
