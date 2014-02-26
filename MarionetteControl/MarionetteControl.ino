// Thibaud & Jackie 
// Embedded Project 1

#include <Servo.h> 
#define RESETPOS 0
#define ZEROPOS 0
#define MAXPOS 180
#define MIDDLEPOS 90

Servo Head;
//arms servo
Servo ArmLeftX;
Servo ArmLeftY;
Servo ArmRightX;
Servo ArmRightY;
//leg servo
Servo LegLeftX;
Servo LegLeftY;
Servo LegRightX;
Servo LegRightY;

char command[100] = {'\0'};


int valueHead;

int valueArmLX;
int valueArmLY;
int valueArmRX;
int valueArmRY;

int valueLegLX;
int valueLegLY;
int valueLegRX;
int valueLegRY;
 
void setup()
{
  setupMotors();
  resetMotors();

  Serial.begin(115200);

} 

void loop()
{

  if(Serial.available() >= 6)
  {
    for (byte i=0; i<10; i++)
    {
      command[i] = Serial.read();
    }
  }
  if ( command[0] == '0' && command[1] == '0' && command[2] == '0' && command[3] == '0' && command[4] == '0' && command[5] == '0')
  {
    resetMotors();
    char command[100] = {'\0'};
  }
  // only applicable to preset moves
  else if (command[0] == 'A' && command[1] == 'A' && command[2] == 'A' && command[3] == 'A' && command[4] == 'A' && command[5] == 'A') 
  {
    headNod();
    resetMotors();
    resetCommand();
  }
  else if (command[0] == 'B' && command[1] == 'B' && command[2] == 'B' && command[3] == 'B' && command[4] == 'B' && command[5] == 'B')
  {
    cheerLeader();
    resetMotors();
    resetCommand();
  }
  else if (command[0] == 'C' && command[1] == 'C' && command[2] == 'C' && command[3] == 'C' && command[4] == 'C' && command[5] == 'C')
  {
     crazyArms();
     resetMotors();
     resetCommand();
  }
  else if (command[0] == 'D' && command[1] == 'D' && command[2] == 'D' && command[3] == 'D' && command[4] == 'D' && command[5] == 'D')
  {
    karateKick(); 
    resetMotors();
    resetCommand();
  }
  else if ( command[0] == 'H' && command[1] == 'H' && command[2] == 'H' ) // heqd
  {
    valueHead = getAngle(command);
    moveHead(valueHead, 250);
  }
  else if ( command[0] == 'A' && command[1] == 'L') //left arm
  {  
    if(command[2] == 'X')
    {
      valueArmLX = getAngle(command); 
      moveLeftArm(valueArmLX, valueArmLY, 250);
    }
    else if(command[2] == 'Y')
    {
      valueArmLY = getAngle(command); 
      moveLeftArm(valueArmLX, valueArmLY, 250);
    }
  } 
  else if (command[0] == 'A' && command[1] == 'R' ) // right arm
  {  
    if(command[2] == 'X')
    {
      valueArmRX = getAngle(command); 
      moveRightArm(valueArmRX, valueArmRY, 250);
    }
    else if(command[2] == 'Y')
    {
      valueArmRY = getAngle(command); 
      moveRightArm(valueArmRX, valueArmRY, 250);
    }
  }
  else if (command[0] == 'L' && command[1] == 'L' ) // left leg 
  {  
    if(command[2] == 'X')
    {
      valueLegLX = getAngle(command); 
      moveLeftLeg(valueLegLX, valueLegLY, 250);
    }
    else if(command[2] == 'Y')
    {
      valueLegLY = getAngle(command); 
      moveLeftLeg(valueLegLX, valueLegLY, 250);
    }
  }
  else if (command[0] == 'L' && command[1] == 'R' ) // right leg 
  {  
    if(command[2] == 'X')
    {
      valueLegRX = getAngle(command); 
      moveRightLeg(valueLegRX, valueLegRY, 250);
    }
    else if(command[2] == 'Y')
    {
      valueLegRY = getAngle(command); 
      moveRightLeg(valueLegRX, valueLegRY, 250);
    }
  }
   char command[100] = {'\0'};
}

 
 void resetCommand()
{
  command[0] = '1';
  command[1] = '1';
  command[2] = '1';
  command[3] = '1';
  command[4] = '1';
  command[5] = '1';
} 

  


/****************************************
            setupMotors()
*****************************************/

void setupMotors()
{
  Head.attach(11); 
  ArmLeftX.attach(2);
  ArmLeftY.attach(3);
  ArmRightX.attach(4);
  ArmRightY.attach(5);
  LegLeftX.attach(6);
  LegLeftY.attach(7);
  LegRightX.attach(8);
  LegRightY.attach(9);
}


void resetMotors()
{
  Head.write(40); 
  ArmLeftX.write(RESETPOS);
  ArmLeftY.write(RESETPOS);
  ArmRightX.write(RESETPOS);
  ArmRightY.write(RESETPOS);
  LegLeftX.write(RESETPOS);
  LegLeftY.write(RESETPOS);
  LegRightX.write(RESETPOS);
  LegRightY.write(RESETPOS);
  delay(1000);
}


/****************************************
            uprightPos()
*****************************************/
void uprightPos()
{
  Head.write(40); 
  ArmLeftX.write(RESETPOS);
  ArmLeftY.write(RESETPOS);
  ArmRightX.write(RESETPOS);
  ArmRightY.write(RESETPOS);
  LegLeftX.write(RESETPOS);
  LegLeftY.write(RESETPOS);
  LegRightX.write(RESETPOS);
  LegRightY.write(RESETPOS);
  delay(1000);
}

/****************************************
            moving marionette
****************************************/

void cheerLeader()
{
//  Cheerleader Straddle Jump!
  moveLeftArm(30, 120, 0);
  moveRightArm(30, 120, 0);
  moveLeftLeg(35 ,90, 0);
  moveRightLeg(35, 90, 5000);
  moveLeftArm(0, 0, 0);
  moveRightArm(0, 0, 0);
  moveLeftLeg(0, 0, 0);
  moveRightLeg(0, 0, 0);
}


void straddleKick()
{ // Straddle kick
  moveLeftArm(0, 90, 0);
  moveRightArm(0, 90, 0);
  moveLeftLeg(45, 90, 0);
  moveRightLeg(45, 90, 4000);
}

void headNod()
{
// Head-nodding
  moveLeftArm(0, 160, 0);
  moveRightArm(0, 160, 2000);
  moveHead(0,1000);
  moveHead(40,1000);
  moveHead(0,1000);
  moveHead(40,1000);
}

void crazyArms()
{
// Hand dance
  moveLeftArm(0,120,500);
  moveLeftArm(0,0,0);
  moveRightArm(0,120,500);
  moveRightArm(0,0,0);
  moveLeftArm(0,120,500);
  moveLeftArm(0,0,0);
  moveRightArm(0,120,500);
  moveRightArm(0,0,0);
  moveLeftArm(0,120,500);
  moveLeftArm(0,0,0);
  moveRightArm(0,120,500);
  moveRightArm(0,0,0);  
}



void karateKick()
{ // Karate kick
  moveLeftArm(0, 45, 0);
  moveRightArm(180, 0, 200);
  moveLeftLeg(0, 50, 3000);
}

/****************************************
            Helper Functions
*****************************************/
void moveHead(int y, int time)
{
  Head.write(y);
  delay(time);
}
void moveLeftArm(int x, int y, int time)
{
  ArmLeftX.write(x);
  ArmLeftY.write(y);
  delay(time);
}

void moveRightArm(int x, int y, int time)
{
  ArmRightX.write(x);
  ArmRightY.write(y);
  delay(time);
}

void moveRightLeg(int x, int y, int time)
{
  LegRightX.write(x);
  LegRightY.write(y);
  delay(time);
}

void moveLeftLeg(int x, int y, int time)
{
  LegLeftX.write(x);
  LegLeftY.write(y);
  delay(time);
}

/****************************************
            getAngle()
*****************************************/

int getAngle(char command[100])
{
  int value = 0;
  switch (command[3]) // the hundreds digit
    {
      case '0': switch (command[4]) // the tens digit
                {
                    case '0' : switch (command[5]) // the ones digit
                               {
                                   case '0' : value = 0; break; // 000
                                   case '1' : value = 1; break;
                                   case '2' : value = 2; break;
                                   case '3' : value = 3; break;
                                   case '4' : value = 4; break;
                                   case '5' : value = 5; break;
                                   case '6' : value = 6; break;
                                   case '7' : value = 7; break;
                                   case '8' : value = 8; break;
                                   case '9' : value = 9; break; // 009
                                   default : break;                                  
                               } break; 
                    case '1' : switch (command[5])
                               {
                                   case '0' : value = 10; break; // 010
                                   case '1' : value = 11; break;
                                   case '2' : value = 12; break;
                                   case '3' : value = 13; break;
                                   case '4' : value = 14; break;
                                   case '5' : value = 15; break;
                                   case '6' : value = 16; break;
                                   case '7' : value = 17; break;
                                   case '8' : value = 18; break;
                                   case '9' : value = 19; break; // 019
                                   default : break;                                  
                               } break; 
                    case '2' : switch (command[5])
                               {
                                   case '0' : value = 20; break;
                                   case '1' : value = 21; break;
                                   case '2' : value = 22; break;
                                   case '3' : value = 23; break;
                                   case '4' : value = 24; break;
                                   case '5' : value = 25; break;
                                   case '6' : value = 26; break;
                                   case '7' : value = 27; break;
                                   case '8' : value = 28; break;
                                   case '9' : value = 29; break;
                                   default : break;                                  
                               } break;
                    case '3' : switch (command[5])
                               {
                                   case '0' : value = 30; break;
                                   case '1' : value = 31; break;
                                   case '2' : value = 32; break;
                                   case '3' : value = 33; break;
                                   case '4' : value = 34; break;
                                   case '5' : value = 35; break;
                                   case '6' : value = 36; break;
                                   case '7' : value = 37; break;
                                   case '8' : value = 38; break;
                                   case '9' : value = 39; break;
                                   default : break;                                  
                               } break;
                    case '4' : switch (command[5])
                               {
                                   case '0' : value = 40; break;
                                   case '1' : value = 41; break;
                                   case '2' : value = 42; break;
                                   case '3' : value = 43; break;
                                   case '4' : value = 44; break;
                                   case '5' : value = 45; break;
                                   case '6' : value = 46; break;
                                   case '7' : value = 47; break;
                                   case '8' : value = 48; break;
                                   case '9' : value = 49; break;
                                   default : break;                                  
                               } break;
                    case '5' : switch (command[5])
                               {
                                   case '0' : value = 50; break;
                                   case '1' : value = 51; break;
                                   case '2' : value = 52; break;
                                   case '3' : value = 53; break;
                                   case '4' : value = 54; break;
                                   case '5' : value = 55; break;
                                   case '6' : value = 56; break;
                                   case '7' : value = 57; break;
                                   case '8' : value = 58; break;
                                   case '9' : value = 59; break;
                                   default : break;                                  
                               } break;
                    case '6' : switch (command[5])
                               {
                                   case '0' : value = 60; break;
                                   case '1' : value = 61; break;
                                   case '2' : value = 62; break;
                                   case '3' : value = 63; break;
                                   case '4' : value = 64; break;
                                   case '5' : value = 65; break;
                                   case '6' : value = 66; break;
                                   case '7' : value = 67; break;
                                   case '8' : value = 68; break;
                                   case '9' : value = 69; break;
                                   default : break;                                  
                               } break; 
                    case '7' : switch (command[5])
                               {
                                   case '0' : value = 70; break;
                                   case '1' : value = 71; break;
                                   case '2' : value = 72; break;
                                   case '3' : value = 73; break;
                                   case '4' : value = 74; break;
                                   case '5' : value = 75; break;
                                   case '6' : value = 76; break;
                                   case '7' : value = 77; break;
                                   case '8' : value = 78; break;
                                   case '9' : value = 79; break;
                                   default : break;                                  
                               } break; 
                    case '8' : switch (command[5])
                               {
                                   case '0' : value = 80; break;
                                   case '1' : value = 81; break;
                                   case '2' : value = 82; break;
                                   case '3' : value = 83; break;
                                   case '4' : value = 84; break;
                                   case '5' : value = 85; break;
                                   case '6' : value = 86; break;
                                   case '7' : value = 87; break;
                                   case '8' : value = 88; break;
                                   case '9' : value = 89; break;
                                   default : break;                                  
                               } break; 
                    case '9' : switch (command[5])
                               {
                                   case '0' : value = 90; break;
                                   case '1' : value = 91; break;
                                   case '2' : value = 92; break;
                                   case '3' : value = 93; break;
                                   case '4' : value = 94; break;
                                   case '5' : value = 95; break;
                                   case '6' : value = 96; break;
                                   case '7' : value = 97; break;
                                   case '8' : value = 98; break;
                                   case '9' : value = 99; break; // 099
                                   default : break;                                  
                               } break;                                
                    default : break;
                } break; 
         case '1' :  value = 100; break; // 100
        default : break;   
     }
     return value;
}

