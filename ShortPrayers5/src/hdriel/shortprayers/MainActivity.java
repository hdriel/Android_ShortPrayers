package hdriel.shortprayers;

import hdriel.shortprayers.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

	Button b1 ,b2, b3, b4, b5, b6, b7 ,b8, b9, b10, b11, b12, b13, b_set;
	Intent i , geti;
	 
	public static final String PRAYERS_NUMBER = "number of prayers" ;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        b1 = (Button) findViewById(R.id.button_1);
        b2 = (Button) findViewById(R.id.button_2);
        b3 = (Button) findViewById(R.id.button_3);
        b4 = (Button) findViewById(R.id.button_4);
        b5 = (Button) findViewById(R.id.button_5);
        b6 = (Button) findViewById(R.id.button_6);
        b7 = (Button) findViewById(R.id.button_7);
        b8 = (Button) findViewById(R.id.button_8);
        b9 = (Button) findViewById(R.id.button_9);
        b10 = (Button) findViewById(R.id.button_10);
        b11 = (Button) findViewById(R.id.button_11);
        b12 = (Button) findViewById(R.id.button_12);
        b13 = (Button) findViewById(R.id.button_13);
        b_set = (Button) findViewById(R.id.button_to_setting);
        
       
        b1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 1);
				startActivity(i);
			}
		});
        
		b2.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 2);
				startActivity(i);
			}
		});
				
		b3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 3);
				startActivity(i);
			}
		});

		b4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 4);
				startActivity(i);
			}
		});
		
		b5.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 5);
				startActivity(i);
			}
		});
		
		b6.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 6);
				startActivity(i);
			}
		});
        b7.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 7);
				startActivity(i);
			}
		});
        
		b8.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 8);
				startActivity(i);
			}
		});
				
		b9.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 9);
				startActivity(i);
			}
		});

		b10.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 10);
				startActivity(i);
			}
		});
		
		b11.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 11);
				startActivity(i);
			}
		});
		
		b12.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 12);
				startActivity(i);
			}
		});
        b13.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ContainerPrayerActivity.class);
				i.putExtra(PRAYERS_NUMBER , 13);
				startActivity(i);
			}
		});

		b_set.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				i = new Intent(MainActivity.this , ButtonSettingActivity.class);
				startActivity(i);
			}
		});
        
        
    }
    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }
 
    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
        		MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("יציאה!");
        // Setting Dialog Message
        alertDialog.setMessage("אתה בטוח שאתה רוצה לצאת?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.exit);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("כן",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("לא",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
			startActivity(new Intent(MainActivity.this , ButtonSettingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
