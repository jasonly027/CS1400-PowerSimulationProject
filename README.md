# CS1400-PowerSimulationProject

This readme file was generated on 12/01/2022 by Group 8

+ Name of Project: CS1400 - Power Simulation Project

+ Contributers:
    - Laurence Tremblay
    - Jason Ly
    - Austin Pan
    - Katherine Torres

 
-------------------------------------------------------------
## TABLE OF CONTENTS
-------------------------------------------------------------
- Introduction/Purpose
- Client Class
- How to Exectue the Program

-------------------------------------------------------------
## INTRODUCTION / PURPOSE
-------------------------------------------------------------
CS1400 - Power Simulation Project is composed of java applications that will simulate the power consumption of a building. Each building has several locations, each with a number of appliances. All appliances information will be stored in a seperate CSV file. The program will ask how many times should the simulation run and what is the max wattage allowed from the user. Each step, the program will randomly turn on appliances based on probability. Then run through the power control algorithm. If the total wattage when all appliances are "ON" exceeds max wattage, then within the power control algorithm, it will determine whether which appliances that are "ON" will be set to either "LOW" (if its a smart appliance), "OFF" (if its a regular appliance) or if one location is "browned out". The output will be a detailed report on what appliances were turned to "LOW" or "OFF" OR what locations were "browned" out to reach max wattage. 

-------------------------------------------------------------
## CLIENT CLASS
-------------------------------------------------------------
- AppClient.java

-------------------------------------------------------------
## HOW TO EXECUTE
-------------------------------------------------------------
- Begins by prompting the user for input by presenting a menu

- Options appear to perform the following functions:
    - "A" to add an aplliance
    - "D" to remove an appliance
    - "L" to print the appliance list
    - "G" to add randomly generated appliances to the appliance list
    - "X" to clear the appliance list
    - "S" to start the simulation
    - "Q" to quit the program
    
- If "A" is seltected, the user will be prompted again for:
    - The new appliance data type
    - The appliance data
        - The program will provide the format of the input
    - "C" to cancel action
- Lastly it will output if the appliance was added and prompt the menu again

- If "D" is seltected, the user will be prompted again for:
    - A vaild appliance ID
    - "C" to cancel action
- Lastly it will output if the appliance was removed and prompt the menu again 

- If "L" is seltected, then an appliance list will be output

- If "G" is seltected, the a randomly generated appliance list will be added to the list
    - Will output if this action was successful
    
- If "X" is seltected, the an appliance list will be cleared
    - Will output if this action was successful
    
- If "S" is seltected, the user will be prompted again for:
    - The max amount of wattage
    - The number of times the simulation will loop
- The simulation will then run and output the results and if any adjustments to the appliances were made

- If "Q" is seltected, the program will end


