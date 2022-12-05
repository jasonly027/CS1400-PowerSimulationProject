package powerSimulation;

public class RegularAppliance extends Appliance{
	private int ID;
	private static int IDCounter = 0;
	
	/**
	 * {@code public RegularAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart)}
	 * <p>
	 * Constructs a regular appliance object with the specified location ID, appliance description, 
	 * wattage, probability of it being "on", whether it is a smart appliance, and the watt reduction if it is a smart appliance (0.00 if not)
	 * <p>
	 * A unique ID is generated and given to it upon instantiation 
	 * @param locID The location ID
	 * @param n The appliance description
	 * @param on The wattage
	 * @param pOn The probability of it being "on"
	 * @param s Whether it is a smart appliance
	 * @param pSmart The watt reduction if it is a smart appliance (0.00 if not)
	 */
	public RegularAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart){
		super(locID, n, on, pOn, s, pSmart);
		this.ID = IDCounter++;
	}
	
	/**
	 * {@code public RegularAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart, int ID)}
	 * <p>
	 * Constructs a regular appliance object with the specified location ID, appliance description, 
	 * wattage, probability of it being "on", whether it is a smart appliance, 
	 * the watt reduction if it is a smart appliance (0.00 if not), and ID
	 * @param locID The location ID
	 * @param n The appliance description
	 * @param on The wattage
	 * @param pOn The probability of it being "on"
	 * @param s Whether it is a smart appliance
	 * @param pSmart The watt reduction if it is a smart appliance (0.00 if not)
	 * @param ID The ID
	 */
	public RegularAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart, int ID){
		super(locID, n, on, pOn, s, pSmart);
		this.setID(ID);
	}
	
	/**
	 * {@code public void setID(int id)}
	 * <p>
	 * Change the ID to the one passed by the argument
	 * @param id The ID
	 */
	public void setID(int id) {
		ID = id;
	}
	
	/**
	 * {@code public int getID()}
	 * <p>
	 * Returns the ID of the appliance
	 * @return The ID
	 */
	public int getID() {
		return ID;
	}
	
	@Override
	public String toString() {
		String retString = super.toString() + ", ID=" + ID;
		return retString;
	}
}
