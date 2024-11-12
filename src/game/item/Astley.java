package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Monologueable;
import game.action.MonologueAction;
import game.exceptions.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents an AI device, called Astley.
 *
 * @author Lee Quan Hong
 */
public class Astley extends Item implements Purchasable, Monologueable {
    /**
     * The base cost of the Astley.
     */
    private static final int COST = 50;

    /**
     * The actual cost to purchase the Astley.
     */
    private int purchaseCost;

    /**
     * The number of turns that the Astley has been carried.
     */
    private int tickCount = 0;

    /**
     * The suspended status where intern will be suspended if they do not have enough credit to pay the subscription fee.
     * Intern will not be able to interact with the device until they have more credits to pay the fee.
     */
    private boolean suspendedStatus;

    /**
     * The actor that is carrying the Astley.
     */
    private final Actor actor;

    /**
     * The subscription fee that the intern has to pay every 5 turns.
     */
    private final int SUBSCRIPTION_FEE = 1;

    /**
     * Constructor for Astley.
     *
     * @param actor the actor that is carrying the Astley
     */
    public Astley(Actor actor) {
        super("Astley", 'z', true);
        this.purchaseCost = COST;
        this.suspendedStatus = false;
        this.actor = actor;
    }

    /**
     * Returns the base cost of the Astley.
     * @return the base cost of the Astley
     */
    @Override
    public int getBaseCost() {
        return COST;
    }

    /**
     * Processes the purchase of the Astley by the player.
     * @param actor the actor that is purchasing the Astley
     * @return a string representing the outcome of the purchase
     * @throws InsufficientBalanceException if the player does not have enough credit to purchase the Astley
     */
    @Override
    public String processPurchase(Actor actor) throws InsufficientBalanceException {
        if (actor.getBalance() < this.purchaseCost) {
            throw new InsufficientBalanceException("Player does not have enough credit to purchase the item, current balance: " + actor.getBalance() + " credit(s), item cost: " + this.purchaseCost + " credit(s)." );
        }
        actor.deductBalance(this.purchaseCost);
        actor.addItemToInventory(this);
        return "Player has successfully purchased the Astley for " + this.purchaseCost + " credit(s), current balance: " + actor.getBalance() + " credit(s).";
    }

    /**
     * Inform Astley of the passage of time when carried.
     * This method is called once per turn, if this Item is being carried.
     * @param currentLocation The location of the actor carrying this Astley.
     * @param actor The actor carrying this Astley.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);

        // When the intern fails to pay the subscription fee,
        // the tick count stops at the tick where the intern has to pay the fee.
        if (!this.suspendedStatus) {
            this.tickCount++;
        }

        // If the tick count is a multiple of 5, deduct 1 credit from the player
        if (this.tickCount % 5 == 0) {
            if (actor.getBalance() < SUBSCRIPTION_FEE) {
                new Display().println("Subscription fee of 1 credit for Astley has not been paid, the usage of Astley has been suspended until the fee is paid.");
                this.suspendedStatus = true;
            } else {
                this.suspendedStatus = false;
                actor.deductBalance(1);
                new Display().println("Subscription fee of 1 credit for Astley has been deducted from your account, current balance: " + actor.getBalance() + " credit(s).");
            }
        }

    }

    /**
     * Returns the allowable actions that the player can perform on the Astley.
     * @param owner the actor that is performing the action
     * @return an ActionList containing the allowable actions that the player can perform on the Astley
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();

        // Add the actions that the player can perform on the Astley
        if (!this.suspendedStatus) {
            actions.add(new MonologueAction(this));
        }

        return actions;
    }

    /**
     * Returns a monologue provided by the Astley.
     * @return a string that represents the monologue
     */
    @Override
    public String getMonologue() {
        ArrayList<String> monologues = new ArrayList<>();

        // Available from the start
        monologues.add("The factory will never gonna give you up, valuable intern!");
        monologues.add("We promise we never gonna let you down with a range of staff benefits.");
        monologues.add("We never gonna run around and desert you, dear intern!");

        // Available if the intern has more than 10 items in their inventory
        if (actor.getItemInventory().size() > 10) {
            monologues.add("We never gonna make you cry with unfair compensation.");
        }

        // Available if the intern carries more than 50 credits in their wallet
        if (actor.getBalance() > 50) {
            monologues.add("Trust is essential in this business. We promise we never gonna say goodbye to a valuable intern like you.");
        }

        // Available if the intern’s health point is below 2
        if (actor.getAttribute(BaseActorAttributes.HEALTH) < 2) {
            monologues.add("Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.");
        }

        Random rand = new Random();
        return monologues.get(rand.nextInt(monologues.size()));
    }

}
