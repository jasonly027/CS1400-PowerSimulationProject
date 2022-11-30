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
			
			else {
				//Create list of Smart Appliance Objects
				while((sumWatts > maxWattage) && smartAppliancesSwitched < SmartApplianceList.size()) {
					//Reduce sumWatts by smallest smart appliance's blah blah
					
					//Each time something's chosen:
					ReportSmartAppliances.add(SmartApplianceList.get(i));
					++smartAppliancesSwitched;
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