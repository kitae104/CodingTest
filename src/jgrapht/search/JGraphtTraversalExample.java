package jgrapht.search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.TraversalListenerAdapter;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class JGraphtTraversalExample
{

	static class MyListener extends TraversalListenerAdapter<String, String>
	{

		SimpleDirectedGraph<String, String> g;
		private boolean newComponent;
		private String reference;

		public MyListener(SimpleDirectedGraph<String, String> g)
		{
			this.g = g;
		}

		@Override
		public void connectedComponentStarted(ConnectedComponentTraversalEvent e)
		{
			newComponent = true;
			System.out.println(newComponent);
		}

		@Override
		public void vertexTraversed(VertexTraversalEvent<String> e)
		{
			String vertex = e.getVertex();

			if (newComponent)
			{
				reference = vertex;
				newComponent = false;
			}

			int l = DijkstraShortestPath.findPathBetween(g, reference, vertex).getLength();
			String x = "";
			for (int i = 0; i < l; i++)
				x += "\t";
			System.out.println(x + "vertex: " + vertex);
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		SimpleDirectedGraph<String, String> g = new SimpleDirectedGraph<String, String>(String.class);

		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");

		g.addEdge("A", "B", "e1");
		g.addEdge("A", "C", "e2");
		g.addEdge("C", "D", "e3");

		GraphIterator<String, String> iterator = new DepthFirstIterator<String, String>(g);
		iterator.addTraversalListener(new MyListener(g));

		while (iterator.hasNext())
		{
			// iterator.next();
			System.out.println(iterator.next());
		}

		// =================================================================
		// GraphViz 출력
		// =================================================================
		System.out.println("== GraphViz 출력 ==");
		DOTExporter<String, String> exporter = new DOTExporter<>();
		exporter.setVertexAttributeProvider((v) ->
		{
			Map<String, Attribute> map = new LinkedHashMap<>();
			map.put("label", DefaultAttribute.createAttribute(v.toString()));
			return map;
		});

		// DOT 파일 생성
		Writer writer = new FileWriter(System.getProperty("user.dir") + "/src/jgrapht/search/Graph.dot");
		exporter.exportGraph(g, writer);

		// 문자열 형태로 출력
		Writer w = new StringWriter();
		exporter.exportGraph(g, w);
		System.out.println(w.toString());

		// 그림 출력하기
		String cmd[] = new String[3];
		cmd[0] = "cmd.exe";
		cmd[1] = "/C";
		cmd[2] = "dot -Tpng ./src/jgrapht/search/Graph.dot > ./src/jgrapht/search/Graph.png";

		Runtime runTime = Runtime.getRuntime();
		Process process = runTime.exec(cmd);
	}

}