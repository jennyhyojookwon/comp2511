package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.tools.javac.comp.Check;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
//import sun.nio.cs.ext.ISCII91;
import javafx.stage.Stage;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.DungeonScreen;


/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    private Player player2;
    
    private DungeonScreen dungeonScreen;

    private Dungeon dungeon;
    private Boolean deleteFlag;
    private ArrayList<String> deleteID;
    
    private String fileString;
    private Boolean doorFlagBoolean;
    private Entity door;
    //
    //private Button reset;

    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.player2 = dungeon.getPlayer2();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setDungeonController(this);
        this.setDeleteFlag(false);
        this.deleteID = new ArrayList<String>();
        this.doorFlagBoolean = false;
    }
    
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        } // for background

        int i = 0;
        for (ImageView entity : initialEntities) {
        	//System.out.println("image " + i);
        	i++;
            squares.getChildren().add(entity);
        }

    }

    /*
     * replace closed door with opened door image
     */
    @FXML
    public void replaceEntityImage(Entity en) {
    	ImageView view = new ImageView(new Image("/open_door.png"));
    	String viewID = en.getPostionString();
    	System.out.println("delete this: " + viewID);
    	for (ImageView entity: initialEntities) {
    		if (entity.getId() != null && entity.getId().equals(viewID)) {
    			squares.getChildren().remove(entity);
    			squares.add(view, en.getX(), en.getY());
    		}
    	}
    }
    
    @FXML
    public void openDoorUp() {
    	Entity next_en = player.upEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door") || ((Door)next_en).checkOpen()==true) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    @FXML
    public void openDoorUp2() {
    	Entity next_en = player2.upEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door") || ((Door)next_en).checkOpen()==true) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    @FXML
    public void openDoorDown() {
    	Entity next_en = player.downEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door") || ((Door)next_en).checkOpen()==true) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    @FXML
    public void openDoorDown2() {
    	Entity next_en = player2.downEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door") || ((Door)next_en).checkOpen()==true) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    @FXML
    public void openDoorLeft() {
    	Entity next_en = player.leftEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door")) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    @FXML
    public void openDoorLeft2() {
    	Entity next_en = player2.leftEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door") || ((Door)next_en).checkOpen()==true) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    
    @FXML
    public void openDoorRight() {
    	Entity next_en = player.rightEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door") || ((Door)next_en).checkOpen()==true) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    @FXML
    public void openDoorRight2() {
    	Entity next_en = player2.rightEnt();
    	if(next_en == null || !next_en.getClass().getName().endsWith("Door") || ((Door)next_en).checkOpen()==true) {return;}
    	if(player.getBag().getKeys().isEmpty()) {return;}
    	if(player.KeyMatchDoor(next_en, player.getBag().getKeys())) {
    		replaceEntityImage(next_en);
    	}
    }
    
    /*
     * for key ** removeing key after collection
     */
    @FXML
    public void removeImageUp() {
    	Entity next_ent = player.upEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    @FXML
    public void removeImageUp2() {
    	Entity next_ent = player2.upEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    @FXML
    public void removeImageDown() {
    	Entity next_ent = player.downEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    @FXML
    public void removeImageDown2() {
    	Entity next_ent = player2.downEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    @FXML
    public void removeImageLeft() {
    	Entity next_ent = player.leftEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    @FXML
    public void removeImageLeft2() {
    	Entity next_ent = player2.leftEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    @FXML
    public void removeImageRight() {
    	Entity next_ent = player.rightEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    @FXML
    public void removeImageRight2() {
    	Entity next_ent = player2.rightEnt();
    	if(next_ent == null || !next_ent.getClass().getName().endsWith("Key")) {return;}
    	deltedEntityImage(next_ent);
    	return;
    }
    
    
    @FXML
    public void handleKeyPress(KeyEvent event) throws Exception {
    	if (player == null) {
    		if (player2 == null) {return;}
    	} else if (!player.getLife()) {
    		if (player2 != null && !player2.getLife()) {return;}
    	}
    	setDungeonScreen(dungeonScreen);
    	
    	switch (event.getCode()) {
        
        case UP:
        	if(player == null) {break;}
        	if (!player.getLife()) {break;}
        	openDoorUp();
            removeImageUp();
            player.moveUp();
            dungeon.setStart();
            break;
        case DOWN:
        	if(player == null) {break;}
        	if (!player.getLife()) {break;}
            openDoorDown();
            removeImageDown();
            player.moveDown();
            dungeon.setStart();
            break;
        case LEFT:
        	if(player == null) {break;}
        	if (!player.getLife()) {break;}
            openDoorLeft();
            removeImageLeft();
            player.moveLeft();
            dungeon.setStart();
            break;
        case RIGHT:
        	if(player == null) {break;}
        	if (!player.getLife()) {break;}
            openDoorRight();
            removeImageRight();
            player.moveRight();
            dungeon.setStart();
            break;
        case SPACE: // player use bomb
        	if(player == null) {break;}
        	if (!player.getLife()) {break;}
        	player.useBomb();
        	break;
        case W: //up for player2
        	if(player2 == null) {break;}
        	if (!player2.getLife()) {break;}
        	openDoorUp2();
        	removeImageUp2();
            player2.moveUp();
        	dungeon.setStart();
        	break;
        case A: //left for player2
        	if(player2 == null ) {break;}
        	if (!player2.getLife()) {break;}
        	removeImageLeft2();
            openDoorLeft2();
            player2.moveLeft();
        	dungeon.setStart();
        	break;
        case D: //right for player2
        	if(player2 == null) {break;}
        	if (!player2.getLife()) {break;}
        	openDoorRight2();
        	removeImageRight2();
            player2.moveRight();
        	dungeon.setStart();
        	break;
        case S: //down for player2
        	if(player2 == null) {break;}
        	if (!player2.getLife()) {break;}
        	removeImageDown2();
            openDoorDown2();
            player2.moveDown();
        	dungeon.setStart();
        	break;
        case B: // player2 use bomb
        	if(player2 == null) {break;}
        	if (!player2.getLife()) {break;}
        	player2.useBomb();
        	break;
        case R: // reset game
        	handleReset();
        	break;
        default:
            break;
        }
    	
    	
    	if (deleteFlag == true) {
    		for (String dString : deleteID) {
    			deltedEntityImage(dString);
    		}
    		deleteID.clear();
		}
    	if (doorFlagBoolean == true) {
    		if (door != null) {
    			replaceEntityImage(door);
    			doorFlagBoolean = false;
    		}
    		
    	}
    }
    
    public void setDoor(Entity door) {this.door = door;}
    
    public void setDoorFlag() {this.doorFlagBoolean = true;}
    
    public Player getPlayer() {return player;}
    
    @FXML
    public void handleReset() throws IOException {
    	

		DungeonScreen newDungeon = new DungeonScreen(DungeonApplication.getPrimaryStage(), DungeonApplication.getPrimaryStage().getTitle(),fileString);
		newDungeon.getController().setDungeonScreen(newDungeon);
		
		newDungeon.start();
    	System.out.println("-----DUNGEON RESET SUCCESSFUL!-----");

    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

    @FXML
    public void deltedEntityImage(Entity en) {
    	String viewID = en.getPostionString();
    	System.out.println("delete this: " + viewID);
    	for (ImageView entity: initialEntities) {
    		if (entity.getId() != null && entity.getId().equals(viewID)) {
    			squares.getChildren().remove(entity);
    		}
    	}
    }

    @FXML
    public void deltedEntityImage(String viewID) {
    	System.out.println("delete this: " + viewID);
    	for (ImageView entity: initialEntities) {
    		if (entity.getId() != null && entity.getId().equals(viewID)) {
    			squares.getChildren().remove(entity);
    		}
    	}
    	deleteFlag = false;
    	
    }
    
    
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public ArrayList<String> getDeleteID() {
		return deleteID;
	}

	
	public void deleteThis(String deleteID) {
		this.deleteID.add(deleteID);
		setDeleteFlag(true);
	}
	
	public void deleteThis(Entity entity) {
		deleteID.add(entity.getPostionString());
		setDeleteFlag(true);
	}

	public String getFileString() {
		return fileString;
	}

	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
	
}

