package graph;

import java.io.*;
import java.util.*;
import graph.exception.*;


public class Graph{

	List<Edge> edges;

	public static int position = 0;

	public void setEdges( List<Edge> edges ){
		this.edges = edges;
	}

	public List<Edge> getEdges(){
		return this.edges;
	}

	public void addEdge( Edge edge ){
		this.getEdges().add(edge);
	}

	public Graph( String file ){
		this.initGraph( file );
	}

	private String[] getEdgesFromFile( String filePath ) throws FileNotFoundException, IOException{
		FileReader reader = new FileReader( filePath );
		BufferedReader buffer = new BufferedReader( reader );
		String firstlLine = buffer.readLine();
		buffer.close();
		firstlLine = firstlLine.trim();
		String[] edge = firstlLine.split("[,]");
		return edge;
	}

	private String[] getArcsFromFile( String filePath ) throws FileNotFoundException, IOException {
		FileReader reader = new FileReader( filePath );
		BufferedReader buffer = new BufferedReader( reader );
		buffer.readLine(); // Read and skip first Line
		Vector<String> lines = new Vector<String>();
		String line = null;
		while( (line = buffer.readLine()) != null ){
			lines.add( line );
		}
		return lines.toArray(new String[ lines.size() ]);
	}

	public void initGraph( String filePath ){
		try{
			this.initEdge( filePath );
			this.initArcs( filePath );

			Set<Edge> temp_childs = new HashSet<Edge>();
			Set<Edge> toSet = new HashSet<Edge>();

			toSet.add( this.getEdges().get(0) );
			while( !toSet.isEmpty() ){
				Edge currentEdge = toSet.iterator().next();
				toSet.remove(currentEdge);
				temp_childs.remove(currentEdge);
				currentEdge.setDepth(Graph.position);
				currentEdge.wasSetted = true;
				// System.out.println("---------------> currentEdge : " + currentEdge + ", Position = " + position  + " <----------------------");
				List<Edge> c = currentEdge.getChilds();
				for( Edge e : c ){
					if( e.wasSetted == false ){
						temp_childs.add(e);
						// System.out.println("------> Edge : " + e + " <-------");
					}
				}
				// System.out.println();
				// System.out.println(temp_childs);
				if( toSet.size() == 0 ){
					// Ahoana no anaovana azy
					for( Edge e : temp_childs ){
						if( !toSet.contains(e) ){
							toSet.addAll( temp_childs );
						}
					}
					temp_childs = new HashSet<Edge>();
					Graph.position = Graph.position + 1;
				}
				// System.out.println(toSet);

			}


		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initEdge( String filePath ) throws FileNotFoundException, IOException, EdgeDontExistException{
		List<Edge> edgesList = new ArrayList<Edge>();
		String[] edges = this.getEdgesFromFile( filePath );
		for ( String string : edges ) {
			Edge edge = new Edge( string.trim() );
			edgesList.add( edge );
		}
		this.setEdges(edgesList);
	}

	private void initArcs( String filePath ) throws FileNotFoundException, IOException,EdgeDontExistException{
		String[] arcs = this.getArcsFromFile( filePath );
		for ( String arc : arcs ) {
			String[] components = arc.split(" ");
			Edge source = this.getEdge( components[0] );
			Edge destination = this.getEdge( components[1] );
			double distance = Double.valueOf( components[2] );
			String reachable = String.valueOf( components[3] );
			source.addArc( destination , distance , reachable);
			// destination.addArc( source , distance, reachable );
		}
	}

	public Edge getEdge( String edge ) throws EdgeDontExistException{
		for ( Edge e : this.getEdges() ) {
			if( e.getName().equalsIgnoreCase( edge.trim() ) ){
				return e;
			}
		}
		throw new EdgeDontExistException( edge );
	}

/// Solve part

	public Edge searchPath( String destination ) throws EdgeDontExistException{

		this.reset();
		List<Edge> edges = this.getEdges(); // not visited daholo ry zareo
		Edge source = this.getEdges().get(0);
		source.setDistanceFrom(0);
		PriorityQueue<Edge> edgess = new PriorityQueue<>( Comparator.comparingDouble( Edge::getDistanceFrom ) );
		edgess.add(source);
		List<Edge> path = new ArrayList<Edge>();
		Edge solution = null;
		while( !edgess.isEmpty() ){
			Edge edge = edgess.poll();
			edge.setVisited(true);
			if( edge.getName().equalsIgnoreCase(destination.trim()) ){
				solution = edge;
				break;
			}

			List<Arc> neighbors = edge.getArcs();
			for( Arc arc : neighbors ){
				Edge e = arc.getTo();
				double distance = edge.getDistanceFrom() + arc.getDistance();
				if( e.isVisited() == false && arc.isReachable() && distance < e.getDistanceFrom() ){
					e.setDistanceFrom(distance);
					e.setParent(edge);
					edgess.remove(e);
					edgess.add(e);
				}
			}
			path.add(edge);
		}

		// solution.lalana();
		if( solution != null ) return solution;
		throw new EdgeDontExistException( destination );

	}

	public Edge searchPath( String from, String destination ) throws EdgeDontExistException{

		this.reset();
		List<Edge> edges = this.getEdges(); // not visited daholo ry zareo
		Edge source = this.getEdge(from.trim());
		source.setDistanceFrom(0);
		PriorityQueue<Edge> edgess = new PriorityQueue<>( Comparator.comparingDouble( Edge::getDistanceFrom ) );
		edgess.add(source);
		List<Edge> path = new ArrayList<Edge>();
		Edge solution = null;
		while( !edgess.isEmpty() ){
			Edge edge = edgess.poll();
			edge.setVisited(true);
			if( edge.getName().equalsIgnoreCase(destination.trim()) ){
				solution = edge;
				break;
			}
			List<Arc> neighbors = edge.getArcs();
			for( Arc arc : neighbors ){
				Edge e = arc.getTo();
				double distance = edge.getDistanceFrom() + arc.getDistance();
				if( e.isVisited() == false && arc.isReachable() && distance < e.getDistanceFrom() ){
					e.setDistanceFrom(distance);
					e.setParent(edge);
					edgess.remove(e);
					edgess.add(e);
				}
			}
			path.add(edge);
		}

		// solution.lalana();
		if( solution != null ) return solution;
		throw new EdgeDontExistException( destination );

	}

	public Edge searchWebsite( String website ) throws EdgeDontExistException{

		this.reset();
		List<Edge> edges = this.getEdges(); // not visited daholo ry zareo
		Edge source = this.getEdges().get(0);
		source.setDistanceFrom(0);
		PriorityQueue<Edge> edgess = new PriorityQueue<>( Comparator.comparingDouble( Edge::getDistanceFrom ) );
		edgess.add(source);
		List<Edge> path = new ArrayList<Edge>();
		Edge dest = this.getEdge(website);
		Edge solution = null;
		boolean isFound = false;

		while( !edgess.isEmpty() ){
			Edge edge = edgess.poll();
			edge.setVisited(true);
			if( edge.getWebsites().containsKey(website) ){
				solution = edge;
				break;
			}

			List<Arc> neighbors = edge.getArcs();
			for( Arc arc : neighbors ){
				Edge e = arc.getTo();
				double distance = edge.getDistanceFrom() + arc.getDistance();
				if( e.isVisited() == false && arc.isReachable() && distance < e.getDistanceFrom() ){
					e.setDistanceFrom(distance);
					e.setParent(edge);
					edgess.remove(e);
					edgess.add(e);
				}
			}
			path.add(edge);
		}
		return solution;
	}

	public Edge searchWebsite( String from, String website ) throws EdgeDontExistException, PageNotFoundException{

		this.reset();
		List<Edge> edges = this.getEdges(); // not visited daholo ry zareo
		Edge source = this.getEdges().get(0);
		if( !from.equals("") && !from.isEmpty() ){
			source = this.getEdge(from);
		}
		source.setDistanceFrom(0);
		PriorityQueue<Edge> edgess = new PriorityQueue<>( Comparator.comparingDouble( Edge::getDistanceFrom ) );
		edgess.add(source);
		List<Edge> path = new ArrayList<Edge>();
		Edge solution = null;
		boolean isFound = false;
		System.out.println(edges);
		while( !edgess.isEmpty() ){
			Edge edge = edgess.poll();
			edge.setVisited(true);
			if( edge.getWebsites().containsKey(website) ){
				solution = edge;
				break;
			}
			List<Arc> neighbors = edge.getArcs();
			for( Arc arc : neighbors ){
				Edge e = arc.getTo();
				double distance = edge.getDistanceFrom() + arc.getDistance();
				if( e.isVisited() == false && arc.isReachable() && distance < e.getDistanceFrom() ){
					e.setDistanceFrom(distance);
					e.setParent(edge);
					edgess.remove(e);
					edgess.add(e);
				}
			}
			path.add(edge);
		}
		if( solution != null ) return solution;
		throw new PageNotFoundException(website);
	}


	public Edge testURL( String url ) throws EdgeDontExistException, PageNotFoundException{
		this.reset();
		List<Edge> edges = this.getEdges(); // not visited daholo ry zareo
		Edge source = this.getEdges().get(0);
		source.setDistanceFrom(0);
		PriorityQueue<Edge> edgess = new PriorityQueue<>( Comparator.comparingDouble( Edge::getDistanceFrom ) );
		edgess.add(source);
		List<Edge> path = new ArrayList<Edge>();

		String[] coordinates = url.split("/");
		String website = coordinates[1].trim();
		String destination = coordinates[0].trim();
		Edge solution = null;
		boolean isFound = false;

		while( !edgess.isEmpty() ){
			Edge edge = edgess.poll();
			edge.setVisited(true);
			if( edge.getName().equalsIgnoreCase(destination) ){
				solution = edge;
				break;
			}

			List<Arc> neighbors = edge.getArcs();
			for( Arc arc : neighbors ){
				Edge e = arc.getTo();
				double distance = edge.getDistanceFrom() + arc.getDistance();
				if( e.isVisited() == false && arc.isReachable() && distance < e.getDistanceFrom() ){
					e.setDistanceFrom(distance);
					e.setParent(edge);
					edgess.remove(e);
					edgess.add(e);
				}
			}
			path.add(edge);
		}
		
		if( solution != null ) return solution;
		throw new EdgeDontExistException( destination );
		// path.forEach( e -> System.out.println( e.getName() ) );
	}

	public boolean isAllVisited(){
		for (Edge e : this.getEdges()) {
			if( !e.isVisited() ) return false;
		}
		return true;
	}

/// Getters and setters

	public ArrayList<Edge> getEdges( int depth ){
		ArrayList<Edge> results = new ArrayList<Edge>();
		for( Edge e : this.getEdges() ){
			if( e.getDepth() == depth ){
				results.add( e );
			}
		}
		return results;
	}

	public Set<Arc> getArcs(){
		Set<Arc> results = new HashSet<Arc>();
		for( Edge edge : this.getEdges() ){
			List<Arc> arcs = edge.getArcs();
			for( Arc arc : arcs ){
				boolean exist = false;
				contains:
				for( Arc i : results ){
					if( i.equals( arc ) ){
						exist = true;
						break contains;
					}
				}
				if( !exist ) results.add(arc);
			}
		}
		return results;
	}

	public void reset(){
		for(Edge edge : this.getEdges()){
			edge.resets();
		}
	}

	public Edge getEdgeAt( int x, int y ){
		for( Edge edge : this.getEdges() ){
			if( edge.isInEdge( x, y ) ) return edge;
		}
		return null;
	}

/// Display part
	public void displayGraph(){
		List<Edge> edges = this.getEdges();
		for( Edge edge : edges ) {
			edge.display();
		}
	}




}