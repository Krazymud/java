Use loc1.getRow()

```java
// @file: info/gridworld/grid/Location.java
// @line: 110~113
public int getRow()
{
	return row;
}
```



After the following statement is executed, the value of b is false.

```java
// @file: info/gridworld/grid/Location.java
// @line: 205~212
public boolean equals(Object other)
{
	if (!(other instanceof Location))
	return false;

	Location otherLoc = (Location) other;
	return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
}

```



loc3 = (4, 4)

```java
// @file: info/gridworld/grid/Location.java
// @line: 130, 138, 147~148, 168
public Location getAdjacentLocation(int direction)
    
int dc = 0;

else if (adjustedDirection == SOUTH)
	dr = 1;

return new Location(getRow() + dr, getCol() + dc);
```



dir = 135(SouthEast)

```java
// @file: info/gridworld/grid/Location.java
// @line: 178~195
    public int getDirectionToward(Location target)
    {
        int dx = target.getCol() - getCol();
        int dy = target.getRow() - getRow();
        // y axis points opposite to mathematical orientation
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

        // mathematical angle is counterclockwise from x-axis,
        // compass angle is clockwise from y-axis
        int compassAngle = RIGHT - angle;
        // prepare for truncating division by 45 degrees
        compassAngle += HALF_RIGHT / 2;
        // wrap negative angles
        if (compassAngle < 0)
            compassAngle += FULL_CIRCLE;
        // round to nearest multiple of 45
        return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
    }
```



The parameter in the getAdjacentLocation method indicates the direction of the adjacent neighbour to find. And it returns the location that is closest to the direction.

```java
// @file: info/gridworld/grid/Location.java
// @line: 130~169
	public Location getAdjacentLocation(int direction)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == EAST)
            dc = 1;
        else if (adjustedDirection == SOUTHEAST)
        {
            dc = 1;
            dr = 1;
        }
        else if (adjustedDirection == SOUTH)
            dr = 1;
        else if (adjustedDirection == SOUTHWEST)
        {
            dc = -1;
            dr = 1;
        }
        else if (adjustedDirection == WEST)
            dc = -1;
        else if (adjustedDirection == NORTHWEST)
        {
            dc = -1;
            dr = -1;
        }
        else if (adjustedDirection == NORTH)
            dr = -1;
        else if (adjustedDirection == NORTHEAST)
        {
            dc = 1;
            dr = -1;
        }
        return new Location(getRow() + dr, getCol() + dc);
    }
```



Assume grid is a reference to a Grid object, then use grid.getOccupiedLocations().size().

```java
// @file: info/gridworld/grid/Grid.java
// @line: 85
ArrayList<Location> getOccupiedLocations();
```

Assume grid is a reference to a bounded grid object, then use grid.getNumRows() * grid.getNumCols() - grid.getOccupiedLocations().size().

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 48~51, 53~58, 66~83
    public int getNumRows()
    {
        return occupantArray.length;
    }

	public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

	public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }
```



Assume grid is a reference to a Grid object, then use grid.isValid(new Location(10, 10))

```java
// @file: info/gridworld/grid/Grid.java
// @line: 50
boolean isValid(Location loc);
```



Because Grid is an interface, other classes extend Grid and implement it's methods. The implementations of these methods can be found in AbstractGrid and BoundedGrid and UnboundedGrid classes. The AbstractGrid class extends Grid and implements some of it's methods, the BoundedGrid and UnboundedGrid classes extends the AbstractGrid and Implement the others.

```java
// @file: info/gridworld/grid/AbstractGrid.java&BoundedGrid.java&UnboundedGrid.java
// @line: 26, 29, 31
public abstract class AbstractGrid<E> implements Grid<E>

public class BoundedGrid<E> extends AbstractGrid<E>

public class UnboundedGrid<E> extends AbstractGrid<E>
```



Using ArrayList avoids keeping track of the size of the list, and thus is  easier to use. If we want to return the objects in an array, we must record those locations previously.



color, direction, location.

```java
// @file: info/gridworld/actor/Actor.java
// @line: 32~34
private Location location;
private int direction;
private Color color;
```



An actor's default color is blue, and it's default direction is north.

```java
// @file: info/gridworld/actor/Actor.java
// @line: 41~42
color = Color.BLUE;
direction = Location.NORTH;
```



Because it has it's own instance variable and it has implemented several methods.



(1) No. If we run the code like this, ,it will throw an IllegalStateException.

```java
// @file: info/gridworld/actor/Actor.java
// @line: 115~119
    public void putSelfInGrid(Grid<Actor> gr, Location loc)
    {
        if (grid != null)
            throw new IllegalStateException(
                    "This actor is already contained in a grid.");
```

(2) No, it will throw an IllegalStateException.

```java
// @file: info/gridworld/actor/Actor.java
// @line: 133~137
    public void removeSelfFromGrid()
    {
        if (grid == null)
            throw new IllegalStateException(
                    "This actor is not contained in a grid.");
```

(3)  Yes, it will complie and run without error.



Use setDirection method.

```java
// @file: info/gridworld/actor/Actor.java
// @line: 80~85
    public void setDirection(int newDirection)
    {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
    }
```



```java
// @file: info/gridworld/actor/Bug.java
// @line: 98~99
        if (!gr.isValid(next))
            return false;
```



```java
// @file: info/gridworld/actor/Bug.java
// @line: 101
        return (neighbor == null) || (neighbor instanceof Flower);
```



isValid and get methods. These methods are called to make sure that the bug can move.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 98~100
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
```



getAdjacentLocation method. This method is called to help the bug to find its next location.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 97
        Location next = loc.getAdjacentLocation(getDirection());
```



getLocation, getDirection, getGrid

```java
// @file: info/gridworld/actor/Bug.java
// @line: 93~97
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = 			loc.getAdjacentLocation(getDirection());
```



The bug will remove itself from the grid.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 78~81
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
```



If we don't use loc and use getLoc() instead, we can't set the flower behind the bug correctly, since moveTo() will change the value getLoc() returns.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 78~79, 83
        if (gr.isValid(next))
            moveTo(next);

        flower.putSelfInGrid(gr, loc);
```



Because we set it.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 82
        Flower flower = new Flower(getColor());
```



If you simply call removeSelfFromGrid, then no. If the removeSelfFromGrid is called in the Bug move method, then yes.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 78~83
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
```



```java
// @file: info/gridworld/actor/Bug.java
// @line: 82~83
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
```



4 times, each turn executes a 45 degree turn.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 62~65
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
```





