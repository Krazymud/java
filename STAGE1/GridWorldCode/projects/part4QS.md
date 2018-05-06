act, getActors, processActors, getMoveLocations, selectMoveLocation, makeMove

```java
// @file: info/gridworld/actor/Critter.java
// @line: 38, 56, 71, 88, 104, 125
    public void act()
    public ArrayList<Actor> getActors()
    public void processActors(ArrayList<Actor> actors)
    public ArrayList<Location> getMoveLocations()
    public Location selectMoveLocation(ArrayList<Location> locs)
    public void makeMove(Location loc)
```



getActors, processActors, getMoveLocations, selectMoveLocation, makeMove



It it needs, it should.



It could eat them or make them change color or make them to move.

```java
// @file: info/gridworld/actor/Critter.java
// @line: 71~78
	public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```



getMoveLocations, selectMoveLocation, makeMove

The critter first get some possible locations by calling getMoveLocations, then it select a location following a pattern, finally it makeMove.

```java
// @file: info/gridworld/actor/Critter.java
// @line: 44~46
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
```



Because Critter extends Actor.

```java
// @file: info/gridworld/actor/Critter.java
// @line: 31
public class Critter extends Actor
```



Because the act method calls getActors, processActors, getMoveLocations, selectMoveLocation, and makeMove. The ChameleonCritter class overrides the processActors and makeMove methods. 

```java
// @file: ChameleonCritter.java
// @line: 36, 50
    public void processActors(ArrayList<Actor> actors)
    public void makeMove(Location loc)
```



Because the makeMove method of the ChameleonCritter first changes the direction of the critter, then move like Critter.

```java
// @file: ChameleonCritter.java
// @line: 50~54
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
```



Modify the makeMove method, such as:

```java
    public void makeMove(Location loc)
    {
        Location oLoc = getLocation();
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
        if(!oLoc.equals(loc)){
            Flower flower = new Flower(getColor());
            flower.putSelfInGrid(getGrid(), oLoc);
        }
    }
```



Because it doesn't need to, it defines no new behavior for getActors.



The Actor class contains the getLocation method, and all subclasses of it inherits it.

```java
// @file: info/gridworld/actor/Actor.java
// @line: 102
    public Location getLocation()
```



A critter can access its own grid by calling the getGrid method inherited from the Actor class.

```java
// @file: info/gridworld/actor/Actor.java
// @line: 92~95
    public Grid<Actor> getGrid()
    {
        return grid;
    }
```



Because it doesn't need to.



The CrabCritter looks for neighbors that are in front of the crab critter and to its right front and left-front locations(getActors). Any Neighbors in those locations will be eaten.

```java
// @file: CrabCritter.java
// @line: 44~57
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
```



The CrabCritter uses getLocationsInDirections to find possible locations that it can move to.

```java
// @file: CrabCritter.java
// @line: 49, 67
        for (Location loc : getLocationsInDirections(dirs))
        for (Location loc : getLocationsInDirections(dirs))
```



Possible locations are (4,3), (4,4), (4,5)

```java
// @file: CrabCritter.java
// @line: 47~48
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
```



Similarities: they both do not turn in the direction that they are moving, and they choose their next location randomly.

Differences: A crab critter only moves to its left or right, while a critter's possible move locations are any of its 8 adjacent locations.



If the "loc" in makeMove is equal to the crab critter's current location, it turns instead of moving.

```java
// @file: CrabCritter.java
// @line: 79~90
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
        else
            super.makeMove(loc);
```



Because it's set not to. It inherits the processActors method from the Critter class, the method only removes actors that are not rocks and not critters.

```java
// @file: info/gridworld/actor/Critter.java
// @line: 75~76
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
```















