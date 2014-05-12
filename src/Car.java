import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
   A car shape that can be positioned anywhere on the screen.
*/
public class Car extends Vehicle
{

   private int number;

   /**
      Constructs a car with a given top left corner.
      @param x the x coordinate of the top left corner
      @param y the y coordinate of the top left corner
   */
   public Car(int x, int y, int number)
   {
	  super (x,y); 
      this.number = number;
   }

   /**
      Draws the car.e
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   
      Rectangle body 
            = new Rectangle(xLeft, yTop + 10, 60, 10);      
      Ellipse2D.Double frontTire 
            = new Ellipse2D.Double(xLeft + 10, yTop + 20, 10, 10);
      Ellipse2D.Double rearTire
            = new Ellipse2D.Double(xLeft + 40, yTop + 20, 10, 10);

      // The bottom of the front windshield
      Point2D.Double r1 
            = new Point2D.Double(xLeft + 10, yTop + 10);
      // The front of the roof
      Point2D.Double r2 
            = new Point2D.Double(xLeft + 20, yTop);
      // The rear of the roof
      Point2D.Double r3 
            = new Point2D.Double(xLeft + 40, yTop);
      // The bottom of the rear windshield
      Point2D.Double r4 
            = new Point2D.Double(xLeft + 50, yTop + 10);

      Line2D.Double frontWindshield 
            = new Line2D.Double(r1, r2);
      Line2D.Double roofTop 
            = new Line2D.Double(r2, r3);
      Line2D.Double rearWindshield
            = new Line2D.Double(r3, r4);
   
      g2.drawString("" + number, xLeft + 27, yTop + 18);
      
      g2.setColor(Color.RED);
      
      g2.draw(body);
 
      g2.draw(frontTire);
      g2.draw(rearTire);
      g2.draw(frontWindshield);      
      g2.draw(roofTop);      
      g2.draw(rearWindshield);      
      
      if (PanelClass.getSelected() == this) //Want to select line of vehicles
      {
    	  
    	  Line2D.Double lineTop = new Line2D.Double(xLeft, yTop - 10, xLeft + 60, yTop - 10);
    	  Line2D.Double lineBottom = new Line2D.Double(xLeft, yTop + 40, xLeft + 60, yTop + 40);	
    	  Line2D.Double lineLeft = new Line2D.Double(xLeft, yTop + 40, xLeft, yTop - 10);	
    	  Line2D.Double lineRight = new Line2D.Double(xLeft + 60, yTop + 40, xLeft + 60, yTop - 10);	
    	  g2.draw(lineTop);
    	  g2.draw(lineBottom);
    	  g2.draw(lineLeft);
    	  g2.draw(lineRight);
    	  
    	  if(hasNext()){
    		  Line2D.Double lineTop1 = new Line2D.Double(getNext().getX(), getNext().getY() - 10, getNext().getX() + 60, getNext().getY() - 10);
        	  Line2D.Double lineBottom1 = new Line2D.Double(getNext().getX(), getNext().getY() + 40, getNext().getX() + 60, getNext().getY() + 40);	
        	  Line2D.Double lineLeft1 = new Line2D.Double(getNext().getX(), getNext().getY() + 40, getNext().getX(), getNext().getY() - 10);	
        	  Line2D.Double lineRight1 = new Line2D.Double(getNext().getX() + 60, getNext().getY() + 40, getNext().getX() + 60, getNext().getY() - 10);	
        	  g2.draw(lineTop1);
        	  g2.draw(lineBottom1);
        	  g2.draw(lineLeft1);
        	  g2.draw(lineRight1); 

    	  }
    	  
    	  if(trailer != null && trailer.hasNext()){
    		  Line2D.Double lineTop2 = new Line2D.Double(trailer.getNext().getX(), trailer.getNext().getY() - 10, trailer.getNext().getX() + 60, trailer.getNext().getY() - 10);
        	  Line2D.Double lineBottom2 = new Line2D.Double(trailer.getNext().getX(), trailer.getNext().getY() + 40, trailer.getNext().getX() + 60, trailer.getNext().getY() + 40);	
        	  Line2D.Double lineLeft2 = new Line2D.Double(trailer.getNext().getX(), trailer.getNext().getY() + 40, trailer.getNext().getX(), trailer.getNext().getY() - 10);	
        	  Line2D.Double lineRight2 = new Line2D.Double(trailer.getNext().getX() + 60, trailer.getNext().getY() + 40, trailer.getNext().getX() + 60, trailer.getNext().getY() - 10);	
        	  g2.draw(lineTop2);
        	  g2.draw(lineBottom2);
        	  g2.draw(lineLeft2);
        	  g2.draw(lineRight2); 
    	  }
    	  
    	  //do for next.next.next
      } // end of if this is selected
   
     
  
      //RECURSIVE DRAW
      if (hasNext()) {trailer.draw(g2);
      trailer.setX(this.getX() + 70);
      trailer.setY(this.getY() + 0);
      }
 
   }

   
   public boolean contains (int x, int y)
   {
	   if ((Math.pow(this.getXMid() - x, 2) + Math.pow(this.getYMid() - y, 2) < Math.pow(60, 2))) return true;
	return false;
	
   }
   
 
@Override
public void link(Vehicle toLink) {
	
	if (!(toLink instanceof Truck)){
	
		if (this.hasNext()) { 
			this.getNext().link(toLink); }
	else
	{
	this.trailer = toLink;	
	 }
	
		
		
	}
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

public int getNumber ()
{
return number;	
}

}


