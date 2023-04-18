package utils.graph;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class MakeGraph {

    public DefaultDirectedGraph<String, DefaultEdge> createGraph() throws IOException {

        DefaultDirectedGraph<String, DefaultEdge> g =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        String x1 = "x1";
        String x2 = "x2";
        String x3 = "x3";

        g.addVertex(x1);
        g.addVertex(x2);
        g.addVertex(x3);

        g.addEdge(x1, x2);
        g.addEdge(x2, x3);
        g.addEdge(x3, x1);

        return g;
    }

    public void createGraphViz(DefaultDirectedGraph<String, DefaultEdge> graph) throws IOException {
        System.out.println("== GraphViz 출력 ==");
        DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>();
        exporter.setVertexAttributeProvider((v) ->
        {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });

        // DOT 파일 생성
        Writer writer = new FileWriter(System.getProperty("user.dir") + "/graphs/graph.dot");
        exporter.exportGraph(graph, writer);

        // 문자열 형태로 출력
        Writer w = new StringWriter();
        exporter.exportGraph(graph, w);
        System.out.println(w.toString());
    }

    public void makeGraphImage() throws IOException {
        // 그림 출력하기
        String cmd[] = new String[3];
        cmd[0] = "cmd.exe";
        cmd[1] = "/C";
        cmd[2] = "dot -Tpng ./graphs/graph.dot > ./graphs/graph.png";

        Runtime runTime = Runtime.getRuntime();
        Process process = runTime.exec(cmd);
    }
}
