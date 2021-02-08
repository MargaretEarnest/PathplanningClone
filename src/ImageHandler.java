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
	private HashMap<HospitalMap.Node, Circle> nodes = new HashMap<>();

	public ImageHandler(ArrayList<HospitalMap.Node> mapNodes, ImageView baseImage, int scale, Group root) {
		this.root = root;
		this.baseImage = baseImage;
		this.scale = scale;
		root.getChildren().add(baseImage);
		for(HospitalMap.Node node : mapNodes) {
			drawEdges(node);
			Circle circle = new Circle(node.xcoord / scale, node.ycoord / scale, 13 / scale);
			circle.setFill(Color.RED);
			circle.setOnMousePressed(e -> {
				if (e.getButton() == MouseButton.SECONDARY) {
					root.getChildren().remove(nodes.get(node));
					for(HospitalMap.Node neighbor : node.connectedNodes) {
						neighbor.connectedNodes.remove(node);
					}
					nodes.remove(node);
					root.getChildren().removeIf(element -> element.getClass() == Line.class);
					for(HospitalMap.Node newNode : this.nodes.keySet()) { drawEdges(newNode);}
				}
			});
			circle.setOnMouseEntered(t -> {
				Circle newCircle = (Circle) root.getChildren().get(root.getChildren().indexOf(circle));
				newCircle.setFill(Color.PINK);
			});

			circle.setOnMouseExited(t -> {
				Circle newCircle = (Circle) root.getChildren().get(root.getChildren().indexOf(circle));
				newCircle.setFill(Color.RED);
			});
			this.nodes.put(node, circle);
		}
		root.getChildren().addAll(nodes.values());
	}

	private void drawEdges(HospitalMap.Node parent) {
		for (HospitalMap.Node child : parent.connectedNodes) {
				Line line = LineBuilder.create()
						.startX(parent.xcoord / scale)
						.startY(parent.ycoord / scale)
						.endX(child.xcoord / scale)
						.endY(child.ycoord / scale)
						.stroke(Color.RED)
						.strokeWidth(14 / scale)
						.build();
				root.getChildren().add(line);
		}
	}
}
