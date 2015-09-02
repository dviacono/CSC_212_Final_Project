/**
 * The Gear class represents the parent class of the subtypes of gear. A user
 * can select which type of gear to equip. Each type of gear has it's own type of
 * bonus to the character that is unique to that piece of gear.  
 * @author David Iacono
 * CSC 212 Brown
 * 04/16/2013
 */
public abstract class Gear
{
	
	/**
	 * Instantiates an instance of the Gear class with default values.
	 */
	Gear(){
		setPower(0);
		setDefense(0);
		setAccuracy(0);
	}

	/**
	 * Instantiates and instance of the Gear class
	 * @param randPower
	 * @param randDefense
	 * @param randAccuracy
	 */
	Gear(int randPower, int randDefense, int randAccuracy){
		setPower(randPower);
		setDefense(randDefense);
		setAccuracy(randAccuracy);
	}

	/**
	 * Resets the statistics of the Gear.
	 */
	public void reset(){
	}

	/**
	 * 
	 * @return The value of the Gear's power.
	 */
	public int getPower(){
		return power;
	}
	
	/**
	 * 
	 * @return The value of the Gear's defense.
	 */
	public int getDefense(){
		return defense;
	}
	
	/**
	 * The value of the Gear's Accuracy.
	 * @return
	 */
	public int getAccuracy(){
		return accuracy;
	}
	
	/**
	 * 
	 * @param someGearType
	 * @return The type of Gear, Boots/Chest/Pants.
	 */
	public String getGearType(){
		return gearType;
	}
	
	/**
	 * 
	 * @return The name of the gear.
	 */
	public String getGearName(){
		return gearName;
	}
	
	/**
	 * Sets the value of power.
	 * @param somePower
	 */
	public void setPower(int somePower){
		power = somePower;
	}

	/**
	 * Sets the value of defense.
	 * @param someDefense
	 */
	public void setDefense(int someDefense){
		defense = someDefense;
	}
	
	/**
	 * Sets the value of accuracy.
	 * @param someAccuracy
	 */
	public void setAccuracy(int someAccuracy){
		accuracy = someAccuracy;
	}
	
	/**
	 * Sets the name of Gear type.
	 * @param someGearType
	 */
	public void setGearType(String someGearType){
		gearType = someGearType;
	}
	
	/**
	 * Sets the name of the Gear.
	 * @param someGearName
	 */
	public void setGearName(String someGearName){
		gearName = someGearName;
	}
	
	public abstract String toString();

	
	protected String gearType, gearName; //Names of the type of Gear and Gear itself.
	protected int power, defense, accuracy; //Statistics for the Gear.

}