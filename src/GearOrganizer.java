/**
 * The gear organizer will generate random pieces of gear for a player
 * to equip to character. The GearOrganizer will interface with the
 * GearCollection to sort the collection according to three properties
 * (Power, Defense, and Accuracy.) It is up to the player how much
 * of each statistic they want to apply to their character. The player
 * will be given a list of pieces of gear to choose from. The player
 * may sort the list by three distinct properties. Once the player 
 * decides which piece of gear to equip, the player will input
 * the line number of the piece of gear. That index will be passed to
 * the GearCollection. The gear will be selected. Once selected, 
 * the the GearOrganizer will determine what type of gear it is
 * and equip the gear to the character (in Gear Collection.) When
 * the piece of gear is equipped, it is also removed from the list.
 * If there was a piece of gear equipped to the character already,
 * the previous piece of gear will be added back to the list. A
 * text will display of what gear is currently equipped that also
 * shows the total gear statistics.
 * 
 * @author David Iacono
 * CSC 212 Brown
 * 04/16/2013
 */
//import java.io.*;
import java.util.*;

public class GearOrganizer{	
	/**
	 * Instantiates the GearOrganizer object. Intializes collection
	 * maximum size and value maximum size. The value size is comparable
	 * to Power, Defense, and Accuracy. 
	 */
	public GearOrganizer(){
		collection = new GearCollection<Gear>();
	}
	
	/**
	 * Main loop of the program. Calls the other methods that interface with
	 * the GearCollection.
	 */
	public void action() {
		String choice = "ALL";
		
		//Open the chest and create the gear
		openChest();
		
		while(choice.compareTo("QUIT") != 0){
			//Default choice, displays gear and gives user all options.
			if (choice.compareTo("ALL") == 0){
				choice = returnChoice();
			}
			//User chooses this to decide which gear to equip.
			else if (choice.compareTo("EQUIP") == 0){
				equipGear();
				choice = "ALL";
			}
			//Filters the gear by gear type
			else if(choice.compareTo("BOOTS") == 0
					|| choice.compareTo("PANTS") == 0
					|| choice.compareTo("CHEST") == 0){
				choice = filterGear(choice);
			}
			//Sorts the gear by gear property
			else if(choice.compareTo("POWER") == 0
					|| choice.compareTo("DEFENSE") == 0
					|| choice.compareTo("ACCURACY") == 0){
				sort(choice);
				choice = "ALL";
			}
			//If user's choice not in this list, ask for a valid option
			else{
				System.out.println("Please enter a valid option:");
				choice = scanner.nextLine().toUpperCase();
			}
		}
	}

	/**
	 * Opens the chest and creates the gear. Each piece of gear will have
	 * random statistics generated for it.
	 */
	public void openChest() {
		collection.clear();
		Gear newGear;
		//Rand type of gear
		int gearType;
		//Stores the random stats for the gear
		int[] gearStats = new int[4];
		
		for(int i = 0; i < COLLECTION_SIZE; i++){
			//Choose random type of gear randomly
			gearType = (int)(Math.random() * 10) % 3;
			//Make random stats for each 
			for(int j = 0; j < 4; j++){
				gearStats[j] = (int)(Math.random() * 100) % MAXIMUM_ITEM_VALUE + 10;
			}
			//Create Boots with random stats
			if(gearType == 0){
					newGear = new Boots(gearStats[0], gearStats[1], gearStats[2], gearStats[3]);
			}
			//Create Pants with random stats
			else if(gearType == 1){
					newGear = new Pants(gearStats[0], gearStats[1], gearStats[2], gearStats[3]);
			}
			//Create Chest with random stats
			else{
					newGear = new Chest(gearStats[0], gearStats[1], gearStats[2], gearStats[3]);
			}
			//Add each gear to the collection
			collection.add(newGear);
		}
	}

	/**
	 * Equips the piece of gear that the user chooses.
	 * @param index the number associated with the piece of gear chosen
	 */
	public void equipGear() {
		int i = 0,
				index = 0,
				valid = 0;
		//Gear swapping variables
		Gear currentGear = null,
						oldGear;
		//Reset to head selected
		collection.reset();
		
		//Get the gear number from the user. Repeat the process if
		//the gear number is out of bounds.
		while(valid == 0){
			System.out.println("-Enter number (1-"+ collection.getSize() +") to equip gear.");
			//Ensure that input from the keyboard is an integer
			try{
				index = (int)scanner.nextInt();
				scanner.nextLine();
			}
			//If not an integer, don't accept the input
			catch (Exception e){
				System.out.println("Not a valid integer!");
				index = 0;
			}
			
			//If input is within the range of the collection, accept it
			if (index > 0 && index <= collection.getSize()){
				valid = 1;
			}
			//If input out of bounds, advise user and wait for input
			else{
				System.out.print("Input invalid: ");
			}
		}
		
		if(index > 0){
			//Get the piece of gear to equip
			while(i < index){
				currentGear = collection.next();
				i++;
			}

			collection.reset();
			
			//Equip the new piece and return the old piece and put back
			//in the chest if any
			oldGear = collection.setGear(currentGear);
			if(oldGear != null)
				collection.add(oldGear);
		}
	}
	
	/**
	 * Prints the list of gear for the player to choose from. 
	 */
	public void showGear(){
		Gear gear;
		int index = 1;
		
		//Create default strings
		String space = " ",
				bootsStr = "empty",
				pantsStr = "empty",
				chestStr = "empty";
		collection.reset();
		//Test if collection is empty
		if(collection.hasNext() == false){
			System.out.println("Collection empty");
		}
		
		//Print the pieces of gear
		while(collection.hasNext() == true){
			if(index == 10){
				space = space.replaceFirst(" ", "");
			}
			gear = collection.next();
			System.out.println(space +index + ") " + gear.toString());
			index++;
		}
		
		//Store the info for each equipped piece of gear
		if(collection.getBoots() != null){
			bootsStr = collection.getBoots().toString();
		}
		if(collection.getPants() != null){
			pantsStr = collection.getPants().toString();
		}
		if(collection.getChest() != null){
			chestStr = collection.getChest().toString();
		}
		
		//Print the pieces of gear that are equipped
		System.out.println("\nEquipped gear:"
				+ "\nBoots: " + bootsStr 
				+ "\nPants: " + pantsStr 
				+ "\nChest: " + chestStr
				+ "\n" + totalGearStats());
	}
	
	/**
	 * 
	 * @return A string of the total stats for all equipped gear
	 */
	public String totalGearStats(){
		//Initialize accumulators
		int 	power = 0,
				defense = 0,
				accuracy = 0;
		String str;
		
		//Accumulate Boots stats
		if(collection.getBoots() != null){
			power += collection.getBoots().getPower();
			defense += collection.getBoots().getDefense();
			accuracy += collection.getBoots().getAccuracy();
		}
		//Accumulate Pants stats
		if(collection.getPants() != null){
			power += collection.getPants().getPower();
			defense += collection.getPants().getDefense();
			accuracy += collection.getPants().getAccuracy();
		}
		//Accumulate Chest stats
		if(collection.getChest() != null){
			power += collection.getChest().getPower();
			defense += collection.getChest().getDefense();
			accuracy += collection.getChest().getAccuracy();
		}
		//Store accumulators in string
		str = "Current statistics:\n"
				+ "Power: " + power + "  "
				+ "Defense: " + defense + "  "
				+ "Accuracy: " + accuracy + "%";
		
		return str;
		
	}
	
	/**
	 * Filters the list of gear by gear type. It is chosen by the user.
	 * 
	 * @param choice The choice of the user
	 * @return the new choice of the user
	 */
	public String filterGear(String choice){
		Gear gear;
		String space = " ";
		//For printing the index
		int index = 1;
		
		collection.reset();
		
		if(collection.hasNext() == true){
			System.out.println("This is the gear filtered by " + choice + ":");
		}
		else{
			System.out.println("There are no pieces of gear in the collection");
		}
		
		while(collection.hasNext() == true){
			if(index == 10){
				space = space.replaceFirst(" ", "");
			}
			gear = collection.next();
			//Only print gear if it is of the Boots class
			if(gear instanceof Boots)
				if (choice.compareTo("BOOTS") == 0){
					System.out.println(space +index + ") " + gear.toString());
				}
			//Only print gear if it is of the Pants class
			if(gear instanceof Pants)
				if (choice.compareTo("PANTS") == 0){
					System.out.println(space +index + ") " + gear.toString());
				}
			//Only print gear if it is of the Chest class
			if(gear instanceof Chest)
				if (choice.compareTo("CHEST") == 0){
					System.out.println(space +index + ") " + gear.toString());
				}
			
			index++;
		}

		System.out.println(userOptions);
		choice = scanner.nextLine().toUpperCase();
		return choice;
	}
	
	/**
	 * Returns the choice of the player. This includes choices
	 * like which gear to equip and the index of the chosen
	 * gear.
	 * @return the choice of the user
	 */
	public String returnChoice(){
		String choice;
		
		//Shows the gear
		System.out.println("This is the gear:");
		showGear();
		
		//Print the options for the user
		System.out.println(userOptions);
		
		//Get the user's input
		choice = scanner.nextLine().toUpperCase();
		
		return choice;
	}
	
	/**
	 * Calls the mergesort in gear collection
	 * @param choice The choice of the user which is what statistic to sort by
	 */
	public void sort(String choice){
		collection.setHead(collection.mergeSort(collection.getHead(), choice));
	}

	private GearCollection<Gear> collection; // To hold the gear

	private final int COLLECTION_SIZE = 10, // Maximum number of gear
			MAXIMUM_ITEM_VALUE = 20; // Maximum value of a piece of gear
	
	private Scanner scanner = new Scanner(System.in);
	
	//The user's options
	private String userOptions = "------------------------------------------------------------------\n"
							+ "-Enter EQUIP to equip.\n"
							+"-Enter QUIT to quit.\n"
							+"-Enter BOOTS, PANTS, or CHEST to filter by gear type.\n"
							+"-Enter POWER, DEFENSE, or ACCURACY to sort by statistic type:";
}