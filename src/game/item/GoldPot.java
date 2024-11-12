package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.action.ConsumeAction;
import game.action.SellingAction;

/**
 * A class that represents a GoldPot item
 * @author : Teh Jia Xuan & Jing Yang
 */
public class GoldPot extends Item implements Consumable, Sellable {
    /**
     * The balance that actor will gain when consume GoldPot
     */
    private static final int BALANCE = 10;

    private static int price = 500;

    private static final double EVENT_RATE = 0.25;

    /**
     * Constructor for GoldPot
     */
    public GoldPot() {
        super("Gold Pot", '$', true);
    }

    /**
     * Method to add consume action to this GoldPot
     * @param owner the actor that owns the item
     * @return a list of actions that can be performed on this GoldPot
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Method when player consume GoldPot to increase balance
     * @param actor The actor that consume the item
     * @param map The map the actor is on.
     * @return a description of the action after actor consume
     */
    @Override
    public String effect(Actor actor, GameMap map){
        actor.addBalance(BALANCE);
        actor.removeItemFromInventory(this);
        String message = String.format("%s take out %s from the pot and gains %d balance\n", actor, "Gold", BALANCE);
        message += String.format("Current balance: %s", actor.getBalance());
        return message;
    }

    /**
     * Method to get menu description of GoldPot
     * @param actor The actor that owns the item
     * @return a description of the item
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " gains " + BALANCE + " balance by taking out Gold from the pot";
    }


    /**
     * Returns the selling price of the item
     *
     * @return an integer representing the selling price of the item
     */
    @Override
    public int sellCost() {
        return price;
    }

    /**
     * Returns the rate of an event that will happen when selling the item
     *
     * @return an integer representing the rate of an event that will happen when selling the item
     */
    @Override
    public double eventRate() {
        return EVENT_RATE;
    }

    /**
     * Processes the selling of the item by the player
     *
     * @param actor the actor that is selling the item
     * @return a string representing the outcome of the sale
     */
    @Override
    public String sellingProcess(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        String message;
        if (Math.random() < EVENT_RATE) {
            message = "Gold Pot was taken away by Humanoid Figure without paying, current balance: " + actor.getBalance() + " credit(s).";
        }
        else {
            actor.addBalance(this.sellCost());
            message = "Player has sold a Gold Pot for " + this.sellCost() + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
        }
        return message;
    }
    /**
     * Returns the allowable actions that the player can perform on the Gold Pot.
     * Which is to attack hostile creatures.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of actions that the player can perform on the Gold Pot
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
