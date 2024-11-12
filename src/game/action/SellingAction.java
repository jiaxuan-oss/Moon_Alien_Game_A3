package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.Sellable;
/**
 * A class that represents an action to sell an item.
 * @author : Hoe Jing Yang
 * Modified by: Teh Jia Xuan
 */
public class SellingAction extends Action {
    /**
     * The item that is being sold.
     */
    private final Sellable sellable;
    /**
     * Constructor for SellingAction.
     * @param sellable the item that is being purchased
     */
    public SellingAction(Sellable sellable) {
        this.sellable = sellable;
    }
    /**
     * Processes the sales of the item by the player.
     * @param actor the actor that is selling the item
     * @param map the map that the actor is on
     * @return a string representing the outcome of the sales
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return sellable.sellingProcess(actor, map);
    }
    /**
     * Returns a string describing the action.
     * @param actor the actor that is selling the item
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Sell %s for %d credit(s).", this.sellable.toString(), this.sellable.sellCost());
    }
}
