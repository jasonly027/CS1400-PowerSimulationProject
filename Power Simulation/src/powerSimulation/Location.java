package powerSimulation;

import java.util.ArrayList;

public class Location {
	private int locationID;
	private int sumWattage;
	private boolean isBrowned;
	
	public int getLocationID() {
		return locationID;
	}

	public int getSumWattage() {
		return sumWattage;
	}
	
	public boolean getIsBrowned() {
		return isBrowned;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public void setSumWattage(int sumWattage) {
		this.sumWattage = sumWattage;
	}
	
	public void setIsBrowned(boolean isBrowned) {
		this.isBrowned = isBrowned;
	}

	public void addWattage(int wattage) {
		sumWattage += wattage;
	}
	
	public static ArrayList<Integer> getLocationIDList(ArrayList<RegularAppliance> regularApplianceList) {
		ArrayList<Integer> locationIDList = new ArrayList<Integer>();
		for(RegularAppliance i : regularApplianceList) {
			if(!locationIDList.contains(i.getLocation())) {
				locationIDList.add(i.getLocation());
			}
		}
		return locationIDList;
	}
	
	Location(int locationID) {
		this.locationID = locationID;
		this.sumWattage = 0;
		this.isBrowned = false;
	}
	//stuff
}
