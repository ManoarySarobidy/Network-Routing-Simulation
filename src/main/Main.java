package main;

import graph.*;
import graph.exception.*;
import java.util.*;
import java.io.*;
import frame.*;
public class Main {
	public static void main(String[] args) throws Exception{
		try{
			Graph graph = new Graph(args[0]);
			MainFrame frame = new MainFrame( graph );
			// graph.initGraph( args[0] );
			// graph.displayGraph();
			// String[] destination = args[1].split("/");
			// String ip = destination[0].trim();
			// String page = destination[1].trim();
			// String from = args[2];
			// Edge solution = graph.searchPath( from, ip);
			// Okey inona ny manaraka
			// solution.lalana();
			// if( solution.getWebsites().containsKey(page) ){
			// 	File f = solution.getWebsites().get(page);
			// }else{
			// 	throw new PageNotFoundException( solution, page );
			// }
		}catch (Exception e) {
			e.printStackTrace();
		}
		// Okey mety ilay izy

	}
}