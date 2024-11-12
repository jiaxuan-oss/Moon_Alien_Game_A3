package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.action.SellingAction;

/**
 * A class that represents a MetalSheet item
 * @author : Teh Jia Xuan
 * Modified by: Hoe Jing Yang
 */
public class MetalSheetItem extends Item implements Sellable {

    private static  int price = 20;
    private static final double EVENT_RATE = 0.60;

    /**
     * Constructor for MetalSheetItem
     *
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public MetalSheetItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
    /**
     * Returns the selling price of the MetalSheetItem
     * @return an integer representing the selling price of the MetalSheetItem
     */
    @Override
    public int sellCost() {
        return price;
    }
    /**
     * Returns the rate of an event that will happen when selling the MetalSheetItem
     * @return an integer representing the rate of an event that will happen when selling the MetalSheetItem
     */
    @Override
    public double eventRate() {
        return EVENT_RATE;
    }
    /**
     * Processes the selling of the MetalSheetItem by the player
     * @param actor the actor that is selling the MetalSheetItem
     * @return a string representing the outcome of the sale
     */
    @Override
    public String sellingProcess(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        String message;
        if (Math.random() < EVENT_RATE) {
            int discountPrice = 10;
            actor.addBalance(discountPrice);
            message = "Player has sold a Metal Sheet for a discount price for " + discountPrice + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
        }
        else {
            actor.addBalance(this.sellCost());
            message = "Player has sold a Metal Sheet for " + this.sellCost() + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
        }
        return message;
    }
    /**
     * Returns the allowable actions that the player can perform on the Metal Sheet.
     * Which is to attack hostile creatures.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of actions that the player can perform on the Metal Sheet.
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
