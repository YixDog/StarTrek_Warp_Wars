public class Player {
	private int energy;
	private int score;
	private int life=5;
	private int MovementSpeed=2;
	Stack Backpack;
	private boolean Energy30Seconds=false;
	private boolean Energy240Seconds=false;
	private String player = "P";
	
	public Player() {
		Backpack = new Stack(8);	
		
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public Stack getBackpack() {
		return Backpack;
	}
	public void setBackpack(Stack backpack) {
		Backpack = backpack;
	}
	public boolean isEnergy30Seconds() {
		return Energy30Seconds;
	}
	public int getMovementSpeed() {
		return MovementSpeed;
	}
	public void setMovementSpeed(int movementSpeed) {
		MovementSpeed = movementSpeed;
	}
	public void setEnergy30Seconds(boolean energy30Seconds) {
		Energy30Seconds = energy30Seconds;
	}
	public boolean isEnergy240Seconds() {
		return Energy240Seconds;
	}
	public void setEnergy240Seconds(boolean energy240Seconds) {
		Energy240Seconds = energy240Seconds;
	}
	
	
	
}