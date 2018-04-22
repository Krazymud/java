/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        ArrayList<Location> doubleNeighbor = getDoubleNeighbor(getLocation());
        if(!doubleNeighbor.isEmpty()){
            if(doubleNeighbor.size() == 1){
                moveTo(doubleNeighbor.get(0));
            }
            else{
                double r = Math.random();
                if (r < 0.5){
                    moveTo(doubleNeighbor.get(0));
                }
                else{
                    moveTo(doubleNeighbor.get(1));
                }
            }
        }
        else{
            super.makeMove(loc);
        }
    }
    public ArrayList<Location> getDoubleNeighbor(Location loc){
        Grid gr = getGrid();
        ArrayList<Location> doubleNeighbor = new ArrayList<Location>();
        Location left = loc.getAdjacentLocation(getDirection() + Location.LEFT);
        Location lleft = left.getAdjacentLocation(getDirection() + Location.LEFT);
        Location right = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
        Location rright = right.getAdjacentLocation(getDirection() + Location.RIGHT);
        if(gr.isValid(left) && gr.isValid(lleft) && gr.get(left) == null && gr.get(lleft) == null){
            doubleNeighbor.add(lleft);
        }
        if(gr.isValid(right) && gr.isValid(rright) && gr.get(right) == null && gr.get(rright) == null){
            doubleNeighbor.add(rright);
        }
        return doubleNeighbor;
    }

}
