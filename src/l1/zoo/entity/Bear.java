package l1.zoo.entity;

public class Bear extends Animal {
    String bearType;

    public Bear ( String bearType , FEEDTYPE feedType) {
        this.bearType = bearType;
        super.AnimalFeedType = feedType;
    }

    @Override
    public String getType () {
        return "Bear";
    }
}
