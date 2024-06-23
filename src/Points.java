package frame;

public class Points{
	
	double x , y;

	int size = 10;

	public Points(){
		this.setX(50);
		this.setY(100);
	}

	public Points( double x , double y ){
		this.setX(x);
		this.setY(y);
	}

	public void setX( double x ){
		this.x = x;
	}
	public double getX(){
		return this.x;
	}
	public void setY(double y){
		this.y = y;
	}
	public double getY(){
		return this.y;
	}

	public void reset(){
		this.setX(50);
		this.setY(100);
	}

}