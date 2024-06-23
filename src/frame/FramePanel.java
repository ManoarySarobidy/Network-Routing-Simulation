package frame;

import javax.swing.*;
import java.awt.*;
import graph.*;
import java.util.*;
import frame.listener.*;

public class FramePanel extends JPanel{

	// Inona no tokony ananan'ny Panel iray
	// Mila manana an'ilay graph
	Graph graph;
	PanelsListener listener;
	PageListener pageListener;
	JButton page;
	JButton host;

	public FramePanel( Graph graph ){
		this.init();
		this.setGraph(graph);
		this.listener = new PanelsListener(this);
		this.pageListener = new PageListener(this);
		this.addMouseListener( listener );
		this.getPage().addMouseListener( pageListener );
		this.addMouseMotionListener( listener );
		this.add( this.getHost() );
		this.add( this.getPage() );
	}

	private void init(){
		this.setBackground( Color.WHITE );
		this.setSize( 500 , 400 );
		this.setPage( " Test Website " );
		this.setHost( " Test Host " );
		this.getHost().setBounds( 10 , 100 , 100 , 60 );
		this.getPage().setBounds( 120 , 100 , 100 , 60 );

	}

	@Override
	public void paint( Graphics g ){
		super.paint(g);
		int max_depth = Graph.position;
		for( int i = 0 ; i < max_depth ; i++ ){
			ArrayList<Edge> atDepth = this.getGraph().getEdges( i );
			for( int j = 0 ; j < atDepth.size() ; j++ ){
				atDepth.get(j).reset();
				atDepth.get(j).setY( atDepth.get(j).getY() + (atDepth.get(j).getY() * j) );
				atDepth.get(j).draw(g);
			}
		}

		Set<Arc> arcs = this.getGraph().getArcs();

		for( Arc arc : arcs){
			arc.draw(g);
		}

		// Maintenant mila mi-draw arc

	}

/// Getters and setters

	public void setGraph(Graph graph){
		this.graph = graph;
	}

	public Graph getGraph(){
		return this.graph;
	}

	public void setPage( String text ){
		this.page = new JButton(text);
	}
	public JButton getPage(){
		return this.page;		
	}

	public void setHost( String text ){
		this.host = new JButton(text);
	}
	public JButton getHost(){
		return this.host;		
	}

}