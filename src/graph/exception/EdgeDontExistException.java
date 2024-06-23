package graph.exception;

public class EdgeDontExistException extends Exception {
	public EdgeDontExistException( String edge ){
		super( "The Host : " + edge + " is not reachable " );
	}
}