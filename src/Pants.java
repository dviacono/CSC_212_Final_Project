/**
 * The Boots class is a child of Gear. It has a bonus of
 * increased endurance to the character. The character's
 * time length of sprint is increased by the endurance trait.
 * @author David Iacono
 * CSC 212 Brown
 * 04/16/2013
 */
public class Pants extends Gear{
	/**
	 * Instantiates an instance of Boots with it's four types of statistics
	 * @param randPower
	 * @param randDefense
	 * @param randAccuracy
	 * @param someEndurance
	 */
	public Pants(int randPower, int randDefense, int randAccuracy, int randEndurance){
		setPower(randPower);
		setDefense(randDefense);
		setAccuracy(randAccuracy);
		setEndurance(randEndurance);
	}
	
	/**
	 * Mutates the endurance bonus to Pants
	 * @param someEndurance
	 */
	public void setEndurance(int someEndurance){
		endurance = someEndurance;
	}
	
	/**
	 * 
	 * @return the endurance bonus to Pants
	 */
	public int getEndurance(){
		return endurance;
	}
	
	/**
	 * the string representation of Pants
	 */
	public String toString(){
		String str = "Pants |"
				+ "  Power: " + getPower() 
				+ "  Defense: " + getDefense()
				+ "  Accuracy: " + getAccuracy() + "%"
				+ "  Endurance:  " + getEndurance();
		return str;
	}
	
	private int endurance;
}
