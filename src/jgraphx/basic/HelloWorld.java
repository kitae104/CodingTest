package jgraphx.basic;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class HelloWorld extends JFrame
{
	public HelloWorld(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 320);
		setLocationRelativeTo(null);
		
		// 그래프 컴포넌트 만들기 
		mxGraphComponent graphComponent = makeGraph();
		add(graphComponent);
		
		setVisible(true);
	}

	/** 
	 * 그래프 만들기 
	 * @return
	 */
	private mxGraphComponent makeGraph()
	{
		mxGraph graph = new mxGraph();						// 그래프 생성 
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();						// 그래프 작성 시작 
		try {
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30,  "ROUNDED;strokeColor=red;fillColor=green");		// 노드 추가 
			Object v2 = graph.insertVertex(parent, null, "World", 240, 150, 80, 30, "defaultVertex;fillColor=blue");	// 노드 추가 
			graph.insertEdge(parent, null, "Edge", v1, v2);								// 간선 추가 
		} finally {
			graph.getModel().endUpdate();					// 그래프 작성 종료 
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		return graphComponent;
	}

	public static void main(String[] args)
	{
		HelloWorld frame = new HelloWorld("Hello World");		
	}
}
