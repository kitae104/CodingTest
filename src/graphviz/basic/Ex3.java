package graphviz.basic;

import static guru.nidi.graphviz.attribute.Records.rec;
import static guru.nidi.graphviz.attribute.Records.turn;
import static guru.nidi.graphviz.model.Compass.NORTH;
import static guru.nidi.graphviz.model.Compass.NORTH_WEST;
import static guru.nidi.graphviz.model.Compass.SOUTH;
import static guru.nidi.graphviz.model.Compass.SOUTH_WEST;
import static guru.nidi.graphviz.model.Compass.WEST;

import java.io.File;
import java.io.IOException;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Records;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.*;		// node, between 등 포함 



public class Ex3
{

	public static void main(String[] args) throws IOException
	{
		Node node0 = node("node0"), node1 = node("node1"), node2 = node("node2");
		

		Graph g = graph("example3").directed().graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
				.linkAttr().with("class", "link-class")		        
				.with(node0.link(node1), node0.link(node2));

		Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("example/ex3.png"));

	}

}
