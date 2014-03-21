package com.example.thibjackmarionette;


import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.content.Intent;

import android.widget.SeekBar.OnSeekBarChangeListener;;



public class manualmove extends Activity 
{
	
	public int ProgressArmLeftX = 0;
	public int ProgressArmLeftY = 0;
	public int ProgressArmRightY = 0;
	public int ProgressArmRightX = 0;
	public int ProgressLegLeftY = 0;
	public int ProgressLegLeftX = 0;
	public int ProgressLegRightY = 0;
	public int ProgressLegRightX = 0;
	public int ProgressHead  = 0;
	
	
	// SPP UUID service 
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	  
	// MAC-address of Bluetooth module (you must edit this line)
	private static String address = "00:06:66:04:AF:59"; // mac address of the BlueSMiRF

	  private BluetoothAdapter btAdapter = null;
	  private BluetoothSocket btSocket = null;
	  private OutputStream outStream = null;
	  
	  private static final String TAG = "bluetooth1";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manualmove);
		
		Button b1 = (Button) findViewById(R.id.Back);
		SeekBar ArmLeftY = (SeekBar) findViewById(R.id.ArmLeftY);
		SeekBar ArmLeftX = (SeekBar) findViewById(R.id.ArmLeftX);		
		SeekBar ArmRightY = (SeekBar) findViewById(R.id.ArmRightY);		
		SeekBar ArmRightX = (SeekBar) findViewById(R.id.ArmRightX);		
		SeekBar LegLeftY = (SeekBar) findViewById(R.id.LegLeftY);		
		SeekBar LegLeftX = (SeekBar) findViewById(R.id.LegLeftX);		
		SeekBar LegRightY = (SeekBar) findViewById(R.id.LegRightY);		
		SeekBar LegRightX = (SeekBar) findViewById(R.id.LegRightX);		
		SeekBar Head = (SeekBar) findViewById(R.id.Head);	
		
		btAdapter = BluetoothAdapter.getDefaultAdapter();
	    checkBTState();
		

		
		b1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View view)
			{
				sendData("000000");
				finish();
			}
		});

		/***************************************************************************************************************************
		*********   SeekBar Listeners To Track User Input Controls into SeekBar for Each Limb's Horiz./Vert. Motions	   *********
		***************************************************************************************************************************/
		
		
		// Left Arm Vertical Motion
		ArmLeftY.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressArmLeftY = 0;
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressArmLeftY = progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//begin tracking clicking on seekBar
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressArmLeftY <= 9) sendData("ALY00"+ Integer.toString(ProgressArmLeftY)); 
				else if(ProgressArmLeftY <= 99) sendData("ALY0"+ Integer.toString(ProgressArmLeftY));
				else if(ProgressArmLeftY == 100) sendData("ALY"+ Integer.toString(ProgressArmLeftY));
				Toast.makeText(manualmove.this, "Arm Left Y "+ ProgressArmLeftY +"%", Toast.LENGTH_SHORT).show();
			}			
		});
		
		
		// Left Arm Horizontal Motion
		ArmLeftX.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressArmLeftX = 0;
			
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressArmLeftX = progress;			
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//begin tracking clicking on seekBar
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressArmLeftX <= 9) sendData("ALX00"+ Integer.toString(ProgressArmLeftX)); 
				else if(ProgressArmLeftX <= 99) sendData("ALX0"+ Integer.toString(ProgressArmLeftX));
				else if(ProgressArmLeftX == 100) sendData("ALX"+ Integer.toString(ProgressArmLeftX));
				Toast.makeText(manualmove.this, "Arm Left X "+ ProgressArmLeftX +"%", Toast.LENGTH_SHORT).show();
			}
			
						
		});
		
		// Right Arm Vertical Motion
		ArmRightY.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressArmRightY = 0;
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressArmRightY = progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//begin tracking clicking on seekBar
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressArmRightY <= 9) sendData("ARY00"+ Integer.toString(ProgressArmRightY)); 
				else if(ProgressArmRightY <= 99) sendData("ARY0"+ Integer.toString(ProgressArmRightY));
				else if(ProgressArmRightY == 100) sendData("ARY"+ Integer.toString(ProgressArmRightY));
				Toast.makeText(manualmove.this, "Arm Right Y "+ ProgressArmRightY +"%", Toast.LENGTH_SHORT).show();
			}
						
		});
		
		// Right Arm Horizontal Motion
		ArmRightX.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressArmRightX = 0;
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressArmRightX = progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//begin tracking clicking on seekBar
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressArmRightX <= 9) sendData("ARX00"+ Integer.toString(ProgressArmRightX)); 
				else if(ProgressArmRightX <= 99) sendData("ARX0"+ Integer.toString(ProgressArmRightX));
				else if(ProgressArmRightX == 100)sendData("ARX"+ Integer.toString(ProgressArmRightX));
				Toast.makeText(manualmove.this, "Arm Right X "+ ProgressArmRightX +"%", Toast.LENGTH_SHORT).show();
			}
						
		});
		
		// Left Leg Vertical Motion
		LegLeftY.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressLegLeftY = 0;
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressLegLeftY = progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//begin tracking clicking on seekBar
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressLegLeftY <= 9) sendData("LLY00"+ Integer.toString(ProgressLegLeftY)); 
				else if(ProgressLegLeftY <= 99) sendData("LLY0"+ Integer.toString(ProgressLegLeftY));
				else if(ProgressLegLeftY == 100)sendData("LLY"+ Integer.toString(ProgressLegLeftY));
				Toast.makeText(manualmove.this, "Leg Left Y "+ ProgressLegLeftY +"%", Toast.LENGTH_SHORT).show();
			}
						
		});
		
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
						
		});
		// Right Leg Vertical Motion
		LegRightY.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressLegRightY = 0;
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressLegRightY = progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//begin tracking clicking on seekBar
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressLegRightY <= 9) sendData("LRY00"+ Integer.toString(ProgressLegRightY)); 
				else if(ProgressLegRightY <= 99) sendData("LRY0"+ Integer.toString(ProgressLegRightY));
				else if(ProgressLegRightY == 100)sendData("LRY"+ Integer.toString(ProgressLegRightY));
				Toast.makeText(manualmove.this, "Leg Right Y "+ ProgressLegRightY +"%", Toast.LENGTH_SHORT).show();
			}
						
		});
		// Right Leg Horizontal Motion
		LegRightX.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressLegRightX = 0;
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressLegRightX = progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressLegRightX <= 9) sendData("LRX00"+ Integer.toString(ProgressLegRightX)); 
				else if(ProgressLegRightX <= 99) sendData("LRX0"+ Integer.toString(ProgressLegRightX));
				else if(ProgressLegRightX == 100) sendData("LRX"+ Integer.toString(ProgressLegRightX));
				Toast.makeText(manualmove.this, "Leg Right X "+ ProgressLegRightX +"%", Toast.LENGTH_SHORT).show();
			}
						
		});
		// Head (Vertical) Motion
		Head.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			int ProgressHead = 0;
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ProgressHead = progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//gnnnnn
			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if(ProgressHead <= 9) sendData("HHH00"+ Integer.toString(ProgressHead)); 
				else if(ProgressHead <= 99) sendData("HHH0"+ Integer.toString(ProgressHead));
				else if(ProgressHead == 100) sendData("HHH"+ Integer.toString(ProgressHead));
				Toast.makeText(manualmove.this, "Head "+ ProgressHead +"%", Toast.LENGTH_SHORT).show();
			}
						
		});
	
	}
	

	  private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
	      if(Build.VERSION.SDK_INT >= 10){
	          try {
	              final Method  m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
	              return (BluetoothSocket) m.invoke(device, MY_UUID);
	          } catch (Exception e) {
	              Log.e(TAG, "Could not create Insecure RFComm Connection",e);
	          }
	      }
	      return  device.createRfcommSocketToServiceRecord(MY_UUID);
	  }
	    
	  @Override
	  public void onResume() {
	    super.onResume();
	  
	    Log.d(TAG, "...onResume - try connect...");
	    
	    // Setting up a pointer to the remote node using it's address.
	    BluetoothDevice device = btAdapter.getRemoteDevice(address);
	    
	    // To make a connection, we have:
	    //   a MAC address (found above) and a UUID for SPP
	    
	    try {
	        btSocket = createBluetoothSocket(device);
	    } catch (IOException e1) {
	        errorExit("Fatal Error", "In onResume() and socket create failed: " + e1.getMessage() + ".");
	    }
	        
	    // Discovery is resource intensive.  
	    // Cancel discovery when attempting to connect and pass your message
	    btAdapter.cancelDiscovery();
	    
	    // Establish the connection
	    Log.d(TAG, "...Connecting...");
	    try {
	      btSocket.connect();
	      Log.d(TAG, "...Connection ok...");
	    } catch (IOException e) {
	      try {
	        btSocket.close();
	      } catch (IOException e2) {
	        errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
	      }
	    }
	      
	    // Create a data stream so we can talk to server
	    Log.d(TAG, "...Create Socket...");
	  
	    try {
	      outStream = btSocket.getOutputStream();
	    } catch (IOException e) {
	      errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
	    }
	  }
	  
	  @Override
	  public void onPause() {
	    super.onPause();
	  
	    Log.d(TAG, "...In onPause()...");
	  
	    if (outStream != null) {
	      try {
	        outStream.flush();
	      } catch (IOException e) {
	        errorExit("Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
	      }
	    }
	  
	    try     {
	      btSocket.close();
	    } catch (IOException e2) {
	      errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
	    }
	  }
	    
	  private void checkBTState() {
	    // Check for Bluetooth support and then check to make sure it is turned on
	    // Emulator doesn't support Bluetooth and will return null
	    if(btAdapter==null) { 
	      errorExit("Fatal Error", "Bluetooth not support");
	    } else {
	      if (btAdapter.isEnabled()) {
	        Log.d(TAG, "...Bluetooth ON...");
	      } else {
	        //Prompt user to turn on Bluetooth
	        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	        startActivityForResult(enableBtIntent, 1);
	      }
	    }
	  }
	  
	  private void errorExit(String title, String message){
	    Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_SHORT).show();
	    finish();
	  }
	  
	  private void sendData(String message) {
	    byte[] msgBuffer = message.getBytes();
	  
	    Log.d(TAG, "...Send data: " + message + "...");
	  
	    try {
	      outStream.write(msgBuffer);
	    } catch (IOException e) {
	      String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
	      if (address.equals("00:00:00:00:00:00")) 
	        msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 35 in the java code";
	        msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";
	        
	        errorExit("Fatal Error", msg);       
	    }
	  }
	
	
}
