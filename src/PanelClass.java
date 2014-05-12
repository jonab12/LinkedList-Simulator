
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class PanelClass extends JPanel implements MouseListener, MouseMotionListener
{

//Keeps all Vehicles locally, a list of Vehicle objects to be drawn by paintComponent
public static ArrayList <Vehicle> vehicles = new ArrayList<Vehicle>();	
public final int MAX = 5; //Max vehicles on screen allowed

public Car car;
public Truck truck; 

public static Vehicle selected = null; 

private static boolean truck_flag = true; // Boolean to make sure Truck is only drawn once, then cars can be drawn after
private static int count = 0;
   
/**Panel Constructor
 * 
 */
public PanelClass()
   {  
	  addMouseListener(this);   
	  addMouseMotionListener(this); 
   }
/** Inherited from MouseListener, when user presses screen decides what action to take
 * 
 */ 
@Override
public void mouseClicked(MouseEvent evt) {

int x_loc = evt.getX();
int y_loc = evt.getY();
	
for (int i = 0; i < vehicles.size();i++){
   
	if (vehicles.get(i).contains(x_loc, y_loc)) {
		
		if ((selected == null) && !(vehicles.get(i) instanceof Truck)){
		selected = vehicles.get(i);	
		System.out.println ("vehicle now selected");
	    handleMouseEvent(evt);
	    }
	   else if (selected == vehicles.get(i))
	   { 
		selected = null; 
		System.out.println ("vehicle now dis-selected");
	    return; // BREAK out of this method
	   }
    }
	else{}
}// end of for loop

if (selected == null && count < MAX){
	boolean car_flag = true;
	
	//Check to see if any vehicle overlaps another vehicle
	for (int i = 0; i < vehicles.size();i++){	   
		if (vehicles.get(i).contains(x_loc, y_loc)) {
			System.out.println ("Cant create a new vehicle over a pre-existing one");
		car_flag = false;
		}
	}
if (truck_flag == true){
	truck = new Truck (x_loc,y_loc);	
	truck_flag = false;
	vehicles.add(truck);
	repaint();
	return;
}
	
if (car_flag == true){	
car = new Car (x_loc, y_loc, count);	
count++; //We cannot use this number again, unless the Menu 'New' item is clicked
vehicles.add(car);
 }
}
repaint();
}

/** handleMouseEvent - key method of PanelClass. Responds to MouseDragged, MouseClicked events and handles them
 * 
 * @param evt - x/y coordinates
 */
private void handleMouseEvent(MouseEvent evt) {
	//Boolean methods check to see if a Vehicle is not placed on the trailer of another. This allows proper placement.
	boolean invalid_Car = false;
	boolean invalid_Truck = false;
	
	if (selected != null){
	Vehicle selected_veh = selected; 

	for (int i = 0; i < vehicles.size();i++){
	
		if (vehicles.get(i) instanceof Car){ 
			if ((Math.pow(vehicles.get(i).getX() - selected_veh.getX(), 2) + Math.pow(vehicles.get(i).getY() - selected_veh.getY() , 2) < Math.pow(45, 2)) && selected_veh != vehicles.get(i)) 
		    {
			//Check to see current car isnt already attached	
			for (int j = 0; j < vehicles.size();j++){
				if (vehicles.get(j).trailer == vehicles.get(i)){ invalid_Car = true; break;}
				invalid_Car = false;
			}
				
				if (invalid_Car == false){
	        System.out.println ("You linked a car to a car");
            selected_veh.link(vehicles.get(i));
            vehicles.remove(i);
            repaint();
            break;
				} 
	 }
	}
	
	   else if (vehicles.get(i) instanceof Truck){
		
		if ((Math.pow(vehicles.get(i).getX() - selected_veh.getX(), 2) + Math.pow(vehicles.get(i).getY() - selected_veh.getY(), 2) < Math.pow(45, 2)) && selected_veh != vehicles.get(i)) 
	    {
			for (int j = 0; j < vehicles.size();j++){
				if (vehicles.get(j).trailer == vehicles.get(i)){ invalid_Truck = true; break;}
				invalid_Truck = false;
			}
				
			//NOTE - If screen is clicked after cars are hitched to the truck it should divert to normal. This is an event queue issue
			// as even if screen is revalidated and repainted the truck wont show its proper trailer until the screen is clicked.
				if (invalid_Truck == false){
			System.out.println ("You linked a car to a truck. CLICK SCREEN TO REFRESH");
			vehicles.get(i).link(selected_veh);
			repaint();
			selected = null;
			break;
				}//end of invalid checker
		}
	 }
	
	}//end of for loop
	
	selected_veh.setX(evt.getX() - 30);
	selected_veh.setY(evt.getY() - 15);
	
	
	repaint();
	}
}



public void mousePressed(MouseEvent arg0) {}
public void mouseReleased(MouseEvent evt) { handleMouseEvent(evt); }
public void mouseDragged(MouseEvent evt) { handleMouseEvent(evt);	}
public void mouseMoved(MouseEvent arg0) {}	
public void mouseEntered(MouseEvent arg0) {}
public void mouseExited(MouseEvent arg0) {}

   
/*
When the paintComponent() method is executed, only the cars in the arraylist vehicles are drawn. 
If a car is a trailer of the truck or of another car, then the recursive draw
method of class Car/class Truck will draw it. Hence every other vehicle will be drawn
from the draw() method. 
 */

public void paintComponent(Graphics g) {
	super.paintComponent(g) ;
	 Graphics2D g2 = (Graphics2D) g;	
	 g2.drawString("Click on number to select or dis-select vehicle, hold to drag.",150, 20);
	

//You need to draw only vehicles that are not hitched (having nothing infront of them) Hence linked list	 
// should only contain vehicles that arent hitched. 
for (int i = 0; i < vehicles.size();i++)
{
Vehicle temp = vehicles.get(i); //local variable
temp.draw(g2); 
 }

}
/** Gets selected vehicle
 * 
 * @return selected
 */
public static Vehicle getSelected ()
{	
return selected;	
	
}
private static void createAndShowGUI()
{
	   JFrame frame = new FrameClass(); 
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setTitle("Assignment 2");
	   frame.setVisible(true);      
}


//Main method
public static void main(String[] args)
{  
	 SwingUtilities.invokeLater(new Runnable()
     {
         @Override
         public void run()
         {
             createAndShowGUI();
         }
     });
}

/** When a truck is drawn, the flag truckFlag is turned false so it can no longer be re-initailzied.
 * 
 * @param b - flag to set
 */
public static void setTruckFlag(boolean b) {
	truck_flag = b;
	
}

/** Reverts count of cars to 0 so when "New" item is clicked vehicles can be re-drawn. 
 * 
 * @param i
 */
public static void setCount(int i) {
	count = i;
	
}


}
