package powerSimulation;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.NumberFormatException;
import java.io.File;
import java.io.IOException;

//------------------------------------------------------------------------------------------------------//
						////////////////////////////////////
						/// ADDITIONAL IS THIS WHOLE FILE //
						////////////////////////////////////
//------------------------------------------------------------------------------------------------------//

public class AppClient {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<RegularAppliance> applianceList = new ArrayList<>();
		
		appMenu();
		while(true) {
			String userInput = scnr.nextLine();
			if (userInput.equals("A")) {
				//Add
				while(true) {
					System.out.println("Input appliance data or type \"C\" to quit");
					System.out.println("/// Appliance data should be in the format of:\n    (Location ID (Integer),"
									 													  + "Appliance Description,"
									 													  + "Watt usage (Integer),"
									 													  + "Probability of appliance being \"on\" (0.00 to 1.00),"
									 													  + "Is it a smart appliance? (true or false),"
									 													  + "Percentage of watt reduction if it is a smart appliance (0.00 to 1.00)"
									 													  + "*Input 0.00 if it is a regular appliance");
					System.out.println("/// Example inputs: 10000001,VCR,45,0.05,false,0.0");
					System.out.println("                    10000002,Laptop,0.20,true,0.23");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						appMenu();
						break;
					}else {
						String []inputArray= userInput.split(",");
						if(inputArray.length != 6) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Integer.parseInt(inputArray[0]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Integer.parseInt(inputArray[2]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Double.parseDouble(inputArray[3]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Boolean.parseBoolean(inputArray[4]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Double.parseDouble(inputArray[5]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						
						applianceList.add(new RegularAppliance(Integer.parseInt(inputArray[0]), 
															   inputArray[1], 
															   Integer.parseInt(inputArray[2]), 
															   Double.parseDouble(inputArray[3]), 
															   Boolean.parseBoolean(inputArray[4]), 
															   Double.parseDouble(inputArray[5])));
					}
				}
				
			}
			else if (userInput.equals("D")) {
				//Remove
				while(true) {
					System.out.println("Input valid appliance ID or type \"c\" to quit");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						appMenu();
						break;
					}else {
						try {
							for(int i=0;i<applianceList.size();i++) {
								if(applianceList.get(i).getID() == Integer.parseInt(userInput)) {
									applianceList.remove(i);
									break;
								}
							}
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
					}
				}
			}
			else if (userInput.equals("L")) {
				//Print List
				for(int i=0;i<applianceList.size();i++) {
					System.out.println(applianceList.get(i));
				}
			}
			else if (userInput.equals("G")) {
				//Generate
				try {
					ApplianceGenerator.main(args);
					File myfile = new File("output.txt");
					Scanner scanner = new Scanner(myfile);
					for(int i=0;i<applianceList.size();i++) {
						String fileInput = scanner.nextLine();
						String []inputArray = fileInput.split(",");
						applianceList.add(new RegularAppliance(Integer.parseInt(inputArray[0]), 
															   inputArray[1], 
															   Integer.parseInt(inputArray[2]), 
															   Double.parseDouble(inputArray[3]), 
															   Boolean.parseBoolean(inputArray[4]), 
															   Double.parseDouble(inputArray[5])));
					}
					scanner.close();
				}
				
				catch(IOException ioe) {
					System.out.println("/// ERROR: Program cannot find the file \"ApplianceDetail.txt\" used for generating appliances ///");
				}
				appMenu();

			}
			else if (userInput.equals("X")) {
				//Empty
				applianceList.clear();
				applianceList.trimToSize();
			}
			else if (userInput.equals("S")) {
				//Simulation
				//Prompt max wattage & time steps
				String maxWatts = new String();
				String timeSteps = new String();
				while(true) {
				System.out.println("Input total allowed wattage");
				maxWatts = scnr.nextLine();
				try {
					if (Integer.parseInt(maxWatts)<=0) {
						System.out.println(wrongInputDisplay());
						continue;
					}
				}catch(NumberFormatException e) {
					System.out.println(wrongInputDisplay());
					continue;
				}
					break;
				}
				while(true) {
				System.out.println("Input time steps");
				timeSteps = scnr.nextLine();
				try {
					if (Integer.parseInt(timeSteps)<=0) {
						System.out.println(wrongInputDisplay());
						continue;
					}
				}catch(NumberFormatException e) {
					System.out.println(wrongInputDisplay());
					continue;
				}
					break;
				}
				System.out.println("Starting simulation");
				
				Simulate.main(applianceList, Integer.parseInt(maxWatts), Integer.parseInt(timeSteps));
				
			}
			else if (userInput.equals("Q")) {
				//Quit
				System.exit(0);
			}
			else {
				//Re-prompt
				System.out.println(wrongInputDisplay());
				appMenu();
			}
		}
	}
	
	
	public static void appMenu() {
		System.out.println("Select an option:");
		System.out.println("/// Type \"A\" Add an appliance");
		System.out.println("/// Type \"D\" Delete an appliance");	
		System.out.println("/// Type \"L\" Print the appliances");
		System.out.println("/// Type \"G\" Generate text file");
		System.out.println("/// Type \"X\" Delete all appliances");
		System.out.println("/// Type \"S\" To Start the simulation");
		System.out.println("/// Type \"Q\" Quit the program");
	}
	
	public static String wrongInputDisplay() {
		String output = "/// ERROR: Wrong input /// \n";
		return output;
	}

	
}
