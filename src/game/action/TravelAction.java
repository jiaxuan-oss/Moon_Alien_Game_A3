package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Action that moves the Actor.
 * @Author: Teh Jia Xuan
 */
public class TravelAction extends Action {
    /**
     * Target location
     */
    private Location moveToLocation;
    /**
     * One of the 8-d navigation
     */
    private String direction;
    /**
     * Or the command key
     */
    private String hotKey;

/**
     * Constructor to create an Action that will move the Actor to a Location in a given Direction, using
     * a given menu hotkey.
     *
     * Note that this constructor does not check whether the supplied Location is actually in the given direction
     * from the Actor's current location.  This allows for (e.g.) teleporters, etc.
     *
     * @param moveToLocation the destination of the move
     * @param direction the direction of the move (to be displayed in menu)
     */
    public TravelAction(Location moveToLocation, String direction) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;

    }

    /**
     * Allow the Actor to be moved to other map if the actor is not already in the map
     * @param actor The actor performing the action.
     * @param gameMap The map the actor is on.
     * @return a string that indicates the actor has moved to the location
     */
    @Override
    public String execute(Actor actor, GameMap gameMap) {
        if(!moveToLocation.map().contains(actor) && !moveToLocation.containsAnActor()) {
            gameMap.moveActor(actor, moveToLocation);
            return menuDescription(actor);
        }
        return actor + " teleport fail might be an actor in the location";
    }

    /**
     * Returns a description of the action after move to the map
     * @param actor The actor performing the action.
     * @return a string that indicates the actor has moved to the map
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels " + direction;
    }
}
