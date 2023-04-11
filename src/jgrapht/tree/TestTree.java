package jgrapht.tree;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestTree {

    public static void main(String[] args) throws IOException {
        Graph<String, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        Graphs.addEdgeWithVertices(graph, "Car", "Trunk");
        Graphs.addEdgeWithVertices(graph, "Trunk", "Engine");
        Graphs.addEdgeWithVertices(graph, "Engine", "Valve");
        Graphs.addEdgeWithVertices(graph, "Valve", "Bolt");
        Graphs.addEdgeWithVertices(graph, "Trunk", "Engine");
        Graphs.addEdgeWithVertices(graph, "Car", "Battery");

        CycleDetector<String, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
        if(cycleDetector.detectCycles())
            throw new RuntimeException("Graph cannot contain cycles, i.e. graph must be acyclic!");

        //Construct the Tree
        Tree<String> tree = Tree.of("Car");
        buildSubtree(graph, tree);

        System.out.println("Tree: \n"+tree);

        //=================================================================
        // GraphViz 출력
        //=================================================================
        System.out.println("== GraphViz 출력 ==");
        DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>();
        exporter.setVertexAttributeProvider((v) ->
        {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });

        // DOT 파일 생성
        Writer writer = new FileWriter(System.getProperty("user.dir") + "/src/jgrapht/tree/Tree.dot");
        exporter.exportGraph(graph, writer);

        // 문자열 형태로 출력
        Writer w = new StringWriter();
        exporter.exportGraph(graph, w);
        System.out.println(w.toString());

        // 그림 출력하기
        String cmd[] = new String[3];
        cmd[0] = "cmd.exe";
        cmd[1] = "/C";
        cmd[2] = "dot -Tpng ./src/jgrapht/tree/Tree.dot > ./src/jgrapht/tree/Tree.png";

        Runtime runTime = Runtime.getRuntime();
        Process process = runTime.exec(cmd);

    }

    private static void buildSubtree(Graph<String, DefaultEdge> graph, Tree<String> root){
        for(DefaultEdge arc : graph.outgoingEdgesOf(root.value)){
            Tree<String> child = root.addChild(graph.getEdgeTarget(arc));
            buildSubtree(graph, child);
        }
    }
}
