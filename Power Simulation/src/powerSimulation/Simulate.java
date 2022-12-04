package powerSimulation;

import java.util.ArrayList;
import java.util.Random;

public class Simulate {
	
	private static ArrayList<SmartAppliance> ReportSmartAppliances = new ArrayList<SmartAppliance>();
	private static ArrayList<Location> ReportLocations = new ArrayList<Location>();
	
	private static void ReportPrint(int maxWattage, int totalWattage, int totalWattageChanged, int changesSmart, int changesLocation) {
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------\\");
		System.out.printf("| Max Allowed Wattage: %,d | Total Wattage before changes: %,d | Total Wattage after changes: %,d |\n",maxWattage,totalWattage,totalWattageChanged);
		System.out.printf("| Smart Appliances turned to \"LOW\": %,d | Locations browned out: %,d |",changesSmart, changesLocation);
		if(changesLocation > 0)
		{
			System.out.printf(" Highest Impact Location Affected: %d with %d watts|",ReportLocations.get(ReportLocations.size()-1).getLocationID(),ReportLocations.get(ReportLocations.size()-1).getSumWattage());
		}
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------/\n");
	}
	
	public static void main(ArrayList<RegularAppliance> applianceList, int maxWattage, int timeSteps) {
		for(int currentStep=0; currentStep<timeSteps; ++currentStep) {
//------------------------------------------------------------------------------------------------------//
					///////////////////////////
					/// PART A+B STARTS HERE //
					///////////////////////////
//------------------------------------------------------------------------------------------------------//
			
			//Populate "ON" appliances list
			ArrayList<RegularAppliance> appOn = new ArrayList<RegularAppliance>();
			
			Random randGen = new Random();
			for(int j = 0; j < applianceList.size(); ++j){
				
				if(/*applianceList.get(j).getProbOn() <= 0.01*(randGen.nextInt(100)+1)*/     true){
					appOn.add(new RegularAppliance(applianceList.get(j).getLocationID(), applianceList.get(j).getAppName(),applianceList.get(j).getOnW(), applianceList.get(j).getProbOn(), applianceList.get(j).getSmart(), applianceList.get(j).getProbSmart()));
				}
			}
			
			//Calculate sum wattage of "ON" list
			
			int totalWattage = 0;
			for(int i = 0; i < appOn.size(); ++i){
				totalWattage += appOn.get(i).getOnW();
			}
			int totalWattageChanged = totalWattage;
			int changesSmart = 0;
			int changesLocation = 0;
			
			//Check if <= max wattage 
			if(totalWattage <= maxWattage) {
				ReportPrint(maxWattage,totalWattage,totalWattageChanged,changesSmart,changesLocation);
				continue;
			}
//-----------------------------------------------------------------------------------------------------//
					////////////////////////
					// PART C STARTS HERE //
					////////////////////////
//------------------------------------------------------------------------------------------------------//
			
			else 
			{
				ArrayList <SmartAppliance> SmartAppliances = new ArrayList<SmartAppliance>(); //Arraylist of appliances that are "ON" and are smart
				ArrayList <SmartAppliance> smartAppliances1 = new ArrayList<SmartAppliance>(); //a copy of smart appliance arraylist
				ArrayList <SmartAppliance> SmartApplianceOrganized = new ArrayList<SmartAppliance>(); //organized list of smart appliances in decreasing order based on their wat reduction
				
				//----------------------------------------------------------
				//Step 1: Find all the appliances that are smart and store it in a separate arraylist
				//----------------------------------------------------------
				
				int counter = 0;
				for(int i = 0; i < appOn.size(); i++)
				{
					if(appOn.get(i).getSmart())
					{
						//SmartAppliance(int id, String name, int o, double pOn1, boolean sm, double pSmart)
						SmartAppliances.add(new SmartAppliance(applianceList.get(i).getLocationID(), applianceList.get(i).getAppName(),applianceList.get(i).getOnW(), applianceList.get(i).getProbOn(), applianceList.get(i).getSmart(), applianceList.get(i).getProbSmart() ));
						SmartAppliances.get(counter).setPosition(counter);//Position value keeps track of the index where the smart appliance is in smart appliance array. 
						smartAppliances1.add(new SmartAppliance(applianceList.get(i).getLocationID(), applianceList.get(i).getAppName(),applianceList.get(i).getOnW(), applianceList.get(i).getProbOn(), applianceList.get(i).getSmart(), applianceList.get(i).getProbSmart() ));
						smartAppliances1.get(counter).setPosition(counter);
						counter++;
					}
				}
				
				//----------------------------------------------------------
				//Step 2: Organize smart appliance array based on their wattage reduction when set to low
				//----------------------------------------------------------
				
				counter = 0;
				int min = smartAppliances1.get(0).getDifference();
				int placement = 0;
				while(counter < SmartAppliances.size())
				{
					placement = 0;
					min = smartAppliances1.get(0).getDifference();
					for(int i = 0; i < smartAppliances1.size(); i++)
					{
						if( min > smartAppliances1.get(i).getDifference())
						{
							placement = i;
							min = smartAppliances1.get(i).getDifference();
						}
					}
					SmartApplianceOrganized.add(smartAppliances1.get(placement));
					smartAppliances1.remove(placement);
					counter++;
				}
				
				smartAppliances1 = null; //erases smartAppliances1 array
				
				//----------------------------------------------------------
				//Step 3: Iterate through organized smart appliance array and minus the difference from total wattage. Keep doing so until reach max wattage
				//----------------------------------------------------------
				System.out.println("/--Total Wattage if all are on: " + totalWattage);
				for(int i = 0; i < SmartApplianceOrganized.size();i++)
				{
					totalWattageChanged = totalWattageChanged - SmartApplianceOrganized.get(i).getDifference();
					if(totalWattageChanged <= maxWattage)
					{
						ReportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
						changesSmart++;
						break;
					}
					else
					{
						ReportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
						changesSmart++;
					}
				}
				
//// TEMP ///////////////////////////////////////////////////////////////////////////////////////////////////////
int reportWatts = totalWattage;
int sumSMT = 0;
for(int i = 0; i < ReportSmartAppliances.size();i++)
{
	reportWatts -= ReportSmartAppliances.get(i).getDifference();
	sumSMT += ReportSmartAppliances.get(i).getDifference();
	System.out.println("| ID:" + ReportSmartAppliances.get(i).getID() + " Difference:" + ReportSmartAppliances.get(i).getDifference());
}
System.out.println("\\--Sum of smart appliances that were turned to \"LOW\"'s differences: " + sumSMT + "(above)");
System.out.println("\\--Total Wattage after smart on LOW: " + reportWatts + "\n");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//-----------------------------------------------------------------------------------------------------//
					////////////////////////
					// PART D STARTS HERE //
					////////////////////////
//------------------------------------------------------------------------------------------------------//
				if(totalWattageChanged > maxWattage) 
				{	
					//Create list of all location numbers then create location objects based off those numbers
					ArrayList<Integer> locationIDList = Location.getLocationIDList(appOn);
					ArrayList<Location> locationList = new ArrayList<Location>();
					for(int i=0; i<locationIDList.size(); ++i) 
					{
						locationList.add(new Location(locationIDList.get(i)));
					}
					
					//Add the wattage of each appliance to their respective location (object)
					for(int i=0; i<appOn.size(); ++i) 
					{
						RegularAppliance currentAppliance = appOn.get(i);
						for(int j=0; j<locationList.size(); ++j) 
						{
							if(currentAppliance.getLocationID() == locationList.get(j).getLocationID()) 
							{
								if(currentAppliance.getSmart()) 
								{
									int smartApplianceWattage = (int)Math.round(currentAppliance.getOnW() * (1 - currentAppliance.getProbSmart()));
									locationList.get(j).addWattage(smartApplianceWattage);
								}
								else 
								{
									locationList.get(j).addWattage(currentAppliance.getOnW());
								}
								break;
							}
						}
					}
					
//// TEMP ///////////////////////////////////////////////////////////////////////////////////////////////////////			
int sumLoc=0;
for(Location location : locationList) {
	System.out.println("|   LocID:" + location.getLocationID() + " Wattage:" + location.getSumWattage());
	sumLoc += location.getSumWattage();
}
System.out.println("\\--Sum of each location's wattage: " + sumLoc + " (above)\n");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

					//Find the min location and subtract from total system's wattage (totalWattageChanged)
					Location minLocation = new Location(-1);
					int minWattLocPos = -1;
					while(totalWattageChanged > maxWattage)
					{
						minLocation.setSumWattage(Integer.MAX_VALUE);
						for(int i=0; i<locationList.size(); ++i)
						{
							if(locationList.get(i).getSumWattage() < minLocation.getSumWattage() && !locationList.get(i).getIsBrowned())
							{
								minLocation.setSumWattage(locationList.get(i).getSumWattage());
								minWattLocPos = i;
							}
						}
						totalWattageChanged -= minLocation.getSumWattage();
						locationList.get(minWattLocPos).setIsBrowned(true);
						++changesLocation;
						ReportLocations.add(locationList.get(minWattLocPos));
					}
				}
			}
			ReportPrint(maxWattage,totalWattage,totalWattageChanged,changesSmart,changesLocation);
			ReportSmartAppliances.clear();
			ReportSmartAppliances.trimToSize();
			ReportLocations.clear();
			ReportLocations.trimToSize();
			
			System.out.println("/------------------\\");
			System.out.println("|                  |");
			System.out.println("| End of time step " + (currentStep+1));
			System.out.println("|                  |");
			System.out.println("\\------------------/\n");
		}
		System.out.println("< - - - Simulation Ended - - - >\n");
	}
}
