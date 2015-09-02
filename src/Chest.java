/**
 * The Chest class is a child of Gear. It has a bonus of
 * increased durability to the character. Durability is
 * a trait that decreases the rate that a character's
 * armor loses it's effectiveness. It lessens the
 * frequency that a repair of armor would be necessary.
 * @author David Iacono
 * CSC 212 Brown
 * 04/16/2013
 */
public class Chest extends Gear{
	/**
	 * Instantiates an instance of Boots with it's four types of statistics
	 * @param randPower
	 * @param randDefense
	 * @param randAccuracy
	 * @param someDurability
	 */
	public Chest(int randPower, int randDefense, int randAccuracy, int randDurability){
		setPower(randPower);
		setDefense(randDefense);
		setAccuracy(randAccuracy);
		setDurability(randDurability);
	}
	
	/**
	 * Mutates the durability bonus to Chest
	 * @param someDurability
	 */
	public void setDurability(int someDurability){
		durability = someDurability;
	}
	
	/**
	 * 
	 * @return the durability bonus to Chest
	 */
	public int getDurability(){
		return durability;
	}

	/**
	 * the string representation of Chest
	 */
	public String toString(){
		String str = "Chest |"
				+ "  Power: " + getPower() 
				+ "  Defense: " + getDefense()
				+ "  Accuracy: " + getAccuracy() + "%"
				+ "  Durability: " + getDurability();
		return str;
	}
	
	private int durability;
}
