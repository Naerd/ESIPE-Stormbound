package model.cards;

public interface Cards {

	/**
	 * Getter for the mana.
	 * 
	 * @return Magic points
	 */
	public int getMana();

	/**
	 * @return Name of the card
	 */
	public String getName();

	/**
	 * Specific string for the current class.
	 * 
	 * @return String to specified the current object.
	 */
	public String specificToString();

}
