package game.plants.plantbehaviours;

import edu.monash.fit2099.engine.positions.Location;
import game.plants.Plant;

/**
 * An interface that represents the plant behaviour
 * @Author: Lee Teck Xian
 */
public interface PlantBehaviour {
    /**
     * Method to execute the plant behaviour
     * @param plant the plant
     * @param location the location of the plant
     */
    void execute(Plant plant, Location location);
}
