package com.example.thibjackmarionette;

//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//import android.widget.TextView;
//import android.widget.Button;
//import android.view.View;
//import android.view.MotionEvent;




import java.util.Set;



import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.Toast;
import android.content.Intent;

import android.bluetooth.BluetoothDevice;







public class ThibJackMario extends Activity //implements View.OnClickListener
{

	private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
	private Set<BluetoothDevice> devices;
	

	
	
	
	void checkBlue() //CHECK BLUE
	{
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); 
		if(bluetoothAdapter == null) //check id your phone has a blue antenna
		{
			Toast.makeText(ThibJackMario.this, "You do not got Bluetooth!", Toast.LENGTH_SHORT).show();
		}	
		else
		{
			Toast.makeText(ThibJackMario.this, "You got Bluetooth!", Toast.LENGTH_SHORT).show();
		}
	
		
		if(!bluetoothAdapter.isEnabled()) //check if the antenna is on or not, if not it asks you if you want to put it on
		{
			Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBluetooth, REQUEST_CODE_ENABLE_BLUETOOTH);
		}	
		
		
		devices = bluetoothAdapter.getBondedDevices();		
		for(BluetoothDevice blueDevice : devices) //get the list of known blue device
		{
			Toast.makeText(ThibJackMario.this, "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
		}
		
		
	/*	Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
		startActivity(discoverableIntent);*/
		Toast.makeText(ThibJackMario.this, "Device = " + devices, Toast.LENGTH_SHORT).show();		
		
	}


	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		checkBlue();

		
		setContentView(R.layout.activity_thib_jack_mario);
		
		Button b1 = (Button) findViewById(R.id.dance1);
		Button b2 = (Button) findViewById(R.id.dance2);
		

	    
	    
	    
		b1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View view)
			{
				Intent Act1 = new Intent(ThibJackMario.this, MakeSpeMove.class);
				startActivity(Act1);
			}
		});

		b2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View view)
			{
				Intent Act2 = new Intent(ThibJackMario.this, manualmove.class);
				startActivity(Act2);
			}
		});
		
		
	}
	
	

	
	

	

}

