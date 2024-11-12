package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ConsumeAction;

/**
 * A class that let item to implement to let the
 * item to be consumed by actor
 * @Author: Teh Jia Xuan
 */
public interface Consumable {
     /**
      * Method when it consumes the item
      * @param actor The actor that consume the item
      * @param map The map the actor is on.
      * @return a description of the action after actor consume
      */
     String effect(Actor actor, GameMap map);

     /**
      * Method for description of the action
      * @param actor The actor that owns the item
      * @return a description of the item
      */
     String menuDescription(Actor actor);

}
