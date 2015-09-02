/**
 * The Boots class is a child of Gear. It has a bonus of
 * increased speed to the character. Speed is a trait
 * that increases the speed at which the character
 * runs.
 * @author David Iacono
 * CSC 212 Brown
 * 04/16/2013
 */
public class Boots extends Gear{
	/**
	 * Instantiates an instance of Boots with it's four types of statistics
	 * @param randPower
	 * @param randDefense
	 * @param randAccuracy
	 * @param someSpeed
	 */
	public Boots(int randPower, int randDefense, int randAccuracy, int randSpeed){
		setPower(randPower);
		setDefense(randDefense);
		setAccuracy(randAccuracy);
		setSpeed(randSpeed);
	}
	/**
	 * Mutates the speed bonus to Boots
	 * @param someSpeed
	 */
	public void setSpeed(int someSpeed){
		speed = someSpeed;
	}
	
	/**
	 * 
	 * @return the speed bonus to Boots
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * @return the string representation of Boots
	 */
	public String toString(){
		String str = "Boots |"
				+ "  Power: " + getPower() 
				+ "  Defense: " + getDefense()
				+ "  Accuracy: " + getAccuracy() + "%"
				+ "  Speed:      " + getSpeed();
		return str;
	}
	
	private int speed;
}
