package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import game.exceptions.InsufficientBalanceException;

/**
 * Interface for items that can be purchased by the player
 *
 * @author Lee Quan Hong
 */
public interface Purchasable {
    /**
     * Returns the base cost of the item
     * @return an integer representing the base cost of the item
     */
    int getBaseCost();

    /**
     * Processes the purchase of the item by the player
     * @param actor the actor that is purchasing the item
     * @return a string representing the outcome of the purchase
     * @throws InsufficientBalanceException if the player does not have enough credit to purchase the item
     */
    String processPurchase(Actor actor) throws InsufficientBalanceException;


}
