package powerSimulation;

import java.util.Scanner;
import java.util.ArrayList;
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
					System.out.println("\nInput appliance data or type \"C\" to quit");
					System.out.println("/// Appliance data should be in the format of:\n    (Location ID (Integer),"
									 													  + "Appliance Description,"
									 													  + "Watt usage (Integer),"
									 													  + "Probability of appliance being \"on\" (0.00 to 1.00),"
									 													  + "Is it a smart appliance? (true or false),"
									 													  + "Percentage of watt reduction if it is a smart appliance (0.00 to 1.00)"
									 													  + "*Input 0.00 if it is a regular appliance");
					System.out.println("/// Example inputs: 10000001,VCR,45,0.05,false,0.0");
					System.out.println("                    10000002,Laptop,0.20,true,0.23\n");
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
						System.out.println("Appliance has been successfully added");
					}
				}
				
			}
			else if (userInput.equals("D")) {
				//Remove
				while(true) {
					if(applianceList.size() == 0) {
						System.out.println("/// ERROR: Cannot use this option if the appliance list is empty ///\n");
						appMenu();
						break;
					}
					System.out.println("Input valid appliance ID or type \"C\" to quit\n");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						appMenu();
						break;
					}else {
						try {
							for(int i=0;i<applianceList.size();i++) {
								if(applianceList.get(i).getID() == Integer.parseInt(userInput)) {
									System.out.println("Appliance " + userInput + " has been successfully deleted\n");
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
				System.out.println("Appliance List:");
				for(int i=0;i<applianceList.size();i++) {
					System.out.println("/// " + applianceList.get(i));
				}
				System.out.println();
				appMenu();
			}
			else if (userInput.equals("G")) {
				//Generate
				try {
					ApplianceGenerator.main(args);
					File myfile = new File("output.txt");
					Scanner scanner = new Scanner(myfile);
					while(scanner.hasNextLine()) {
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
					System.out.println("/// ERROR: Program cannot find the file \"ApplianceDetail.txt\" used for generating appliances ///\n");
				}
				System.out.println("Successfully added randomly generated appliances to the appliance list\n");
				appMenu();
			}
			else if (userInput.equals("F")) {
				while(true) {
					System.out.println("Input path to text file or type \"C\" to quit\n");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						appMenu();
						break;
					}
					try {
						File myfile = new File(userInput);
						Scanner scanner = new Scanner(myfile);
						while(scanner.hasNextLine()) {
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
						System.out.println("Appliances from text file have been successfully added\n");
						appMenu();
						break;
						
					}catch (IOException ioe) {
						System.out.println("/// ERROR: Program cannot find a file at the location \"" + userInput + "\" ///\n");
					}
					catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("/// ERROR: Text file has improper formatting ///\n");
					}
					catch (NumberFormatException e) {
						System.out.println("/// ERROR: Text file has improper formatting ///\n");
					}
				}
			}
			else if (userInput.equals("X")) {
				//Empty
				applianceList.clear();
				applianceList.trimToSize();
				System.out.println("Appliance list successfully emptied\n");
				appMenu();
			}
			else if (userInput.equals("S")) {
				if(applianceList.size() == 0) {
					System.out.println("/// ERROR: Cannot use this option if the appliance list is empty ///\n");
					appMenu();
					continue;
				}
				//Simulation
				//Prompt max wattage & time steps
				String maxWatts = new String();
				String timeSteps = new String();
				while(true) {
				System.out.println("Input total allowed wattage: ");
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
				System.out.println("Input time steps: ");
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
				System.out.println("< - - - Starting simulation - - - >\n");
				
				Simulate.main(applianceList, Integer.parseInt(maxWatts), Integer.parseInt(timeSteps));
				appMenu();
			}
			else if (userInput.equals("Q")) {
				//Quit
				System.out.println("Closing Program\n");
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
		System.out.println("/// Type \"A\" to add an appliance to the appliance list");
		System.out.println("/// Type \"D\" to delete an appliance from the appliance list");	
		System.out.println("/// Type \"L\" to print the appliance list");
		System.out.println("/// Type \"G\" to add randomly generated appliances to the appliance list");
		System.out.println("/// Type \"F\" to add appliances from a text file");
		System.out.println("/// Type \"X\" to empty the appliance list");
		System.out.println("/// Type \"S\" to start the simulation");
		System.out.println("/// Type \"Q\" to quit the program");
		System.out.println();
	}
	
	public static String wrongInputDisplay() {
		String output = "/// ERROR: Invalid input ///\n";
		return output;
	}

	
}
