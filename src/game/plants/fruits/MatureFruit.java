package game.plants.fruits;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.action.ConsumeAction;
import game.action.SellingAction;
import game.item.Consumable;
import game.item.Sellable;

/**
 * A class that represents a Fruit item
 * @author : Teh Jia Xuan
 * Modified by: Hoe Jing Yang
 */
public class MatureFruit extends Item implements Consumable, Sellable {
    /**
     * The health points that actor will gain/loss when consume Fruit
     */
    private static final int HEALTH_POINTS = 2;
    /**
     * The selling price of Fruit
     */
    private static final int PRICE = 30;

    /**
     * Constructor for Fruit
     */
    public MatureFruit() {
        super("Large Inheritree Fruit", 'O', true);
    }

    /**
     * Method to add consumeFruit action to this fruit
     * @param owner the actor that owns the item
     * @return consumefruitaction for this fruit
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Method to heal actor and remove fruit from inventory
     *
     * @param actor The actor consuming the fruit
     * @return a description of the action after actor consume
     */
    @Override
    public String effect(Actor actor, GameMap map){
        actor.heal(HEALTH_POINTS);
        actor.removeItemFromInventory(this);
        String message = String.format("%s consumes %s and heals for %d\n", actor, "Large Inheritree Fruit", HEALTH_POINTS);
        message += String.format("Current HP: %s", actor);
        return message;
    }

    /**
     * Method to get menu description of fruit
     *
     * @param actor The actor consuming the fruit
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " heal " + " for " + HEALTH_POINTS + " by consuming Large Inheritree Fruit";
    }

    /**
     * Returns the selling price of the LargeFruit
     * @return an integer representing the selling price of the LargeFruit
     */
    @Override
    public int sellCost() {
        return PRICE;
    }

    /**
     * Returns the rate of an event that will happen when selling the LargeFruit
     * @return an integer representing the rate of an event that will happen when selling the LargeFruit
     */
    @Override
    public double eventRate() {
        return 0;
    }

    /**
     * Processes the selling of the LargeFruit by the player
     * @param actor the actor that is selling the LargeFruit
     * @param map  the location where the other actor is standing
     * @return a string representing the outcome of the sale
     */
    @Override
    public String sellingProcess(Actor actor, GameMap map) {
        actor.addBalance(this.sellCost());
        actor.removeItemFromInventory(this);
        return "Player has sold a Large Fruit for " + this.sellCost() + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
    }
    /**
     * Returns the allowable actions that the player can perform on the Large Fruit.
     * Which is to attack hostile creatures.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of actions that the player can perform on the Large Fruit.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.ABILITY_TRADING)){
            actions.add(new SellingAction(this));
        }
        return actions;
    }
}
