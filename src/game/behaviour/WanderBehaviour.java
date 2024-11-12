package game.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Teh Jia Xuan
 *
 */
/**
 * A class that represents a WanderBehaviour
* @Author: Teh Jia Xuan
 */
public class WanderBehaviour implements Behaviour {
    /**
     * Random number generator
     */
    private final Random random = new Random();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();


        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            //creature cant enter floor, will not be chasing player
            if (destination.canActorEnter(actor)){
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }
}
