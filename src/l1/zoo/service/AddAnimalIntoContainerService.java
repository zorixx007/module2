package l1.zoo.service;

import l1.zoo.Container;
import l1.zoo.entity.*;

import java.util.ArrayList;
import java.util.List;


/*
Create a method that will place an Animal of any species inside a particular container
(both Animal and Container are arguments)
*/
public class AddAnimalIntoContainerService {
    public static void addAnimalIntoContainer ( Animal animal , Container currentContainer ) {
        switch (animal.getType ( )) {
            case "Bear":
                List<Bear> bears= new ArrayList<> (  );
                bears.add ( (Bear) animal );
                currentContainer.setAnimalContainer ( bears );
                break;
            case "Bird":
                List<Bird> birds= new ArrayList<> (  );
                birds.add ( (Bird) animal );
                currentContainer.setAnimalContainer ( birds );
                break;
            case "Dog":
                List<Dog> dogs= new ArrayList<> (  );
                dogs.add ( (Dog) animal );
                currentContainer.setAnimalContainer ( dogs );
                break;
            case "Monkey":
                List<Monkey> monkeys= new ArrayList<> (  );
                monkeys.add ( (Monkey) animal );
                currentContainer.setAnimalContainer ( monkeys );
                break;
            case "Wolf":
                List<Wolf> wolfs= new ArrayList<> (  );
                wolfs.add ( (Wolf) animal );
                currentContainer.setAnimalContainer ( wolfs );
                break;
        }
    }
}
