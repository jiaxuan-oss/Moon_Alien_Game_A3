package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeAction;
import game.item.Consumable;

/**
 * A class that represents a Puddle item
 * @Author: Teh Jia Xuan & Jing Yang
 */
public class Puddle extends Ground implements Consumable{
    /**
     * The health points that actor will gain when consume Puddle
     */
    private static final int HEALTH_POINTS = 1;

    /**
     * Constructor for Puddle
     */
    public Puddle() {
        super('~');
    }

    /**
     * Method to add consume action to this Puddle
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of actions that can be performed on this Puddle
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if(location.containsAnActor()){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * Method when player consume puddle to increase maximum health
     * @param actor The actor consuming the fruit
     * @param map The map the actor is on.
     * @return a description of the action after actor consume
     */
    @Override
    public String effect(Actor actor, GameMap map){
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HEALTH_POINTS);
        String message = String.format("%s consumes %s and increase maximum health for %d\n", actor, "Puddle", HEALTH_POINTS);
        message += String.format("Current HP: %s", actor);
        return message;
    }

    /**
     * Method to get menu description of Puddle
     * @param actor The actor consuming the fruit
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " increase maximum health " + "for " + HEALTH_POINTS + " by consuming Puddle";
    }
}
