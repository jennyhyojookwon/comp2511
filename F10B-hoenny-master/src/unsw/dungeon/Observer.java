package unsw.dungeon;

public interface Observer {
	
	public void update(Player player); // for tell them to run from the player
	public void update_dis(Player player);
	
}
