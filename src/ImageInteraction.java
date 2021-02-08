import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import java.util.ArrayList;
import java.util.List;

public class ImageInteraction {

	public static Group drawNodesOnImage(ArrayList<Map.Node> nodes, ArrayList<Map.Edge> edges, Group root, int scale) {
		for (Map.Edge edge : edges) {
			Map.Node start = getNode(nodes, edge.startNode);
			Map.Node end = getNode(nodes, edge.endNode);
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

	static Map.Node getNode(List<Map.Node> mapNodes, String id) {
		for (Map.Node node : mapNodes) {
			if (node.id.equals(id)) {
				return node;
			}
		}
		return null;
	}

	Map.Edge getEdge(List<Map.Edge> mapEdges, String id) {
		for (Map.Edge node : mapEdges) {
			if (node.id.equals(id)) {
				return node;
			}
		}
		return null;
	}
}
