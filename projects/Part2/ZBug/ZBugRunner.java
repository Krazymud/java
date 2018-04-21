/* 
 * 
 * @author Eadric
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains z bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ZBugRunner
{
    private ZBugRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(4);
        alice.setColor(Color.ORANGE);
        alice.setDirection(90);
        world.add(new Location(2, 2), alice);
        world.show();
    }
}