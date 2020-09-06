package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Random;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        if (jsonGoals.getString("goal").equals("OR")) {
        	Composite goalsComposite = new Composite("OR", null);
        	dungeon.setComposite(goalsComposite);
        	loadGoals( dungeon, jsonGoals, goalsComposite);
        } else if (jsonGoals.getString("goal").equals("AND")) {
        	Composite goalsComposite = new Composite("AND", null);
        	dungeon.setComposite(goalsComposite);
        	loadGoals( dungeon, jsonGoals, goalsComposite);
        } else {
        	Goal onegoal = new Goal(jsonGoals.getString("goal"), null);
        	dungeon.setGoal(onegoal);
        	System.out.println(jsonGoals.getString("goal"));
        }
        
        calculateTreasure(dungeon);
        
        
        return dungeon;
    }

    private void loadGoals( Dungeon dungeon, JSONObject jsonGoals, Composite upone) {
		String s = jsonGoals.getString("goal");
		System.out.println(s);
		if (s.equals("AND")) {
			//System.out.println("11111");
			Composite oneDownComposite = new Composite("AND", upone);
			//System.out.println("ANDdddddddinf");
			upone.add(oneDownComposite);
			JSONArray goalArray = jsonGoals.getJSONArray("subgoals");
			System.out.println("here1");
			for (int i = 0; i < goalArray.length(); i++) {
				System.out.println("here2");
				loadGoals(dungeon, goalArray.getJSONObject(i), oneDownComposite);
			}
			
		} else if (s.equals("OR")) {
			Composite oneDownComposite = new Composite("OR", upone);
			upone.add(oneDownComposite);
			JSONArray goalArray = jsonGoals.getJSONArray("subgoals");
			for (int i = 0; i < goalArray.length(); i++) {
				loadGoals(dungeon, goalArray.getJSONObject(i), oneDownComposite);
			}
			
		} else {
			//System.out.println("22222");
			loadGoal(dungeon, jsonGoals, upone);
			//System.out.println("77777");
		}
		
	}
    
    
    private void loadGoal(Dungeon dungeon, JSONObject goal, Composite upone) {
    	//System.out.println("333333");
    	String string = goal.getString("goal");
    	//System.out.println(string);
    	//System.out.println("oooooooo");
        Goal goalLeaf = new Goal(string, upone);
        //System.out.println("44444");
        //System.out.println(json.getString("goal"));
        //System.out.println("55555");
        upone.add(goalLeaf);
        //System.out.println("66666");
    	//System.out.println("here3");
    }
    
    
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        //System.out.println(type);
        Entity entity = null;
        switch (type) {
        case "player":
        	
        	int playerID = json.getInt("id");
            Player player = new Player(dungeon, x, y, playerID);
            if (player == null) {System.out.println("player is null 1");}
            if (playerID == 1) {dungeon.setPlayer(player);System.out.println("load player1");}
            else if (playerID == 2) {dungeon.setPlayer2(player);System.out.println("load player2");}
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "enemy":
        	int ID = json.getInt("id");
        	Enemy enemy = new Enemy(dungeon, x, y, ID);
        	onLoad(enemy);
        	entity = enemy;
        	Observer observer = (Observer)enemy;
        	int ram = getRandomNumberInRange(1,2)%2;
        	System.out.println("ram is "+ram);
        	if (ram == 1 && dungeon.getPlayer() != null) {dungeon.addObsPlayer1(observer);}
        	else if (ram == 0 && dungeon.getPlayer2() != null) {dungeon.addObsPlayer2(observer);}
        	else if (dungeon.getPlayer() == null){dungeon.addObsPlayer2(observer);}
        	else if (dungeon.getPlayer2() == null){dungeon.addObsPlayer1(observer);}
        	dungeon.addEnemy(enemy);
        	break;
        case "invincibility":
        	Potion potion = new Potion(x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "key":
        	int keyID = json.getInt("id");
        	Key key = new Key(x, y, keyID);
        	onLoad(key);
        	entity = key;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "portal":
        	int portalID = json.getInt("id");
        	Portal portal = new Portal(x, y, portalID);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "door":
        	int doorID = json.getInt("id");
        	Door door = new Door(x, y, doorID);
        	onLoad(door);
        	entity = door;
        	break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	FloorSwitch floorswitch = new FloorSwitch(x, y);
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x, y);
        	onLoad(bomb);
        	entity = bomb;
        	break;
        // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
        
    }

    
    public void calculateTreasure(Dungeon dungeon) {
    	dungeon.setTreasure(dungeon.CountEnetity("Treasure"));
    }
    
    private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
    
    
    
    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Door door);
    
    //public abstract void onLoad(OpenDoor door);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch floorSwitch);
    
    public abstract void onLoad(Bomb bomb);
    
    // TODO Create additional abstract methods for the other entities

}
