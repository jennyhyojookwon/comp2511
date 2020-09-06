package unsw.dungeon;



public class Enemy extends Entity implements Observer{

	// id is for different type of enemy: also speed and intensity
	private Boolean movePattern;
	private Dungeon dungeon;
	private int disX;
	private int disY;
	private int a;
	private Thread thread;
	private Boolean life;
	// movement for one second
	// consumption of sword to kill them
	private Boolean ischanged;
	private Player backupPlayer; 
	private Boolean justDie; // if a enemy is dead but justDie is still true, then we should move the pic
	private DungeonController dungeonController;
	
	public Enemy(Dungeon dungeon, int x, int y, int id) {
		super(x, y, id);
		this.dungeon = dungeon;

		this.movePattern = true;
		this.a = 1;
		this.life = true;
		this.ischanged = false;
		this.backupPlayer = null;
		this.justDie = true;
		this.dungeonController = null;
	}

	public void setDungeonController(DungeonController dungeonController) {
		this.dungeonController = dungeonController;
	}
	
	
	@Override
	public void update(Player player) {
		if (player.checkStatus()) {
			escape();
		} else {
			attack();
		}
	}

	@Override
	public void update_dis(Player player) {
		//System.out.println("updating enemy data");
		onlyUpdate_dis(player);
		if (a == 1) {
			this.backupPlayer = dungeon.getAnother(player);
			System.out.println("hello here:)");
			this.a = 0;
			Thread threadOne = new Thread(new Runnable() {
		        public void run() {
		            movement(player);
		        }
		    });
			this.thread = threadOne;
			threadOne.start();
		}
		
	}
	
	public void onlyUpdate_dis(Entity player) {
		//System.out.println("updating enemy data");
		//Entity player = dungeon.checkPlayer();
		int playerX = player.getX();
		int playerY = player.getY();
		this.disX = getX() - playerX;
		this.disY = getY() - playerY;
	}
	
	@Override
	public void setIschanged() {this.ischanged = true;}
	
	
	// Enemy need to move automatically
	
	public void escape() {this.movePattern = false;}
	public void attack() {this.movePattern = true;}
	
	//@SuppressWarnings("deprecation")
	/*
	 * method for movement of the enemy depending on the position of the player
	 */
	public void movement(Player currplayer) {
		while(true) {
			if (this.ischanged) {currplayer = backupPlayer;onlyUpdate_dis(currplayer);}
			if (!this.life) {System.out.println("interrupt thread");break;}
			if (movePattern) {// forward player
			
				if ((disX > 0) && checkMove(getX() - 1, getY())) {
					this.setX(getX() - 1);
				} else if ((disX < 0) && checkMove(getX() + 1, getY())){
					this.setX(getX() + 1);
				} else if ((disY > 0) && checkMove(getX(), getY() - 1)) {
					this.setY(getY() - 1);
				} else if ((disY < 0) && checkMove(getX(), getY() + 1)) {
					this.setY(getY() + 1);
				} else if (disX == 0 && disY == 0){ // just in case
					if (currplayer.meetEnemy(this)) {
						System.out.println("this thread should be interrupted now");
					}
				}
				onlyUpdate_dis(currplayer);
				if (disX == 0 && disY == 0){
					if (currplayer.meetEnemy(this)) {// kill enemy here
						System.out.println("this thread should be interrupted now");
					}
				}
				wait((int)(1000 / getID()));
			} else {
			
				if (disX > 0 && checkMove(getX() + 1, getY())) {
					this.setX(getX() + 1);
				} else if (disX < 0 && checkMove(getX() - 1, getY())){
					this.setX(getX() - 1);
				} else if (disY > 0 && checkMove(getX(), getY() + 1)) {
					this.setY(getY() + 1);
				} else if (disY < 0 && checkMove(getX(), getY() - 1)) {
					this.setY(getY() - 1);
				} else if (disX == 0 && disY == 0){// just in case
					killEnemy();
				}
				onlyUpdate_dis(currplayer);
				if (disX == 0 && disY == 0){
					if (currplayer.meetEnemy(this)) { // kill enemy here
						System.out.println("this thread should be interrupted now");
					}
				}
				wait((int)(1000 / getID()));
				
			}
			
		}
	}

	public void wait( int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void killEnemy() {
		setLife();
		dungeon.removeEntity(this);
		dungeon.removeObserver(this);
		System.out.println("enemy dies --------------");
		setLife();
		if (dungeon.checkAllEnemyDead()) {
			dungeon.checkName("enemies");
		}
	}
	
	public boolean checkMove(String nameString) {
		//System.out.println(nameString);
    	if (nameString.equals("unsw.dungeon.Wall") || nameString.equals("unsw.dungeon.Door") || nameString.equals("unsw.dungeon.Enemy")) {return false;}
		return true;
	}
	
	public boolean checkMove(int x, int y) {
		String string = dungeon.EntityName(x, y);
		return checkMove(string);
	}
	
	public void setLife() {this.life = false;}
	
	@Override
	public Boolean getJustDie() {return justDie;}
	
	@Override
	public void setJustDie() {
		this.justDie = false;
	}
	
}
