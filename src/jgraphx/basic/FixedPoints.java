package jgraphx.basic;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class FixedPoints extends JFrame
{
	
	public FixedPoints(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 320);
		setLocationRelativeTo(null);
		
		// 그래프 컴포넌트 만들기 
		mxGraphComponent graphComponent = makeGraph();				// 그래프 작성 
		add(graphComponent);										// 화면에 추가 
		
		setVisible(true);
	}
	
	
	private mxGraphComponent makeGraph()
	{
		mxGraph graph = new mxGraph();						// 그래프 생성 
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();						// 그래프 작성 시작 
		try 
		{
			// 정점의 모양과 간선의 모양 설정 
			Object v1 = graph.insertVertex(parent, null, "Hello,", 20, 20, 80, 60, "shape=triangle;perimeter=trianglePerimeter");
			Object v2 = graph.insertVertex(parent, null, "World!", 200, 150,80, 60, "shape=ellipse;perimeter=ellipsePerimeter");
			Object v3 = graph.insertVertex(parent, null, "Hello,", 200, 20, 80, 30);
			Object e1 = graph.insertEdge(parent, null, "", v1, v2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
							+ "exitX=0.5;exitY=1;exitPerimeter=1;entryX=0;entryY=0;entryPerimeter=1;");							// 간선 추가 
			Object e2 = graph.insertEdge(parent, null, "", v3, v2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;orthogonal=0;"
							+ "entryX=0;entryY=0;entryPerimeter=1;");
		} 
		finally 
		{
			graph.getModel().endUpdate();					// 그래프 작성 종료 
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		return graphComponent;
	}


	public static void main(String[] args)
	{
		FixedPoints frame = new FixedPoints("FixedPoints");		
	}

}
