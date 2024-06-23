package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.Graphics;
import frame.*;
import java.util.Vector;
import java.util.Collections;

public class Edge extends Points{

	public final static String WEBSITE_FOLDER = "Website";

/// Attribute declaration

	boolean visited = false;
	double distanceFrom = Integer.MAX_VALUE;
	Edge parent = null;
	HashMap< String, File > websites = new HashMap<String, File>();
	int depth = 0;
	List<Arc> arcs;
	public boolean wasSetted = false;
	String name;

	public boolean isPath = false;

	public Edge(){
		super();
		this.setArcs();
	}

	public Edge( String name ){
		super();
		this.setName( name );
		this.init();
	}

	public void setVisited( boolean b ){
		this.visited = b;
	}

	public boolean isVisited(){
		return this.visited;
	}

	private void init(){
		this.setArcs();
		this.initializeWebsite();
	}

	public void setName( String name ){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}

	public void setArcs(){
		this.arcs = new ArrayList<Arc>();
	}
	public List<Arc> getArcs(){
		return this.arcs;
	}

	public void addArc(Edge edge , double distance){
		Arc arc = new Arc( this, edge, distance );
		this.getArcs().add(arc);
	}

	public void addArc(Edge edge , double distance, String reach){
		Arc arc = new Arc( this, edge, distance , reach);
		this.getArcs().add(arc);
	}

	public void setParent(Edge edge){
		this.parent = edge;
	}

	public Edge getParent(){
		return this.parent;
	}

	public void setDistanceFrom( double distance ){
		this.distanceFrom = distance;
	}

	public double getDistanceFrom(){
		return this.distanceFrom;
	}

/// Initialize Websites

	public void initializeWebsite() {
		try{
			
			File file = new File( Edge.WEBSITE_FOLDER + File.separator + this.getName() );
			System.out.println(file.getAbsolutePath());
			// System.out.println(file);
			if( file.isDirectory() ){
				File[] files = file.listFiles();
				for( File f : files ){
					this.addWebsite( f.getName().substring( 0 , f.getName().lastIndexOf(".") ) , f );
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Edge> getChilds(){
		ArrayList<Edge> childs = new ArrayList<Edge>();
		for( Arc arc : this.getArcs() ){
			childs.add( arc.getTo() );
		}
		return childs;
	}

	public HashMap<String, File> getWebsites(){
		return this.websites;
	}

	public void addWebsite( String website, File file ){
		// this.getWebsites().add( website );
		this.getWebsites().put( website , file );
	}

	public void updateChildDistance(){
		for( Arc arc : this.getArcs() ){
			double distance = this.getDistanceFrom() + arc.getDistance();
			if( arc.getTo().isVisited() == false && distance <= arc.getTo().getDistanceFrom() ){
				arc.getTo().setParent(this);
				arc.getTo().setDistanceFrom(distance);
			}
		}
	}

	public void setDepth( int depths ){
		// if( this.wasSetted == false ){
		// 	this.depth = depths;
		// 	this.wasSetted = true;
		// }
		this.depth = depths;
	}

	public int getDepth(){
		return this.depth;
	}

	// @Override
	public boolean equals( Edge edge ){
		return this.getName().equals( edge.getName().trim() );
	}

	public File getPage( String file ){
		return this.getWebsites().get(file);
	}


/// Display Part
	public void display(){
		String dis = this.getName() + " depth " + this.getDepth();
		// for ( Arc arc : this.getArcs() ) {
		// 	dis += " -> " + arc.getTo().getName() + " depth " + arc.getTo().getDepth();	
		// }
		System.out.println(dis);
	}

	public String getPath(){
		Vector<Edge> path = new Vector<Edge>();
		Edge current = this;
		while( current != null ){
			path.add(current);
			current.isPath = true;
			current = current.getParent();
		}
		Collections.reverse( path );
		String paths = "";
		for ( Edge p : path ) {
			paths = paths + " " + p.getName() + " distance = " + p.getDistanceFrom();
		}
		return paths;
	}

	@Override
	public String toString(){
		return this.getName() + " ---- " + this.getDepth();
	}

/// Drawing Section
	public int gap(){
		int g = 4;
		return ( 1 + this.getDepth() * g);
	}


	public void draw( Graphics graphics ){
		// int size = 50;
		// this.reset();
		graphics.setColor( ( this.isPath ) ? java.awt.Color.GREEN : java.awt.Color.BLACK );
		graphics.drawString( this.getName().substring( this.getName().lastIndexOf(".") ) , (int) this.getX() * ( this.gap() ) , (int) this.getY() );
		graphics.fillOval( (int) this.getX() * ( this.gap() ) , (int) this.getY() , this.getSize() , this.getSize()  );
		// graphics
	}

	public int getSize(){
		return 50;
	}

	public void resets(){
		this.isPath = false;
		this.setVisited(false);
		this.setDistanceFrom( Integer.MAX_VALUE );
	}

	public boolean isInEdge( int x, int y ){
		int x0 = (int) this.getX() * this.gap();
		int y0 = (int) this.getY();
		return ( x >= x0 && x <= x0 + this.getSize() && y >= y0 && y <= y0 + this.getSize() );

	}

}