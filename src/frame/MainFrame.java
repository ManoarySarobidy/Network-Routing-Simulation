package frame;

import java.awt.*;
import javax.swing.*;
import graph.*;

public class MainFrame extends JFrame{

	FramePanel panel;

	public MainFrame(){
		this.init();
	}


	public MainFrame( Graph graph ){
		this.setPanel( graph );
		this.add( this.getPanel() );
		this.init();
	}

	public void init(){
		this.setLocationRelativeTo( null );
		this.setSize( 600 , 500 );
		this.setTitle("Network routing - Mr Tsinjo");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible(true);
	}

	public void setPanel( Graph g ){
		this.panel = new FramePanel( g );
	}
	public FramePanel getPanel(){
		return this.panel;
	}

}