/**
 * This class defines and implements a collection of character gear.
 * The implementation is based on an array. This class tracks the head
 * of a linked list, and then the three types of gear that a character
 * can have equipped (boots, pants, and chest.) When a player chooses
 * a piece of gear to equip, an index is used to loop until that gear
 * is selected. Once selected, it is removed from the list and the
 * previous gear (if any) is put back in the list. An inner class Node
 * is used so that only GearCollection can use it.
 * @author David Iacono
 * CSC 212 Brown
 * 04/16/2013
 * @param <T> the Class of object to be stored
 */
public class GearCollection<T>
{
	private Node head;
	private Gear 	boots, 
					pants, 
					chest;
	private T selected;					//	Keeps track of the "selected" item

	/**
	 * Set up a collection that can hold a given maximum number of Gear.
	 * @param someMaximumSize
	 */
	public GearCollection(){
		selected = null;						//		and no selected item either
		head = null;
		boots = null;
		pants = null;
		chest = null;
	}

	/**
	 * Adds the given Gear to the collection.
	 * That Gear becomes the item currently selected.
	 * @param someItem
	 */ 
	public boolean add(T someGear){		
		Node newNode = new Node(someGear, head);
		head = newNode;
		selected = head.getData();

		return true;
	}


	/**
	 * The clear method remove all gear.
	 */
	public void clear()	{
		//Remove reference to the entire list by
		//discarding head and selected.
		head = null;
		selected = null;
	}

	/**
	 * 
	 * @return true if next() will return a piece of Gear
	 */
	public boolean hasNext(){
		//If there is something to return,
		//then there is a next.
		if (selected == null)
			return false;
		else
			return true;
	}

	/**
	 * @return the currently selected T
	 */
	public T next(){
		//The selected Gear
		T result = selected;
		//The current Node
		Node current = head;

		//Get the next Node until the current Node is empty
		while(current != null){
			//If selected gear is found
			if(current.getData() == selected){
				//Get the next node after
				current = current.getNextNode();

				if(current != null){
					selected = current.getData();
					current = null;
				}

				else{
					selected = null;
				}

			}

			else{
				current = current.getNextNode();
			}
		}
		//Return the item that was selected
		return result;				
	}

	/**
	 * Remove the selected Gear (if any)
	 * POSTCONDITION: No Gear is selected
	 */
	public void remove(){
		//Three nodes are tracked in case a first node should
		//be connected with a third node.
		Node firstNode = null, secondNode = null, thirdNode = null;
		boolean found = false;

		firstNode = head;
		//Only remove if the list has a head
		if (firstNode != null){
			//Assign a reference to a node for each variable.
			//Only assign a variable a node if there is a linked list
			if(firstNode.getNextNode() != null){
				secondNode = firstNode.getNextNode();
				if(secondNode.getNextNode() != null){
					thirdNode = secondNode.getNextNode();
				}
			}

			//If there is only one node, check for a match and remove
			//if there is a match.
			if(firstNode.getData() == selected){
				head = secondNode;
				selected = null;
				found = true;
			}

			//Run through the linked list and find the given item
			//to remove. If secondNode is null, then firstNode is
			//the last node in the linked list.
			while(secondNode != null && found == false){
				//If second node is selected, remove and
				//link first node to third node in sequence.
				if(secondNode.getData() == selected){
					firstNode.setNextNode(thirdNode);
					found = true;
					selected = null;
				}
				//If second node is not the selected Element,
				//advance one node in to the list
				else{
					firstNode = secondNode;
					secondNode = thirdNode;
					//If thirdNode is empty, no more nodes to 
					//search for
					if (thirdNode != null){
						thirdNode = thirdNode.getNextNode();

					}
				}
			}
		}
	}


	/**
	 * Select the gear at the beginning of the collection (if any)
	 */
	public void reset(){
		//If head has a node, return the node's element
		if (head != null)
			selected = head.getData();
		else
			selected = null;
	}

	/**
	 * If gearIndex is in the collection, select it
	 * @param someGear that might be in the collection
	 */
	public void reset(int gearIndex){
		Node current = head;
		for (int i = 0; i < gearIndex; i++){
			current = current.getNextNode();
		}
		selected = current.getData();
	}
	/**
	 * Replaces the old piece gear with a new piece of to be equipped
	 * @param newGear The new piece of gear to set as equipped
	 * @return The old piece of gear to put back in the collection
	 */
	public Gear setGear(Gear newGear){
		Node current = head;
		Node previous = null;
		Boolean found = false;
		
		Gear oldGear = null;
		
		//If the list is empty, then don't set any gear
		if(head == null){
			System.out.println("Empty list!");
		}
		else{
			//Find the piece of gear in the collection
			while(current != null && found == false){
				//Once found, move on
				if(current.getData() == newGear){
					found = true;
				}
				//Keep going until found
				else{
					previous = current;
					current = current.getNextNode();
				}
			}
		}
		//Find which child class of Gear that the gear falls in to and set it as the
		//equipped piece of gear
		if(newGear instanceof Boots){
			oldGear = boots;
			boots = newGear;
		}
		if(newGear instanceof Pants){
			oldGear = pants;
			pants = newGear;
		}
		if(newGear instanceof Chest){
			oldGear = chest;
			chest = newGear;
		}
		
		//Relink the collection to skip over the newly equipped piece of gear
		if(previous == null){
			head = head.getNextNode();
		}
		else{
			previous.setNextNode(current.getNextNode());
		}
		return oldGear;
	}
	
	/**
	 * 
	 * @return Gear of type Boots
	 */
	public Gear getBoots(){
		return boots;
	}
	
	/**
	 * 
	 * @return Gear of type Pants
	 */
	public Gear getPants(){
		return pants;
	}
	
	/**
	 * 
	 * @return Gear of type Chest
	 */
	public Gear getChest(){
		return chest;
	}
	
	/**
	 * 
	 * @return The head of the linked collection
	 */
	public Node getHead(){
		return head;
	}
	
	/**
	 * 
	 * @param newHead The new head of the linked collection
	 */
	public void setHead(Node newHead){
		head = newHead;
		selected = head.getData();
	}
	
	/**
	 * 
	 * @param newHead The new head of the linked list being passed in
	 * @param choice The choice of the user, which would be POWER, DEFENSE, or ACCURACY
	 * @return a list of merged lists sorted in ascending order
	 */
	public Node mergeSort(Node newHead, String choice){
		Node result;
		//If head or head's next null, then return
		if (newHead.getNextNode() == null){
			result = newHead;
		}
		else{
			Node left, right;
			left = newHead;
			right = newHead.getNextNode();
			
			//If right and right's next has Node
			while(right.getNextNode() != null && right.getNextNode().getNextNode() != null){
				//
				left = left.getNextNode();
				right = right.getNextNode().getNextNode();
			}
			
			right = left.getNextNode();
			left.setNextNode(null);
			left = newHead;
			
			//Split the lists and sort them
			left = mergeSort(left, choice);
			right = mergeSort(right, choice);
			
			//Merge the sorted lists together
			result = merge(left, right, choice);
		}
		return result;
	}
	
	/**
	 * 
	 * @param left The first list to merge
	 * @param right The second list to merge
	 * @param choice The choice of the user
	 * @return A sorted list by ascending order
	 */
	public Node merge(Node left, Node right, String choice){
		Node current = null, tempHead = current;
		Gear leftGear, rightGear;
		int leftInt, rightInt;
		
		//Initialize the new list of gear
		leftGear = (Gear)left.getData();
		rightGear = (Gear)right.getData();
		
		//Determine the choice
		if(choice.compareTo("POWER") == 0){
			leftInt = leftGear.getPower();
			rightInt = rightGear.getPower();
		}
		else if(choice.compareTo("DEFENSE") == 0){
			leftInt = leftGear.getDefense();
			rightInt = rightGear.getDefense();
		}
		else{
			leftInt = leftGear.getAccuracy();
			rightInt = rightGear.getAccuracy();
		}

		if(leftInt >= rightInt){
			tempHead = left;
			left = left.getNextNode();
		}
		else{
			tempHead = right;
			right = right.getNextNode();
		}

		//Initializes the new head
		current = tempHead;
		current.setNextNode(null);
		
		while ((left != null) && (right != null)){
			//Get the gear
			leftGear = (Gear)left.getData();
			rightGear = (Gear)right.getData();
			
			//Get the statistic based on the choice
			if(choice.compareTo("POWER") == 0){
				leftInt = leftGear.getPower();
				rightInt = rightGear.getPower();
			}
			else if(choice.compareTo("DEFENSE") == 0){
				leftInt = leftGear.getDefense();
				rightInt = rightGear.getDefense();
			}
			else{
				leftInt = leftGear.getAccuracy();
				rightInt = rightGear.getAccuracy();
			}

			if (leftInt >= rightInt){
				current.setNextNode(left);
				left = left.getNextNode();
			}
			else{
				current.setNextNode(right);
				right = right.getNextNode();
			}
			
			//Add it to the new sorted list
			current = current.getNextNode();
			current.setNextNode(null);
		}
		
		//Append any any tail list to the new sorted list
		if(left != null)
			current.setNextNode(left);
		else
			current.setNextNode(right);
		
		//Return the sorted list
		return tempHead;
	}
	
	/**
	 * 
	 * @return The size of the list
	 */
	public int getSize(){
		return getSize(head);
	}
	
	/**
	 * Recursively count the Nodes in the list
	 * @param current
	 * @return if there is a Node or not represented by its tail count + 1
	 */
	public int getSize(Node current){
		if (current == null){
			return 0;
		}
		else{
			return (1 + getSize(current.getNextNode()));
		}
	}
	
	/**
	 * Inner class of the GearCollection class that can be used to create nodes of a linked collection
	 * @author David Iacono
	 *
	 */
	public class Node {

		/**
		 * Instantiates a node with a Gear and empty next
		 * @param dataPortion An Element item
		 */
		public Node(T dataPortion){
			this(dataPortion, null);
		}

		/**
		 * Instantiates a node with a Gear and a link to the next node
		 * @param dataPortion a Gear
		 * @param nextNode The reference to the next node in the list
		 */
		public Node(T dataPortion, Node nextNode){
			data = dataPortion;
			next = nextNode;
		}

		/**
		 * @return the reference to Gear
		 */
		public T getData(){
			return data;
		}

		/**
		 * @param newData Assigns the node a reference to Gear
		 */
		public void setData(T newData){
			data = newData;
		}

		/**
		 * 
		 * @return The next node in the linked list
		 */
		public Node getNextNode(){
			return next;
		}

		/**
		 * Assigns the link to the next node
		 * @param nextNode The reference to the new next node
		 */
		public void setNextNode(Node nextNode){
			next = nextNode;
		}

		private T data; //The piece of gear
		private Node next; //The next Node in the list

	}

}