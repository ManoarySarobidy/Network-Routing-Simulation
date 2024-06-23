package frame.listener;
import graph.*;
import java.awt.*;
import java.awt.event.*;
import frame.*;

public class PanelsListener implements MouseListener, MouseMotionListener{
	
	FramePanel panel;

	public PanelsListener(FramePanel panel){
		this.setPanel(panel);
	}

/// Overrided methods

	// Mouse Listener
	public void mousePressed(MouseEvent e){

	}
	public void mouseClicked(MouseEvent e){
		// Eto no mila ny x sy y aho
		// Si ao anatiny de je retourne fotsiny ange
		int x = e.getX();
		int y = e.getY();
		Edge edge = this.getPanel().getGraph().getEdgeAt( x, y );
		if( edge != null ){
			javax.swing.JOptionPane.showMessageDialog( null , edge.getName() );
		}
		// Maintenant inona ny atao
		// Je colorie les chemins en vert


	}
	public void mouseEntered(MouseEvent e){

	}
	public void mouseExited(MouseEvent e){

	}
	public void mouseReleased(MouseEvent e){

	}

	// Mouse Motion listener

	public void mouseMoved( MouseEvent e ){
		
	}

	public void mouseDragged(MouseEvent e){

	}


/// Getters and setters

	public void setPanel(  FramePanel panel ){
		this.panel = panel;
	}
	public FramePanel getPanel(){
		return this.panel;
	}

}