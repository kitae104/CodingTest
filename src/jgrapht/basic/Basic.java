package jgrapht.basic;

import java.net.URI;
import java.net.URISyntaxException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Basic {

	public static void main(String[] args) throws URISyntaxException {
		Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class); 
		
		URI google = new URI("http://www.google.com");
        URI wikipedia = new URI("http://www.wikipedia.org");
        URI jgrapht = new URI("http://www.jgrapht.org");
        
        // 정점 추가 
        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);
        
        // 간선 추가 
        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);
        
        URI start = g.vertexSet().stream()
        		.filter(uri -> uri.getHost().equals("www.jgrapht.org"))
        		.findAny()
        		.get();
        
        System.out.println(start.getHost());
        System.out.println(g.getEdgeTarget(g.getEdge(start, wikipedia)));
	}

}
