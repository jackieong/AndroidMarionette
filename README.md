AndroidMarionette
=================


Check it out now Check it out now :D

Welcome to the Android-Controlled Marionette Project Page!

### Table of Contents  
[Introduction] (#introduction)   
[Materials List](#materials)     
[Overall Schematic](#overallschematic)    
[Housing and Servo Motor Setup](#housingsetup)      
[Arduino](#arduino)      
[Android App](#android)        
[Links to Arduino and Android Code](#code)  

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

Because of the steep cost in motors, we were able to secure partial funding from HMC's Fabrication Studio to offset the hardware cost.                  

<a name="overallschematic"/>
## Overall Schematic

Below is the overall schematic of the electronics of the servo motors.             
![alt text](https://raw.github.com/jackieong/AndroidMarionette/master/schemq_bb.png)           
For the BlueSMiRF, the pins (from left to right) are CTS, VCC, GND, TX, RX, RTS.

You have the option to have it battery-powered or PC-powered via USB. Because we were coding, we decided to power by PC.              

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

#### User Interface            
For the user interface, we designed the app to have three screens. A main screen to choose between two sub-screens whether you wanted to...           
1) control the marionette's individual limbs             
2) choose from a selection of four preset moves the marionette could perform.          

######  Sub-Screen 1
There are eight "seekbars" that lets the user scroll the seekbar to a desired angle (i.e.: extent) and ultimately customize the marionette's moves. Once the user lets go of the scroll bar the marionette will hold that position until the user chooses to reset the marionette's position to an upright position.

######  Sub-Screen 2
There are four buttons corresponding to four preset moves that allow the user to see a few fun moves the marionette can do. 
 
#### Creating the Android App            
We used [Android Developer Tools Eclipse Platform](http://developer.android.com/sdk/index.html?utm_source=weibolife) to code the Android App with the desirable interface and necessary buttons to command the servo motors.

Below is the code used to control the left leg's horizontal motion via the Android app scroll bar control for the left leg horizontal motion.

```
// Left Leg Horizontal Motion
	LegLeftX.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
	{
		int ProgressLegLeftX = 0;
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		{
			ProgressLegLeftX = progress;
		}
		public void onStartTrackingTouch(SeekBar seekBar)
		{
			//begin tracking clicking on seekBar
		}
		public void onStopTrackingTouch(SeekBar seekBar)
		{
			if(ProgressLegLeftX <= 9) sendData("LLX00"+ Integer.toString(ProgressLegLeftX)); 
			else if(ProgressLegLeftX <= 99) sendData("LLX0"+ Integer.toString(ProgressLegLeftX));
			else if(ProgressLegLeftX == 100)sendData("LLX"+ Integer.toString(ProgressLegLeftX));
			Toast.makeText(manualmove.this, "Leg Left X "+ ProgressLegLeftX +"%", Toast.LENGTH_SHORT).show();
		}
```

#### Bluetooth Connection - Sending Data            
To send data, we used the sendData() command and sent a char string, six characters long.
The first three chars tell which servo motor (i.e.: which limb) to move.
The last three chars tell that motor the angle the servo motor's arm will rotate.


For example: "ALX100"
The "ALX" stands for ArmLeftX, so the servo motor controlling the left arm's horizontal motion will move by 100 degrees.           

Note: Your manifest file should have the following code to grant the necessary "permissions" for Bluetooth to work.  
```
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
    <uses-permission android:name="android.permission.BLUETOOTH"/>
```

For full Android Code, click [here](#code).

<a name="arduino"/>
## Arduino

#### Calibrating Servo Motors

Below is a diagram showing how the servo motors were oriented against the housing as well as the angle orientation of the servo motors with respect to the housing.       
![alt text](https://raw.github.com/jackieong/AndroidMarionette/master/servoMotorOrientation.png)

#### Bluetooth Setup - Receiving Data 
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

For full Arduino Code, see the next section (Code).

## Arduino and Android Code
<a name="code"/>

#### [Arduino Code](https://github.com/jackieong/AndroidMarionette/blob/master/MarionetteControl/MarionetteControl.ino)


#### Android App Code            
###### Main Screen
[ThibJackMario.java](https://github.com/jackieong/AndroidMarionette/blob/master/AndroidManifest.xml)         
[activity_thib_jack_mario.xml](https://github.com/jackieong/AndroidMarionette/blob/master/)

###### Manual Move Screen
[Manual Move Java File](https://github.com/jackieong/AndroidMarionette/blob/master/manualmove.java)            
[Manual Move Activity File](https://github.com/jackieong/AndroidMarionette/blob/master/manualmove.xml)  

###### Preset Move Screen          
[Preset Move Java File](https://github.com/jackieong/AndroidMarionette/blob/master/MakeSpeMove.java)            
[Preset Move Activity File](https://github.com/jackieong/AndroidMarionette/blob/master/makeSpemove.xml)            

###### Manifest File 
[Manifest (Permissions) File](https://github.com/jackieong/AndroidMarionette/blob/master/AndroidManifest.xml)      
