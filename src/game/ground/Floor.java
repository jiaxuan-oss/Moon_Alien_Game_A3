package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Ability;
import game.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Teh Jia Xuan
 *
 */
public class Floor extends Ground {
    /**
     * Constructor.
     */
    public Floor() {
        super('_');
    }

    /**
     * Check if the Actor can enter the Floor.
     * only status with HOSTILE_TO_ENEMY can enter
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(Ability.ABILITY_ENTER_SPACESHIP);
    }
}
