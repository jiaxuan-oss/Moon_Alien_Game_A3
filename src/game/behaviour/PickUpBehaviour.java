package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class for actor so that it will have pick up behaviour
 * @Author: Teh Jia Xuan
 */
public class PickUpBehaviour implements Behaviour{
    /**
     * Random object for random number
     */
    private final Random random = new Random();

    /**
     * get action for picking up item from the ground
     * loop through all the item on the ground
     * randomly pick one
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the action of picking up the item or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Item> items = new ArrayList<>();
        for(Item locationItem : map.locationOf(actor).getItems()){
            items.add(locationItem);
        }

        if(!items.isEmpty()){
            return items.get(random.nextInt(items.size())).getPickUpAction(actor);
        }
        return null;
    }


}
