package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.action.SellingAction;

/**
 * A class that represents a Bolt item
 * @author : Teh Jia Xuan & Hoe Jing Yang
 */
public class BoltItem extends Item implements Sellable{
    private static final int PRICE = 25;
    /**
     * Constructor for BoltItem
     *
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public BoltItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
    /**
     * Returns the selling price of the BoltItem
     * @return an integer representing the selling price of the BoltItem
     */
    @Override
    public int sellCost() {
        return PRICE;
    }
    /**
     * Returns the rate of an event that will happen when selling the BoltItem
     * @return an integer representing the rate of an event that will happen when selling the BoltItem
     */
    @Override
    public double eventRate() {
        return 0;
    }
    /**
     * Processes the selling of the BoltItem by the player
     * @param actor the actor that is selling the BoltItem
     * @return a string representing the outcome of the sale
     */
    @Override
    public String sellingProcess(Actor actor, GameMap map) {
        actor.addBalance(this.sellCost());
        actor.removeItemFromInventory(this);
        return "Player has sold a Large Bolt for " + this.sellCost() + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
    }
    /**
     * Returns the allowable actions that the player can perform on the BoltItem
     * Which is to attack hostile creatures.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of actions that the player can perform on the BoltItem
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
