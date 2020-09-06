package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {

	private Dungeon dungeon;
    private Boolean status; 
    private Bag bag;
    //private Boolean life;
    private ArrayList<Observer> observers;
    private PlayerState playerState;
    private TimerTask task; // used for timer
    private Timer timer; //timer is for set the player to be not invincible after 30s
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, int id) {
        super(x, y, id);
        this.dungeon = dungeon;
        this.status = false;
        this.bag = new Bag(dungeon, this);
        //this.life = true;
        this.playerState = new VulnerableState();
        this.observers = new ArrayList<Observer>();
        this.timer = new Timer();
		this.task = new TimerTask() {
			@Override
			public void run() {
			}
		};
    }

    
	public void moveUp() {
        if (!playerState.equals(new GameOverState()) && getY() > 0) {
        	Entity next_en = dungeon.checkEntity(getX(), getY() - 1);
        	Entity nnext_en = dungeon.checkEntity(getX(), getY() - 2);
        	ArrayList<Key> keys = bag.getKeys();
        	 
        	if(dungeon.getStart() == 0) {dungeon.checkInitiallyTriggered();}
        	openDoorWithKey(next_en, keys);
    		if (!checkMove(next_en, keys) || dungeon.noObstacle(next_en, nnext_en, this)) {return;}
        	if (next_en != null) {
        		dungeon.meetEnetity(next_en, this);
        		if (dungeon.checkBoulder(next_en) || dungeon.checkSwitch(next_en)) {
        			checkObstacle(getX(), getY() - 2);
        			if(collisionOccurs(getX(), getY()-2, next_en)) {return;}
            		if(dungeon.checkSwitch(getX(), getY()-2)) {
	        			FloorSwitch triggered = (FloorSwitch) dungeon.checkEntity(getX(), getY()-2);
		    			if(checkDoubleBoulder(next_en, triggered)) {return;}
		    			else if(dungeon.checkBoulder(next_en) && triggered.checkTrigger() == false) {
		    				triggerwithBoulder(next_en, nnext_en);
		    				return;
		    			}
	        		}
        			if(dungeon.getClass().getName().endsWith("FloorSwich") && ((FloorSwitch)next_en).checkTrigger() == true) {
	        			if(!checkWall(getX(), getY() - 2) || !checkMultipleBoulder(getX(), getY() - 2)) {return;}
	        			Boulder bt = (Boulder)dungeon.getTriggerBoulder(dungeon.getAllEntity(getX(), getY() - 1));
	        			dungeon.pushBoulder(bt, this);
	        			((FloorSwitch)next_en).untriggerSwitch();
	            		return;
        			}
            	} 
        		else if (dungeon.checkPortal(next_en)) {
	        		MovePlayertoEnetity(next_en);
	        		notify_dis();
	        		return;
    			} 
        	}
            y().set(getY() - 1);
        	notify_dis();
        }
    }

	public void moveDown() {
        if (!playerState.equals(new GameOverState()) && getY() < dungeon.getHeight() - 1) {
        	Entity next_en = dungeon.checkEntity(getX(), getY() + 1);
        	Entity nnext_en = dungeon.checkEntity(getX(), getY() + 2);
        	ArrayList<Key> keys = bag.getKeys();
        	 
        	if(dungeon.getStart() == 0) {dungeon.checkInitiallyTriggered();}
        	openDoorWithKey(next_en, keys);
    		if (! checkMove(next_en, keys) || dungeon.noObstacle(next_en, nnext_en, this)) {return;}
        	if (next_en != null) {
        		dungeon.meetEnetity(next_en, this);
        		if (dungeon.checkBoulder(next_en) || dungeon.checkSwitch(next_en)) {
        			checkObstacle(getX(), getY() + 2);
        			if(collisionOccurs(getX(), getY() + 2, next_en)) {return;}
	        		if(dungeon.checkSwitch(getX(), getY()+2)) {
	        			FloorSwitch triggered = (FloorSwitch) dungeon.checkEntity(getX(), getY() + 2);
	        			if(checkDoubleBoulder(next_en, triggered)) {return;}
	        			else if(dungeon.checkBoulder(next_en) && triggered.checkTrigger() == false) {
	        				triggerwithBoulder(next_en, nnext_en);
		            		notify_dis();
		    				return;
		    			}
	        		}
        			if(dungeon.getClass().getName().endsWith("FloorSwich") && ((FloorSwitch)next_en).checkTrigger() == true) {
        				if(!checkWall(getX(), getY() + 2) || !checkMultipleBoulder(getX(), getY() + 2)) {return;}
	        			Boulder bt = (Boulder)dungeon.getTriggerBoulder(dungeon.getAllEntity(getX(), getY() + 1));
	        			dungeon.pushBoulder(bt, this);
	        			((FloorSwitch)next_en).untriggerSwitch();
	        			return;
        			}
        		}
        		else if (dungeon.checkPortal(next_en)) {
		        		MovePlayertoEnetity(next_en);
		        		notify_dis();
		        		return;
        		} 
        	}
        	y().set(getY() + 1);
        	notify_dis();
        }
    }

	public void moveLeft() {
        if (!playerState.equals(new GameOverState()) && getX() > 0) {
        	Entity next_en = dungeon.checkEntity(getX()-1, getY());
        	Entity nnext_en = dungeon.checkEntity(getX()-2, getY());
        	ArrayList<Key> keys = bag.getKeys();
        	 
        	if(dungeon.getStart() == 0) {dungeon.checkInitiallyTriggered();}
        	openDoorWithKey(next_en, keys);
    		if (!checkMove(next_en, keys) || dungeon.noObstacle(next_en, nnext_en, this)) {return;}
    		if (next_en != null) {
        		dungeon.meetEnetity(next_en, this);
        		if (dungeon.checkBoulder(next_en) || dungeon.checkSwitch(next_en)) {
        			checkObstacle(getX()-2, getY());
        			if(collisionOccurs(getX()-2, getY(), next_en)) {return;}
	        		if(dungeon.checkSwitch(getX()-2, getY())) {
	        			FloorSwitch triggered = (FloorSwitch) dungeon.checkEntity(getX()-2, getY());
	        			if(checkDoubleBoulder(next_en, triggered)) {return;}
		    			else if(dungeon.checkBoulder(next_en) && triggered.checkTrigger() == false) {
		    				triggerwithBoulder(next_en, nnext_en);
		    				return;
		    			}
	        		}
        			if(dungeon.getClass().getName().endsWith("FloorSwich") && ((FloorSwitch)next_en).checkTrigger() == true) {
	        			if(!checkWall(getX()-2, getY() ) || !checkMultipleBoulder(getX()-2, getY() )) {return;}
	        			Boulder bt = (Boulder)dungeon.getTriggerBoulder(dungeon.getAllEntity(getX()-1, getY()));
	        			dungeon.pushBoulder(bt, this);
	        			((FloorSwitch)next_en).untriggerSwitch();
	            		return;
        			}
        		} 
        		else if (dungeon.checkPortal(next_en)) {
	        		MovePlayertoEnetity(next_en);
	        		notify_dis();
	        		return;
        		} 
        		
        	}
        	x().set(getX() - 1);
            notify_dis();
        }
    }


	public void moveRight() {
        if (!playerState.equals(new GameOverState()) && getX() < dungeon.getWidth() - 1) {
        	Entity next_en = dungeon.checkEntity(getX()+1, getY());
        	Entity nnext_en = dungeon.checkEntity(getX()+2, getY());
        	ArrayList<Key> keys = bag.getKeys();
        	 
        	if(dungeon.getStart() == 0) {dungeon.checkInitiallyTriggered();}
        	openDoorWithKey(next_en, keys);
    		if (! checkMove(next_en, keys) || dungeon.noObstacle(next_en, nnext_en, this)) {return;}
        	if (next_en != null) {
        		dungeon.meetEnetity(next_en, this);
        		if (dungeon.checkBoulder(next_en) || dungeon.checkSwitch(next_en)) {
        			checkObstacle(getX()+2, getY());
            		if(collisionOccurs(getX()+2, getY(), next_en)) {return;}
                    if(dungeon.checkSwitch(getX()+2, getY())) {
	        			FloorSwitch triggered = (FloorSwitch) dungeon.checkEntity(getX()+2, getY());
	        			if(checkDoubleBoulder(next_en, triggered)) {return;}
	        			else if(dungeon.checkBoulder(next_en) && triggered.checkTrigger() == false) {
	        				triggerwithBoulder(next_en, nnext_en);
		    				return;
		    			}
	        		}
        			if(dungeon.getClass().getName().endsWith("FloorSwich") && ((FloorSwitch)next_en).checkTrigger() == true) {
	        			if(!checkWall(getX()+2, getY()) || !checkMultipleBoulder(getX()+2, getY())) {return;}
	        			Boulder bt = (Boulder)dungeon.getTriggerBoulder(dungeon.getAllEntity(getX()+1, getY()));
	        			dungeon.pushBoulder(bt, this);
	        			((FloorSwitch)next_en).untriggerSwitch();
	            		return;
            		}
            	} 
        		else if (dungeon.checkPortal(next_en)) {
	        		MovePlayertoEnetity(next_en);
	        		notify_dis();
	        		return;
	        	} 
        		
        	} 
    		x().set(getX() + 1);
    		notify_dis();
        }
    }
    
	/*
	 * restrict player's movement when there is an obstacle in the front
	 */
    public boolean checkMove(Entity nextEntity, ArrayList<Key> keys) {
    	String nameString = dungeon.EntityName(nextEntity);
    	if (nameString.equals("unsw.dungeon.Wall")) {return false;}
    	else if(nameString.equals("unsw.dungeon.Door") && ((Door)nextEntity).checkOpen() == true) {return true;}
    	else if(nameString.equals("unsw.dungeon.Door") && !KeyMatchDoor(nextEntity, keys)) {return false;}
    	
		return true;
	}
    
    /*
     * method to check whether the player has a matching key to open the door
     */
	public boolean KeyMatchDoor(Entity next_en, ArrayList<Key> arrayList) {
		if(arrayList.isEmpty()) {return false;}
		int doorID = ((Door)next_en).getID();
			int keyID = ((Key)arrayList.get(0)).getID();
			if(keyID == doorID) {
				System.out.println("you have the matching key to open the door.");
				return true;
			}
		return false;
	}
	
    public Boolean checkDoor(Entity nextEntity) {
    	String nameString = dungeon.EntityName(nextEntity);
    	if(nameString.endsWith("Door")) {
    		if(((Door) nextEntity).checkOpen()) {return false;}
    		return true;
    	}
    	return false;
    }
    
    /*
     * check item for the player to pick up
     */
    public Boolean checkItem(Entity nextEntit) { 
    	String nameString = dungeon.EntityName(nextEntit);
    	if (nameString.endsWith("Potion")) {
    		nextEntit.changeStaus(this); //this is to change the status of player (like became incincible)
    		return true;
    	} else if (nameString.endsWith("Treasure")) {
    		bag.addTreasure();
    		checkGoal("treasure");
    		return true;
    	} else if (nameString.endsWith("Sword")) {
    		bag.addSword();
    		return true;
    	} else if (nameString.endsWith("Key") && bag.getKeys().size() < 1) {
    		bag.addKeys((Key)nextEntit);
    		return true;
    	} else if(nameString.endsWith("Key") && bag.getKeys().size() >= 1) {
    		bag.replaceKey(nextEntit);
    		return false;
    	} else if (nameString.endsWith("Exit")) {
    		nextEntit.setLife();
    		checkGoal("exit");
    	} else if (nameString.endsWith("Bomb")) {
    		bag.getBomb();
    		return true;
    	}
    	return false;
    }
    
    /*
     * check status after player meeting enemy
     */
    public Boolean meetEnemy(Entity enemy) {
    	if (this.status) {//invinciable
    		//dungeon.removeEntity(enemy); // this function is not finished yet
    		enemy.setLife();
    		checkGoal("enemies");
    		return true;
    	} else if (this.bag.checkSword(enemy)){  // ok, we have sword 
    		killEnemy_Sword(enemy);
    		
    		return true;
    	} else {
    		// killed by enemy
    		//dungeon.removeEntity(this); // this function is not finished yet
    		//dungeon.setPlayer(null);
    		killPlayer(enemy);
    		setPlayerState(new GameOverState());
    		return false;
    	}
    }
    
    public void killEnemy_Sword(Entity enemy) {
    	enemy.setLife();
    	dungeon.removeEntity(enemy);
    	Enemy en = (Enemy) enemy;
    	en.killEnemy();
    	bag.useSword(enemy);
    	checkGoal("enemies");
    }
    
    public void useBomb() {
    	System.out.println("try to use bomb");
    	bag.useBomb();
    }
    
    
    public void killPlayer(Entity enemy) {
    	enemy.setIschanged();
    	if (dungeon.getPlayer() == null) {
    		System.out.println("player is null");
    	}
    	this.setLife();
    	System.out.println("player dies============" + getID());
    	if (!dungeon.allPlayerLife()) {
    		
    		System.out.println("you lose");
    		System.exit(1); // I don't think that is a good idea
    	}
    	
    	dungeon.removeEntity(this);
    	moveObservers(enemy);
    }
    
    public void moveObservers(Entity enemy) {
    	dungeon.moveObservers(this, enemy);
    }
    
    public ArrayList<Observer> getObservers() {return observers;}
    
   
    public void MovePlayertoEnetity(Entity portal) {
    	Portal portal2 = (Portal) portal;
    	Entity dest = dungeon.checkEntity(portal2);
    	if (dest == null) {System.out.println("dest portal is null");}
    	x().set(dest.getX());
    	y().set(dest.getY());
    }
    
    public boolean checkWall(int x, int y) {
    	String nameString = dungeon.EntityName(x, y);
    	if (nameString.equals("unsw.dungeon.Wall") ) {return false;}
    	return true;
    }
 
    public boolean checkMultipleBoulder(int x, int y) {
    	String nameString = dungeon.EntityName(x, y);
    	if (nameString.equals("unsw.dungeon.Boulder") ) {return false;}
        return true;
    }

    public void addObderver(Observer ob) {this.observers.add(ob);}
    public void removeObserver(Observer ob) {this.observers.remove(ob);}
    
	@Override
	public void notify_them() {
		for (Observer ob: observers) {
			ob.update(this);
		}
	}

	public PlayerState getPlayerState() {return playerState;}

	public void setPlayerState(PlayerState playerState) {this.playerState = playerState;}

	public Boolean playerStatus() {return this.status;}
	
    /*
     * open door with correspodnign key
     */
	public void openDoorWithKey(Entity next_en, ArrayList<Key> keys) {
    	if(checkDoor(next_en)) {
			if(bag.getKeys().size() == 0) {return;}
			if(bag.getKeys().size() >= 1 && dungeon.EntityName(next_en).equals("key")) {bag.replaceKey((Key)next_en);}
			else if(!KeyMatchDoor(next_en, bag.getKeys())) {return;}
			else if(KeyMatchDoor(next_en, bag.getKeys())) {
				dungeon.removeEntity(keys.get(0));
				bag.removeKey(keys.get(0));
				//y().set(getY() - 1);
				((Door)next_en).openDoor();
				dungeon.getDungeonController().setDoorFlag();
				dungeon.getDungeonController().setDoor(next_en);
				return;
			}
		}
    }
    
	/*
	 * check collision before pushing boulder
	 */
	private boolean collisionOccurs(int x, int y, Entity next_en) {
		if(!checkMultipleBoulder(x, y) && dungeon.checkBoulder(next_en)) {return true;}
		if(!checkWall(x, y) && dungeon.checkBoulder(next_en)) {return true;}
		return false;
	}
	
	private void triggerSwitch(FloorSwitch ent) {
		if(dungeon.checkSwitch(ent) && ent.checkTrigger() == false) {
			ent.triggerSwitch();
			return;
		}
	}

	private void checkObstacle(int x, int y) {
		if(!checkWall(x, y) || !checkMultipleBoulder(x, y)) {return;}
	}
	
	@Override
	public void notify_dis() {
		for(Observer ob: observers) {
			ob.update_dis(this);
		}
	}
    
	public void checkGoal(String goalString) {
		if (goalString.equals("enemies")) {
			if (dungeon.checkAllEnemyDead()) {
    			dungeon.checkName("enemies");
    		}
		} else if (goalString.equals("exit")) {
			System.out.println("1111111");
			dungeon.checkName("exit");
			System.out.println("22222");
		} else if (goalString.equals("treasure")) {
			if (dungeon.getAllcollectedTreasure() == dungeon.getTreasure()) {
				dungeon.checkName("treasure");
			}
		} else if (goalString.equals("boulder")) {
			if (dungeon.checkTriggered(dungeon.getAllSwitch())) {
				dungeon.checkName("boulder");
			}
		}
		dungeon.checkReachGoal();
	}
    
	public int getTreasure() {return bag.getTreasure();}
	
	public Boolean getStatus() {return status;}

	public Bag getBag() {return bag;}
	
	public int getSwordTime() {return bag.getSwordTime();}
	
	private boolean checkDoubleBoulder(Entity next_en, FloorSwitch triggered) {
		if(dungeon.checkBoulder(next_en) && triggered.checkTrigger() == true) {return true;}
		return false;
		
	}
    
	
	public void changeinvincibleStatus() {
		this.status = true; 
		notify_them(); //notify all the enemies that change their move pattern to "leave player"
	}
	
	public void changeStatusPotion() {
		this.status = false;
		notify_them(); //notify them the player is not invincible anymore
	}
	
	public Boolean checkStatus() {return status;}
	
	public void resetTimer() {
		System.out.println("resetting the potion timer");
		timer.cancel();
		timer.purge();
		task.cancel();
		timer = new Timer();
		this.task = new TimerTask() {
			@Override
			public void run() {
				changeStatusPotion(); // change is back
			}
		};
		timer.schedule(this.task, 5000);
	}
	
	
	public void typeSpace() {
		System.out.println("I typred space");
	}
	
	
	public void typeW() {
		System.out.println("I typred W");
	}
	
    public void triggerwithBoulder(Entity next_en, Entity nnext_en) {

    	if(nnext_en == null) {return;}
		//push down
		if(next_en.getY() == getY()-1) {
			y().set(getY() - 1); //move player
			((Boulder)next_en).moveUp();
			triggerSwitch((FloorSwitch)nnext_en);
    		//return;
		}
		// push down
		if(next_en.getY() == getY()+1) {
			y().set(getY() + 1); //move player
			((Boulder)next_en).moveDown();
			triggerSwitch((FloorSwitch)nnext_en);
    		//return;
		}
		// push left
		if(next_en.getX() == getX()-1) {
			x().set(getX() - 1); //move player
			((Boulder)next_en).moveLeft();
			triggerSwitch((FloorSwitch)nnext_en);
    		//return;
		}
		if(next_en.getX() == getX()+1) {
			x().set(getX() + 1); //move player
			((Boulder)next_en).moveRight();
			triggerSwitch((FloorSwitch)nnext_en);
    		//return;
		}
    }
    
    public Entity upEnt() {
    	return dungeon.checkEntity(getX(), getY() - 1);
    }
    public Entity downEnt() {
    	return dungeon.checkEntity(getX(), getY() + 1);
    }
    public Entity leftEnt() {
    	return dungeon.checkEntity(getX()-1, getY());
    }
    public Entity rightEnt() {
    	return dungeon.checkEntity(getX()+1, getY());
    }
}
