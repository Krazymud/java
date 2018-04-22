/* 
 * 
 * @author Eadric
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains Circle bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class CircleBugRunner
{
    private CircleBugRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        BoundedGrid grid = new BoundedGrid(30, 30);
        world.setGrid(grid);
        CircleBug alice = new CircleBug(1);
        alice.setColor(Color.ORANGE);
        world.add(new Location(9, 9), alice);
        world.show();
    }
}