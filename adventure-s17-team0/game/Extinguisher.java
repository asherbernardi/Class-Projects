package game;

public class Extinguisher extends Item {

	public Extinguisher(String n, boolean pickUp) {
		super(n, pickUp);
		super.addCommand("put out fire " + "<insert direction>");
	}
	
	public static void putOutFire(Room room) 
	{
		room.putOutFire();
	}

}
