package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.RandomRange;
import game.action.ConsumeAction;
import game.exceptions.InsufficientBalanceException;

import java.util.Random;

/**
 * A class that represents Theseus, an item that allows the player to teleport to a random location.
 * @Author: Teh Jia Xuan
 */
public class Theseus extends Item implements Purchasable{
    /**
     * The cost of purchasing Theseus.
     */
    private static final int cost = 100;
    /**
     * Constructor.
     */
    public Theseus() {
        super("Theseus", '^', true);
    }

    /**
     * Returns the cost of purchasing Theseus.
     * @return the cost of purchasing Theseus
     */
    public int getBaseCost() {
        return cost;
    }

    /**
     * Teleport the player to a random location.
     * using randomRange class to generate random x and y coordinates
     * @param map the map that the player is on
     * @return the location that the player is teleported to
     */
    public Location teleport(GameMap map) {
        // Teleport the player to a random location
        int x,y;
        do {
            x = RandomRange.generateRandomRange(0, map.getXRange().max());
            y = RandomRange.generateRandomRange(0, map.getYRange().max());
        }
        while (map.at(x, y).containsAnActor());

        return map.at(x, y);
    }

    /**
     * Process the purchase of Theseus.
     * if not enough balance, throw exception
     * @param actor the actor that is purchasing the item
     * @return a string that indicates the purchase of Theseus
     * @throws InsufficientBalanceException if the actor does not have enough credit to purchase Theseus
     */
    @Override
    public String processPurchase(Actor actor) throws InsufficientBalanceException {
        if (actor.getBalance() < getBaseCost()) {
            throw new InsufficientBalanceException("You do not have enough credit to purchase Theseus.");
        }
        actor.deductBalance(getBaseCost());
        actor.addItemToInventory(new Theseus());
        return "You have purchased Theseus for " + getBaseCost() + " credits.";
    }

    /**
     * Returns the allowable actions that the player can perform on Theseus.
     * if player stands on theseus it has the option to teleport to a random location
     * @param location the location of the ground on which the item lies
     * @return an ActionList containing the allowable actions that the player can perform on Theseus
     */
    @Override
    public ActionList allowableActions(Location location){
        ActionList actions = new ActionList();
        if(location.containsAnActor()) {
            actions.add(new MoveActorAction(teleport(location.map()), "Random Location"));
        }
        return actions;
    }

    /**
     * Returns a description of the action
     * @param actor player that is performing the action
     * @return a string that indicates the action
     */
    public String menuDescription(Actor actor) {
        return "Teleport with THESEUS";
    }
}
