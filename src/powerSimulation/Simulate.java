package powerSimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Simulate {
	
	private static ArrayList<SmartAppliance> reportSmartAppliances = new ArrayList<SmartAppliance>();
	private static ArrayList<Location> reportLocations = new ArrayList<Location>();
	private static int reportFileNo = 0;
	private static int currentStep;
	private static int timeSteps;
	
	private static void ReportPrint(int maxWattage, int totalWattage, int totalWattageChanged) {
		//Prints to console
		System.out.println("/// Time Step [" + (currentStep+1) + "] ///");
		System.out.printf("Max Allowed Wattage: %,d\t\t\tTotal Wattage before changes: %,d\t\t\tTotal Wattage after changes: %,d\n",maxWattage,totalWattage,totalWattageChanged);
		System.out.printf("Smart Appliances turned to low: %,d\t\t\tLocations browned out: %,d",reportSmartAppliances.size(),reportLocations.size());
		if(reportLocations.size() > 0)
		{
			System.out.printf("\t\t\tHighest Impact Location Affected: %d with %,d watts\n\n",reportLocations.get(reportLocations.size()-1).getLocationID(),reportLocations.get(reportLocations.size()-1).getSumWattage());
		}
		else {
			System.out.print("\n\n");
		}
		//Prints to text file
		
		//Create folder for text files
		File reportFolder = new File("Detailed Reports");
		reportFolder.mkdir();
		
		//Check if file name is taken
		File reportFile = new File(reportFolder,"Report" + reportFileNo + ".txt");
		if(currentStep == 0)
		{
			while(reportFile.exists())
			{
				++reportFileNo;
				reportFile = new File(reportFolder, "Report" + reportFileNo + ".txt");
			}
		}
		
		//Print detailed report in a text file
		try 
		{
			FileWriter fw = new FileWriter(reportFile,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("/// Time Step [" + (currentStep+1) + "] ///");
			pw.printf("Max Allowed Wattage: %,d\t\t\tTotal Wattage before changes: %,d\t\t\tTotal Wattage after changes: %,d\n",maxWattage,totalWattage,totalWattageChanged);
			pw.printf("Smart Appliances turned to low: %d\t\t\tLocations browned out: %d\n\n",reportSmartAppliances.size(),reportLocations.size());
			pw.println("Smart Appliances turned to low:");
			pw.printf("| ID   | Location | Name                                             | Wattage    | Reduction  |\n");
			for(SmartAppliance smartAppliance : reportSmartAppliances)
			{
				pw.printf("| %-4d | %-8d | %-48s | %4d watts | %4d watts |\n", 
						smartAppliance.getID(),smartAppliance.getLocationID(),smartAppliance.getAppName(),smartAppliance.getOnW(),smartAppliance.getDifference());
			}
			pw.println("\nLocations browned out:");
			pw.printf("| Location | Wattage     |\n");
			for(Location location : reportLocations)
			{
				pw.printf("| %-8d | %5d watts |\n",location.getLocationID(),location.getSumWattage());
			}
			pw.print("\n\n\n\n\n\n\n\n\n\n");
			pw.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if(currentStep == timeSteps-1) {
			System.out.println("*Check \"" + reportFile.getAbsolutePath() + "\" for more info*\n");
		}
	}
	
	public static void main(ArrayList<RegularAppliance> applianceList, int maxWattage, int timeSteps) {
		Simulate.timeSteps = timeSteps;
		for(currentStep=0; currentStep<Simulate.timeSteps; ++currentStep) {
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
					appOn.add(applianceList.get(j));
				}
			}
			
			//Calculate sum wattage of "ON" list
			
			int totalWattage = 0;
			for(int i = 0; i < appOn.size(); ++i){
				totalWattage += appOn.get(i).getOnW();
			}
			int totalWattageChanged = totalWattage;
			
			//Check if <= max wattage 
			if(totalWattage <= maxWattage) {
				ReportPrint(maxWattage,totalWattage,totalWattageChanged);
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
						SmartAppliances.add(new SmartAppliance(applianceList.get(i).getLocationID(), applianceList.get(i).getAppName(),applianceList.get(i).getOnW(), applianceList.get(i).getProbOn(), applianceList.get(i).getSmart(), applianceList.get(i).getProbSmart(), applianceList.get(i).getID() ));
						SmartAppliances.get(counter).setPosition(counter);//Position value keeps track of the index where the smart appliance is in smart appliance array. 
						smartAppliances1.add(new SmartAppliance(applianceList.get(i).getLocationID(), applianceList.get(i).getAppName(),applianceList.get(i).getOnW(), applianceList.get(i).getProbOn(), applianceList.get(i).getSmart(), applianceList.get(i).getProbSmart(), applianceList.get(i).getID() ));
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
//				System.out.println("/--Total Wattage if all are on: " + totalWattage);
				for(int i = 0; i < SmartApplianceOrganized.size();i++)
				{
					totalWattageChanged = totalWattageChanged - SmartApplianceOrganized.get(i).getDifference();
					if(totalWattageChanged <= maxWattage)
					{
						reportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
						break;
					}
					else
					{
						reportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
					}
				}
				
//// TEMP ///////////////////////////////////////////////////////////////////////////////////////////////////////
//int reportWatts = totalWattage;
//int sumSMT = 0;
//for(int i = 0; i < reportSmartAppliances.size();i++)
//{
//	reportWatts -= reportSmartAppliances.get(i).getDifference();
//	sumSMT += reportSmartAppliances.get(i).getDifference();
//	System.out.println("| ID:" + reportSmartAppliances.get(i).getID() + " Difference:" + reportSmartAppliances.get(i).getDifference());
//}
//System.out.println("\\--Sum of smart appliances that were turned to \"LOW\"'s differences: " + sumSMT + "(above)");
//System.out.println("\\--Total Wattage after smart on LOW: " + reportWatts + "\n");
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
//int sumLoc=0;
//for(Location location : locationList) {
//	System.out.println("|   LocID:" + location.getLocationID() + " Wattage:" + location.getSumWattage());
//	sumLoc += location.getSumWattage();
//}
//System.out.println("\\--Sum of each location's wattage: " + sumLoc + " (above)\n");
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
						reportLocations.add(locationList.get(minWattLocPos));
					}
				}
			}
			ReportPrint(maxWattage,totalWattage,totalWattageChanged);
			reportSmartAppliances.clear();
			reportSmartAppliances.trimToSize();
			reportLocations.clear();
			reportLocations.trimToSize();
			
		}
	}
}
