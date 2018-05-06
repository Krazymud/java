import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
 * This class runs a world with additional grid choices.
 */
public final class SparseGridRunner
{
    private SparseGridRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("info.gridworld.grid.SparseBoundedGrid");
        world.addGridClass("info.gridworld.grid.SparseBoundedGrid2");
        world.addGridClass("info.gridworld.grid.UnboundedGrid2");
        world.addGridClass("SparseBoundedGrid3");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}