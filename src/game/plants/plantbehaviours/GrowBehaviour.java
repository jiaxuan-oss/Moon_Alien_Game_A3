package game.plants.plantbehaviours;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.plants.Plant;

/**
 * A class that represents the plant behaviour of growing to the next stage
 * @Author: Lee Teck Xian
 */
public class GrowBehaviour implements PlantBehaviour {

    private Ground nextStages;
    private int growTurn;

    /**
     * Constructor for GrowBehaviour
     * @param nextStages the next stage of the plant
     * @param growTurn the turn to grow to the next stage
     */
    public GrowBehaviour(Ground nextStages, int growTurn) {
        this.growTurn = growTurn;
        this.nextStages = nextStages;
    }

    private void addAge(Plant plant){
        plant.setAge(plant.getAge() + 1);
    }

    private void checkAge(Plant plant, Location location){
        if (plant.getAge() >= growTurn){
            location.setGround(nextStages);
        }
    }

    private void grow(Plant plant, Location location){
        addAge(plant);
        checkAge(plant,location);
    }

    @Override
    public void execute(Plant plant, Location location) {
        grow(plant, location);
    }
}
