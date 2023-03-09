package jgrapht.gui;

import javax.swing.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.DepthFirstSearchAlgorithm;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Color;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.alg.interfaces.VertexTraversalListener;
import org.jgrapht.alg.interfaces.VertexTraversalListener.Colors;
import org.jgrapht.alg.interfaces.VertexTraversalListener.VisitingState;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class DepthFirstSearchGUIExample extends JFrame {

    private static final long serialVersionUID = -2006168557008324478L;

    public DepthFirstSearchGUIExample() {
        super("Depth-First Search with JGraphT and JGraphX");

        Graph<String, DefaultEdge> graph = createSampleGraph();

        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();

        mxGraph.getModel().beginUpdate();
        try {
            drawGraph(mxGraph, parent, graph);
        } finally {
            mxGraph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);

        getContentPane().add(graphComponent);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 320);
        setVisible(true);

        performDepthFirstSearch(graph);
    }

    private Graph<String, DefaultEdge> createSampleGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "E");

        return graph;
    }

    private void drawGraph(mxGraph mxGraph, Object parent, Graph<String, DefaultEdge> graph) {
        mxGraph.getStylesheet().getDefaultEdgeStyle().put("strokeColor", "black");

        for (String vertex : graph.vertexSet()) {
            mxGraph.insertVertex(parent, null, vertex, 50, 50, 50, 30);
        }

        for (DefaultEdge edge : graph.edgeSet()) {
            String sourceVertex = graph.getEdgeSource(edge);
            String targetVertex = graph.getEdgeTarget(edge);
            mxGraph.insertEdge(parent, null, "", mxGraph.getVertex(sourceVertex), mxGraph.getVertex(targetVertex));
        }
    }

    private void performDepthFirstSearch(Graph<String, DefaultEdge> graph) {
        DepthFirstSearchAlgorithm<String, DefaultEdge> dfs = new DepthFirstSearchAlgorithm<>(graph);

        VertexTraversalListener<String, DefaultEdge> traversalListener = new VertexTraversalListener<String, DefaultEdge>() {
            @Override
            public void vertexTraversed(String vertex, VisitingState state) {
                highlightVertex(vertex, state == VisitingState.DISCOVERED ? Colors.GREEN : Colors.BLUE);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void vertexFinished(String vertex) {
                highlightVertex(vertex, Colors.RED);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            dfs.addTraversalListener(traversalListener);

            VertexColoringAlgorithm.Coloring<String> coloring = dfs.getVertexColors();
            for (String vertex : coloring.getColoredVertices(Color.WHITE)) {
                dfs.traverse(vertex);
            }
        }

        private void highlightVertex(String vertex, Colors color) {
            mxGraphComponent graphComponent = (mxGraphComponent) getContentPane().getComponent(0);
            mxGraph mxGraph = graphComponent.getGraph();

            Object cell = mxGraph.getVertex(vertex);
            mxGraph.setCellStyles(mxGraph.STYLE_FILLCOLOR, color.getColor(), new Object[] { cell });
        }

        public static void main(String[] args) {
            new DepthFirstSearchGUIExample();
        }
    }
