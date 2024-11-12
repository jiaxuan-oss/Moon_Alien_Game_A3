package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawn.SpawnActor;
/**
 * A class that represents a Crater ground
 * @Author: Teh Jia Xuan
 */
public class Crater extends Ground {
    /**
     * The spawner that will spawn creature
     */
    private SpawnActor spawner;
    /**
     * Constructor.
     * @param initSpawner the spawner that will spawn creature
     */
    public Crater (SpawnActor initSpawner){
        super('u');
        spawner = initSpawner;
    }

    /**
     * every tick spawn the spawner
     * @param location the location of the crater
     */
    @Override
    public void tick(Location location){
        spawner.spawn(location);
    }
}
