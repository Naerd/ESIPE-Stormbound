package model;

/**
 * 
 * Base à détruire.
 * 
 * @author Axel ROSTAGNY
 * @author Guillaume GAUJAC
 *
 */
public class Base {
	private int health;

	public Base() {
		this.health = 10;
	}

	public int getHealth() {
		return this.health;
	}

	public void outch(int val) {
		this.health -= val;
	}

	public String toString() {
		if (this.health == 10) {
			return " ___________________________________________________________\n" + "|___________________________| "
					+ this.health + " |__________________________|\n"
					+ "|_______________________Health Points_______________________|\n";
		} else {
			return " ___________________________________________________________\n" + "|___________________________| "
					+ this.health + " |___________________________|\n"
					+ "|_______________________Health Points_______________________|\n";
		}
	}
}
