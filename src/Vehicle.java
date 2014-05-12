import java.awt.Graphics2D;

/** Vehicle Superclass. Handles x,y coordinates of vehicles and their references
 * 
 * @author Jonathan
 *
 */
public abstract class Vehicle {

	
	//SINGLY LINKED LIST
	Vehicle trailer;
	
	protected int xLeft;
	protected int yTop;
	
	public Vehicle(int x, int y) {
        trailer = null;
		xLeft = x;
		yTop = y;
	}	
	/**
	 * Set xLeft
	 * @param x
	 */
	public void setX(int x)
	{	
	xLeft = x;	
	}
	/** Set yTop
	 * 
	 * @param y
	 */
	public void setY(int y)
	{	
	yTop = y;	
	}
	
	 /** Get yTop
	  * 
	  * @return yTop
	  */
	 public int getY() {return yTop;}
	 
	 /** Get xLeft
	  * 
	  * @return xLeft
	  */
	 public int getX() {return xLeft;}
	 
	 /**
	  * Sees if this vehicles points to another vehicles
	  * @return true if has a trailer, false otherwise
	  */
	 abstract public boolean hasNext();
	 
	 /**
	  * Gets the vehicle this vehicle points to
	  * @return trailer
	  */
	 abstract public Vehicle getNext();

	 /**
	  * Sets the vehicle this vehicle points to
	  */
	 abstract public void setNext(Vehicle next);
	 

	 
	public abstract void draw (Graphics2D g2);
	public abstract void link (Vehicle other);
	public abstract boolean contains(int x_loc, int y_loc);
	
	/** Returns mid coords for x
	 * 
	 * @return xLeft + 40
	 */
	public int getXMid ()
	{
		return xLeft + 40;
	}
	/** Returns mid coords for y
	 * 
	 * @return yTop - 40
	 */
	public int getYMid ()
	{
		return yTop - 40;
	}
	
	
}
