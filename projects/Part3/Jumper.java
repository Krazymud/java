/* 
 * @author Eadric
 */


import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

/**
 * A <code>JumperActor</code> jump two cells at a time. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{

    /**
     * Constructs a jumper actor that traces a square of a given side length
     */
    public Jumper()
    {
        
    }

    /**
     * Jumps to the next location.
     */
    public void act()
    {
        if(canJump()){
            Jump();
        }
        else if(canMove()){
            move();
        }
        else{
            turn();
        }
    }

    public void jump()
    {
        Location loc = getLocation();
        Location nnext = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        moveTo(nnext);
    }
    /**
     *Or, move to the next location.
     */
    public void move()
    {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        moveTo(next);
    }

    /**
     * Test if the jumper can move or jump
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)){
            return false;
        }
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
    public boolean canJump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return false;
        }
        Location loc = getLocation();
        Location nnext = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (!gr.isValid(nnext)){
            return false;
        }
        Actor neighbor = gr.get(nnext);
        return (neighbor == null) || (neighbor instanceof Flower);
        // ok to jump into empty location or onto flower
        // not ok to jump onto any other actor
    }
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
}