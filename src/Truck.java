import java.awt.geom.* ;
import java.awt.* ;

/**
   Truck is a vehicle that can pull a chain of cars behind it, but cannot be moved itself
 */
public class Truck extends Vehicle
{

    /**
       Constants
     */
    private static final double WIDTH = 35 ;
    private static final double UNIT = WIDTH / 7 ;
    private static final double LENGTH_FACTOR = 14 ; // length is 14U
    private static final double HEIGHT_FACTOR = 5 ; // height is 5U
    private static final double U_3 = 0.3 * UNIT ; 
    private static final double U2_5 = 2.5 * UNIT ; 
    private static final double U3 = 3 * UNIT ; 
    private static final double U4 = 4 * UNIT ; 
    private static final double U5 = 5 * UNIT ; 
    private static final double U10 = 10 * UNIT ; 
    private static final double U10_7 = 10.7 * UNIT ; 
    private static final double U12 = 12 * UNIT ; 
    private static final double U13 = 13 * UNIT ; 
    private static final double U14 = 14 * UNIT ; 
    
    /**
       Constructs truck at position
       @param x the x position
       @param y the y position
     */
    public Truck(int x, int y)
    {
    	  super (x,y); 

    }
    
    /**
       Draws the truck
       @param g2 the graphics context
     */
    public void draw(Graphics2D g2)
    {
	int x1 = xLeft;
	int y1 = yTop;
	Rectangle2D.Double hood = new Rectangle2D.Double(x1, y1 + UNIT, 
							 U3, U3 ) ;
	g2.setColor(Color.red) ;
	g2.fill(hood) ;

	Rectangle2D.Double body = new Rectangle2D.Double(x1 + U3,y1,
							 U10, U4) ;
	g2.setColor(Color.blue) ;
	g2.fill(body) ;

	
	Ellipse2D.Double wheel1 = new Ellipse2D.Double(x1 + U_3, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel1) ;

	Ellipse2D.Double wheel2 = new Ellipse2D.Double(x1 + U3, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel2) ;

	Ellipse2D.Double wheel3 = new Ellipse2D.Double(x1 + 4 * UNIT, 
						       y1 + 4 * UNIT, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel3) ;

	Ellipse2D.Double wheel4 = new Ellipse2D.Double(x1 + U10_7, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel4) ;

	Ellipse2D.Double wheel5 = new Ellipse2D.Double(x1 + U12, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel5) ;
	
	
	//RECURSIVE DRAW
	 if (hasNext()) {trailer.draw(g2);
     trailer.setX(this.getX() + 70);
     trailer.setY(this.getY() + 0);
     }
	
	
   }

    /** Link method (recursive)
     * @param toLink - vehicle which to set this point to (make its trailer)
     */
    public void link(Vehicle toLink) {
    	
    	
    		if (this.hasNext()) { 
    			this.getNext().link(toLink); }
    	else
    	{
    	this.trailer = toLink;	
    	 }
    }

  
	@Override
	public boolean contains(int x, int y) {
		  if ((Math.pow(x - this.getX(), 2) + Math.pow(y - this.getY(), 2) < Math.pow(15, 2))) return true;
			return false; 
	}
	
	//------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean hasNext() {
		if (trailer != null && trailer instanceof Car) return true;
		return false;
	}

	//Could you just call 'next' instead?
	@Override
	public Vehicle getNext() {
	if (hasNext() == true){	
		return trailer;}
	return null;
	}

	@Override
	public void setNext(Vehicle next) {
		// TODO Auto-generated method stub
		
	}
}