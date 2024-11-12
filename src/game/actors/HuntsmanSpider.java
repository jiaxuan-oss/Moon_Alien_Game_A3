package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.Weapon.LongLegWeapon;
import game.action.AttackAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.WanderBehaviour;

import java.util.*;

/**
 * Class representing the Huntsman Spider.
 * @Author: Teh Jia Xuan
 */
public class HuntsmanSpider extends Enemy {

    /**
     * Constructor
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1);
        super.behaviours.put(999, new AttackBehaviour());
    }

    /**
     * Huntsman Spider has an intrinsic weapon Long leg
     * @return IntrinsicWeapon of the Huntsman Spider
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(1, "TAK!!", 25);
    }

    /**
     * The huntsman spider can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that can be performed on this Huntsman Spider
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));

        }
        return actions;
    }
}

