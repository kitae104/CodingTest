package jgrapht.basic;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GraphAlgorithm
{

	public static void main(String[] args)
	{
		// 유향 그래프 생성 
        Graph<String, DefaultEdge> directedGraph =
            new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("c");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.addVertex("f");
        directedGraph.addVertex("g");
        directedGraph.addVertex("h");
        directedGraph.addVertex("i");
        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "d");
        directedGraph.addEdge("d", "c");
        directedGraph.addEdge("c", "a");
        directedGraph.addEdge("e", "d");
        directedGraph.addEdge("e", "f");
        directedGraph.addEdge("f", "g");
        directedGraph.addEdge("g", "e");
        directedGraph.addEdge("h", "e");
        directedGraph.addEdge("i", "h");

        // 강 결합 요소 구하기 
        StrongConnectivityAlgorithm<String, DefaultEdge> scAlg = 
        		new KosarajuStrongConnectivityInspector<>(directedGraph);
        
        List<Graph<String, DefaultEdge>> stronglyConnectedSubgraphs = scAlg.getStronglyConnectedComponents();
        System.out.println(stronglyConnectedSubgraphs);
        
        stronglyConnectedSubgraphs.stream().forEach(System.out::println);
        
        // 최단 경로 출력
        System.out.println("최단 경로 : ");
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(directedGraph);
        SingleSourcePaths<String, DefaultEdge> iPaths = dijkstraAlg.getPaths("i");
        System.out.println(iPaths.getPath("c") + "\n");
        
        System.out.println("최단 경로2 : ");
        SingleSourcePaths<String, DefaultEdge> cPaths = dijkstraAlg.getPaths("a");
        System.out.println(cPaths.getPath("d") + "\n");
	}

}
