import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import java.util.ArrayList;
import java.util.List;

public class ImageInteraction {

	public static Group drawNodesOnImage(ArrayList<ReadCSV.MapNode> nodes, ArrayList<ReadCSV.MapEdge> edges, Group root, int scale) {
		for (ReadCSV.MapEdge edge : edges) {
			ReadCSV.MapNode start = getNode(nodes, edge.startNode);
			ReadCSV.MapNode end = getNode(nodes, edge.endNode);
			Line line = LineBuilder.create()
					.startX(start.xcoord / scale)
					.startY(start.ycoord / scale)
					.endX(end.xcoord / scale)
					.endY(end.ycoord / scale)
					.stroke(Color.RED)
					.strokeWidth(14 / scale)
					.build();
			root.getChildren().add(line);
		}
		return root;
	}

	static ReadCSV.MapNode getNode(List<ReadCSV.MapNode> mapNodes, String id) {
		for (ReadCSV.MapNode node : mapNodes) {
			if (node.id.equals(id)) {
				return node;
			}
		}
		return null;
	}

	ReadCSV.MapEdge getEdge(List<ReadCSV.MapEdge> mapEdges, String id) {
		for (ReadCSV.MapEdge node : mapEdges) {
			if (node.id.equals(id)) {
				return node;
			}
		}
		return null;
	}
}
