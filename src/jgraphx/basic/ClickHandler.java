package jgraphx.basic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * 그래프에 항목 클릭시 해당 라벨 출력하기  
 * @author kitae
 *
 */
public class ClickHandler extends JFrame implements MouseListener
{
	private mxGraphComponent graphComponent;	// 그래프 컴포넌트 
	private mxGraph graph;						// 그래프 객체 

	public ClickHandler(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 320);
		setLocationRelativeTo(null);
		
		// 그래프 컴포넌트 만들기 
		mxGraphComponent graphComponent = makeGraph();				// 그래프 작성 
		add(graphComponent);										// 화면에 추가 
		graphComponent.getGraphControl().addMouseListener(this); 	// 마우스 리스터 등록  
		
		setVisible(true);
	}

	/** 
	 * 그래프 만들기 
	 * @return
	 */
	private mxGraphComponent makeGraph()
	{
		graph = new mxGraph();						// 그래프 생성 
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();						// 그래프 작성 시작 
		try {
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30,  "ROUNDED;strokeColor=red;fillColor=green");		// 노드 추가 
			Object v2 = graph.insertVertex(parent, null, "World", 240, 150, 80, 30, "defaultVertex;fillColor=blue");	// 노드 추가 
			graph.insertEdge(parent, null, "Edge", v1, v2);								// 간선 추가 
		} finally {
			graph.getModel().endUpdate();					// 그래프 작성 종료 
		}
		
		graphComponent = new mxGraphComponent(graph);
		return graphComponent;
	}

	public static void main(String[] args)
	{
		ClickHandler frame = new ClickHandler("Hello World");		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// 마우스 위치에 해당하는 셀의 라벨 출력  
		Object cell = graphComponent.getCellAt(e.getX(), e.getY());		// 마우스 위치 
		if(cell != null)
		{
			System.out.println("cell = " + graph.getLabel(cell));
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
