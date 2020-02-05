package l1.zoo.service;

import l1.zoo.Container;
import l1.zoo.entity.Animal;

import java.util.List;

/*
Create a method that can place multiple Animals inside one container if they are of the
same family (carnivore/herbivore). One container cannot contain animals of different
families (otherwise, only one family will reach the destination )
*/
public class AddAnimalByFeedTypeIntoContainerService {
    public static void addAnimalByFeedTypeIntoContainer ( Animal animal , Container currentContainer ) {
        List<Animal> newAnimalContainer = (List<Animal>) currentContainer.getAnimalContainer ( );
        if ( animal.getAnimalFeedType ( ) == currentContainer.getFeedType ( ) ) {
            newAnimalContainer.add ( animal );
            currentContainer.setAnimalContainer ( newAnimalContainer );
        } else {
            System.out.println ( "Feed type mismatch. cannot add " + animal.getType ( ) + " into current container as it has type: " + currentContainer.getFeedType ( ) );
        }
    }
}
