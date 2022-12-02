package powerSimulation;

	/* TODO:
	Attributes:
		private int ID; //keeps track of id of appliance
		
	Methods:
		RegularAppliance() <--- takes in super class attributes + Id
		Getters and Setters of attribute
		toString method

	
	*/
public class RegularAppliance extends Appliance{
	private int ID;
	private static int IDCounter = 0;
	
	public RegularAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart){
		super(locID, n, on, pOn, s, pSmart);
		this.ID = IDCounter++;
	}
	
	public void setID(int id) {
		ID = id;
	}
	
	public int getID() {
		return ID;
	}
	
	@Override
	public String toString() {
		String retString = super.toString() + ", ID=" + ID;
		return retString;
	}
}
