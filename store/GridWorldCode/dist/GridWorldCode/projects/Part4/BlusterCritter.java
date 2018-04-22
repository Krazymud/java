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
import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    private static final double COLOR_FACTOR = 0.5;
    private int c;

    public BlusterCritter(int courage){
        c = courage;
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Location> neighborNear = getDoubleNeighbors(getLocation());
        ArrayList<Actor> actor = new ArrayList<Actor>();
        for(Location l : neighborNear){
            if(getGrid().get(l) instanceof Critter){
                actor.add(getGrid().get(l));
            }
        }
        return actor;
    }

    public ArrayList<Location> getDoubleNeighbors(Location loc){
        Grid gr = getGrid();
        ArrayList<Location> neighborNear = new ArrayList<Location>();
        for(int i = 0; i < gr.getNumRows(); i++){
            for(int j = 0; j < gr.getNumCols(); j++){
                if( (Math.abs(i - loc.getRow()) >= 2) && (Math.abs(j - loc.getCol()) >= 2)  &&
                    (i != loc.getRow() && j != loc.getCol())){
                    neighborNear.add(new Location(i, j));
                }
            }
        }
        return neighborNear;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n >= c){
            Color color = getColor();
            int red = (int) (color.getRed() * (1 - COLOR_FACTOR));
            int green = (int) (color.getGreen() * (1 - COLOR_FACTOR));
            int blue = (int) (color.getBlue() * (1 - COLOR_FACTOR));

            setColor(new Color(red, green, blue));
            return;
        }
        else{
            Color color = getColor();
            int red = color.getRed(), green = color.getGreen(), blue = color.getBlue();
            if(red != 255){
                red++;
            }
            if(green != 255){
                green++;
            }
            if(blue != 255){
                blue++;
            }
            setColor(new Color(red, green, blue));
            return;
        }
    }
}