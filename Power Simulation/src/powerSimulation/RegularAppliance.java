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
	
	public RegularAppliance(int ID, String n, int on, int off, double pOn, boolean s, double pSmart){
		super(n, on, off, pOn, s, pSmart);
		this.ID = ID;
	}
	
	public void setID(int id) {
		ID = id;
	}
	
	public int getID() {
		return ID;
	}
	
	@Override
	public String toString() {
		return "RegularAppliance [ID=" + ID + "]";
	}
}
