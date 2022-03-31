import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

public class CoffeeMakerQuestTest {

	CoffeeMakerQuest cmq;
	Player player;
	Room room1;	// Small room
	Room room2;	// Funny room
	Room room3;	// Refinanced room
	Room room4;	// Dumb room
	Room room5;	// Bloodthirsty room
	Room room6;	// Rough room

	@Before
	public void setup() {
		// 0. Turn on bug injection for Player and Room.
		Config.setBuggyPlayer(true);
		Config.setBuggyRoom(true);
		
		// 1. Create the Coffee Maker Quest object and assign to cmq.
		cmq = CoffeeMakerQuest.createInstance();

		// TODO: 2. Create a mock Player and assign to player and call cmq.setPlayer(player). 
		// Player should not have any items (no coffee, no cream, no sugar)
		player = Mockito.mock(Player.class);
		
		Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkSugar()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(false);
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		cmq.setPlayer(player);

		// TODO: 3. Create mock Rooms and assign to room1, room2, ..., room6.
		// Mimic the furnishings / adjectives / items of the rooms in the original Coffee Maker Quest.
		room1 = Mockito.mock(Room.class);
		room2 = Mockito.mock(Room.class);
		room3 = Mockito.mock(Room.class);
		room4 = Mockito.mock(Room.class);
		room5 = Mockito.mock(Room.class);
		room6 = Mockito.mock(Room.class);

		Mockito.when(room1.getFurnishing()).thenReturn("Quaint sofa");
		Mockito.when(room1.getAdjective()).thenReturn("Small");
		Mockito.when(room1.getItem()).thenReturn(Item.CREAM);

		Mockito.when(room2.getFurnishing()).thenReturn("Sad record player");
		Mockito.when(room2.getAdjective()).thenReturn("Funny");
		Mockito.when(room2.getItem()).thenReturn(Item.NONE);

		Mockito.when(room3.getFurnishing()).thenReturn("Tight pizza");
		Mockito.when(room3.getAdjective()).thenReturn("Refinanced");
		Mockito.when(room3.getItem()).thenReturn(Item.COFFEE);

		Mockito.when(room4.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(room4.getAdjective()).thenReturn("Dumb");
		Mockito.when(room4.getItem()).thenReturn(Item.NONE);

		Mockito.when(room5.getFurnishing()).thenReturn("Beautiful bag of money");
		Mockito.when(room5.getAdjective()).thenReturn("Bloodthirsty");
		Mockito.when(room5.getItem()).thenReturn(Item.NONE);

		Mockito.when(room6.getFurnishing()).thenReturn("Perfect air hockey table");
		Mockito.when(room6.getAdjective()).thenReturn("Rough");
		Mockito.when(room6.getItem()).thenReturn(Item.SUGAR);


		// TODO: 4. Add the rooms created above to mimic the layout of the original Coffee Maker Quest.
		cmq.addFirstRoom(room1);
		cmq.addRoomAtNorth(room2, "Magenta", "Massive");
		cmq.addRoomAtNorth(room3, "Beige", "Smart");
		cmq.addRoomAtNorth(room4, "Dead", "Slim");
		cmq.addRoomAtNorth(room5, "Vivacious", "Sandy");
		cmq.addRoomAtNorth(room6, "Purple", "Minimalist");
		
	}

	@After
	public void tearDown() {
		cmq = null;
		player = null;
		room1 = null;
		room2 = null;
		room3 = null;
		room4 = null;
		room5 = null;
		room6 = null;
	}
	
	/**
	 * Test case for String getInstructionsString().
	 * Preconditions: None.
	 * Execution steps: Call cmq.getInstructionsString().
	 * Postconditions: Return value is " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 */
	@Test
	public void testGetInstructionsString() {
		// DONE SHISHIR
		assertEquals("Must return the instructions", " INSTRUCTIONS (N,S,L,I,D,H) > ", cmq.getInstructionsString());
	}
	
	/**
	 * Test case for boolean addFirstRoom(Room room).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock room and assign to myRoom.
	 * Execution steps: Call cmq.addFirstRoom(myRoom).
	 * Postconditions: Return value is false.
	 */
	@Test
	public void testAddFirstRoom() {
		// DONE SHISHIR
		Room myRoom = Mockito.mock(Room.class);
		boolean rslt = cmq.addFirstRoom(myRoom);
		assertFalse("Should not be able to add room to already created rooms", rslt);
	}
	@Test
	public void testAddFirstRoomNull() {
		// DONE SHISHIR
		Room myRoom = Mockito.mock(Room.class);
		boolean rslt = cmq.addFirstRoom(null);
		assertFalse("Should not be able to add room to already created rooms", rslt);
	}

	@Test
	public void testAddFirstRoomTrue() {
		cmq = null;
		player = null;
		room1 = null;
		room2 = null;
		room3 = null;
		room4 = null;
		room5 = null;
		room6 = null;
		cmq = CoffeeMakerQuest.createInstance();

		// TODO: 2. Create a mock Player and assign to player and call cmq.setPlayer(player). 
		// Player should not have any items (no coffee, no cream, no sugar)
		player = Mockito.mock(Player.class);
		
		Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkSugar()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(false);
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		cmq.setPlayer(player);

		// TODO: 3. Create mock Rooms and assign to room1, room2, ..., room6.
		// Mimic the furnishings / adjectives / items of the rooms in the original Coffee Maker Quest.
		room1 = Mockito.mock(Room.class);
		room2 = Mockito.mock(Room.class);
		room3 = Mockito.mock(Room.class);
		room4 = Mockito.mock(Room.class);
		room5 = Mockito.mock(Room.class);
		room6 = Mockito.mock(Room.class);

		Mockito.when(room1.getFurnishing()).thenReturn("Quaint sofa");
		Mockito.when(room1.getAdjective()).thenReturn("Small");
		Mockito.when(room1.getItem()).thenReturn(Item.CREAM);

		Mockito.when(room2.getFurnishing()).thenReturn("Sad record player");
		Mockito.when(room2.getAdjective()).thenReturn("Funny");
		Mockito.when(room2.getItem()).thenReturn(Item.NONE);

		Mockito.when(room3.getFurnishing()).thenReturn("Tight pizza");
		Mockito.when(room3.getAdjective()).thenReturn("Refinanced");
		Mockito.when(room3.getItem()).thenReturn(Item.COFFEE);

		Mockito.when(room4.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(room4.getAdjective()).thenReturn("Dumb");
		Mockito.when(room4.getItem()).thenReturn(Item.NONE);

		Mockito.when(room5.getFurnishing()).thenReturn("Beautiful bag of money");
		Mockito.when(room5.getAdjective()).thenReturn("Bloodthirsty");
		Mockito.when(room5.getItem()).thenReturn(Item.NONE);

		Mockito.when(room6.getFurnishing()).thenReturn("Perfect air hockey table");
		Mockito.when(room6.getAdjective()).thenReturn("Rough");
		Mockito.when(room6.getItem()).thenReturn(Item.SUGAR);


		// TODO: 4. Add the rooms created above to mimic the layout of the original Coffee Maker Quest.
		assertTrue(cmq.addFirstRoom(room1));
	}

	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Fake bed" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is true.
	 *                 room6.setNorthDoor("North") is called.
	 *                 myRoom.setSouthDoor("South") is called.
	 */
	@Test
	public void testAddRoomAtNorthUnique() {
		// DONE SHISHIR
		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Fake bed");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		assertTrue(cmq.addRoomAtNorth(myRoom, "North", "South"));
		Mockito.verify(room6).setNorthDoor("North");
		Mockito.verify(myRoom).setSouthDoor("South");
	}
	
	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Flat energy drink" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is false.
	 *                 room6.setNorthDoor("North") is not called.
	 *                 myRoom.setSouthDoor("South") is not called.
	 */
	@Test
	public void testAddRoomAtNorthDuplicate() {

		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		assertFalse(cmq.addRoomAtNorth(myRoom, "North", "South"));
		Mockito.verify(room6, never()).setNorthDoor("North");
		Mockito.verify(myRoom, never()).setSouthDoor("South");
		
	}
	@Test
	public void testAddRoomAtNorthDuplicate2() {

		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Flat ene");
		Mockito.when(myRoom.getAdjective()).thenReturn("Small");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		assertFalse(cmq.addRoomAtNorth(myRoom, "North", "South"));
		Mockito.verify(room6, never()).setNorthDoor("North");
		Mockito.verify(myRoom, never()).setSouthDoor("South");
		
	}
	@Test
	public void testAddRoomAtNorthDuplicate3() {
		// TODO GRANT
		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		assertFalse(cmq.addRoomAtNorth(myRoom, null, "South"));
		Mockito.verify(room6, never()).setNorthDoor("North");
		Mockito.verify(myRoom, never()).setSouthDoor("South");
		
	}
	@Test
	public void testAddRoomAtNorthDuplicate4() {
		// TODO GRANT
		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		assertFalse(cmq.addRoomAtNorth(null, "North", null));
		Mockito.verify(room6, never()).setNorthDoor("North");
		Mockito.verify(myRoom, never()).setSouthDoor("South");
		
	}
	
	/**
	 * Test case for Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room) has not yet been called.
	 * Execution steps: Call cmq.getCurrentRoom().
	 * Postconditions: Return value is null.
	 */
	@Test
	public void testGetCurrentRoom() {
		assertNull(cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room room) has not yet been called.
	 * Execution steps: Call cmq.setCurrentRoom(room3).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(room3) is true. 
	 *                 Return value of cmq.getCurrentRoom() is room3.
	 */
	@Test
	public void testSetCurrentRoom() {
		
		assertTrue("should be allowed to go to room 3", cmq.setCurrentRoom(room3));
		assertEquals("return of getRoom should be room3", cmq.getCurrentRoom(), room3);
	}
	@Test
	public void testSetCurrentRoom2() {
		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		assertFalse("should not be allowed to go to room", cmq.setCurrentRoom(myRoom));
	}
	
	/**
	 * Test case for String processCommand("I").
	 * Preconditions: Player does not have any items.
	 * Execution steps: Call cmq.processCommand("I").
	 * Postconditions: Return value is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n".
	 */
	@Test
	public void testProcessCommandI() {
		// DONE SHISHIR
		String rslt = cmq.processCommand("I");
		
		assertEquals("Must display correct response", "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n", rslt);
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some creamy cream!\n".
	 *                 player.addItem(Item.CREAM) is called.
	 */
	@Test
	public void testProcessCommandLCream() {
		// TODO SHISHIR
		cmq.setCurrentRoom(room1);

		Mockito.when(room1.getItem()).thenReturn(Item.CREAM);
		String rslt = cmq.processCommand("l");
		//assertEquals("you should find nothing here", "You don't see anything out of the ordinary.\n", rslt);
		assertEquals("should find cream here", "There might be something here...\nYou found some creamy cream!\n", rslt);
		Mockito.verify(player).addItem(Item.CREAM);
	}
	@Test
    public void testProcessCommandLNoItem() {
        cmq.setCurrentRoom(room2);
        assertEquals("You don't see anything out of the ordinary.\n", cmq.processCommand("L"));
    }
	@Test
    public void testProcessCommandLSugar() {
        cmq.setCurrentRoom(room6);
        assertEquals("There might be something here...\nYou found some sweet sugar!\n", cmq.processCommand("L"));
        Mockito.verify(player).addItem(Item.SUGAR);
    }
	@Test
    public void testProcessCommandLCoffee() {
        cmq.setCurrentRoom(room3);
        assertEquals("There might be something here...\nYou found some caffeinated coffee!\n", cmq.processCommand("L"));
        Mockito.verify(player).addItem(Item.COFFEE);
    }
	
	/**
	 * Test case for String processCommand("n").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room4) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("n") is "".
	 *                 Return value of cmq.getCurrentRoom() is room5.
	 */
	@Test
	public void testProcessCommandN() {
		// TODO SHISHIR
		cmq.setCurrentRoom(room4);
		assertEquals("should allow to go north","",cmq.processCommand("n"));
		assertEquals("room should be changed to room5", cmq.getCurrentRoom(), room5);
	}
	
	
	/**
	 * Test case for String processCommand("s").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("s").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is "A door in that direction does not exist.\n".
	 *                 Return value of cmq.getCurrentRoom() is room1.
	 */
	@Test
	public void testProcessCommandS() {
		cmq.setCurrentRoom(room1);
		assertEquals("should not allow to go south","A door in that direction does not exist.\n",cmq.processCommand("s"));
		assertEquals("room shold not be changed", cmq.getCurrentRoom(), room1);

	}
	@Test
	public void testProcessCommandS2() {
		cmq.setCurrentRoom(room4);
		assertEquals("should  allow to go south","",cmq.processCommand("s"));
		assertEquals("room shold  be changed", cmq.getCurrentRoom(), room3);

	}
	@Test
    public void testProcessCommandH() {
        assertEquals("N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n", cmq.processCommand("H"));
    }
	
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has no items.
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDLose() {
		assertEquals("cannot win without winning", "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n", cmq.processCommand("D"));
		assertTrue(cmq.isGameOver());
	}
	@Test
    public void testProcessCommandDLoseHaveCoffee() {
        Mockito.when(player.checkCoffee()).thenReturn(true);
        Mockito.when(player.getInventoryString()).thenReturn("");
        assertEquals("\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n", cmq.processCommand("D"));
        assertTrue(cmq.isGameOver());
    }
    @Test
    public void testProcessCommandDLoseHaveSugar() {
        Mockito.when(player.checkSugar()).thenReturn(true);
        Mockito.when(player.getInventoryString()).thenReturn("");
        assertEquals("\nYou eat the sugar, but without caffeine, you cannot study.\nYou lose!\n", cmq.processCommand("D"));
        assertTrue(cmq.isGameOver());
    }
    @Test
    public void testProcessCommandDLoseHaveCream() {
        Mockito.when(player.checkCream()).thenReturn(true);
        Mockito.when(player.getInventoryString()).thenReturn("");
        assertEquals((Object)"\nYou drink the cream, but without caffeine, you cannot study.\nYou lose!\n", cmq.processCommand("D"));
        assertTrue(this.cmq.isGameOver());
    }
    @Test
    public void testProcessCommandDLoseHaveCreamAndSugar() {
        Mockito.when(player.checkCream()).thenReturn(true);
        Mockito.when(player.checkSugar()).thenReturn(true);
        Mockito.when(player.getInventoryString()).thenReturn("");
        assertEquals("\nYou drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n", cmq.processCommand("D"));
        assertTrue(cmq.isGameOver());
    }
	public void testProcessCommandDLoseHaveCreamAndCoffee() {
		Mockito.when(player.checkCoffee()).thenReturn(true);
        Mockito.when(player.checkCream()).thenReturn(true);
        Mockito.when(player.getInventoryString()).thenReturn("");
        assertEquals("\nWithout sugar, the coffee is too bitter. You cannot study.\nYou lose!\n", cmq.processCommand("D"));
        assertTrue(cmq.isGameOver());
    }
    public void testProcessCommandDLoseHaveCoffeeAndSugar() {
        Mockito.when(player.checkSugar()).thenReturn(true);
        
        Mockito.when(player.getInventoryString()).thenReturn("");
        assertEquals("\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n", cmq.processCommand("D"));
        assertTrue(cmq.isGameOver());
    }
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has all 3 items (coffee, cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDWin() {
		Mockito.when(player.checkCoffee()).thenReturn(true);
		Mockito.when(player.checkSugar()).thenReturn(true);
		Mockito.when(player.checkCream()).thenReturn(true);
		Mockito.when(player.getInventoryString()).thenReturn("You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n");
		assertEquals("you should win", "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n", cmq.processCommand("D"));
		assertTrue(cmq.isGameOver());
	}
	
	@Test
	public void isGameOverNo(){
		assertFalse(cmq.isGameOver());
	}
	@Test
	public void testWhat(){
		assertEquals("What?",cmq.processCommand("hoho"));
	}

    

}
