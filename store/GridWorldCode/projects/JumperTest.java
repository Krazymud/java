import junit.framework.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest extends TestCase{

        /**
        *  Test the move when there's a rock two steps ahead of the jumper.
        */
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

        /**
        *  Test the move when there's nothing two steps ahead of the jumper.
        */
        public void testb(){
                ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(1, 2), alice);
        alice.act();
        Location loc = new Location(0, 2);
        assertEquals(loc, alice.getLocation());
        }
        /**
        *  Test the move when the jumper is facing the edge
        */
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
        /**
        *  Test the move when there's a jumper two steps ahead of the jumper.
        *  Two cases: contested or not contested.
        */
        public void testd(){
                ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Jumper bob = new Jumper();
        world.add(new Location(5, 5), alice);
        world.add(new Location(3, 5), bob);
        world.add(new Location(7, 7), tom);
        world.add(new Location(9, 9), pat);
        tom.setDirection(90);
        bob.setDirection(90);
        world.step();
        Location loc1 = new Location(3, 5);
        Location loc2 = new Location(3, 7);
        Location loc3 = new Location(7, 9);
        Location loc4 = new Location(8, 9);
        assertEquals(loc1, alice.getLocation());
        assertEquals(loc2, bob.getLocation());
        assertEquals(loc3, tom.getLocation());
        assertEquals(loc4, pat.getLocation());
        }
}