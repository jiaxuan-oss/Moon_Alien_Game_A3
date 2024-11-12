package game.plants;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.demo.conwayslife.Status;
import edu.monash.fit2099.engine.positions.Location;
import game.plants.plantbehaviours.PlantBehaviour;

import java.util.Map;
import java.util.TreeMap;

/**
 * A abstract class that represents a tree
 * @Author: Teh Jia Xuan
 * @modified: Teck xian
 */
public abstract class Plant extends Ground{
    /**
     * age of the tree
     */
    private int age = 0;

    /**
     * TreeMap to store the plant behaviours
     */
    protected Map<Integer, PlantBehaviour> plantBehaviours = new TreeMap<>();

    /**
     * Constructor for plant
     * @param displayChar
     */
    public Plant(char displayChar) {
        super(displayChar);
        addCapability(Status.ALIVE);
    }

    /**
     * Method to check if actor can stand on the tree
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return true;
    }

    /**
     * Method to get age of the tree
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Method to set age of the tree
     * @param newAge the new age of the tree
     */
    public void setAge(int newAge){
        age = newAge;
    }

    @Override
    public void tick(Location location){
        super.tick(location);
        for (PlantBehaviour plantBehaviour : plantBehaviours.values()){
            plantBehaviour.execute(this,location);
        }

    }
}
