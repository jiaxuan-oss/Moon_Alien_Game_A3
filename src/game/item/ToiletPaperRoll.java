package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.action.SellingAction;
import game.exceptions.InsufficientBalanceException;

import java.util.Random;

/**
 * A class that represents a Toilet Paper Roll.
 *
 * @author Lee Quan Hong
 *  Modified by: Hoe Jing Yang
 */
public class ToiletPaperRoll extends Item implements Purchasable, Sellable {
    /**
     * Random number generator.
     */
    private final Random rand = new Random();
    /**
     * The probability of the Toilet Paper Roll malfunctioning,
     * causing the price to drop to 1 credit.
     */
    private static final int MALFUNCTION_PROBABILITY = 75;
    /**
     * The base cost of the Toilet Paper Roll.
     */
    public static final int BASE_COST = 5;
    /**
     * The actual cost to purchase the Toilet Paper Roll.
     */
    private int purchaseCost;

    private static final int PRICE = 1;

    private static final double EVENT_RATE = 0.5;

    /**
     * Constructor for Toilet Paper Roll.
     */
    public ToiletPaperRoll() {
        super("Toilet Paper Roll", 's', true);
        this.purchaseCost = BASE_COST;
    }

    /**
     * Returns the base cost of the Toilet Paper Roll.
     * @return an integer representing the base cost of the Toilet Paper Roll
     */
    @Override
    public int getBaseCost() {
        return BASE_COST;
    }

    /**
     * Processes the purchase of the Toilet Paper Roll by the player.
     * @param actor the actor that is purchasing the Toilet Paper Roll
     * @return a string representing the outcome of the purchase
     * @throws InsufficientBalanceException if the player does not have enough credit to purchase the Toilet Paper Roll
     */
    @Override
    public String processPurchase(Actor actor) throws InsufficientBalanceException {
        if (rand.nextInt(100) < MALFUNCTION_PROBABILITY) {
            this.purchaseCost = 1;
            new Display().println(String.format("💥The Toilet Paper Roll only cost %d credit this time.\n", this.purchaseCost));
        }

        if (actor.getBalance() < this.purchaseCost) {
            throw new InsufficientBalanceException("Player does not have enough credit to purchase the item, current balance: " + actor.getBalance() + " credit(s), item cost: " + this.purchaseCost + " credit(s)." );
        }
        actor.deductBalance(this.purchaseCost);
        actor.addItemToInventory(this);
        return "Player has purchased a Toilet Paper Roll for " + this.purchaseCost + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
    }


    /**
     * Returns the selling price of the ToiletPaperRoll
     *
     * @return an integer representing the selling price of the ToiletPaperRoll
     */
    @Override
    public int sellCost() {
        return PRICE;
    }

    /**
     * Returns the rate of an event that will happen when selling the ToiletPaperRoll
     *
     * @return an integer representing the rate of an event that will happen when selling the ToiletPaperRoll
     */
    @Override
    public double eventRate() {
        return EVENT_RATE;
    }

    /**
     * Processes the selling of the ToiletPaperRoll by the player
     * @param actor the actor that is selling the ToiletPaperRoll
     * @param map the location where the other actor is standing
     * @return a string representing the outcome of the sale
     */
    @Override
    public String sellingProcess(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        String message;
        if (Math.random() < EVENT_RATE) {
            actor.hurt(Integer.MAX_VALUE);
            message =  actor + " was killed by the Humanoid Figure while selling Toilet Paper Roll\n";
            message += actor.unconscious(actor, map);
        }
        else {
            actor.addBalance(this.sellCost());
            message = "Player has sold a Toilet Paper Roll for " + this.sellCost() + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
        }

        return message;
    }
    /**
     * Returns a list of actions that the other actor can perform on the ToiletPaperRoll
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of actions that the other actor can perform on the ToiletPaperRoll
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
