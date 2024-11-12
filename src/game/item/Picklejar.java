package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.action.ConsumeAction;
import game.action.SellingAction;

/**
 * A class that represents a Picklejar item
 * @author : Teh Jia Xuan & Jing Yang
 */
public class Picklejar extends Item implements Consumable, Sellable{
    /**
     * The health points that actor will gain/loss when consume Picklejar
     */
    private static final int HEALTH_POINTS = 1;
    /**
     * The selling price of Picklejar
     */
    private static final int PRICE = 25;
    /**
     * The rate of event trigger when selling Picklejar
     */
    private static final double EVENT_RATE = 0.50;

    /**
     * Constructor for Picklejar
     */
    public Picklejar() {
        super("Picklejar", 'n', true);
    }

    /**
     * Method to add consume action to this Picklejar
     * @param actor the actor that owns the item
     * @return a list of actions that can be performed on this Picklejar
     */
    @Override
    public ActionList allowableActions(Actor actor){
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }
    /**
     * Returns the allowable actions that the player can perform on the Pickle Jar.
     * Which is to attack hostile creatures.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of actions that the player can perform on the Pickle Jar
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.ABILITY_TRADING)){
            actions.add(new SellingAction(this));
        }
        return actions;
    }

    /**
     * Method when player consume Picklejar to increase/decrease health
     * according to the percentage if is more or equal to 50 then heal else hurt
     * @param actor The actor that consume the item
     * @param map The map the actor is on.
     * @return a description of the action after actor consume
     */
    @Override
    public String effect(Actor actor, GameMap map){
        String message;
        if(Math.random() >= 0.5){
            message = String.format("%s consumes %s and heals for %d\n", actor, "Pickle", HEALTH_POINTS);
            actor.heal(HEALTH_POINTS);
        }
        else{
            message = String.format("%s consumes %s and it is past its expiry date, %s loses %d health\n", actor, "Pickle", actor, HEALTH_POINTS);
            actor.hurt(HEALTH_POINTS);
        }
        actor.removeItemFromInventory(this);
        message += String.format("Current HP: %s", actor);
        if (!actor.isConscious()){
            message += "\n" + actor.unconscious(actor, map);
        }
        return message;
    }

    /**
     * Method to get menu description of Picklejar
     * @param actor The actor consuming the item
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " gains/loses " + HEALTH_POINTS + " health by consuming Pickle";
    }

    /**
     * Returns the selling price of the Picklejar
     *
     * @return an integer representing the selling price of the Picklejar
     */
    @Override
    public int sellCost() {
        return PRICE;
    }

    /**
     * Returns the rate of an event that will happen when selling the Picklejar
     *
     * @return an integer representing the rate of an event that will happen when selling the Picklejar
     */
    @Override
    public double eventRate() {
        return EVENT_RATE;
    }

    /**
     * Processes the selling of the Picklejar by the player
     * @param actor the actor that is selling the Picklejar
     * @param map the location that the actor is standing
     * @return a string representing the outcome of the sale
     */
    @Override
    public String sellingProcess(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        String message;
        if (Math.random() < EVENT_RATE) {
            int doublePrice = this.sellCost() * 2;
            actor.addBalance(doublePrice);
            message = "Player has sold a Jar of Pickle for double the price at" + doublePrice + " credit(s), current balance: " + actor.getBalance() + " credit(s).";

        }
        else {
            actor.addBalance(this.sellCost());
            message = "Player has sold a Jar of Pickle for " + this.sellCost() + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
        }

        return message;
    }
}
