package graph.exception;
import graph.*;

public class PageNotFoundException extends Exception{

	public PageNotFoundException(String file){
		// String exception = " The " + file + " page is not found in the url";
		super(" The " + file + " page is not found");
	}

	public PageNotFoundException(Edge edge, String file){
		// String exception = " The " + file + " page is not found in the ip : " + edge.getName();
		super(" The " + file + " page is not found in the ip : " + edge.getName());
	}


}