package jgrapht.basic;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.SupplierUtil;

public class CompleteGraphDemo {

	private static final int SIZE = 10;
	
	public static void main(String[] args) throws URISyntaxException {
		Supplier<String> vSupplier = new Supplier<String>() {
			private int id = 0; 
			@Override
			public String get() {
				return "v" + id++;
			}
			
		};

		// 그래프 생성 
		Graph<String, DefaultEdge> completeGraph = 
			new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

		CompleteGraphGenerator<String, DefaultEdge> completeGenerator =
				new CompleteGraphGenerator<>(SIZE);

		completeGenerator.generateGraph(completeGraph);

		Iterator<String> iter = new DepthFirstIterator<>(completeGraph);
		while(iter.hasNext()){
			String vertex = iter.next();
			System.out.println("Vertex " + vertex + " is connected to: "
					+ completeGraph.edgesOf(vertex).toString());
		}

		// 그래프 순회
		Graph<URI, DefaultEdge> hrefGraph = createHrefGraph();
		URI start = hrefGraph.vertexSet().stream()
						.filter(uri -> uri.getHost().equals("www.jgrapht.org"))
						.findAny()
						.get();
		
		Iterator<URI> iterator = new DepthFirstIterator<>(hrefGraph, start);
		while(iterator.hasNext()) {
			URI uri = iterator.next();
			System.out.println(uri);
		}
	}

	private static Graph<URI, DefaultEdge> createHrefGraph() throws URISyntaxException
    {

        Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        URI google = new URI("http://www.google.com");
        URI wikipedia = new URI("http://www.wikipedia.org");
        URI jgrapht = new URI("http://www.jgrapht.org");

        // add the vertices
        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        // add edges to create linking structure
        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);


        return g;
    }
}
