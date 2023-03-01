package jgrapht.graph;

import java.util.Iterator;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.SupplierUtil;

public class CompleteGraphEx
{
	private static final int SIZE = 10; // 정점 갯수

	public static void main(String[] args)
	{
		// Create the VertexFactory so the generator can create vertices
		Supplier<String> vSupplier = new Supplier<String>()
		{
			private int id = 0;

			@Override
			public String get()
			{
				return "v" + id++;
			}
		};

		// 그래프 객체 생성
		Graph<String, DefaultEdge> completeGraph = new SimpleGraph<>(vSupplier,
				SupplierUtil.createDefaultEdgeSupplier(), false);

		// CompleteGraphGenerator 객체 생성
		CompleteGraphGenerator<String, DefaultEdge> completeGenerator = new CompleteGraphGenerator<>(SIZE);

		// CompleteGraphGenerator 개체를 사용하여 완전 그래프 생성
		completeGenerator.generateGraph(completeGraph);

		Iterator<String> iter = new DepthFirstIterator<>(completeGraph);
		while (iter.hasNext())
		{
			String vertex = iter.next();
			System.out.println("Vertex " + vertex + " is connected to: " + completeGraph.edgesOf(vertex).toString());
		}
	}
}
