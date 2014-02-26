AndroidMarionette
=================


Check it out now Check it out now :D

Welcome to the Motor-Controlled Marionette Tutorial Page!

### Table of Contents  
[Introduction] (#introduction)   
[Materials List](#materials)     
[Overall Schematic](#overallschematic)    
[Housing and Servo Motor Setup](#housingsetup)      
[Arduino](#arduino)      
[Android App](#android)        
[Potential Roadblocks](#roadblocks)  
[Code and Syntax Highlighting](#code)  

<a name="introduction"/>
## Introduction

This project was a partner collaboration between Thibaud Cochet and Jacqueline Ong and was created for Harvey Mudd College's [E190P: Embedded Systems] (http://www3.hmc.edu/~jspjut/class/e190p/) course taught by Professor [Josef Spjut] (http://jspjut.github.io/).

To see a video demo of the final project, click the link below.             
<a href="http://www.youtube.com/watch?feature=player_embedded&v=Keeoy24ys9w
" target="_blank"><img src="http://img.youtube.com/vi/Keeoy24ys9w/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>


<a name="materials"/>
## Billing of Materials

If there is no cost listed, then it was free or was provided, courtesy of the HMC Microprocessors lab and wood shop.

| Quantity        | Item           | Cost  |
| :-------------: |:-------------:| :-----:|
| 9 | Hitec HS-422 Servo Motors | $90 |
| 1 | Arduino Uno               | $35 |
| 1 | Monkey Marionette         | $13 |
| 3 | 12" x 36" Plywood Panels  |     |
| 9 | Skinny Popsicle Sticks    |     |
|   | **Total Cost**            | **$138** |

Other items not listed, but we used were a switch, 1 kOhm resistor, wire, screws, electric tape, super glue, and pull-ties.

<a name="overallschematic"/>
## Overall Schematic

Below is the overall schematic of the electronics of the servo motors.             
![alt text](https://raw.github.com/jackieong/AndroidMarionette/master/schemq_bb.png)           
For the BlueSMiRF, the pins (from left to right) are CTS, VCC, GND, TX, RX, RTS.

Note: For this one-way Bluetooth connection (from Android to Arduino) to work, connect the BlueSMiRF's TX pin to the Arduino Uno's RX pin.

<a name="housingsetup"/>
## Servo Motor Setup and Housing
When deciding the servo motor setup, we chose one, such that we could maximize the amount of motion the marionette could achieve. 
We chose to have nine servo motors.
Five control the vertical motion for hands (2), feet (2), and head (1)
Four control the horizontal motion for hands (2) and feet (2) 

![alt text](https://raw.github.com/jackieong/AndroidMarionette/master/marionettemotor.png) 

The wooden housing was made from three donated sheets of wood to create the support for the servo motor setup.
<a name="android"/>
## Android

### User Interface            
For the user interface, we designed the app to have three screens. A main screen to choose between two sub-screens whether you wanted to...           
1) control the marionette's individual limbs             
2) choose from a selection of four preset moves the marionette could perform.          

######  Screen 1

### Creating the Android App            
We used (Android Developer Tools Eclipse Platform)[http://developer.android.com/sdk/index.html?utm_source=weibolife] to code the Android App with the desirable interface and necessary buttons to command the servo motors.

### Bluetooth Connection - Sending Data            


#### Java and Activity Files - Code            


##### Main Screen
[ThibJackMario.java](https://github.com/jackieong/AndroidMarionette/blob/master/AndroidManifest.xml)         
[activity_thib_jack_mario.xml](https://github.com/jackieong/AndroidMarionette/blob/master/)

##### Manual Move Screen
[Manualmove.java](https://github.com/jackieong/AndroidMarionette/blob/master/Manualmove.java)            
[manualmove.xml](https://github.com/jackieong/AndroidMarionette/blob/master/manualmove.xml)  

##### Preset Move Screen          
[MakeSpeMove.java](https://github.com/jackieong/AndroidMarionette/blob/master/MakeSpeMove.java)            
[makeSpemove.xml](https://github.com/jackieong/AndroidMarionette/blob/master/makeSpemove.xml)            

##### Manifest File 
[AndroidManifest.xml](https://github.com/jackieong/AndroidMarionette/blob/master/AndroidManifest.xml)      

<a name="arduino"/>
## Arduino

### Calibrating Servo Motors

Below is a diagram showing how the servo motors were oriented against the housing as well as the angle orientation of the servo motors with respect to the housing.       
![alt text](https://raw.github.com/jackieong/AndroidMarionette/master/servoMotorOrientation.png)
<a name="servosetup"/>

### Bluetooth Setup - Receiving Data 
To setup the Bluetooth, the following bit of starter code is helpful to setup Serial to start receiving data.
```
void setup() 
{...
     Serial.begin(115200);
}

void loop()
{
  if(Serial.available() >= 6) // for six characters we are sending from Android
  {
    for (byte i=0; i<=6; i++)
    {
      command[i] = Serial.read();
    }
  }
}
```
Check your baud rate matches that of your bluetooth modem to ensure that the Arduino was processing appropriately to register the data received from the bluetooth connection. Then, to setup the transfer the data, we set Serial as available and wait until all six have been transferred before we begin reading the values into our char array called command.

### Entire Arduino Code
For further detail, check out the code [here](https://github.com/jackieong/AndroidMarionette/blob/master/MarionetteControl/MarionetteControl.ino).
