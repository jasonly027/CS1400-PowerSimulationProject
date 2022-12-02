package powerSimulation;

import java.util.ArrayList;
import java.util.Random;

public class Simulate {
	
	private static ArrayList<SmartAppliance> ReportSmartAppliances = new ArrayList<SmartAppliance>();
	private static ArrayList<Location> ReportLocations = new ArrayList<Location>();
	
	private static void ReportPrint() {
		System.out.println("Smart appliances that were switched to low mode:");
		for(SmartAppliance smartAppliance : ReportSmartAppliances) {
			System.out.println("/// " + smartAppliance);
		}
		System.out.println("\nLocations that were browned out:");
		for(Location location : ReportLocations) {
			System.out.println("/// " + location);
		}
	}
	
	public static void main(ArrayList<RegularAppliance> applianceList, int maxWattage, int timeSteps) {
		
//		ArrayList<RegularAppliance> appOn = new ArrayList<RegularAppliance>(); //Arraylist of appliances that are "ON"
//		ArrayList <SmartAppliance> SmartAppliances = new ArrayList<SmartAppliance>(); //Arraylist of appliances that are "ON" and are smart
//		int totalWattage; //stores total amount of wattage when all appliances are "ON"
		
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
				
				if(applianceList.get(j).getProbOn() <= 0.01*(randGen.nextInt(100)+1)){
					appOn.add(new RegularAppliance(applianceList.get(j).getLocationID(), applianceList.get(j).getAppName(),applianceList.get(j).getOnW(), applianceList.get(j).getProbOn(), applianceList.get(j).getSmart(), applianceList.get(j).getProbSmart()));
				}
			}
			
			//Calculate sum wattage of "ON" list
			
			int totalWattage = 0;
			for(int i = 0; i < appOn.size(); ++i){
				totalWattage += appOn.get(i).getOnW();
			}
			
			//Check if <= max wattage 
			if(totalWattage <= maxWattage) {
				ReportPrint();
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
				
				int totalChangesSmart = 0;
				
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
				int totalWattWhenLow = totalWattage; //total wattage when smart appliances are set to low
				System.out.println("Total Wattage if all are on:" + totalWattWhenLow);
				for(int i = 0; i < SmartApplianceOrganized.size();i++)
				{
					totalWattWhenLow = totalWattWhenLow - SmartApplianceOrganized.get(i).getDifference();
					if(totalWattWhenLow <= maxWattage)
					{
						ReportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
						totalChangesSmart++;
						break;
					}
					else
					{
						ReportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
						totalChangesSmart++;
					}
				}
				
//-----------------------------------------------------------------------------------------------------//
					////////////////////////
					// PART D STARTS HERE //
					////////////////////////
//------------------------------------------------------------------------------------------------------//
			
				if(totalWattWhenLow > maxWattage) 
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
									int smartApplianceWattage = (int)(currentAppliance.getOnW() * (1 - currentAppliance.getProbSmart()));
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
					
					//Find the min location and subtract from total system's wattage (totalWattWhenLow)
					while(totalWattWhenLow > maxWattage) 
					{
						int minLocationWattage = Integer.MAX_VALUE;
						Location minLocation = new Location(-1);
						for(Location location : locationList) 
						{
							if(location.getSumWattage() < minLocationWattage && !location.getIsBrowned()) 
							{
								minLocationWattage = location.getSumWattage();
								minLocation = location;
								location.setIsBrowned(true);
							}
						}
						totalWattWhenLow -= minLocationWattage;
						ReportLocations.add(minLocation);
					}
				}
			}
//			ReportPrint();
//			ReportSmartAppliances.clear();
//			ReportSmartAppliances.trimToSize();
//			ReportLocations.clear();
//			ReportLocations.trimToSize();
		}
	}
}
