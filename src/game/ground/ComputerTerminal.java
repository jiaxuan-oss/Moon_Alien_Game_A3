package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.*;
import game.item.Astley;
import game.item.DragonSlayerSword;
import game.item.EnergyDrink;
import game.item.Theseus;
import game.item.ToiletPaperRoll;

import java.util.ArrayList;


/**
 * A class that represents a Computer Terminal that allows the player to purchase items.
 *
 * @author Lee Quan Hong & Teh Jia Xuan
 */
public class ComputerTerminal extends Ground {
    /**
     * maps to travel
     */
    private ArrayList<TravelAction> travelActions = new ArrayList<>();

    /**
     * Constructor for Computer Terminal.
     */
    public ComputerTerminal() {
        super('=');
    }

    /**
     * Returns the allowable actions that the player can perform on the Computer Terminal.
     * @param actor the actor that is performing the action
     * @param location the location of the Computer Terminal
     * @param direction the direction of the actor
     * @return an ActionList containing the allowable actions that the player can perform on the Computer Terminal
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Add the actions that the player can perform on the Computer Terminal
        actions.add(new PurchaseAction(new ToiletPaperRoll()));
        actions.add(new PurchaseAction(new EnergyDrink()));
        actions.add(new PurchaseAction(new DragonSlayerSword()));
        actions.add(new PurchaseAction(new Astley(actor)));
        actions.add(new PurchaseAction(new Theseus()));
        for (Action action : travelActions) {
            actions.add(action);
        }

        return actions;
    }

    /**
     * add maps to travel to travelaction list
     * @param travelAction
     */
    public void addMapLocation(TravelAction travelAction){
        this.travelActions.add(travelAction);
    }
}
