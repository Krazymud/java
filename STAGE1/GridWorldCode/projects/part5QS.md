The isValid method is specified in the Grid interface. The BoundedGrid and UnboundedGrid classes implement this method. 

```java
// @file: info/gridworld/grid/Grid.java
// @line: 50
    boolean isValid(Location loc);
```



Method getValidAdjacentLocations calls the isValid method. Other methods only need to call getValidAdjacentLocations  to call isValid indirectly.

```java
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 44~45
    if (isValid(neighborLoc))
        locs.add(neighborLoc);
```



getOccupiedAdjacentLocations and get methods are called by the method getNeighbors, The AbstractGrid class implements the getOccupiedAdjacentLocations method, the BoundedGrid and UnboundedGrid classes implement the get method.

```java
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 62
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
        
```



The getEmptyAdjacentLocations calls the get method and tests the result to see if it is null, null means empty.

```java
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 56~57
    if (get(neighborLoc) == null)
        locs.add(neighborLoc);
```



The valid adjacent locations will only exists in north, south, east and west direction.

```java
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 41
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
```



The constructor of the BoundedGrid. It will throw an IllegalArgumentException if the number of rows <= 0 or the number of cols <= 0. 

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 41~44
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
```



occupantArray[0].length

The constructor insures that a BoundedGrid object has at least one row and one column.

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 53~58
    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }
```



The location need to be in the boundedgrid's range.

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60~64
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```



return type: ArrayList<Location>; O(r * c)

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 66~83
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



return type: E; parameter: Location; O(1)

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 85~91
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }
```



if the location is invalid(not in the grid), an IllegalArgumentException will be thrown.

If the object sent to the put method is null,  a NullPointerException will be thrown.

O(1)

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 93~105
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
```



return type: E; Remove an item from an empty location will store null in the location and return null.

O(1)

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 107~117
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
```



Yes, the time complexity of these methods are mainly O(1), except the getOccupiedLocations method.

However, using this kind of BoundedGrid might waste space, since it not only stores occupants, but it also stores null.

```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 45
        occupantArray = new Object[rows][cols];
```



Hash: The Location class must implement the hashCode and the equals methods. The hashCode method must return the same value for two locations that test true when the equals method is called. 

Tree: The Location class implements the Comparable interface. Therefore, the compareTo method must be implemented for the Location class to be a nonabstract class. The compareTo method should return 0 for two locations that test true when the equals method is called. The TreeMap requires keys of the map to be Comparable. 

The Location class satisfies all of these requirements.



We use a HashMap to implement the unboundedGrid, in which null is also a legal value for a key. And we need only object that are not null to be stored in the HashMap.

With BoundedGrid, we use an array to implement it, we can easily distinguish null from object.



The average time complexity for the three methods: get, put, and remove is O(1); If it is a TreeMap, the complexity should be O(log n), where n is the number of occupied locations in the grid.



the getOccupiedLocations might return the occupants inn a different order. Since HashMap and TreeMap manage their elements differently.





Sure a map could be used to implement a BoundedGrid, and since the map stores both the locations and the items, it might use more memory when the grid is almost full.



| Methods                      | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version |
| ---------------------------- | ---------------------- | --------------------------------- | --------------- | --------------- |
| getNeighbors                 | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| getEmptyAdjacentLocations    | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| getOccupiedAdjacentLocations | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| getOccupiedLocations         | O(n)                   | O(n)                              | O(n)            | O(n)            |
| get                          | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| put                          | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| remove                       | O(c)                   | O(c)                              | O(1)            | O(logn)         |



get: O(1);

put: O(1);

resize: O(r * c)











