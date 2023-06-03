package graph;

import java.util.*;
import java.awt.*;


public class Arc{

	Edge from;
	Edge to;
	double distance;
	boolean reachable;

	public Arc(){

	}

	public Arc( Edge from, Edge to, double distance ){
		this.setFrom(from);
		this.setTo(to);
		this.setDistance(distance);
	}

	public Arc( Edge from, Edge to, double distance, String reach ){
		this.setFrom(from);
		this.setTo(to);
		this.setDistance(distance);
		this.setReachable(reach);
	}

	public void setDistance( double distance ){
		this.distance = distance;
	}

	public void setTo( Edge edge ){
		this.to = edge;
	}

	public void setFrom( Edge edge ){
		this.from = edge;
	}

	public Edge getTo(){
		return this.to;
	}
	public Edge getFrom(){
		return this.from;
	}

	public double getDistance(){
		return this.distance;
	}

	public void setReachable( boolean reachable ){
		this.reachable = reachable;
	}

	public void setReachable( String reachable ){
		this.setReachable( Boolean.valueOf(reachable) );
	}

	public boolean isReachable(){
		return this.reachable;
	}

	public void draw( Graphics graphics ){

		int x0 = (int) this.getFrom().getX() * ( this.getFrom().gap() ) + this.getFrom().getSize() ;
		int y0 = (int) this.getFrom().getY() + (this.getFrom().getSize() / 2);
		int x1 = (int) this.getTo().getX() * ( this.getTo().gap() );
		int y1 = (int) this.getTo().getY() + (this.getTo().getSize() / 2);
		if( !this.isReachable() ){
			graphics.setColor(Color.RED);
		}else{
			graphics.setColor( (this.isPath()) ? Color.GREEN : Color.BLACK );
		}

		graphics.drawLine( x0 , y0 , x1 , y1 );
		graphics.fillOval( x0 , y0 , 5 , 5 );
		graphics.fillOval( x1 , y1 , 5 , 5 );
		graphics.drawString( String.valueOf(this.getDistance()) , (x0 + x1) / 2 , ( y0 + y1 ) / 2 );
	}

/// Overrided methods

	public boolean equals( Arc arc ){
		if( this.getFrom().getName().trim().equalsIgnoreCase( arc.getTo().getName().trim() ) && this.getTo().getName().trim().equalsIgnoreCase( arc.getFrom().getName().trim() )  ){
			return true;
		}
		return false;
	}

	public boolean isPath(){
		return ( this.getFrom().isPath && this.getTo().isPath );
	}

}