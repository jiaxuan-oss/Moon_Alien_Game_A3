package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviour.AttackBehaviour;


/**
 * A class that represents the Suspicious Astronaut.
 * @Author : Lee Teck Xian
 */
public class SuspiciousAstronaut extends Enemy {

    /**
     * Constructor.
     */
    public SuspiciousAstronaut() {
        super("Suspicious Astronaut", 'ඞ', 99);
        super.behaviours.put(1,new AttackBehaviour());
    }

    /**
     * The Suspicious Astronaut has an intrinsic weapon of integer max value damage
     * @return IntrinsicWeapon of the Suspicious Astronaut
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(Integer.MAX_VALUE, "stab", 100);
    }


}
