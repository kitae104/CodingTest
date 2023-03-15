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



public class Ex2
{

	public static void main(String[] args) throws IOException
	{
		Node node0 = node("node0")
				.with(Records.of(rec("f0", ""), rec("f1", ""), rec("f2", ""), rec("f3", ""), rec("f4", ""))),
				node1 = node("node1").with(Records.of(turn(rec("n4"), rec("v", "719"), rec("")))),
				node2 = node("node2").with(Records.of(turn(rec("a1"), rec("805"), rec("p", "")))),
				node3 = node("node3").with(Records.of(turn(rec("i9"), rec("718"), rec("")))),
				node4 = node("node4").with(Records.of(turn(rec("e5"), rec("989"), rec("p", "")))),
				node5 = node("node5").with(Records.of(turn(rec("t2"), rec("v", "959"), rec("")))),
				node6 = node("node6").with(Records.of(turn(rec("o1"), rec("794"), rec("")))),
				node7 = node("node7").with(Records.of(turn(rec("s7"), rec("659"), rec(""))));
		Graph g = graph("example2").directed().graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
				.with(node0.link(between(port("f0"), node1.port("v", SOUTH)), between(port("f1"), node2.port(WEST)),
						between(port("f2"), node3.port(WEST)), between(port("f3"), node4.port(WEST)),
						between(port("f4"), node5.port("v", NORTH))),
						node2.link(between(port("p"), node6.port(NORTH_WEST))),
						node4.link(between(port("p"), node7.port(SOUTH_WEST))));
		Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("example/ex2.png"));

	}

}
