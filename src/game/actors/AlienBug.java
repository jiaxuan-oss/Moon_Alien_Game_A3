package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.RandomRange;
import game.Status;
import game.action.AttackAction;
import game.behaviour.FollowBehaviour;
import game.behaviour.PickUpBehaviour;
import game.item.MetalSheetItem;

import java.util.ArrayList;

/**
 * A class that represents an AlienBug.
 * @Author: Teh Jia Xuan & Lee Quan Hong
 */
public class AlienBug extends Enemy {
    /**
     * The constructor of the Actor class.
     * it has ability enter spaceship
     * it can pickup item
     */
    public AlienBug() {
        super("Feature-" + RandomRange.generateRandomRange(100,999), 'a', 2);
        super.behaviours.put(1, new PickUpBehaviour());
        this.addCapability(Ability.ABILITY_ENTER_SPACESHIP);
    }

    /**
     * Method to get the actions that the AlienBug can perform
     * has following behaviour and can be attack
     *
     * @param otherActor the Actor that might be performing the action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that can be performed on this AlienBug
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            addBehaviour(200, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Method when AlienBug is unconscious
     * it drops all the item on the ground
     * @param actor that fell unconscious
     * @param map where the actor fell unconscious
     * @return a description of the action after actor fell unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map){
        for (Item allItem : this.getItemInventory()){
            map.locationOf(this).addItem(allItem);
        }
        return super.unconscious(actor, map);
    }

}
