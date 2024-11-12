package game.plants;

import game.plants.fruits.MatureFruit;
import game.plants.plantbehaviours.DropFruitBehaviour;

/**
 * A class that represents a mature inheritree
 * @Author: Lee Teck Xian
 */
public class MatureTree extends Plant {

    /**
     * Constructor for MatureTree
     */
    public MatureTree(){
        super('T');
        super.plantBehaviours.put(999, new DropFruitBehaviour(new MatureFruit(),0.2));
    }

}
