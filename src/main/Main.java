package main;

import graph.*;

import frame.*;
public class Main {
	public static void main(String[] args) throws Exception{
		try{
			Graph graph = new Graph(args[0]);
			new MainFrame( graph );
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		// Okey mety ilay izy

	}
}