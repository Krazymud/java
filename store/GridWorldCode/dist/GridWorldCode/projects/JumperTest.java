import junit.framework.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest extends TestCase{

	public void testa(){
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(7, 2), alice);
        world.add(new Location(5, 2), rock);
        alice.act();
        Location loc = new Location(6, 2);
        assertEquals(loc, alice.getLocation());
	}

	public void testb(){
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(1, 2), alice);
        alice.act();
        Location loc = new Location(0, 2);
        assertEquals(loc, alice.getLocation());
	}

	public void testc(){
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(0, 2), alice);
        alice.act();
        alice.act();
        int direction = alice.getDirection();
        assertEquals(direction, 90);
        alice.act();
        Location loc = new Location(0, 4);
        assertEquals(loc, alice.getLocation());
	}

	public void testd(){
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Jumper bob = new Jumper();
        world.add(new Location(5, 5), alice);
        world.add(new Location(3, 5), bob);
        bob.setDirection(90);
        world.step();
        Location loc1 = new Location(3, 5);
        Location loc2 = new Location(3, 7);
        assertEquals(loc1, alice.getLocation());
        assertEquals(loc2, bob.getLocation());
	}

	public void teste(){
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Jumper bob = new Jumper();
        world.add(new Location(5, 5), alice);
        world.add(new Location(3, 3), bob);
        bob.setDirection(90);
        world.step();
        Location loc1 = new Location(4, 5);
        Location loc2 = new Location(3, 5);
        assertEquals(loc1, alice.getLocation());
        assertEquals(loc2, bob.getLocation());
	}
}