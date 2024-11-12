package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologueable;

/**
 * A class that represents an action of listening to the monologue of a monologueable.
 *
 * @author Lee Quan Hong
 */
public class MonologueAction extends Action {
    /**
     * The monologueable that provides the monologue.
     */
    private final Monologueable monologueable;

    /**
     * Constructor for MonologueAction.
     *
     * @param monologueable the monologueable that provides the monologue
     */
    public MonologueAction(Monologueable monologueable) {
        this.monologueable = monologueable;
    }

    /**
     * Execute the action of listening to the monologue.
     *
     * @param actor the actor that is listening to the monologue
     * @param map   the map that the actor is on
     * @return a string that represents the monologue
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return monologueable + " says: " + monologueable.getMonologue();
    }

    /**
     * Returns a description of the action of listening to the monologue
     *
     * @param actor the actor that is listening to the monologue
     * @return a string that represents the monologue
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Listen to the monologue of " + monologueable;
    }
}
