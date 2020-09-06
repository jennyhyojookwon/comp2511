package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
	private int id;
    private IntegerProperty x, y;
    private Boolean life;
    private String postionString;
    
    /**
     * 
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, int id) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);

        this.id = id;

        this.life = true;
        this.setPostionString("");
    }

    
    
    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
    	//System.out.println(y().get());
        return y().get(); // this return value is the coordinate int x
    }

    public int getX() {
        return x().get();
    }
    
    public void setX(int x) {
    	this.x.set(x);
    }
    
    public void setY(int y) {
    	this.y.set(y);
    }
    
    public int getID() {
    	return this.id;
    }

    public void setLife() {
    	this.life = false;
    }
    
    public Boolean getLife() {return life;}
    
    public String EntityName() {
    	//if (this == null) {
    	//	return "empty";
    	//}
    	return getClass().getName();
    }
    
    public void changeStaus(Player player) {} // different methods in different entity
    // the only not override "changePotionStatus" is in player, there is no argument player in it
    
    public void killEnemy() {}
    
    public void setIschanged() {}
    
    public Boolean getJustDie() {return false;}
    
    public void setJustDie() {}



	public String getPostionString() {
		return postionString;
	}



	public void setPostionString(String postionString) {
		this.postionString = postionString;
	}
}
