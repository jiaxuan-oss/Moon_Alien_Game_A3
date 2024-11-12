package game.plants.plantbehaviours;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.plants.Plant;

import java.util.List;
import java.util.Random;

/**
 * A class that represents the plant behaviour of dropping fruit
 * @Author: Lee Teck Xian
 */
public class DropFruitBehaviour implements PlantBehaviour {

    private Item fruit;
    private double fruitSpawnRate;

    /**
     * Constructor for DropFruitBehaviour
     * @param fruit the fruit to be dropped
     * @param fruitSpawnRate the rate of fruit to be dropped
     */
    public DropFruitBehaviour(Item fruit, double fruitSpawnRate) {
        this.fruit = fruit;
        this.fruitSpawnRate = fruitSpawnRate;
    }

    private double generateRandomNumber() {
        return new Random().nextDouble();
    }

    private Location getRandomLocation(Location location) {
        List<Exit> exits = location.getExits();
        Exit exit = exits.get(new Random().nextInt(exits.size()));
        return exit.getDestination();
    }

    private void checkSpawnFruitRate(double randomNumber, Location newLocation) {
        if (randomNumber <= fruitSpawnRate) {
            //one fruit is produced at one tick
            newLocation.addItem(fruit);
        }
    }

    private void dropFruit(Location location) {
        double randomNumber = generateRandomNumber();
        Location newLocation = getRandomLocation(location);
        checkSpawnFruitRate(randomNumber, newLocation);
    }

    @Override
    public void execute(Plant plant, Location location) {
        dropFruit(location);

    }
}
