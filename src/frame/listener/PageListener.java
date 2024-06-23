package frame.listener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import graph.*;
import frame.*;

public class PageListener implements MouseListener{
	
	FramePanel panel;
	Logger logger = Logger.getLogger(PageListener.class.getName());

	public PageListener( FramePanel panel ){
		this.setPanel( panel );
		logger.setLevel(Level.INFO);
		logger.addHandler(new ConsoleHandler());
	}

	public void mouseExited(MouseEvent e){

	}
	public void mousePressed(MouseEvent e){

	}
	public void mouseReleased(MouseEvent e){

	}
	public void mouseEntered(MouseEvent e){

	}
	public void mouseClicked(MouseEvent e){
		// Efa jbutton no mitondra an'ito ka tokony tsy hisy olana intsony ny ato
		try{
			JTextField page = new JTextField();
			JPanel panel = new JPanel();
			JTextField hosts = new JTextField();
			JLabel pageLabel = new JLabel("Go to : ");
			JLabel hostLabel = new JLabel("From Host : ");
			// pageLabel.setBounds( 10 , 10 , 100 , 50 );
			// page.setBounds( 10 , 30 , 100 , 50 );
			panel.add(pageLabel);
			panel.add(page);
			panel.add(hostLabel	);
			panel.add(hosts);
			panel.setLayout(new GridLayout(0,1));
			panel.setSize( 400 , 400 );
			int result = JOptionPane.showConfirmDialog( new JFrame() , panel, "HAHAHA" , JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE );
			if( result == JOptionPane.OK_OPTION ){
				String pages = page.getText().trim();
				String host = hosts.getText().trim();
				Edge solution = this.getPanel().getGraph().searchWebsite( host , pages );
				File web = solution.getPage( pages );
				String pathss = solution.getPath();
				this.getPanel().repaint();
				JOptionPane.showMessageDialog( new JFrame() , pathss );
				Desktop desktop = Desktop.getDesktop();
				desktop.browse( web.getAbsoluteFile().toURI() );
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog( new JFrame() , ex.getMessage() );
		}
	}

/// Getters and setters

	public void setPanel(FramePanel panel){
		this.panel = panel;
	}
	public FramePanel getPanel(){
		return this.panel;
	}

}