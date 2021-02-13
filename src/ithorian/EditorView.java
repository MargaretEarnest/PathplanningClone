package ithorian;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import java.util.HashMap;

public class EditorView {

	private Group root;
	private int scale;
	private HashMap<HospitalMap.Node, Circle> references = new HashMap<>();
	private MapManager mapManager;

	public EditorView(MapManager mapManager, ImageView baseImage, int scale, Group root) {
		this.mapManager = mapManager;
		this.root = mapManager.getRoot();
		this.scale = mapManager.getScale();
		root.getChildren().add(baseImage);
		for(HospitalMap.Node node : HospitalMap.nodes) {
			drawEdges(node);
			Circle circle = new Circle(node.xcoord / scale, node.ycoord / scale, 13 / scale);
			circle.setFill(Color.RED);
			circle.setOnMousePressed(e -> {
				onRightClick(e, node);
			});
			circle.setOnMouseEntered(t -> {
				Circle newCircle = (Circle) root.getChildren().get(root.getChildren().indexOf(circle));
				newCircle.setFill(Color.PINK);
			});

			circle.setOnMouseExited(t -> {
				Circle newCircle = (Circle) root.getChildren().get(root.getChildren().indexOf(circle));
				newCircle.setFill(Color.RED);
			});
			this.references.put(node, circle);
		}
		root.getChildren().addAll(references.values());
	}

	public void update() {
		//
	}

	private void onRightClick(MouseEvent e, HospitalMap.Node node) {
		if (e.getButton() == MouseButton.SECONDARY) {
			root.getChildren().remove(references.get(node));
			references.remove(node);
			mapManager.getMapEditor().removeNode(node);
			root.getChildren().removeIf(element -> element.getClass() == Line.class);
			for(HospitalMap.Node newNode : this.references.keySet()) { drawEdges(newNode);}
		}
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
