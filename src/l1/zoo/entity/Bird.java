package l1.zoo.entity;

public class Bird extends  Animal{

        String birdType;

        public Bird ( String birdType , FEEDTYPE feedType) {
            this.birdType = birdType;
            super.AnimalFeedType = feedType;
        }

    @Override
    public String getType () {
        return "Bird";
    }
}
