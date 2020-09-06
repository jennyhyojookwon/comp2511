package unsw.dungeon;

public class InvincibleState implements PlayerState {

	String state;
	
	public InvincibleState() {
		state = "InvincibleState";
	}
	
	@Override
	public void toInvincible(Player player) {
		// TODO Auto-generated method stub
		player.setPlayerState(new InvincibleState());
		
	}

	@Override
	public void toVulnerable(Player player) {
		// TODO Auto-generated method stub
		player.setPlayerState(new VulnerableState());
		
	}

	@Override
	public void toGameOver(Player player) {
		// TODO Auto-generated method stub
		player.setPlayerState(new GameOverState());
		
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
		InvincibleState other = (InvincibleState) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	
}
