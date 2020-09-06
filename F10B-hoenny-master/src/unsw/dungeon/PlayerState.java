package unsw.dungeon;

/**
 * State pattern used
 */
public interface PlayerState {
	public void toInvincible(Player player);
	public void toVulnerable(Player player);
	public void toGameOver(Player player);
}