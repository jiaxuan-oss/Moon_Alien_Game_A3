package game.behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.action.AttackAction;

import java.util.ArrayList;

/**
 * A class that represents a AttackBehaviour
 * @Author: Teh Jia Xuan
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns a AttackAction to attack the player, if possible.
     * If no player is present, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an AttackAction, or null if no AttackAction is possible
     */
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                if (exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(exit.getDestination().getActor(), exit.getName());
                }
            }
        }
        return null;
    }
}


