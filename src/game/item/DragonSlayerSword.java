package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.action.AttackAction;
import game.exceptions.InsufficientBalanceException;

import java.util.Random;

/**
 * A class that represents a Dragon Slayer Sword.
 *
 * @author Lee Quan Hong
 */
public class DragonSlayerSword extends WeaponItem implements Purchasable {
    /**
     * Random number generator.
     */
    private final Random rand = new Random();
    /**
     * The probability of the Dragon Slayer Sword malfunctioning,
     * taking the intern’s credits without printing out the weapon.
     */
    private static final int MALFUNCTION_PROBABILITY = 50;
    /**
     * The base cost of the Dragon Slayer Sword.
     */
    public static final int BASE_COST = 100;
    /**
     * The actual cost to purchase the Dragon Slayer Sword.
     */
    private int purchaseCost;

    /**
     * Constructor for Dragon Slayer Sword.
     */
    public DragonSlayerSword() {
        super("Dragon Slayer Sword", 'x', 50, "strikes", 75);
        this.purchaseCost = BASE_COST;
    }

    @Override
    public int getBaseCost() {
        return BASE_COST;
    }

    /**
     * Processes the purchase of the Dragon Slayer Sword by the player.
     * @param actor the actor that is purchasing the Dragon Slayer Sword
     * @return a string representing the outcome of the purchase
     * @throws InsufficientBalanceException if the player does not have enough credit to purchase the Dragon Slayer Sword
     */
    @Override
    public String processPurchase(Actor actor) throws InsufficientBalanceException {
        if (actor.getBalance() < this.purchaseCost) {
            throw new InsufficientBalanceException("Player does not have enough credit to purchase the item, current balance: " + actor.getBalance() + " credit(s), item cost: " + this.purchaseCost + " credit(s)." );
        }
        actor.deductBalance(this.purchaseCost);

        if (rand.nextInt(100) < MALFUNCTION_PROBABILITY) {
            return "The computer terminal has malfunctioned, " + this.purchaseCost + " credit(s) is deducted" + " but intern did not receive the Dragon Slayer Sword, current balance: " + actor.getBalance() + " credit(s).";
        } else {
            actor.addItemToInventory(this);
            return "Player has purchased a Dragon Slayer Sword for " + this.purchaseCost + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
        }
    }

    /**
     * Returns the allowable actions that the player can perform on the Dragon Slayer Sword.
     * Which is to attack hostile creatures.
     * @param actor the other actor
     * @param location the location of the other actor
     * @return a list of actions that the player can perform on the Dragon Slayer Sword
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actions = new ActionList();

        // Add the actions to allow intern to attack hostile creatures.
        if (actor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new AttackAction(actor, location.toString(), this));
        }

        return actions;
    }
}

