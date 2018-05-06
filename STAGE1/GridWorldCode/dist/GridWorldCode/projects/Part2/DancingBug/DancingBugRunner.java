/* 
 * 
 * @author Eadric
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains Dancing bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class DancingBugRunner
{
    private DancingBugRunner(){}
    public static void main(String[] args)
    {
        int dir[] = new int[]{1, 2, 3, 4, 5};
        UnboundedGrid grid = new UnboundedGrid();
        ActorWorld world = new ActorWorld();
        world.setGrid(grid);
        DancingBug alice = new DancingBug(dir);
        alice.setColor(Color.ORANGE);
        world.add(new Location(17, 10), alice);
        world.show();
    }
}