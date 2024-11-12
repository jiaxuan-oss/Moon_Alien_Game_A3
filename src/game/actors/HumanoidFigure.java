package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.action.SellingAction;
import game.item.Sellable;

/**
 * Class representing the Humanoid Figure.
 * @author Hoe Jing Yang
 * Modified by: Teh Jia Xuan
 *
 */
public class HumanoidFigure extends Actor{
    /**
     * Constructor.
     */
    public HumanoidFigure() {
        super("Humanoid Figure", 'H', Integer.MAX_VALUE);
        this.addCapability(Ability.ABILITY_TRADING);
    }
    /**
     * Method to get action of the player
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}

