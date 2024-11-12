package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for items that can be sold by the player
 *
 * @author Hoe Jing Yang & Jia Xuan
 */
public interface Sellable {
    /**
     * Returns the selling price of the item
     * @return an integer representing the selling price of the item
     */
    int sellCost();
    /**
     * Returns the rate of an event that will happen when selling the item
     * @return an integer representing the rate of an event that will happen when selling the item
     */
    double eventRate();
    /**
     * Processes the selling of the item by the player
     * @param actor the actor that is selling the item
     * @param map the location where the other actor is standing
     * @return a string representing the outcome of the sale
     */
    String sellingProcess(Actor actor, GameMap map);
}
