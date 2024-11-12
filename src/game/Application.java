package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Weapon.MetalPipe;
import game.action.TravelAction;
import game.actors.*;
import game.ground.*;
import game.item.BoltItem;
import game.item.GoldPot;
import game.item.MetalSheetItem;
import game.item.Picklejar;
import game.plants.SproutTree;
import game.spawn.AlienBugSpawn;
import game.spawn.HuntsmanSpiderSpawn;
import game.spawn.SuspiciousAstronautSpawn;
import game.plants.SaplingTree;

import java.util.Arrays;
import java.util.List;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Teh Jia Xuan
 * Please note that i have copilot turned on for this file
 */
public class Application {
    /**
     * The main method to start the game
     * @param args
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new ComputerTerminal());

        List<String> map = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~........................",
                "..............................",
                ".............#####............",
                ".............#___#...........~",
                ".............#___#..........~~",
                ".............##_##.........~~~",
                ".................~~........~~~",
                "................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        List<String> parkingLotMap = Arrays.asList(
                ".......",
                ".#####.",
                ".#___#.",
                ".#___#.",
                ".##_##.",
                ".......",
                ".......",
                ".......",
                ".......",
                ".......");

        GameMap parkingLot = new GameMap(groundFactory, parkingLotMap);
        world.addGameMap(parkingLot);

        List<String> refactorioMap = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~........................",
                "..............................",
                ".............#####............",
                ".............#___#...........~",
                ".............#___#..........~~",
                ".............##_##.........~~~",
                ".................~~........~~~",
                "................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");
        GameMap refactorio = new GameMap(groundFactory, refactorioMap);
        world.addGameMap(refactorio);


        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        gameMap.at(10,10).addActor(new HuntsmanSpider());
        gameMap.at(10,9).addActor(new AlienBug());
        gameMap.at(20,9).addActor(new SuspiciousAstronaut());
        gameMap.at(10,9).addItem(new MetalSheetItem("Metal Sheet", '%', true));
        gameMap.at(6,9).addItem(new GoldPot());
        gameMap.at(7,8).addItem(new GoldPot());
        gameMap.at(11,9).addItem(new Picklejar());
        gameMap.at(11,9).addItem(new Picklejar());
        gameMap.at(11,9).addItem(new Picklejar());
        gameMap.at(11,9).addItem(new Picklejar());
        gameMap.at(11,9).addItem(new Picklejar());

        gameMap.at(7, 9).setGround(new Crater(new HuntsmanSpiderSpawn()));
        gameMap.at(1, 10).setGround(new Crater(new AlienBugSpawn()));
        gameMap.at(4, 11).setGround(new Crater(new SuspiciousAstronautSpawn()));

        gameMap.at(12, 9).setGround(new SaplingTree());
        gameMap.at(16,8).addItem(new MetalPipe());
        gameMap.at(15, 6).addItem(new BoltItem("Bolt", '+', true));
        gameMap.at(15, 8).addItem(new BoltItem("Bolt", '+', true));
        gameMap.at(15,10).addItem(new MetalSheetItem("Metal Sheet", '%', true));
        ComputerTerminal refactorioComputerTerminal = new ComputerTerminal();
        ComputerTerminal polymorphiaComputerTerminal = new ComputerTerminal();
        ComputerTerminal parkingLotComputerTerminal = new ComputerTerminal();
        //add travel action to the map they not inside
        refactorioComputerTerminal.addMapLocation(new TravelAction(parkingLot.at(3,3), "Parking Lot"));
        refactorioComputerTerminal.addMapLocation(new TravelAction(gameMap.at(15,6), "Polymorphia"));

        parkingLotComputerTerminal.addMapLocation(new TravelAction(refactorio.at(15,6), "Refactorio"));
        parkingLotComputerTerminal.addMapLocation(new TravelAction(gameMap.at(15,6), "Polymorphia"));

        polymorphiaComputerTerminal.addMapLocation(new TravelAction(parkingLot.at(3,3), "Parking Lot"));
        polymorphiaComputerTerminal.addMapLocation(new TravelAction(refactorio.at(15,6), "Refactorio"));

        refactorio.at(16, 6).setGround(refactorioComputerTerminal);
        refactorio.at(12, 9).setGround(new SproutTree());
        parkingLot.at(4,3).setGround(parkingLotComputerTerminal);
        gameMap.at(16, 6).setGround(polymorphiaComputerTerminal);

        Player player = new Player("Intern", '@', 4);
        player.addBalance(1000);
        world.addPlayer(new HumanoidFigure(), parkingLot.at(2, 5));
        world.addPlayer(player, gameMap.at(15, 6));
        world.run();
    }
}
