package jgraphx.basic;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.view.mxGraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.*;
import java.util.EventObject;

public class UserObject extends JFrame {
    public UserObject() {
        Document doc = mxDomUtils.createDocument();

        Element person1 = doc.createElement("Person");
        person1.setAttribute("firstName", "Daffy");
        person1.setAttribute("lastName", "Duck");

        Element person2 = doc.createElement("Person");
        person2.setAttribute("firstName", "Bugs");
        person2.setAttribute("lastName", "Bunny");

        Element relation = doc.createElement("Knows");
        relation.setAttribute("since", "1985");

        mxGraph graph = new mxGraph(){
            // 디스플레이에 셀 레이블을 제공하는 방법을 재정의합니다.
            @Override
            public boolean isCellEditable(Object cell) {
                return !getModel().isEdge(cell);
            }

            @Override
            public String convertValueToString(Object cell) {
                if(cell instanceof mxCell){
                    Object value = ((mxCell) cell).getValue();
                    if(value instanceof Element){
                        Element elt = (Element) value;
                        if(elt.getTagName().equalsIgnoreCase("Person")){
                            String firstName = elt.getAttribute("firstName");
                            String lastName = elt.getAttribute("lastName");

                            if(lastName != null && lastName.length() > 0){
                                return lastName + ", " + firstName;
                            }
                            return firstName;

                        }
                        else if(elt.getTagName().equals("Knows")){
                            return elt.getTagName() + " (Since " + elt.getAttribute("since") + ")";
                        }
                    }
                }
                return super.convertValueToString(cell);
            }


            // 모델에 셀 레이블을 저장하는 방법을 재정의합니다.
            @Override
            public void cellLabelChanged(Object cell, Object newValue, boolean autoSize) {
                if(cell instanceof mxCell && newValue != null) {
                    Object value = ((mxCell) cell).getValue();

                    if(value instanceof Node){
                        String label = newValue.toString();
                        Element elt = (Element) value;

                        if (elt.getTagName().equalsIgnoreCase("person")) {
                            int pos = label.indexOf(' ');

                            String firstName = (pos > 0) ? label.substring(0, pos).trim() : label;
                            String lastName = (pos > 0) ? label.substring(pos + 1, label.length()).trim() : "";

                            elt = (Element) elt.cloneNode(true);

                            elt.setAttribute("firstName", firstName);
                            elt.setAttribute("lastName", lastName);

                            newValue = elt;
                        }
                    }
                }
                super.cellLabelChanged(cell, newValue, autoSize);
            }
        };

        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try {
            Object v1 = graph.insertVertex(parent, null, person1, 20, 20, 80, 30);
            Object v2 = graph.insertVertex(parent, null, person2, 240, 150, 80, 30);
            Object e1 = graph.insertEdge(parent, null, relation, v1, v2);
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph) {

            public String getEditingValue(Object cell, EventObject trigger) {
                if (cell instanceof mxCell) {
                    Object value = ((mxCell) cell).getValue();

                    if (value instanceof Element) {
                        Element elt = (Element) value;

                        if (elt.getTagName().equalsIgnoreCase("Person")) {
                            String firstName = elt.getAttribute("firstName");
                            String lastName = elt.getAttribute("lastName");

                           return firstName + " " + lastName;
                        } else if(elt.getTagName().equalsIgnoreCase("Knows")){
                            return elt.getAttribute("since");
                        }
                    }
                }
                return super.getEditingValue(cell, trigger);
            }
        };

        getContentPane().add(graphComponent);

        graphComponent.setEnterStopsCellEditing(true);
    }
    public static void main(String[] args) {
        UserObject frame = new UserObject();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setLocationRelativeTo(null);
        frame.setTitle("JGraphX - User Object");
        frame.setVisible(true);
    }
}
