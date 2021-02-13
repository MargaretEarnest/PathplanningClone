package ithorian;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {


	public void start(Stage stage) throws IOException {
		MapManager mapManager = new MapManager("src/ithorian/MapPFaulkner1Nodes.csv", "src/ithorian/MapPFaulkner1Edges.csv", "src/ithorian/FaulknerFloor1.png");
		mapManager.init();
	}

	public static void main(String args[]) { launch(args); }
}
