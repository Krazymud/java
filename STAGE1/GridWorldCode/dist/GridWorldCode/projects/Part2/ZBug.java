/* 
 * 
 * @author Eadric
 */

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a Z shape of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int turnCount;;
    private int zlength;
    /**
     * Constructs a Z bug that traces a Z shape a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        zlength = length;
        turnCount = 0;
    }

    /**
     * Moves to the next location of the Z.
     */
    public void act()
    {
        if(canMove() && turnCount < 3){
            if (steps < zlength)
            {
                move();
                steps++;
            }
            else
            {
                if(turnCount == 0){
                    setDirection(225);
                }
                else{
                    setDirection(90);
                }
                turnCount++;
                steps = 0;
            }
        }
    }
}