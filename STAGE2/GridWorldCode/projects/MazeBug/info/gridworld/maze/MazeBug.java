package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    private Location next;
    private Location last;
    private boolean isEnd = false;
    private int direction[] = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
    private int dir[] = {1, 1, 1, 1};
    private Stack<Location> crossLocation = new Stack<Location>();
    private Integer stepCount = 0;
    private boolean hasShown = false;
    //final message has been shown

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public MazeBug() {
        setColor(Color.GREEN);
        last = new Location(0, 0);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();
        if (isEnd) {
        //to show step count when reach the goal        
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            //increase step count when move 
            stepCount++;
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        int[] dirs = { Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST };
        for(int i : dirs){
            if(gr.isValid(loc.getAdjacentLocation(i))) {
                valid.add(loc.getAdjacentLocation(i));
            }
        }
        return valid;
    }

    /**
     * check if there's a location that can move to
     * if no, go back. Else determine the direction.
     */
    public boolean check(ArrayList<Location> unVisited){
        if(unVisited.isEmpty()){
            Location now = crossLocation.peek();
            crossLocation.pop();
            next = crossLocation.peek();
            int tmp = next.getDirectionToward(now);
            dir[tmp / 90]--;
        }
        else{
            int size = unVisited.size(), index = 1;
            int find = getLocation().getDirectionToward(unVisited.get(0));
            int max[] = new int[]{find, 0, 0, 0};
            int maxNum = dir[find / 90];
            for(int i = 1; i < size; i++){
                int j = getLocation().getDirectionToward(unVisited.get(i));
                int tmp = dir[j / 90];
                if(maxNum < tmp){
                    maxNum = tmp;
                    find = j;
                    max[0] = j;
                    index = 1;
                    max[1] = 0;
                    max[2] = 0;
                    max[3] = 0;
                }
                else if(maxNum == tmp){
                    max[index++] = j;
                }
            }
            if(index > 1){
                Random rand = new Random();
                int random = (int)(Math.random() * index);
                next = getLocation().getAdjacentLocation(direction[max[random] / 90]);
                dir[max[random] / 90]++;
            }
            else{
                next = getLocation().getAdjacentLocation(direction[find / 90]);
                dir[find / 90]++;
            }
            crossLocation.push(next);
        }
        return true;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        Location loc;
        Grid<Actor> gr = getGrid();
        if(crossLocation.empty()){
            loc = getLocation();
            crossLocation.push(loc);
        }
        else{
            loc = crossLocation.peek();
        }
        last = loc;
        ArrayList<Location> valid = getValid(loc);
        ArrayList<Location> unVisited = new ArrayList<>();
        for(Location l : valid){
            if(gr.get(l) instanceof Rock && gr.get(l).getColor().equals(Color.red)){
                isEnd = true;
                return false;
            }
            else if(gr.get(l) == null){
                unVisited.add(l);
            }
        }
        return check(unVisited);
    }
    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else{
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}
