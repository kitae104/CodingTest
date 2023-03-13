package jgraphx.basic;

import java.util.Map;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;

public class Port extends JFrame
{
	
	final int PORT_DIAMETER = 20;
	final int PORT_RADIUS = PORT_DIAMETER / 2;
	
	public Port(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 320);
		setLocationRelativeTo(null);
		
		// 그래프 컴포넌트 만들기 
		mxGraphComponent graphComponent = makeGraph();
		graphComponent.setToolTips(true);
		add(graphComponent);
		
		setVisible(true);
	}

	/** 
	 * 그래프 만들기 
	 * @return
	 */
	private mxGraphComponent makeGraph()
	{
		mxGraph graph = new mxGraph()						// 그래프 생성
		{
			// 포트는 에지의 터미널로 사용되지 않으며 그래픽 연결 지점을 계산하는 데만 사용.
			public boolean isPort(Object cell)
			{
				mxGeometry geo = getCellGeometry(cell);
				
				return (geo != null) ? geo.isRelative() : false;
			}
			
			// 에지의 실제 소스 및 대상을 표시하는 툴팁을 구현
			public String getToolTipForCell(Object cell)
			{
				if (model.isEdge(cell))
				{
					return convertValueToString(model.getTerminal(cell, true)) + " -> " + convertValueToString(model.getTerminal(cell, false));
				}
				
				return super.getToolTipForCell(cell);
			}
			
			// 접기 아이콘을 제거하고 접기를 비활성화
			public boolean isCellFoldable(Object cell, boolean collapse)
			{
				return false;
			}
		};
		
		// 기본 간선 스타일 설정 
		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.ElbowConnector);
		
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();						// 그래프 작성 시작 
		try {
			
			mxCell v1 = (mxCell) graph.insertVertex(parent, null, "Hello", 20,	20, 100, 100, "");
			v1.setConnectable(false);
			mxGeometry geo = graph.getModel().getGeometry(v1);
			
			// 빼기 기호를 클릭했을 때 사각형의 크기
			geo.setAlternateBounds(new mxRectangle(20, 20, 100, 50));
			
			mxGeometry geo1 = new mxGeometry(0, 0.5, PORT_DIAMETER,	PORT_DIAMETER);
			
			// 원점은 왼쪽 위 모서리에 있기 때문에 포트 중심을 올바르게 배치하려면 변환
			geo1.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
			geo1.setRelative(true);
			
			mxCell port1 = new mxCell(null, geo1, "shape=ellipse;perimter=ellipsePerimeter");
			port1.setVertex(true);
			
			mxGeometry geo2 = new mxGeometry(1.0, 0.5, PORT_DIAMETER, PORT_DIAMETER);
			geo2.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
			geo2.setRelative(true);

			mxCell port2 = new mxCell(null, geo2, "shape=ellipse;perimter=ellipsePerimeter");
			port2.setVertex(true);

			graph.addCell(port1, v1);
			graph.addCell(port2, v1);

			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
			
			graph.insertEdge(parent, null, "Edge", port2, v2);
						
		} finally {
			graph.getModel().endUpdate();					// 그래프 작성 종료 
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		return graphComponent;
	}

	public static void main(String[] args)
	{
		Port frame = new Port("Port");		
	}
}
