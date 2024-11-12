package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ConsumeAction;
import game.exceptions.InsufficientBalanceException;

import java.util.Random;

/**
 * A class that represents an Energy Drink.
 *
 * @author Lee Quan Hong
 */
public class EnergyDrink extends Item implements Purchasable, Consumable {
    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * The probability of the Energy Drink malfunctioning, causing the price to double.
     */
    private static final int MALFUNCTION_PROBABILITY = 20;

    /**
     * The base cost of the Energy Drink.
     */
    public static final int BASE_COST = 10;

    /**
     * The actual cost to purchase the Energy Drink.
     */
    private int purchaseCost;

    /**
     * The health points that the Energy Drink heals.
     */
    private static final int HEALTH_POINTS = 1;

    /**
     * Constructor for Energy Drink.
     */
    public EnergyDrink() {
        super("Energy Drink", '*', true);
        this.purchaseCost = BASE_COST;
    }

    /**
     * Returns the base cost of the Energy Drink.
     * @return an integer representing the base cost of the Energy Drink
     */
    @Override
    public int getBaseCost() {
        return BASE_COST;
    }

    /**
     * Processes the purchase of the Energy Drink by the player.
     * @param actor the actor that is purchasing the Energy Drink
     * @return a string representing the outcome of the purchase
     * @throws InsufficientBalanceException if the player does not have enough credit to purchase the Energy Drink
     */
    @Override
    public String processPurchase(Actor actor) throws InsufficientBalanceException {
        if (rand.nextInt(100) < MALFUNCTION_PROBABILITY) {
            this.purchaseCost = BASE_COST * 2;
            new Display().println(String.format("💥The Energy Drink cost %d credit this time.\n", this.purchaseCost));
        }

        if (actor.getBalance() < this.purchaseCost) {
            throw new InsufficientBalanceException("Player does not have enough credit to purchase the item, current balance: " + actor.getBalance() + " credit(s), item cost: " + this.purchaseCost + " credit(s)." );
        }
        actor.deductBalance(this.purchaseCost);
        actor.addItemToInventory(this);
        return "Player has purchased an Energy Drink for " + this.purchaseCost + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
    }

    /**
     * Method to heal actor and remove Energy Drink from inventory.
     * @param actor The actor consuming the Energy Drink
     * @return a description of the action after actor consumes the Energy Drink
     */
    @Override
    public String effect(Actor actor, GameMap map){
        actor.heal(HEALTH_POINTS);
        actor.removeItemFromInventory(this);
        String message = String.format("%s consumes %s and heals for %d\n", actor, "Energy Drink", HEALTH_POINTS);
        message += String.format("Current HP: %s", actor);
        return message;
    }

    /**
     * Method to get allowable actions that can be performed on Energy Drink.
     * which is to consume the Energy Drink.
     * @param owner the actor that owns the item
     * @return a list of actions that can be performed on the item
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Method to get menu description of Energy Drink.
     * @param actor The actor consuming the Energy Drink
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " heal " + "for " + HEALTH_POINTS + " by consuming Energy Drink";
    }
}
