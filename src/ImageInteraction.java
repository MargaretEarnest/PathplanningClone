import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ImageHandler {

	private Group root;
	private int scale;
	private ImageView baseImage;
	private HashMap<Map.Node, Circle> nodes = new HashMap<>();
	private ArrayList<Map.Edge> edges;

	public ImageHandler(ArrayList<Map.Node> mapNodes, ImageView baseImage, int scale, Group root) {
		this.root = root;
//		this.edges = mapEdges;
		this.baseImage = baseImage;
		this.scale = scale;
		root.getChildren().add(baseImage);
		for(Map.Node node : mapNodes) {
			Circle circle = new Circle(node.xcoord / scale, node.ycoord / scale, 13 / scale);
			circle.setFill(Color.RED);
			circle.setOnMousePressed(e -> {
				if (e.getButton() == MouseButton.SECONDARY) {
					root.getChildren().remove(nodes.get(node));
					nodes.remove(node);
					drawNodesOnImage();
				}
			});
			circle.setOnMouseEntered(t -> {
				Circle newCircle = (Circle) root.getChildren().get(root.getChildren().indexOf(circle));
				newCircle.setFill(Color.PINK);
				drawNodesOnImage();
			});

			circle.setOnMouseExited(t -> {
				Circle newCircle = (Circle) root.getChildren().get(root.getChildren().indexOf(circle));
				newCircle.setFill(Color.RED);
				drawNodesOnImage();
			});
			this.nodes.put(node, circle);
		}
//		ArrayList<ReadCSV.MapEdge> modifiedEdges = new ArrayList<>(edges);
//		for (ReadCSV.MapEdge edge : this.edges) {
//			ReadCSV.MapNode start = getNode(nodes.keySet(), edge.startNode);
//			ReadCSV.MapNode end = getNode(nodes.keySet(), edge.endNode);
//			System.out.println(start + " " + end);
//			if(start != null && end != null) {
//				Line line = LineBuilder.create()
//						.startX(start.xcoord / scale)
//						.startY(start.ycoord / scale)
//						.endX(end.xcoord / scale)
//						.endY(end.ycoord / scale)
//						.stroke(Color.RED)
//						.strokeWidth(14 / scale)
//						.build();
//				root.getChildren().add(line);
//			}
//			edges = modifiedEdges;
//		}
		root.getChildren().addAll(nodes.values());
	}

	public Group drawNodesOnImage() {

//		for(Circle circle : nodes.values()) {
//			root.getChildren().add(circle);
//		}

		return root;
	}

	static ReadCSV.MapNode getNode(Set<ReadCSV.MapNode> mapNodes, String id) {
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
