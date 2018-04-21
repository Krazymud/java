/* 
 * 
 * @author Eadric
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains Spiral bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class SpiralBugRunner
{
    private SpiralBugRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        UnboundedGrid grid = new UnboundedGrid();
        world.setGrid(grid);
        SpiralBug alice = new SpiralBug(2);
        alice.setColor(Color.ORANGE);
        world.add(new Location(9, 9), alice);
        world.show();
    }
}