 /* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */


import java.util.ArrayList;
import info.gridworld.grid.*;
import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private E[][] occupantArray;
    private int row;
    private int col;
    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        occupantArray = (E[][])new Object[16][16];
        row = 16;
        col = 16;
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && 0 <= loc.getCol();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < row; r++)
        {
            for (int c = 0; c < col; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null){
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    public boolean checkValid(Location loc){
        return 0 <= loc.getRow() && loc.getRow() < row
                && 0 <= loc.getCol() && loc.getCol() < col;
    }

    public E get(Location loc)
    {
        if (!checkValid(loc)){
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        // Add the object to the grid.
        int oldRow = row, oldCol = col;
        while(loc.getRow() >= row || loc.getCol() >= col){
            row *= 2;
            col *= 2;
        }
        if(row != oldRow){
            enLarge(oldRow, oldCol);
        }
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
    public void enLarge(int oldRow, int oldCol){
        E[][] newArray = (E[][])new Object[row][col];
        for(int i = 0; i < oldRow; i++){
            for(int j = 0; j < oldCol; j++){
                newArray[i][j] = occupantArray[i][j];
            }
        }
        occupantArray = newArray;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}