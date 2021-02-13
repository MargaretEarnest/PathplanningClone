package ithorian;

import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorView {

	private final Group root;
	private final int scale;
	private final HashMap<HospitalMap.Node, Circle> references = new HashMap<>();
	private final MapManager mapManager;

	public EditorView(MapManager mapManager) {
		this.mapManager = mapManager;
		this.root = mapManager.getRoot();
		this.scale = mapManager.getScale();
		update();
	}

	public void update() {
		root.getChildren().clear();
		root.getChildren().add(mapManager.getImageView());
		for(HospitalMap.Node node : HospitalMap.nodes) {
			drawEdges(node);
		}
		root.getChildren().addAll(references.values());
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

	private Circle makeNodeCircle(HospitalMap.Node node) {
		Circle circle = new Circle();
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
		return circle;
	}

	/*
	Update function that compares a list of HospitalNodes to each Node key in the references HashMap
	If there is a hash map val that has been added to references, create a new node with a node location and circle for HospitalMap
	If there is an edited node, set the references location of the node to the Hospital map node
	If a node has been removed from the HashMap values, delete the node from the Hospital map array
	currently handles edits
	 */
	public void updateReferencesToCurrNodes() {
		// iterate through a list of hospital map nodes
		for (HospitalMap.Node refNode : references.keySet()) {
			for (HospitalMap.Node currNode : mapManager.getHospitalMapNodes()) {
				if (currNode.id == refNode.id) {
					// change coords to double?
					currNode.xcoord = (int) references.get(refNode).getCenterX();
					currNode.ycoord = (int) references.get(refNode).getCenterY();
				}
			}
		}
	}
}
