package jgrapht.search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

/**
 * 무방향 그래프 깊이 우선 탐색
 * 
 * @author kitae
 *
 */
public class GraphDFS2
{

	public static void main(String[] args) throws IOException
	{
		DefaultUndirectedGraph<Integer, DefaultEdge> graph = new DefaultUndirectedGraph<Integer, DefaultEdge>(
				DefaultEdge.class);

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
		while (iterator.hasNext())
		{
			System.out.print(iterator.next() + " ");
		}
		System.out.println();

		//=================================================================
		// GraphViz 출력
		//=================================================================
		System.out.println("== GraphViz 출력 ==");
		DOTExporter<Integer, DefaultEdge> exporter = new DOTExporter<>();
		exporter.setVertexAttributeProvider((v) ->
		{
			Map<String, Attribute> map = new LinkedHashMap<>();
			map.put("label", DefaultAttribute.createAttribute(v.toString()));
			return map;
		});

		// DOT 파일 생성
		Writer writer = new FileWriter(System.getProperty("user.dir") + "/src/jgrapht/search/GraphDFS.dot");
		exporter.exportGraph(graph, writer);

		// 문자열 형태로 출력
		Writer w = new StringWriter();
		exporter.exportGraph(graph, w);
		System.out.println(w.toString());

		// 그림 출력하기 
		String cmd[] = new String[3];
		cmd[0] = "cmd.exe";
		cmd[1] = "/C";
		cmd[2] = "dot -Tpng ./src/jgrapht/search/GraphDFS.dot > ./src/jgrapht/search/GraphDFS.png";

		Runtime runTime = Runtime.getRuntime();
		Process process = runTime.exec(cmd);
		
	}
}