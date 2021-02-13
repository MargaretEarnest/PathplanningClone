import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.InputStream;

public class MapManager {
	public int scale = 2; // scales image to 1/scale

	//MapPFaulkner1Nodes.csv
	HospitalMap map = new HospitalMap();
//    	map.generateElementFromData(ReadCSV.readFromFile("src/testmapNodes.csv"), ReadCSV.readFromFile("src/testmapEdges.csv"));
		map.generateElementFromData(ReadCSV.readFromFile("src/MapPFaulkner1Nodes.csv"), ReadCSV.readFromFile("src/MapPFaulkner1Edges.csv"));

//		System.out.println(DepthFirstSearch.performSearch(HospitalMap.nodesHash.get("N01"), HospitalMap.nodesHash.get("N13"), map));

	//System.out.println(HospitalMap.nodes);

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
	EditorView hander = new EditorView(HospitalMap.nodes, imageView, scale, root);
	Scene scene = new Scene(root, image.getWidth() / scale, image.getHeight() / scale);
		stage.setTitle("Floor Map");
		stage.setScene(scene);
		stage.show();

		System.out.println(HospitalMap.nodeToCSV(HospitalMap.nodes));

		System.out.println(HospitalMap.edgeToCSV(HospitalMap.edges));
}
