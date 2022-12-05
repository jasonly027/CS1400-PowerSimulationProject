package powerSimulation;

import java.util.ArrayList;

public class Location {
	private int locationID;
	private int sumWattage;
	private boolean isBrowned;
	
	/**
	 * {@code public int getLocationID()}
	 * <p>
	 * Returns the location ID
	 * @return The location ID
	 */
	public int getLocationID() {
		return locationID;
	}
	
	/**
	 * {@code public int getSumWattage()}
	 * <p>
	 * Returns the sum wattage of the location
	 * @return The sum wattage of the location
	 */
	public int getSumWattage() {
		return sumWattage;
	}
	
	/**
	 * {@code public boolean getIsBrowned(}
	 * <p>
	 * Returns whether or not the locations is browned out
	 * @return Whether or not the location is browned out
	 */
	public boolean getIsBrowned() {
		return isBrowned;
	}
	
	/**
	 * {@code public void setLocationID(int locationID)}
	 * <p>
	 * Changes the location ID to the one passed by the argument
	 * @param locationID The location ID that will replace this location's ID
	 */
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	/**
	 * {@code public void setSumWattage(int sumWattage)}
	 * <p>
	 * Changes the sum wattage to the one passed by the argument
	 * @param sumWattage The sum wattage that will replace this location's sum wattage
	 */
	public void setSumWattage(int sumWattage) {
		this.sumWattage = sumWattage;
	}
	
	/**
	 * {@code public void setIsBrowned(boolean isBrowned)}
	 * <p>
	 * Changes whether the location is browned
	 * @param isBrowned The new state of the isBrowned attribute
	 */
	public void setIsBrowned(boolean isBrowned) {
		this.isBrowned = isBrowned;
	}
	
	/**
	 * {@code public void addWattage(int wattage)}
	 *<p>
	 *Add wattage to the sum wattage 
	 * @param wattage The wattage that will be added to the location's sum wattage
	 */
	public void addWattage(int wattage) {
		sumWattage += wattage;
	}
	
	/**
	 * {@code public static ArrayList<Integer> getLocationIDList(ArrayList<RegularAppliance> regularApplianceList)}
	 * <p>
	 * Given a list of regular appliances, return a list of all the locations
	 * @param regularApplianceList A list of regular appliances
	 * @return A list of locations
	 */
	public static ArrayList<Integer> getLocationIDList(ArrayList<RegularAppliance> regularApplianceList) {
		ArrayList<Integer> locationIDList = new ArrayList<Integer>();
		for(RegularAppliance i : regularApplianceList) {
			if(!locationIDList.contains(i.getLocationID())) {
				locationIDList.add(i.getLocationID());
			}
		}
		return locationIDList;
	}
	
	/**
	 * {@code Location(int locationID)}
	 * <p>
	 * Constructs a location object given the location ID of it. By default, the sum wattage is 0 and it is not browned out.
	 * @param locationID The locationID for the location
	 */
	Location(int locationID) {
		this.locationID = locationID;
		this.sumWattage = 0;
		this.isBrowned = false;
	}
	
	@Override
	public String toString() {
		String retString = "Location: locationID=" + this.locationID + ", sumWattage=" + this.sumWattage;
		return retString;
	}
}
