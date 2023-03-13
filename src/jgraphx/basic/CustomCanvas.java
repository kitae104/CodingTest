package jgraphx.basic;

import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

public class CustomCanvas extends JFrame
{
	public CustomCanvas(String title)
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
		// 그래프 생성
		mxGraph graph = new mxGraph() {
			
			public void drawState(mxICanvas canvas, mxCellState state, boolean drawLabel)
			{
				String label = (drawLabel) ? state.getLabel() : "";
				
				if (getModel().isVertex(state.getCell())
						&& canvas instanceof mxImageCanvas
						&& ((mxImageCanvas) canvas).getGraphicsCanvas() instanceof SwingCanvas)
				{
					((SwingCanvas) ((mxImageCanvas) canvas).getGraphicsCanvas()).drawVertex(state, label);
				}
				
				// Redirection of drawing vertices in SwingCanvas
				else if (getModel().isVertex(state.getCell()) && canvas instanceof SwingCanvas)
				{
					((SwingCanvas) canvas).drawVertex(state, label);
				}
				else
				{
					super.drawState(canvas, state, drawLabel);
				}
			}			
		};
		
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();						// 그래프 작성 시작 
		try {
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30,  "ROUNDED;strokeColor=red;fillColor=green");		// 노드 추가 
			Object v2 = graph.insertVertex(parent, null, "World", 240, 150, 80, 30, "defaultVertex;fillColor=blue");	// 노드 추가 
			graph.insertEdge(parent, null, "Edge", v1, v2);								// 간선 추가 
		} finally {
			graph.getModel().endUpdate();					// 그래프 작성 종료 
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph)
		{
			public mxInteractiveCanvas createCanvas() 
			{
				return new SwingCanvas(this);
			}
		};
		
		getContentPane().add(graphComponent);
		
		// 러버밴드 선택 추가
		new mxRubberband(graphComponent);		
		
		return graphComponent;
	}

	class SwingCanvas extends mxInteractiveCanvas
	{
		protected CellRendererPane rendererPane = new CellRendererPane();
		
		protected JLabel vertexRenderer = new JLabel();
		
		protected mxGraphComponent graphComponent;
		
		public SwingCanvas(mxGraphComponent graphComponent)
		{
			this.graphComponent = graphComponent;
			vertexRenderer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			vertexRenderer.setHorizontalAlignment(JLabel.CENTER);
			vertexRenderer.setBackground(graphComponent.getBackground().darker());
			vertexRenderer.setOpaque(true);
		}
		
		public void drawVertex(mxCellState state, String label)
		{
			vertexRenderer.setText(label);
			
			rendererPane.paintComponent(g, vertexRenderer, graphComponent,
					(int) (state.getX() + translate.getX()),
					(int) (state.getY() + translate.getY()),
					(int) state.getWidth(), (int) state.getHeight(), true);
		}
	}
	

	public static void main(String[] args)
	{
		CustomCanvas frame = new CustomCanvas("Custom Canvas");		
	}
}
