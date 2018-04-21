/* 
 * @author Eadric
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains jumper actor. <br />
 */
public final class JumperRunner
{
    private JumperRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Rock rock = new Rock(); 
        world.add(new Location(7, 3), rock);
        world.add(new Location(7, 2), alice);
        world.show();
    }
}