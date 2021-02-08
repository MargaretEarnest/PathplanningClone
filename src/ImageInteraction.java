import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import java.util.ArrayList;
import java.util.List;

public class ImageInteraction {

	public static Group drawNodesOnImage(ArrayList<HospitalMap.Node> nodes, ArrayList<HospitalMap.Edge> edges, Group root, int scale) {
		for (HospitalMap.Edge edge : edges) {
			HospitalMap.Node start = getNode(nodes, edge.startNode);
			HospitalMap.Node end = getNode(nodes, edge.endNode);
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

	static HospitalMap.Node getNode(List<HospitalMap.Node> mapNodes, String id) {
		for (HospitalMap.Node node : mapNodes) {
			if (node.id.equals(id)) {
				return node;
			}
		}
		return null;
	}

	HospitalMap.Edge getEdge(List<HospitalMap.Edge> mapEdges, String id) {
		for (HospitalMap.Edge node : mapEdges) {
			if (node.id.equals(id)) {
				return node;
			}
		}
		return null;
	}
}
