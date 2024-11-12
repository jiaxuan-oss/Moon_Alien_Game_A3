package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.exceptions.InsufficientBalanceException;
import game.item.Purchasable;

/**
 * A class that represents an action to purchase an item.
 *
 * @author Lee Quan Hong
 */
public class PurchaseAction extends Action {
    /**
     * The item that is being purchased.
     */
    private final Purchasable purchasable;

    /**
     * Constructor for PurchaseAction.
     * @param purchasable the item that is being purchased
     */
    public PurchaseAction(Purchasable purchasable) {
        this.purchasable = purchasable;
    }

    /**
     * Processes the purchase of the item by the player.
     * @param actor the actor that is purchasing the item
     * @param map the map that the actor is on
     * @return a string representing the outcome of the purchase
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        try {
            return purchasable.processPurchase(actor);
        } catch (InsufficientBalanceException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a string describing the action.
     * @param actor the actor that is purchasing the item
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Purchase " + this.purchasable.toString() + " for " + this.purchasable.getBaseCost() + " credit(s)";
    }

}
