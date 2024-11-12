package game.plants.fruits;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ConsumeAction;
import game.item.Consumable;

/**
 * A class that represents a Fruit item
*@Author: Teh Jia Xuan
 */
public class SaplingFruit extends Item implements Consumable {
    /**
     * The health points that actor will gain/loss when consume Fruit
     */
    private static final int HEALTH_POINTS = 1;

    /**
     * Constructor for Fruit
     */
    public SaplingFruit() {
        super("Small Inheritree Fruit", 'o', true);
    }

    /**
     * Method to get health points of fruit
     *
     * @return healthPoints
     */
    public int getHealthPoints(){
        return HEALTH_POINTS;
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

    @Override
    public String effect(Actor actor, GameMap map){
        actor.heal(HEALTH_POINTS);
        actor.removeItemFromInventory(this);
        String message = String.format("%s consumes %s and heals for %d\n", actor, "Small Inheritree Fruit", HEALTH_POINTS);
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
        return actor + " heal " + " for " + HEALTH_POINTS + " by consuming Small Inheritree Fruit";
    }
}
