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


import info.gridworld.grid.*;
import java.util.ArrayList;

/**
 * <code>Grid</code> provides an interface for a two-dimensional, grid-like
 * environment containing arbitrary objects. <br />
 * This interface is testable on the AP CS A and AB exams.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private class SparseGridNode
    {
        public SparseGridNode(E o, int c, SparseGridNode n){
            occupant = o;
            col = c;
            next = n;
        }
        private E occupant;
        private int col;
        private SparseGridNode next;
        public E getObj(){
            return occupant;
        }
        public int getCol(){
            return col;
        }
        public SparseGridNode getNext(){
            return next;
        }
        public void setObj(E obj){
            occupant = obj;
        }
        public void setNext(SparseGridNode newNext){
            next = newNext;
        }
    }
    private ArrayList<SparseGridNode> sparseArray;
    private int row;
    private int col;

    public SparseBoundedGrid(int rows, int cols){
        if(rows <= 0){
            throw new IllegalArgumentException("row <= 0");
        }
        if(cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        row = rows;
        col = cols;
        sparseArray = new ArrayList<SparseGridNode>();
        for(int i = 0; i < rows; i++){
            sparseArray.add(new SparseGridNode(null, 0, null));
        }
    }
    /**
     * Returns the number of rows in this grid.
     * @return the number of rows, or -1 if this grid is unbounded
     */
    public int getNumRows(){
        return row;
    }

    /**
     * Returns the number of columns in this grid.
     * @return the number of columns, or -1 if this grid is unbounded
     */
    public int getNumCols(){
        return col;
    }

    /**
     * Checks whether a location is valid in this grid. <br />
     * Precondition: <code>loc</code> is not <code>null</code>
     * @param loc the location to check
     * @return <code>true</code> if <code>loc</code> is valid in this grid,
     * <code>false</code> otherwise
     */
    public boolean isValid(Location loc){
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    /**
     * Puts an object at a given location in this grid. <br />
     * Precondition: (1) <code>loc</code> is valid in this grid (2)
     * <code>obj</code> is not <code>null</code>
     * @param loc the location at which to put the object
     * @param obj the new object to be added
     * @return the object previously at <code>loc</code> (or <code>null</code>
     * if the location was previously unoccupied)
     */
    public E put(Location loc, E obj){
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null){
            throw new IllegalArgumentException("obj == null");
        }

        //delete the old
        E preObj = remove(loc);
        //add object to the grid
        int rows = loc.getRow(), cols = loc.getCol();
        SparseGridNode node = sparseArray.get(rows);
        sparseArray.set(rows, new SparseGridNode(obj, cols, node));
        return preObj;
    }

    /**
     * Removes the object at a given location from this grid. <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc the location of the object that is to be removed
     * @return the object that was removed (or <code>null<code> if the location
     *  is unoccupied)
     */
    public E remove(Location loc){
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        //remove object
        int rows = loc.getRow(), cols = loc.getCol();
        E oldObj = get(loc);
        if(oldObj == null){
            return null;
        }
        SparseGridNode node = sparseArray.get(rows);
        if(node != null){
            if(node.getCol() == cols){
                sparseArray.set(rows, node.getNext());
            }
            else{
                SparseGridNode node2 = node.getNext();
                while(node2 != null && node2.getCol() != cols){
                    node = node.getNext();
                    node2 = node.getNext();
                }
                if(node2 != null){
                    node.setNext(node2.getNext());
                }
            }
        }
        return oldObj;
    }

    /**
     * Returns the object at a given location in this grid. <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return the object at location <code>loc</code> (or <code>null<code> 
     *  if the location is unoccupied)
     */
    public E get(Location loc){
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        //get object
        int rows = loc.getRow(), cols = loc.getCol();
        SparseGridNode node = sparseArray.get(rows);
        while(node != null){
            if(cols == node.getCol()){
                return node.getObj();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * Gets the locations in this grid that contain objects.
     * @return an array list of all occupied locations in this grid
     */
    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> loc = new ArrayList<Location>();
        int rows = 0;
        for(SparseGridNode node : sparseArray){
            while(node != null && node.getObj() != null){
                loc.add(new Location(rows, node.getCol()));
                node = node.next;
            }
            rows++;
        }
        return loc;
    }
}