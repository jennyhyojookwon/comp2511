package unsw.dungeon;

import java.awt.Event;
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.*;

public class DungeonApplication extends Application {
	private static Stage primaryStage;
	Parent root;
	Scene scene;

	
	private void setPrimaryStage(Stage stage) {
        DungeonApplication.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return DungeonApplication.primaryStage;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	primaryStage.setTitle("Dungeon");
    	setPrimaryStage(primaryStage);

    	DungeonScreen dungeonscreen = new DungeonScreen(primaryStage, primaryStage.getTitle(),"advanced.json");

    	dungeonscreen.start();

    }

    public static void main(String[] args) {
    	launch(args);
        
    }

}
