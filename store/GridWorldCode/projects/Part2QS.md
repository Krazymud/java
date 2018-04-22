The sideLength instance variable limits the number of steps a BoxBug can move on one direction.

```java
// @file: BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
	move();
	steps++;
}
else
{
	turn();
	turn();
	steps = 0;
}
```



The steps instance variable measures the steps that the BoxBug has moved, and it's set to zero when the BoxBug turns.

```java
// @file: BoxBug.java
// @line: 45~55 
if (steps < sideLength && canMove())
{
	move();
	steps++;
}
else
{
	turn();
	turn();
	steps = 0;
}
```



Because the turn method only executes a 45 degree turn, and we need it to turn 90 degrees. Therefore it takes two turn method calls.

```java
// @file: info/gridworld/actor/Bug.java
// @line: 62~65
public void turn()
{
	setDirection(getDirection() + Location.HALF_RIGHT);
}
```



Because  the Bug class has a move method that can be used by the BoxBug class, since BoxBug class extends the Bug class.

```java
// @file: BoxBug.java, info/gridworld/actor/Bug.java
// @line: 25, 71
public class BoxBug extends Bug

public void move()
```



Yes, when a BoxBug is constructed, its sideLength is determined and it defines the square pattern.

```java
// @file: BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
	move();
	steps++;
}
else
{
	turn();
	turn();
	steps = 0;
}
```



Yes, if a Rock or a Bug is in front of the BoxBug,  the BoxBug will turn and find a new path.

```java
// @file: BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
	move();
	steps++;
}
else
{
	turn();
	turn();
	steps = 0;
}
```



Initially the value of steps is set to zero. And when steps is equal to sideLength then it's set to zero. It's also set to zero when it cannot move.

```java
// @file: BoxBug.java
// @line: 36, 50~55
steps = 0;

else
{
	turn();
	turn();
	steps = 0;
}
```

