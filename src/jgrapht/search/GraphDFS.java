package jgrapht.search;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

/**
 * 무방향 그래프 깊이 우선 탐색
 * @author kitae
 *
 */
public class GraphDFS {

    public static void main(String[] args) {
    	DefaultUndirectedGraph<Integer, DefaultEdge> graph = 
            new DefaultUndirectedGraph <Integer, DefaultEdge>(DefaultEdge.class);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);


        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 7);
        graph.addEdge(3, 6);
        graph.addEdge(2, 5);
        graph.addEdge(2, 4);
        graph.addEdge(7, 8);
        graph.addEdge(6, 8);
        graph.addEdge(5, 8);
        graph.addEdge(4, 8);

        GraphIterator<Integer, DefaultEdge> iterator = new DepthFirstIterator<Integer, DefaultEdge>(graph);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " " );
        }
        System.out.println();
        
    }
}