package l1.zoo;

import l1.zoo.entity.*;
import l1.zoo.service.AddAnimalByFeedTypeIntoContainerService;
import l1.zoo.service.AddAnimalIntoContainerService;

import java.util.ArrayList;
import java.util.List;

public class ApplicationZoo {
    private static void run () {
/*
Create a method that will place an Animal of any species inside a particular container
(both Animal and Container are arguments)
*/
        List<? extends Animal> animal = new ArrayList<> ( );
        Dog newDog = new Dog ( "Dog" , FEEDTYPE.Predators );
        Container newContainer = new Container ( animal , FEEDTYPE.Predators );
        AddAnimalIntoContainerService.addAnimalIntoContainer ( newDog , newContainer );
        System.out.println ( newContainer.getAnimalContainer ( ).toString ( ) );


/*
Create a method that can place multiple Animals inside one container if they are of the
same family (carnivore/herbivore). One container cannot contain animals of different
families (otherwise, only one family will reach the destination )
*/
        List<? extends Animal> newAnimal = new ArrayList<> ( );
        Container mixedContainer = new Container ( newAnimal , FEEDTYPE.Predators );
        Wolf newWolf = new Wolf ("Wolf", FEEDTYPE.Predators);
        Monkey newMonkey = new Monkey ( "Monkey" , FEEDTYPE.Herbivores);
        AddAnimalByFeedTypeIntoContainerService.addAnimalByFeedTypeIntoContainer ( newDog, mixedContainer );
        AddAnimalByFeedTypeIntoContainerService.addAnimalByFeedTypeIntoContainer ( newMonkey, mixedContainer );
        AddAnimalByFeedTypeIntoContainerService.addAnimalByFeedTypeIntoContainer ( newWolf, mixedContainer );
        System.out.println (mixedContainer.getAnimalContainer ().toString () );


    }

    public static void main ( String[] args ) {
        ApplicationZoo.run ( );
    }
}
