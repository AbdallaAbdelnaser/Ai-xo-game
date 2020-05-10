package xoGame;

public class Player {
	private String name;
	private char sympol;
	private int score;
	public Player() {}
	public Player(String name, char sympol ) {
	
		this.name = name;
		this.sympol = sympol;
		this.score = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSympol() {
		return sympol;
	}
	public void setSympol(char sympol) {
		this.sympol = sympol;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	
}
