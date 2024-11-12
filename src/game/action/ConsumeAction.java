package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.Consumable;

/**
 * Action for consumable
 * @Author: Teh Jia Xuan
 */

public class ConsumeAction extends Action{
    /**
     * consumable item
     */
    private Consumable item;

    /**
     * Constructor set consumable
     *
     * @param initConsumable the fruit to be consumed
     */
    public ConsumeAction(Consumable initConsumable){
        item = initConsumable;
    }

    /**
     * Execute the action of consumable
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action after actor consume
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return item.effect(actor, map);
    }

    /**
     * Describe the action
     *
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return item.menuDescription(actor);
    }
}
