import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;




/** FrameClass class - Top Level container JFrame
 * 
 * @author Jonathan
 *
 */
public class FrameClass extends JFrame{

	public static final int FRAME_WIDTH  = 600;
	public static final int FRAME_HEIGHT = 600;
	
	/** FrameClass Construtor - Creates bottom level container - JPanel within JFrame
	 * 
	 */
	public FrameClass() {
		
		JMenuBar menuBar = new JMenuBar();     
		   setJMenuBar(menuBar);
		   menuBar.add(createFileMenu());
		   menuBar.add(createListMenu());
		   menuBar.add(createEditMenu());
		
		createPanel() ;
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		   
		
	}
	
	/** Called when "Exit" is clicked. Closes Frame
	 * 
	 * @author Jonathan
	 *
	 */
	 class ExitItemListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	         System.exit(0);
	      }
	   }      
	 /** Called when "New" is clicked. Clears arraylist that stores vehicles
	  * 
	  * @author Jonathan
	  *
	  */
	 class NewItemListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	    	  PanelClass.vehicles.clear();
	    	 PanelClass.setTruckFlag(true);
	    	 PanelClass.setCount(0);
	    	  repaint();
	      }
	   }  
	 
/** AddFirst - links 'selected' vehicle onto the hitch of the truck
 * 
 * @author Jonathan
 *
 */
	 class AFItemListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	    	
	   
	    	  for (int i = 0; i < PanelClass.vehicles.size();i++)
	   		  {
		      if (PanelClass.vehicles.get(i) instanceof Truck){
		    	  PanelClass.vehicles.get(i).trailer = PanelClass.selected; 
		    	  PanelClass.selected = null;
		    	  repaint();
		       }
	   		  }
	    	  
	      }
	   } 
	 
	 /** AddLast - links 'selected' vehicle onto the hitch of the truck, pushing others back
	  * 
	  * @author Jonathan
	  *
	  */
	 class ALItemListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	    	
	   
	    	  for (int i = 0; i < PanelClass.vehicles.size();i++)
	   		  {
		      if (PanelClass.vehicles.get(i) instanceof Truck){
		    	  PanelClass.vehicles.get(i).trailer = PanelClass.selected; 
		    	  PanelClass.selected = null;
		    	  repaint();
		       }
	   		  }
	    	  
	      }
	   } 
	   
	 /** Sets every vehicle to a random position onto the screen
	  * 
	  * @author Jonathan
	  *
	  *
	  */
	 class RandomItemListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	      Random random = new Random();    
	    	
	      
	      for (int i = 0; i < PanelClass.vehicles.size();i++)
	      {
	    int x = random.nextInt(300);
	    int y = random.nextInt(300);
	    
	    PanelClass.vehicles.get(i).setX(x);
	    PanelClass.vehicles.get(i).setY(y);
	    repaint();
	    	  
	      }
	    	  
	      }
	   } 
	 
	   /**
	      Creates the File menu.
	      @return the menu
	   */
	   public JMenu createFileMenu()
	   {
	      JMenu menu = new JMenu("File");
	      JMenuItem newItem = new JMenuItem("New");   
	      JMenuItem exitItem = new JMenuItem("Exit");      
	      ActionListener listener = new ExitItemListener();
	      ActionListener listener1 = new NewItemListener();
	      
	      exitItem.addActionListener(listener);
	      newItem.addActionListener(listener1);
	      
	      menu.add(newItem);
	      menu.add(exitItem);
	     
	      return menu;
	   }

	   /**
	      Creates the List submenu.
	      @return the menu
	   */
	   public JMenu createListMenu()
	   {
	      JMenu menu = new JMenu("List");
	  	JMenuItem addFirst = new JMenuItem("Add First");       
	  	JMenuItem addLast = new JMenuItem("Add Last");  
	  	
	    ActionListener listener2 = new AFItemListener();
	    ActionListener listener3 = new ALItemListener();
	    addFirst.addActionListener(listener2);
	    addLast.addActionListener(listener3);
	    
	    menu.add(addFirst);
	    menu.add(addLast);
	      return menu;
	   }  
	   
	   public JMenu createEditMenu()
	   {
	      JMenu menu = new JMenu("Edit");
	      menu.add(createMoveMenu());
	    
	      JMenuItem Random_item = new JMenuItem("Randomize");  
	      ActionListener listener4 = new RandomItemListener();
	      Random_item.addActionListener(listener4);
	      menu.add(Random_item);
	      
	      return menu;
	   }  

	   /**
	      Creates the Move submenu.
	      @return the menu
	   */
	   public JMenu createMoveMenu()
	   {
	      JMenu menu = new JMenu("Move");
	      menu.add(createMoveItem("Left"));
	      menu.add(createMoveItem("Right"));
	      menu.add(createMoveItem("Up"));
	      menu.add(createMoveItem("Down"));
	      return menu;
	   }  
	   

	   /**
	      Creates a menu that has options left, right, down, up that changes the x/y of all vehicles, accordingly.
	      @param name the name of the move marker
	      @return the menu item
	   */
	   public JMenuItem createMoveItem(final String name)
	   {
	      class FaceItemListenerLeft implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 for (int i = 0; i < PanelClass.vehicles.size();i++)
	   	      {
	   	   
	   	    PanelClass.vehicles.get(i).setX(PanelClass.vehicles.get(i).getX() - 50);
	   	    repaint();
	   	    	  
	   	      }
	         }
	      }  
	      //-----------//
	      class FaceItemListenerRight implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 for (int i = 0; i < PanelClass.vehicles.size();i++)
	   	      {
	   	   
	   	    PanelClass.vehicles.get(i).setX(PanelClass.vehicles.get(i).getX() + 50);
	   	    repaint();
	   	    	  
	   	      }
	         }
	      }  
	      //-----------//
	      class FaceItemListenerDown implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 for (int i = 0; i < PanelClass.vehicles.size();i++)
	   	      {
	   	   
	   	    PanelClass.vehicles.get(i).setY(PanelClass.vehicles.get(i).getY() - 50);
	   	    repaint();
	   	    	  
	   	      }
	         }
	      }  
	      //-----------//
	      class FaceItemListenerUp implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 for (int i = 0; i < PanelClass.vehicles.size();i++)
	   	      {
	   	   
	   	    PanelClass.vehicles.get(i).setY(PanelClass.vehicles.get(i).getY() + 50);
	   	    repaint();
	   	    	  
	   	      }
	         }
	      }  
	      //-----------//

	      
	      JMenuItem item = new JMenuItem(name);      
	      if (name.equals("Left")){
	      ActionListener listenerL = new FaceItemListenerLeft();
	      item.addActionListener(listenerL);
	      }
	      else if (name.equals("Right")){
		      ActionListener listenerR = new FaceItemListenerRight();
		      item.addActionListener(listenerR);
		      }
	      else if (name.equals("Down")){
		      ActionListener listenerD = new FaceItemListenerDown();
		      item.addActionListener(listenerD);
		      }
	      else {
		      ActionListener listenerU = new FaceItemListenerUp();
		      item.addActionListener(listenerU);
		      }
	      return item;
	   }

	
	
	
/**CreatePanel Method
 * 
 * creates new panel object
 */
	 public void createPanel()
	    {
	 JPanel panel = new PanelClass() ;
	 add(panel, BorderLayout.CENTER) ;
	    }
	
}
