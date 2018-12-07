package game;

import java.text.DecimalFormat;

/**
 * Game.java
 * 
 * Class to model the game as a collection of rooms. The
 * rooms are organized as a graph, and the Room objects
 * are nodes in the graph.
 *
 * @author Thomas VanDrunen
 * Wheaton College, CS 245, Spring 2007
 * Lab 5
 * Feb 8, 2007
 */

public class Game {
	
	private int hour = 11;
	private int min = 00;
	private int sec = 00;
	
    /**
     * The current room the user is in. This serves two
     * purposes-- it is our only permanent connection to
     * the rooms in this game (the other rooms are reachable
     * by traversing this room's "doors"-- and it maintains
     * the state by representing the user's current location.
     */
    private Room currentRoom;
    
    /**
 	 * The current item that contains something that the user
 	 * might be looking at. This variable is NULL if the user
 	 * is not looking at a ContainerItem.
     */
    private Container currentBox;
    
    /**
     * The player who is playing. This refers to the object of
     * the character who is being played by the user.
     */
    private Player firstPlayer;

    /**
     * Keeps track of whether this game is over or not.
     */
    private boolean over;
    
    /**
     * The fire extinguisher. Needs to be kept track of.
     */
    private Extinguisher extinguisher = new Extinguisher("fire extinguisher", false);
    
    /**
     * The final key to the main entrance. Needs to be kept track of.
     */
    private Key finalKey;
    
    /**
     * The fire extinguisher. Needs to be kept track of.
     */
    private Room foyer;
    
    /**
     * The outside of the mansion. If you are here, you've won the game.
     */
    private Room win;
    
    /**
     * return the winning room.
     * @return win
     */
    public Room winRoom() { return win; }
    
    /**
     * return the room for the foyer.
     * @return the foyer.
     */
    public Room getFoyer() { return foyer; }
    
    /**
     * return the key for the main entrance.
     * @return the final key.
     */
    public Key getFinalKey() { return finalKey; }
    
    /**
     * return the item for the fire extinguisher.
     * @return the extinguisher
     */
    public Extinguisher getExtinguisher() { return extinguisher; }
    
    /**
     * Return the room in which the user is currently.
     */
    public Room getCurrentRoom() { return currentRoom; }
    
    /**
     * Return the current box.
     * @return the current box.
     */
    public Container getCurrentBox() { return currentBox; }
    
    /**
     * Look at a ContainerItem.
     */
    public void setCurrentBox(Container currentBox) { this.currentBox = currentBox; }
    
    /**
     * Return the player which the user is playing.
     */
    public Player getFirstPlayer() { return firstPlayer; }
    
    /**
     * Whether the game has been won or not.
     * @return whether the current room is the winning room, the outside.
     */
    public boolean winCondition() {
    	return currentRoom == win;
    }
    
    /**
     * Constructor to set up the game.
     */
    public Game() {

    	firstPlayer = new Player();

        
        foyer = new Room("foyer", false);
        foyer.setDescription("You enter a grand foyer with a front entrance to the mansion. \n"
        		+ "There are many pieces of extravagant furniture in the foyer.\n"
        		+ "There is a granfather clock in the northeastern corner.\n"
        		+ "*sniff sniff* Do you smell something burning? \n"
        		+ "The main entrance of the mansion is to the north!\n"
        		+ "You try to open the door, but it is locked.\n"
        		+ "You realize will have to find the key to escape. \n"
        		+ "There are more doors to the south, west, and east.");
        Room den = new Room("den", false);
        den.setDescription("You come into a den.\nSofa's and lounge chairs are all over the room\n"
        		+ "There is a fireplace on the east side and a closet in the corner.\n"
        		+ "You see a door to the west.");
        Room living = new Room("living", false);
        living.setDescription("This room is a living room.\nThere are many different types of game hung on the wall.\n"
        		+ "A couch sits with a coffee table in front of it. There is a TV.\n"
        		+ "You see a giant chest in one corner of the room and a closet in the other.\n"
        		+ "You see a door to the east and south.");
        Room billiards = new Room("billiards", false);
        //key in billiards
        billiards.setDescription("You are in a billiards room. There is pool table in the middle\n"
        		+ "Cue sticks hang on the wall. There is a chest.\n"
        		+ "You see a door to the north, east, and south");
        Room stairsMain = new Room("stairsMain", false);
        stairsMain.setDescription("You enter a great hall on the main floor with a stairway going up.");
        Room bathroomMain = new Room("bathroomMain", false);
        bathroomMain.setDescription("You are in the powder room.\n"
        		+ "A huge portrait of the Olympic Champions Zach Van Dyke, Asher Bernardi, and Micah Kimel.\n"
        		+ "There is cupboard above the sink.\n"
        		+ "You see a door the west");
        Room dining = new Room("dining", false);
        dining.setDescription("This room is a large dining room with a table made of unique stone\n"
        		+ "There is big cupboard that looks like it is full of plates, bowls, and silverware.\n"
        		+ "You see a door to the north and east.");
        Room kitchen = new Room("kitchen", false);

        kitchen.setDescription("You enter a kitchen perfect for cooking a five guys burger.\n"
        		+ "There's a sink, a counter, a dishwasher, and a stove."
        		+ "You see a door to a pantry, and doors to the north and west.");

        Room starting = new Room("starting", false);
        starting.setDescription("You are in a guest room that seems to also be a music room filled with a rare collection of CDs.\n"
        		+ "There is a bed for you to sleep on.\n"
        		+ "There is a closet and a dresser for you to put your clothes in.\n"
        		+ "You see doors to the south, and west.");
        Room hallwayOne = new Room("hallwayOne", false);
        hallwayOne.setDescription("You enter a hallway with extravagant paintings, one of which is quasimodo.\n"
        		+ "You see a great chest against the wall."
        		+ "You see doors to the west, east and south.");
        Room office = new Room("office", false);
        office.setDescription("This room seems to be a study room, with Michael Scott's worlds best boss mug to give some motivation.\n"
        		+ "There is a desk with a drawer and a closet. You see doors to the east and south.");
        Room masterBed = new Room("masterBed", false);
        masterBed.setDescription("Wow this room is a really nice master bedroom, with such a nice bed you could sleep for days...."
        		+ "\nZZZzzzZZZzzzZZZzzz"
        		+ "\nZZZzzzZZZzzzZZZzzz"
        		+ "\nwait what???"
        		+ "\nYou look around and see a dresser, a closet, a vanity with a drawer, and a TV."
        		+ "\nYou see a door to the south and north.");
        Room masterBath = new Room("masterBath", false);
        masterBath.setDescription("You enter a master bathroom, with an aquarium."
        		+ "\nYou find a note, it reads:\n"
        		+ "   \"I've been trapped for days."
        		+ "\n    Whatever you do don't come knocking.\"\n"
        		+ "You peak behind the shower curtain, then you really wish you hadn't...\n"
        		+ "There is a cupboard above the sink and a small box on the floor in the corner of the room.\n"
        		+ "You see a door to the north");
        Room guestBath = new Room("guestBath", false);
        guestBath.setDescription("This room is the guest bathroom, nice, quite small..."
        		+ " but at least it is clean.\n"
        		+ "There is a sink, a shower, and a toilet: All the essentials.\n"
        		+ "You notice a cupboard above the sink.\n"
        		+ "You see a door to the west, north, and south.");
        Room guestBed = new Room("guestBed", false);
        guestBed.setDescription("You enter a guest bedroom, quite small... possibly for the mother in law\n"
        		+ "There is a closet and a dresser.\n"
        		+ "You see a door to the west, north, and south.");
        Room hallwayTwo = new Room("hallwayTwo", false);
        hallwayTwo.setDescription("You enter a hallway full of pictures of your family...\n"
        		+ "Where did they get pictures of your family???\n"
        		+ "You see doors to the east, west, and north.");
        Room stairsUpper = new Room("stairsUpper", false);
        stairsUpper.setDescription("You are at the top landing of a grand staircase.\n"
        		+ "It leads down to first floor.");
        Room pantry = new Room("stairsKitchen", false);
        pantry.setDescription("You are in the pantry\n"
        		+ "There is much foodies on all the shelvesies!\n"
        		+ "You open a can of spam and endulge to your heart's desire,\n"
        		+ "gorging yourself and spilling the meat all over your clothes.\n"
        		+ "You feel ashamed of your glutony.\n"
        		+ "SHAME!!!\n"
        		+ "Wait... you see a trap door on the floor...");
        Room cellar = new Room("cellar", false);
        cellar.setDescription("This is the cellar a dark gloomy place, but wait, you see your precious shiny glowing ring in the corner.\n"
        		+ "You pick it up and chant my precious, my precious, my precious, until waking up and realizing your were\n"
        		+ "only just hallucinating and it is really just a picture of Dr. Devin J. Pohly.\n"
        		+ "You see a room to the west, and stairs.");
        Room workshop = new Room("workshop", false);
        workshop.setDescription("You enter a workshop.\n"
        		+ "There are many scary tools hanging on the walls.\n"
        		+ "Many tables are set up against the walls.\n"
        		+ "(Is that blood you see dripping from the tables?!)\n"
        		+ "There is a a closet in the southwestern corner of the room.\n"
        		+ "A fire extinguisher is in a glass case.\n"
        		+ "You see a door to the east.");
        win = new Room("win", false);
        win.setDescription("What?!?!? You beat the game? No Way, Devin J. Pohly himself is here to congratulate you. \n"
        		+ "he tells you to find the owner, they owe you a five guys burger for winning");
        
    
        
        ///// SETTING THE CONNECTIONS BETWEEN ALL THE ROOMS //////
        
        // Set the directions from stairsMain.
        Lock stairsFoyer = new Lock(false);
        Key stairsFoyerKey = new Key("stairsfoyerkey", stairsFoyer);
        stairsMain.setNorth(foyer, stairsFoyer);
        Lock stairsBilliards = new Lock(true);
        Key stairsBilliardsKey = new Key("stairsbilliardskey", stairsBilliards);
        stairsMain.setWest(billiards, stairsBilliards);
        Lock stairsBathroom = new Lock(false);
        Key stairsBathroomKey = new Key("stairsbathroomkey", stairsBathroom);
        stairsMain.setEast(bathroomMain, stairsBathroom);
        Lock stairsMainLock = new Lock(false);
        Key stairsMainLockKey = new Key("stairsmainlockkey", stairsMainLock);
        stairsMain.setDirection("stairs", stairsUpper, stairsMainLock);
        
        
        // Set the direction from foyer.
        foyer.setSouth(stairsMain, stairsFoyer);
        Lock foyerLiving = new Lock(false);
        Key foyerLivingKey = new Key("foyerlivingkey", foyerLiving);
        foyer.setWest(living, foyerLiving);
        Lock foyerDen = new Lock(true);
        Key foyerDenKey = new Key("den key", foyerDen);
        foyer.setEast(den, foyerDen);
        Lock finalLock = new Lock(true);
        finalKey = new Key("main entrance key", finalLock);
        foyer.setNorth(win, finalLock);

        
        // Set directions from living.
        living.setEast(foyer, foyerLiving);
        Lock livingBilliards = new Lock(false);
        Key livingBilliardsKey = new Key("livingbilliardskey", livingBilliards);
        living.setSouth(billiards, livingBilliards);
        
        
        // Set directions from billiard.
        billiards.setNorth(living, livingBilliards);
        billiards.setEast(stairsMain, stairsBilliards);
        Lock billiardsDining = new Lock(true);
        Key billiardsDiningKey = new Key("dining room key", billiardsDining);
        billiards.setSouth(dining, billiardsDining);
        
        
        // Set directions from dining.
        dining.setNorth(billiards, billiardsDining);
        Lock diningKitchen = new Lock(false);
        Key diningKitchenKey = new Key("diningkitchenkey", diningKitchen);
        dining.setEast(kitchen, diningKitchen);
 

        // Set directions from kitchen.
        kitchen.setWest(dining, diningKitchen);
        Lock kitchenBathroom = new Lock(true);
        Key kitchenBathroomKey = new Key("kitchenbathroomkey", kitchenBathroom);
        kitchen.setNorth(bathroomMain, kitchenBathroom);
        Lock kitchenPantry = new Lock(false);
        Key kitchenPantryKey = new Key("pantry key", kitchenPantry);
        kitchen.setDirection("pantry", pantry, kitchenPantry);
        
        
        // Set directions from pantry.
        pantry.setDirection("kitchen", kitchen, kitchenPantry);
        Lock pantryCellarLock = new Lock(true);
        Key pantryCellarLockKey = new Key("cellar key", pantryCellarLock);
        pantry.setDirection("trap door", cellar, pantryCellarLock);
        
        
        // Set directions from cellar.
        cellar.setDirection("trap door", pantry, pantryCellarLock);
        Lock cellarWorkshop = new Lock(false);
        Key cellarWorkshopKey = new Key("cellarworkshopkey", cellarWorkshop);
        cellar.setWest(workshop, cellarWorkshop);
        
        
        Container glassCase = new Container("glass case");
        glassCase.setDescription("The glass case contains a red, dented fire extinguisher.");
        glassCase.addItem(extinguisher);
        workshop.addItem(new ContainerItem(glassCase));
        
        Container workBench = new Container("work bench");
        workBench.setDescription("a beaten dusty plywood work bench, precariously balanced on two barrels.\non it there is an ugly burnt orange tool box");
        Container toolBox = new Container("tool box");
        toolBox.setDescription("inside there is a key labeled \"main entrance.\"");
        toolBox.addItem(finalKey);
        workBench.addItem(new ContainerItem(toolBox));
        workshop.addItem(new ContainerItem(workBench));
        
        // Set directions from workshop.
        workshop.setEast(cellar, cellarWorkshop);
        

        // Set directions from bathroomMain.
        bathroomMain.setWest(stairsMain, stairsBathroom);


        // Set directions from den.
        den.setWest(foyer, foyerDen);

        
        // Set directions from stairsUpper.
        Lock stairsHW2 = new Lock(true);
        Key stairsHW2Key = new Key("stairs key", stairsHW2);
        stairsUpper.setSouth(hallwayTwo, stairsHW2);
        Lock stairsHW1 = new Lock(true);
        Key stairsHW1Key = new Key("north stairs key", stairsHW1);
        stairsUpper.setNorth(hallwayOne, stairsHW1);
        stairsUpper.setDirection("stairs", stairsMain, stairsMainLock);


        // Set directions from hallwayOne.
        Lock hw1Starting = new Lock(true);

        Key hw1StartingKey = new Key("hallway key", hw1Starting);

        hallwayOne.setEast(starting, hw1Starting);
        Lock hw1Office = new Lock(false);
        hallwayOne.setWest(office, hw1Office);
        hallwayOne.setSouth(stairsUpper, stairsHW1);

        Container hallOneContainer = new Container("chest");
        /*
        hallOneContainer.addItem(stairsHW2Key);
        ContainerItem hallOneitem = new ContainerItem("chest", hallOneContainer);
        */
        ContainerItem hallOneitem = new ContainerItem(hallOneContainer);



        Lock guestBathChestLock = new Lock(true);
        Key guestBathChestLockKey = new Key("closet key", guestBathChestLock);
        guestBath.addLockedItem(hallOneitem, guestBathChestLock);


        
        // Set directions from office.
        office.setEast(hallwayOne, hw1Office);
        Lock officeMaster = new Lock(true);
        Key officeMasterKey = new Key("master bedroom key", officeMaster);
        office.setSouth(masterBed, officeMaster);
        

        // Set directions from masterBed.
        Lock masterMasterBath = new Lock(false);
        Key masterMasterBathKey = new Key("master bathroom key", masterMasterBath);
        masterBed.setSouth(masterBath, masterMasterBath);
        masterBed.setNorth(office, officeMaster);
        
        
        // Set directions from masterBath.
        masterBath.setNorth(masterBed, masterMasterBath);

        
        // Set directions from hallwayTwo.
        /*
        Lock hw2Master = new Lock(false);
        Key hw2MasterKey = new Key("hw2masterkey", hw2Master);
        hallwayTwo.setWest(masterBath, hw2Master);
        */
        hallwayTwo.setNorth(stairsUpper, stairsHW2);
        Lock hw2Guest = new Lock(false);
        Key hw2GuestKey = new Key("guest bedroom key", hw2Guest);
        hallwayTwo.setEast(guestBed, hw2Guest);
        
        
        // Set directions from guestBed.
        guestBed.setWest(hallwayTwo, hw2Guest);
        Lock guestGuestBath = new Lock(true);

        Key guestGuestBathKey = new Key("guest room key", guestGuestBath);
        hallOneContainer.addItem(guestGuestBathKey);
        guestBed.setNorth(guestBath, guestGuestBath);
        /*
        guestBath.setSouth(guestBed, guestGuestBath);
        Container guestBathContainer = new Container("cupboard");
        guestBathContainer.setDescription("the drawer has a key in it.");
        guestBathContainer.addItem(hw1StartingKey);
        ContainerItem guestBathCloset = new ContainerItem("cupboard", guestBathContainer);
        guestBath.addItem(guestBathCloset);
         */



        // Set directions from guestBath.
        Lock guestBathStarting = new Lock(true);
        Key guestBathStartingKey = new Key("bathroom key", guestBathStarting);
        guestBath.setNorth(starting, guestBathStarting);
        guestBath.setSouth(guestBed, guestGuestBath);

        
        // Set directions from starting.
        starting.setWest(hallwayOne, hw1Starting);
        starting.setSouth(guestBath, guestBathStarting);

        
        
        ///// ADDING ITEMS INTO ALL THE ROOMS /////
        
        // Starting items:
        // A closet
        Container startingCloset = new Container("wardrobe");
        startingCloset.setDescription("There are two hangers with nothing on them. A paperclip is on the floor of the wardrobe.");
        startingCloset.addItem(new Item("hanger", true));
        startingCloset.addItem(new Item("paperclip", true));
        starting.addItem(new ContainerItem(startingCloset));
        // 4 drawers
        Container startingDrawer1 = new Container("drawer 1");
        Container startingDrawer2 = new Container("drawer 2");
        Container startingDrawer3 = new Container("drawer 3");
        Container startingDrawer4 = new Container("drawer 4");
        startingDrawer1.setDescription("The drawer is empty.");
        startingDrawer2.setDescription("There is nothing but a severed thumb rolling around in the drawer. It looks rottens.");
        startingDrawer2.addItem(new Item("thumb", true));
        startingDrawer3.setDescription("You see a half eaten apple, a rusty key, and a belt.");
        startingDrawer3.addItem(guestBathStartingKey);
        startingDrawer3.addItem(new Item("apple core", true));
        startingDrawer3.addItem(new Item("belt", true));
        startingDrawer4.setDescription("the drawer is empty.");
        starting.addItem(new ContainerItem(startingDrawer1));
        starting.addItem(new ContainerItem(startingDrawer2));
        starting.addItem(new ContainerItem(startingDrawer3));
        starting.addItem(new ContainerItem(startingDrawer4));
        
        
        // guestBath items:
        // a cupboard
        Container guestBathCupboard = new Container("cupboard");
        guestBathCupboard.setDescription("There are many bottles of pills and a tube of toothpaste in the cupboard.\n"
        		+ "You dig behind the bottles and see a shiny silver key.");
        guestBathCupboard.addItem(new Item("bottle of pills", true));
        guestBathCupboard.addItem(new Item("toothpaste", true));
        guestBathCupboard.addItem(hw1StartingKey);
        guestBath.addItem(new ContainerItem(guestBathCupboard));

        
        // hallwayOne items:
        // A chest
        Container hw1Chest = new Container("chest");
        hw1Chest.setDescription("The chest has several books piled inside. There are a top hat and a scarf.\n"
        		+ "You notice a flash of light at the bottom. You reach down and feel a small metal key.");
        hw1Chest.addItem(new Item("book", true));
        hw1Chest.addItem(new Item("top hat", true));
        hw1Chest.addItem(new Item("scarf", true));
        hw1Chest.addItem(officeMasterKey);
        Lock hw1ChestLock = new Lock(true);
        Key hw1ChestKey = new Key("chest key", hw1ChestLock);
        hallwayOne.addLockedItem(new ContainerItem(hw1Chest), hw1ChestLock);
        
        
        // office items:
        // A desk drawer
        Container officeDeskDrawer = new Container("desk drawer");
        officeDeskDrawer.setDescription("The drawer contains a notebook, a pen, a flashlight, and a old-looking key.");
        officeDeskDrawer.addItem(new Item("notebook", true));
        officeDeskDrawer.addItem(new Item("pen", true));
        officeDeskDrawer.addItem(new Item("flashlight", true));
        officeDeskDrawer.addItem(hw1ChestKey);
        office.addItem(new ContainerItem(officeDeskDrawer));
        // A closet
        Container officeCloset = new Container("closet");
        officeCloset.setDescription("There are electronics in the closet: a modem, a router, a computer body, and many wires.");
        officeCloset.addItem(new Item("modem", false));
        officeCloset.addItem(new Item("router", false));
        officeCloset.addItem(new Item("computer", false));
        office.addItem(new ContainerItem(officeCloset));
        
        
        // masterBed items:
        // A vanity drawer
        Container masterBedVanityDrawer = new Container("vanity drawer");
        masterBedVanityDrawer.setDescription("The drawer contains some makeup and... oh look! A key!");
        masterBedVanityDrawer.addItem(new Item("lipstick", true));
        masterBedVanityDrawer.addItem(new Item("blush", true));
        masterBedVanityDrawer.addItem(new Item("mascara", true));
        masterBedVanityDrawer.addItem(new Item("foundation", true));
        masterBedVanityDrawer.addItem(new Item("nail polish", true));
        masterBedVanityDrawer.addItem(guestGuestBathKey);
        Lock masterBedVanityDrawerLock = new Lock(true);
        Key masterBedVanityDrawerKey = new Key("vanity drawer key", masterBedVanityDrawerLock);
        masterBed.addLockedItem(new ContainerItem(masterBedVanityDrawer), masterBedVanityDrawerLock);
        // 3 drawers
        Container masterBedDrawer1 = new Container("drawer 1");
        Container masterBedDrawer2 = new Container("drawer 2");
        Container masterBedDrawer3 = new Container("drawer 3");
        masterBedDrawer1.setDescription("The drawer is full of shirts.");
        masterBedDrawer1.addItem(new Item("shirt", true));
        masterBedDrawer2.setDescription("The drawer is full of pairs of pants.");
        masterBedDrawer2.addItem(new Item("pants", true));
        masterBedDrawer3.setDescription("The drawer is very dusty, but otherwise empty.");
        masterBed.addItem(new ContainerItem(masterBedDrawer1));
        masterBed.addItem(new ContainerItem(masterBedDrawer2));
        masterBed.addItem(new ContainerItem(masterBedDrawer3));
        // a closet
        Container masterBedCloset = new Container("closet");
        masterBedCloset.setDescription("The closet is full of expensive fur coats.");
        masterBedCloset.addItem(new Item("leopard-print coat", true));
        masterBedCloset.addItem(new Item("zebra-print coat", true));
        masterBed.addItem(new ContainerItem(masterBedCloset));
        
        
        // masterBath items:
        // a cupboard
        Container masterBathCupboard = new Container("cupboard");
        masterBathCupboard.setDescription("The cupboard is mostly empty, except for a lone, 3-inch strand of floss.");
        masterBathCupboard.addItem(new Item("floss", true));
        masterBath.addItem(new ContainerItem(masterBathCupboard));
        // a box
        Container masterBathBox = new Container("box");
        masterBathBox.setDescription("It is time for celebration! The box has a key in it!");
        masterBathBox.addItem(masterBedVanityDrawerKey);
        masterBath.addItem(new ContainerItem(masterBathBox));
        
        
        // guestBed items:
        // A closet (not to be opened)
        Container guestBedCloset = new Container("closet");
        guestBedCloset.setDescription("The closet is empty.");
        Lock guestBedClosetLock = new Lock(true);
        Key guestBedClosetKey = new Key("closet key", hw1ChestLock);
        guestBed.addLockedItem(new ContainerItem(masterBedCloset), guestBedClosetLock);
        // 2 drawers
        Container guestBedDrawer1 = new Container("drawer 1");
        Container guestBedDrawer2 = new Container("drawer 2");
        guestBedDrawer1.setDescription("A mouse scurries out of the drawer, then hides under the bed.\n"
        		+ "It left a piece of cheese in the drawer.");
        guestBedDrawer1.addItem(new Item("cheese", true));
        guestBedDrawer2.setDescription("Just socks in this drawer. You pick out a pair to try on.\n"
        		+ "*gasp* what's this? There is a key inside the sock!");
        guestBedDrawer2.addItem(new Item("socks", true));
        guestBedDrawer2.addItem(stairsHW2Key);
        guestBed.addItem(new ContainerItem(guestBedDrawer1));
        guestBed.addItem(new ContainerItem(guestBedDrawer2));
        
        
        // bathRoomMain items:
        // A cupboard
        Container bathroomMainCupboard = new Container("cupboard");
        bathroomMainCupboard.setDescription("Confetti burst out of the cupboard! Among the debris was a key.");
        bathroomMainCupboard.addItem(pantryCellarLockKey);
        Lock bathroomMainCupboardLock = new Lock(true);
        Key bathroomMainCupboardKey = new Key("cupboard key", bathroomMainCupboardLock);
        bathroomMain.addLockedItem(new ContainerItem(bathroomMainCupboard), bathroomMainCupboardLock);
        
        
        // den items:
        // A closet
        Container denCloset = new Container("closet");
        denCloset.setDescription("There are many board games in the closet and.......\n"
        		+ "You guessed it! A key!");
        denCloset.addItem(billiardsDiningKey);
        den.addItem(new ContainerItem(denCloset));
        
        
        // living items:
        // A chest
        Container livingChest = new Container("chest");
        livingChest.setDescription("The big chest has a bowling ball and ten bowling pins in it.\n"
        		+ "Wanna go bowling?");
        livingChest.addItem(new Item("bowling ball", true));
        for (int i = 1; i <= 10; i++) {
        	livingChest.addItem(new Item("bowling pin " + i, true));
        }
        living.addItem(new ContainerItem(livingChest));
        // A cupboard
        Container livingCupboard = new Container("cupboard");
        livingCupboard.setDescription("A very bright light shines from inside the cupboard, blinding you!\n"
        		+ "You cannot see anything! Close the cupboard now.");
        living.addItem(new ContainerItem(livingCupboard));
        
        
        // billiards items:
        // a chest
        Container billiardsChest = new Container("chest");
        billiardsChest.setDescription("Some extra pool balls and chalk are in this chest, as well as a very large\n"
        		+ "key and a live turkey.");
        billiardsChest.addItem(new Item("cue ball", true));
        billiardsChest.addItem(new Item("chalk", true));
        for (int i = 1; i <= 15; i++) {
        	livingChest.addItem(new Item("the " + i + " ball", true));
        }
        billiardsChest.addItem(new Item("turkey", true));
        billiardsChest.addItem(foyerDenKey);
        billiards.addItem(new ContainerItem(billiardsChest));
        // cue stick
        billiards.addItem(new Item("cue stick", true));
        
        
        // dining items:
        Container diningCupboard = new Container("cupboard");
        diningCupboard.setDescription("The cupboard is full of extravagant china, fancy crystal glasses, and\n"
        		+ "expensive silverware. You are enthralled by the designs on the plates!\n"
        		+ "When you snap out of your daze, you notice a golden key in the back of the cupboard.");
        diningCupboard.addItem(new Item("plate", true));
        diningCupboard.addItem(new Item("glass", true));
        diningCupboard.addItem(new Item("fpoon", true));
        diningCupboard.addItem(new Item("snife", true));
        diningCupboard.addItem(new Item("knork", true));
        diningCupboard.addItem(bathroomMainCupboardKey);
        dining.addItem(new ContainerItem(diningCupboard));
        
        
        
        
        /*Container  = new Container("");
        .setDescription("");
        .addItem(new Item("", true));
        .addItem(new ContainerItem());*/
        
        

        over = false;
        currentRoom = starting;

    }
    
    /**
     * Is this game over or not?
     */
    public boolean isOver() { return over; }

    /**
     * Move into a different current room.
     */
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }

    /**
     * Indicate that the game is now over.
     */
    public void finishGame() { over = true; }
    

    public void increaseTime(){
    	sec++;
    	if (sec >= 60){
    		sec = 0;
    		min++;
    	}
    	if (min >= 60){
    		min = 0;
    		sec = 0;
    		hour++;
    	}
    	if (hour == 13){
    		hour = 1;
    	}
    }
    
    public int getMin(){
    	return min;
    }
    
    public int getHour(){
    	return hour;
    }
    
    public String getTime(){
    	DecimalFormat formatter = new DecimalFormat("00");
    	String minFormatted = formatter.format(min);
    	String secFormatted = formatter.format(sec);
    	return (Integer.toString(hour) + ":" + minFormatted + ":" + secFormatted);
    }
    
}
