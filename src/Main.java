import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main extends Application {
	// Lists that hold arrays of map nodes and edges based on csv input
	ArrayList<ReadCSV.MapNode> mapNodes = new ArrayList<>();
	ArrayList<ReadCSV.MapEdge> mapEdges = new ArrayList<>();

	public int scale = 2; // scales image to 1/scale

	public void start(Stage stage) throws IOException {
		mapNodes = ReadCSV.readFromFile("src/MapPFaulkner1Nodes.csv", ReadCSV.MapNode.class);
		mapEdges = ReadCSV.readFromFile("src/MapPFaulkner1Edges.csv", ReadCSV.MapEdge.class);
		System.out.println(mapNodes);
		System.out.println(mapEdges);
		//creating the image object
		InputStream stream = new FileInputStream("src/FaulknerFloor1.png");
		Image image = new Image(stream);
		//Creating the image view
		ImageView imageView = new ImageView();
		//Setting image to the image view
		imageView.setImage(image);
		//Setting the image view parameters
		imageView.setX(0);
		imageView.setY(0);
		imageView.setFitWidth(image.getWidth() / scale);
		imageView.setPreserveRatio(true);
		//Setting the Scene object
		Group root = new Group();
		ImageHandler hander = new ImageHandler(mapNodes, mapEdges, imageView, scale, root);
		root = hander.drawNodesOnImage();
		Scene scene = new Scene(root, image.getWidth() / scale, image.getHeight() / scale);
		stage.setTitle("Floor Map");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}
