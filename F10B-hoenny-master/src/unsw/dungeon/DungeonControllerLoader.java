package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image enemyImage;
    private Image potionImage;
    private Image swordImage;
    private Image keyImage;
    private Image portalImage;
    private Image doorImage;
    private Image opendoorImage;
    private Image treasureImage;
    private Image exitImage;
    private Image boulderImage;
    private Image floorswitchImage;
    private Image bombImage;
    private Image player2Image;
    private Image enemyImage2;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        potionImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        keyImage = new Image("/key.png");
        portalImage = new Image("/portal.png");
        doorImage = new Image("/closed_door.png");
        opendoorImage = new Image("/open_door.png");
        treasureImage = new Image("/gold_pile.png");
        exitImage = new Image("/exit.png");
        boulderImage = new Image("/boulder.png");
        floorswitchImage = new Image("/pressure_plate.png");
        bombImage = new Image("/bomb.png");
        player2Image = new Image("/player2.png");
        enemyImage2 = new Image("/gnome.png");
    }

    @Override
    public void onLoad(Entity player) {
    	ImageView view;
    	String VID = player.getX() + " " + player.getY();
    	System.out.println("player this: " + VID);
    	if (player.getID() == 1) { 
    		view = new ImageView(playerImage);
    		//VID = "player1";
    	} else {
    		view = new ImageView(player2Image);
    		//VID = "player2";
    	}
    	view.setId(VID);
    	player.setPostionString(VID);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
		if (enemy.getID() == 2) {
			view = new ImageView(enemyImage2);
		}
		String stringID = enemy.getX() + " " + enemy.getY();
		view.setId(stringID);
		enemy.setPostionString(stringID);
		addEntity(enemy, view);
	}
	
	@Override
	public void onLoad(Potion potion) {
		ImageView view = new ImageView(potionImage);

        int id = potion.getX() + potion.getY();
		String stringID = String.valueOf(id);
		view.setId(stringID);
		potion.setPostionString(stringID);
	    addEntity(potion, view);
	}

	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);

		String id = sword.getX() + " " + sword.getY();
		//String stringID = String.valueOf(id);
		view.setId(id);
		sword.setPostionString(id);
		addEntity(sword, view);
	}

	@Override
	public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);

		String id = key.getX() +" " + key.getY();
		//String stringID = String.valueOf(id);
		view.setId(id);
		key.setPostionString(id);
		addEntity(key, view);
	}

	@Override
	public void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
		addEntity(portal, view);
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(doorImage);

		String id = door.getX() + " " + door.getY();
		//String stringID = String.valueOf(id);
		view.setId(id);
		door.setPostionString(id);
		addEntity(door, view);
	}
	
	//@Override
	//public void onLoad(OpenDoor door) {
	//    ImageView view = new ImageView(opendoorImage);
	//    addEntity(door, view);
	//}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
		
		String id = treasure.getX() + " " + treasure.getY();
		//String stringID = String.valueOf(id);
		view.setId(id);
		treasure.setPostionString(id);
		addEntity(treasure, view);
	}
	
	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
		addEntity(exit, view);
	}
	
	
	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
		addEntity(boulder, view);
	}
	
	@Override
	public void onLoad(FloorSwitch floorswitch) {
		ImageView view = new ImageView(floorswitchImage);
		addEntity(floorswitch, view);
	}
	
	@Override
	public void onLoad(Bomb bomb) {
		ImageView view = new ImageView(bombImage);
		String id = bomb.getX() +" "+ bomb.getY();
		//String stringID = String.valueOf(id);
		view.setId(id);
		bomb.setPostionString(id);
		addEntity(bomb, view);
	}
	
	
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

	

	


}
