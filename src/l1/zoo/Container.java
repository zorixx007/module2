package l1.zoo;

import l1.zoo.entity.Animal;
import l1.zoo.entity.FEEDTYPE;

import java.util.List;

public class Container {
    final FEEDTYPE feedType;
    List<? extends Animal> animalContainer;

    public Container ( List<? extends Animal> animal , FEEDTYPE feedtype ) {
        this.animalContainer = animal;
        this.feedType = feedtype;
    }

    public FEEDTYPE getFeedType () {
        return feedType;
    }

    public List<? extends Animal> getAnimalContainer () {
        return animalContainer;
    }

    public void setAnimalContainer ( List<? extends Animal> animalContainer ) {
        this.animalContainer = animalContainer;
    }

//    @Override
//    public String toString () {
//        String s = null;
//        animalContainer.forEach ( item -> s += item.getType ( ) );
//        return s;
//    }
}
