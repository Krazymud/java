/* 
 * 
 * @author Eadric
 */

import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> A bug that is dancing. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int steps;
    private int[] dir;
    private int loop;
    private int sideLength;

    /**
     * Constructs a box bug that is dancing towards random directions.
     * @param length the side length
     */
    public DancingBug(int[] direction)
    {
        steps = 0;
        sideLength = 2;
        loop = 0;
        dir = new int[direction.length];
        System.arraycopy(direction, 0, dir, 0, direction.length);
    }   

    /**
     * Moves to the next location of the dance.
     */
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            for(int i = 0; i < dir[loop]; i++){
                turn();
            }
            loop++;
            if(loop == dir.length){
                loop = 0;
            }
            steps = 0;
        }
    }
}