package powerSimulation;

import java.util.ArrayList;
import java.util.Scanner;

public class Simulate {
	
	private static ArrayList<SmartAppliance> ReportSmartAppliances;
	private static ArrayList<Location> ReportLocations;
	
	private static void ReportPrint() {
		//stuff
	}
	
	public static void main(ArrayList<RegularAppliance> ApplianceList, int maxWattage, int timeSteps) {
		for(int currentStep=0; currentStep<timeSteps; ++currentStep) {
//------------------------------------------------------------------------------------------------------//
					///////////////////////////
					/// PART A+B STARTS HERE //
					///////////////////////////
//------------------------------------------------------------------------------------------------------//
			
			//Populate "ON" appliances list
			
			//Calculate sum wattage of "ON" list
			
			//Check if <= max wattage 
			if(sumWatts <= maxWattage) {
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
				
				ArrayList <SmartAppliance> SmartAppliances = new ArrayList<SmartAppliance>(); //this here lists all the smart appliances.
				ArrayList <SmartAppliance> smartAppliances1 = new ArrayList<SmartAppliance>(); //a copy of the array above, do not use this! I will empty this array!
				ArrayList <SmartAppliance> SmartApplianceOrganized = new ArrayList<SmartAppliance>(); //organized list of smart appliances in decreasing order based on their wat reduction
				int totalChangesSmart = 0;
				int totalWattage = 0;
				
				//----------------------------------------------------------
				//Step 1: Find all the appliances that are smart and store it in a separate arraylist
				//----------------------------------------------------------
				
				int counter = 0;
				for(int i = 0; i < ApplianceList.size(); i++)
				{
					totalWattage = totalWattage + ApplianceList.get(i).getOnW();//This is for calculations, but this will be calculated in earlier part
					if(ApplianceList.get(i).isSmart())
					{
						//SmartAppliance(int id, String name, int o, double pOn1, boolean sm, double pSmart)
						SmartAppliances.add(new SmartAppliance(ApplianceList.get(i).getID(), ApplianceList.get(i).getName(),ApplianceList.get(i).getOnW(), ApplianceList.get(i).getProbOn(), ApplianceList.get(i).isSmart(), ApplianceList.get(i).getProbSmart() ));
						SmartAppliances.get(counter).setPosition(counter);//Position value keeps track of the index where the smart appliance is in smart appliance array. 
						smartAppliances1.add(new SmartAppliance(ApplianceList.get(i).getID(), ApplianceList.get(i).getName(),ApplianceList.get(i).getOnW(), ApplianceList.get(i).getProbOn(), ApplianceList.get(i).isSmart(), ApplianceList.get(i).getProbSmart() ));
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
				int totalWatWhenLow = sumWatts; //total wattage when smart appliances are set to low
				System.out.println("Total Wattage if all are on:" + totalWatWhenLow);
				for(int i = 0; i < SmartApplianceOrganized.size();i++)
				{
					totalWatWhenLow = totalWatWhenLow - SmartApplianceOrganized.get(i).getDifference();
					if(totalWatWhenLow <= maxWattage)
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
				
				//----------------------------------------------------------
				//Step 4: Report these changes - if any. If not, procced to Part D
				//----------------------------------------------------------
				if(totalChangesSmart != SmartApplianceOrganized.size())
				{
					ReportPrint();
				}
			}
//-----------------------------------------------------------------------------------------------------//
					////////////////////////
					// PART D STARTS HERE //
					////////////////////////
//------------------------------------------------------------------------------------------------------//
			
			if(sumWatts > maxWattage) {
				//Create list of Location Objects
				while(sumWatts > maxWattage) {
					//Reduce sumWatts by a location's blah blah
					
					//Each time something's chosen:
					ReportLocations.add(LocationList.get(i));
				}
			}
			
			ReportPrint();
			ReportSmartAppliances.clear();
			ReportSmartAppliances.trimToSize();
			ReportLocations.clear();
			ReportLocations.trimToSize();
		}
	}
}
