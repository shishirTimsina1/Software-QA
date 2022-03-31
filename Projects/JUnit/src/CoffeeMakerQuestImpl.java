import java.util.*;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	// TODO: Add more member variables and methods as needed.
	// datastructure to hold the rooms probably an arraylist
	// player variables
	ArrayList<Room>rooms = new ArrayList<Room>();
	Player player;
	int roomNumber;
	boolean checkWon;


	CoffeeMakerQuestImpl() {
		// TODO
		roomNumber = -1;
		checkWon = false;
		rooms = new ArrayList<Room>();
	}

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean isGameOver() {
		//TODO
		if (checkWon)
			return true;
		return false;
	}

	/**
	 * Set the player to p.
	 * 
	 * @param p the player
	 */
	public void setPlayer(Player p) {
		//TODO
		if(player == null)
			player = p;
	}
	
	/**
	 * Add the first room in the game. If room is null or if this not the first room
	 * (there are pre-exiting rooms), the room is not added and false is returned.
	 * 
	 * @param room the room to add
	 * @return true if successful, false otherwise
	 */
	public boolean addFirstRoom(Room room) {
		
		if(room == null)
			return false;
		//System.out.println("***************************");
		//System.out.println(room.getAdjective());
		if(rooms.isEmpty()){
			//roomNumber = 1;
			return rooms.add(room);
		}
		return false;
	}

	/**
	 * Attach room to the northern-most room. If either room, northDoor, or
	 * southDoor are null, the room is not added. If there are no pre-exiting rooms,
	 * the room is not added. If room is not a unique room (a pre-exiting room has
	 * the same adjective or furnishing), the room is not added. If all these tests
	 * pass, the room is added. Also, the north door of the northern-most room is
	 * labeled northDoor and the south door of the added room is labeled southDoor.
	 * Of course, the north door of the new room is still null because there is
	 * no room to the north of the new room.
	 * 
	 * @param room      the room to add
	 * @param northDoor string to label the north door of the current northern-most room
	 * @param southDoor string to label the south door of the newly added room
	 * @return true if successful, false otherwise
	 */
	public boolean addRoomAtNorth(Room room, String northDoor, String southDoor) {
		// TODO
		if(room == null || northDoor == null || southDoor == null || rooms.size() == 0)
			return false;
		for (Room eachRoom : rooms){
			if( eachRoom.getAdjective().equals(room.getAdjective())) return false;
			if (eachRoom.getFurnishing().equals(room.getFurnishing())) return false;
		}
		rooms.get(rooms.size()-1).setNorthDoor(northDoor);
		room.setSouthDoor(southDoor); 
		//System.out.println(room.getAdjective());
		return rooms.add(room);
		
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */ 
	public Room getCurrentRoom() {
		// TODO
		if(rooms.isEmpty()) return null;
		if(roomNumber<=0 ) return null;
		return rooms.get(roomNumber);
	}
	
	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {	
		if(rooms.indexOf(room) == -1)
			return false;
		else {
			roomNumber = rooms.indexOf(room);
			//System.out.println(roomNumber + "*****************");
			return true;
		}
	}
	
	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return comamnd prompt string
	 */
	public String getInstructionsString() {
		
		return " INSTRUCTIONS (N,S,L,I,D,H) > ";
	}
	
	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please see the Coffee Maker Quest
	 * requirements documentation (note that commands can be both upper-case and
	 * lower-case). For the response strings, observe the response strings printed
	 * by coffeemaker.jar. The "N" and "S" commands potentially change the location
	 * of the player. The "L" command potentially adds an item to the player
	 * inventory. The "D" command drinks the coffee and ends the game. Make
     * sure you use Player.getInventoryString() whenever you need to display
     * the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		// TODO
		switch(cmd){

			case"N": case "n":
				if(roomNumber < rooms.size()){	
					roomNumber++;
					return "";
				}
				return "A door in that direction does not exist.\n";

			case"S": case "s":
				if(roomNumber>0){
					roomNumber--;
					return "";
				}
				return "A door in that direction does not exist.\n";

			case "L": case "l":
				Room r = rooms.get(roomNumber);
				String s = doLook();
				player.addItem(r.getItem());
				return s;
			case "I": case "i":
				return player.getInventoryString();
			case"D": case"d":
				String f = player.getInventoryString();
				return f + "\n" + doWin();
			case"H": case"h":
				return "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n";
			default:
				return "What?";
		}
	}
	
	private String doLook(){
		StringBuilder s = new StringBuilder();
		//System.out.println(roomNumber + "*****************");
		Room r = rooms.get(roomNumber);
		if(r.getItem() == Item.NONE)
			s.append("You don't see anything out of the ordinary.\n");
		else{
			s.append("There might be something here...\nYou found some ");
			switch(r.getItem()){
				case COFFEE:
					s.append("caffeinated coffee!\n");
					break;
				case CREAM:
					s.append("creamy cream!\n");
					break;
				case SUGAR:
					s.append("sweet sugar!\n");
					break;
			}

		}
		//System.out.println(s.toString());
		return s.toString();
	}
	private String doWin(){
		StringBuilder s = new StringBuilder();
		if(player.checkCream() && player.checkSugar() && player.checkCoffee()){

			s.append("You drink the beverage and are ready to study!\nYou win!\n");
			checkWon = true;
			return s.toString();
		}
		else {
			if(player.checkCoffee() && player.checkSugar() && !player.checkCream())
				s.append("Without cream, you get an ulcer and cannot study.\n");
			else if(player.checkSugar() && player.checkCream() && !player.checkCoffee())
				s.append("You drink the sweetened cream, but without caffeine you cannot study.\n");
			else if(player.checkCoffee() && player.checkCream() && !player.checkSugar())
				s.append("Without sugar, the coffee is too bitter. You cannot study.\n");
			else if(player.checkCoffee())
				s.append("Without cream, you get an ulcer and cannot study.\n");
			else if(player.checkSugar())
				s.append("You eat the sugar, but without caffeine, you cannot study.\n");
			else if(player.checkCream())
				s.append("You drink the cream, but without caffeine, you cannot study.\n");
			else
				s.append("You drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\n");
		}
		s.append("You lose!\n");
		checkWon = true;
		return s.toString();
	}
}
