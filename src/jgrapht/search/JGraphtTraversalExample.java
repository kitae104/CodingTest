package jgrapht.search;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.TraversalListenerAdapter;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class JGraphtTraversalExample {

	
	static class MyListener extends TraversalListenerAdapter<String, String> {
		
		SimpleDirectedGraph<String, String> g;
		private boolean newComponent;
		private String reference;
		public MyListener(SimpleDirectedGraph<String, String> g) {
			this.g = g;
		}
		
		@Override
		public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
			newComponent = true;
			System.out.println(newComponent);
		}
		
		@Override
		public void vertexTraversed(VertexTraversalEvent<String> e) {
			String vertex = e.getVertex();
			
			if (newComponent) {
				reference = vertex;
				newComponent = false;
			}
			
			int l = DijkstraShortestPath.findPathBetween( g, reference, vertex).getLength();
			String x = "";
			for (int i=0; i<l; i++) x+= "\t";
			System.out.println( x + "vertex: " + vertex);
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDirectedGraph<String,String> g = new SimpleDirectedGraph<String, String>(String.class);
		
		
		
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		
		
		g.addEdge("A", "B", "e1");
		g.addEdge("A", "C", "e2");
		g.addEdge("C", "D", "e3");
		
		
		GraphIterator<String, String> iterator = new DepthFirstIterator<String, String>(g);
		iterator.addTraversalListener( new MyListener( g));
		
		while (iterator.hasNext()) {
			//iterator.next();
			System.out.println( iterator.next() );
		}
	}

}