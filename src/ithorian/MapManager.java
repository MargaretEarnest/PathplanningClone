package ithorian;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MapManager {

	private String nodePath = null;
	private String edgePath = null;
	private String imagePath = null;
	private MapEditor mapEditor;

	public MapManager(String nodePath, String edgePath, String imagePath) throws FileNotFoundException {
		this.nodePath = nodePath;
		this.edgePath = edgePath;
		this.imagePath = imagePath;
		this.mapEditor = new MapEditor();
	}

	public void init() {
		int scale = 2; // scales image to 1/scale

		HospitalMap hospitalMap = new HospitalMap();

		hospitalMap.generateElementFromData(ReadCSV.readFromFile(nodePath), ReadCSV.readFromFile(edgePath));

		//creating the image object
		InputStream stream = null;
		try {
			stream = new FileInputStream(imagePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		new EditorView(this, imageView, scale, root);
		Scene scene = new Scene(root, image.getWidth() / scale, image.getHeight() / scale);

		Stage stage = new Stage();
		stage.setTitle("Floor Map");
		stage.setScene(scene);
		stage.show();
	}

	public MapEditor getMapEditor() {
		return mapEditor;
	}
}
