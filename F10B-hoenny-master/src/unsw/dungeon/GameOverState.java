package unsw.dungeon;

public class GameOverState implements PlayerState {

	String state;
	
	public GameOverState() {
		state = "GameOverState";
	}
	
	@Override
	public void toInvincible(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toVulnerable(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toGameOver(Player player) {
		// TODO Auto-generated method stub
		System.out.println("===GAME OVER===");
		
	}

	/*
	 * Auto-generated equals method
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameOverState other = (GameOverState) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	

}
