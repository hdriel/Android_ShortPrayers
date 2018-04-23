package hdriel.shortprayers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import service.NotifyService;
import service.ScheduleClient;
import hdriel.shortprayers.R;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ZoomControls;

public class ContainerPrayerActivity extends Activity {

	 private final int max_size_text = 35;
	 private final int min_size_text = 10;
	 private final int BLACK = 0xff000000;
	 private final int WHITE = 0xFFF3F3F3;
     
     private int size_text = 14;
     private int color_text , color_bg , numPrayRemainder = -1;
     private Typeface typeComix2 , typeSimple , typeStam ,typeTrashim,typeDavid , curentType , prefType;
     private ZoomControls zoom;
     private LinearLayout bg;
     private String font = "";
     private TextView contain , title;
     private ToggleButton change_bg, reminder;
     private Intent geti, mNotificationIntent, serviceIntent;
	 private PendingIntent mContentIntent;
	 
	 private SharedPreferences prefs;
	 public static final String RemainderToggle = "Remainer to me";
	 public static final String NumberRemainder = "number of pray to remainer to me";
	 public static final String TEXT_TITLE_REMINDER = "the title of the notification";
	 public static final String TEXT_CONTAIN_REMINDER = "the contain of the notification";
	 
	 public static final String PRAYERS_NUMBER = "number of prayers";
	 public static final String MyPREFERENCES  = "MyPrefs";
	 public static final String SizeText       = "SizeOfText";
	 public static final String ModeToggle     = "ModeOfToggleButton";
	 public static final String FontText       = "TypeFontOfText";
	 
	 // This is a handle so that we can call methods on our service
	 private ScheduleClient scheduleClient;
	 // This is the date picker used to select the date for our notification
	 private DatePicker pickerDate;
	 private TimePicker pickerTime;
	 
	 Calendar myCalendar = Calendar.getInstance();

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.prayers_layout);
	        
	        typeComix2  = Typeface.createFromAsset(getAssets(),"comixno2clm_bold-webfont.ttf"); 
	        typeSimple  = Typeface.createFromAsset(getAssets(),"SimpleCLM-BoldOblique.ttf"); 
	        typeStam    = Typeface.createFromAsset(getAssets(),"stamashkenazclm-webfont.ttf");
	        typeTrashim = Typeface.createFromAsset(getAssets(),"trashimclm-bold-webfont.ttf"); 
	        typeDavid   = Typeface.createFromAsset(getAssets(),"david.ttf"); 
	        
	        prefs       = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	        
	        zoom        = (ZoomControls) findViewById(R.id.zoomControls1_button);
	        bg          = (LinearLayout) findViewById(R.id.linearLayout_bg_button);
	        contain     = (TextView) findViewById(R.id.contain_button);
	        title       = (TextView) findViewById(R.id.title_button);
	        reminder    = (ToggleButton) findViewById(R.id.remmember_me_button);
	        change_bg   = (ToggleButton) findViewById(R.id.bgColor_button);
	        
	        // Create a new service client and bind our activity to this service
	        scheduleClient = new ScheduleClient(this);
	        scheduleClient.doBindService();
	        serviceIntent = new Intent("service.NotifyService");
	        // set the size
	        contain.setTextSize(prefs.getInt(SizeText, size_text));
	        
	        // get the number of pray to display from the prev intent
	        geti = getIntent();
	        Bundle b = geti.getExtras();
	        int numPrayer = 0;
	        if(b != null)
	        	numPrayer = getIntent().getExtras().getInt(PRAYERS_NUMBER);
	        
	        // update the text by the correct pray
	        switch (numPrayer) 
	        {
				case 1:
					title.setText(getResources().getString(R.string.text_title_button1));
					contain.setText(getResources().getString(R.string.text_contain_button1));
					break;
				case 2:
					title.setText(getResources().getString(R.string.text_title_button2));
					contain.setText(getResources().getString(R.string.text_contain_button2));
					break;
				case 3:
					title.setText(getResources().getString(R.string.text_title_button3));
					contain.setText(getResources().getString(R.string.text_contain_button3));
					break;
				case 4:
					title.setText(getResources().getString(R.string.text_title_button4));
					contain.setText(getResources().getString(R.string.text_contain_button4));
					break;
				case 5:
					title.setText(getResources().getString(R.string.text_title_button5));
					contain.setText(getResources().getString(R.string.text_contain_button5));
					break;
				case 6:
					title.setText(getResources().getString(R.string.text_title_button6));
					contain.setText(getResources().getString(R.string.text_contain_button6));
					break;
				case 7:
					title.setText(getResources().getString(R.string.text_title_button7));
					contain.setText(getResources().getString(R.string.text_contain_button7));
					break;
				case 8:
					title.setText(getResources().getString(R.string.text_title_button8));
					contain.setText(getResources().getString(R.string.text_contain_button8));
					break;
				case 9:
					title.setText(getResources().getString(R.string.text_title_button9));
					contain.setText(getResources().getString(R.string.text_contain_button9));
					break;
				case 10:
					title.setText(getResources().getString(R.string.text_title_button10));
					contain.setText(getResources().getString(R.string.text_contain_button10));
					break;
				case 11:
					title.setText(getResources().getString(R.string.text_title_button11));
					contain.setText(getResources().getString(R.string.text_contain_button11));
					break;
				case 12:
					title.setText(getResources().getString(R.string.text_title_button12));
					contain.setText(getResources().getString(R.string.text_contain_button12));
					break;
				case 13:
					title.setText(getResources().getString(R.string.text_title_button13));
					contain.setText(getResources().getString(R.string.text_contain_button13));
					break;
				default:
					if(prefs.getBoolean(RemainderToggle, false) && prefs.getInt(NumberRemainder, -1) != -1){
			        	title.setText(prefs.getString(TEXT_TITLE_REMINDER, "שגיאה!"));
			        	contain.setText(prefs.getString(TEXT_CONTAIN_REMINDER, "צצה בעיה בטעינת הברכה."));
			        }
					else{
						title.setText("שגיאה!");
						contain.setText(".צצה בעיה בטעינת הברכה הזאת");
						numPrayRemainder = -1;
					}
					break;
			}
			numPrayRemainder = numPrayer;
	        
			// set the font
	        font = prefs.getString(FontText, "דויד");
	        switch  (font) 
	        {
				case "פשוט":
					curentType = typeSimple;
					break;
				case "קומיקס מס\' 2":
					curentType = typeComix2;
					break;
				case "כתב סתם":
					curentType = typeStam;
					break;
				case "טרשים":
					curentType = typeTrashim;
					break;
				case "דויד":
					curentType = typeDavid;
					break;
				default: 
					curentType = typeDavid;
					break;
			}
	        title.setTypeface(curentType);
	        contain.setTypeface(curentType);
	        
	        // zoom in
	        zoom.setOnZoomInClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View v) {
	    			// TODO Auto-generated method stub
	    			if(size_text <= max_size_text)
	    			{
	    				size_text = prefs.getInt(SizeText, size_text);
	    				size_text = size_text + 2;
	    		     	SharedPreferences.Editor editor = prefs.edit();
			            editor.putInt( SizeText , size_text);
			            editor.commit();
			            contain.setTextSize(size_text);
    				}
	    			else 
	    			    Toast.makeText(getApplicationContext(), "לא ניתן להגדיל עוד", Toast.LENGTH_SHORT).show();
	    		}
	    	  });
	        
	        // zoom out
		    zoom.setOnZoomOutClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					if(size_text >= min_size_text)	
					{
						size_text = prefs.getInt(SizeText, size_text);
						size_text = size_text-2;
	    		     	SharedPreferences.Editor editor = prefs.edit();
			            editor.putInt( SizeText , size_text);
			            editor.commit();
			            contain.setTextSize(size_text);
					}
				    else 
	    			    Toast.makeText(getApplicationContext(), "לא ניתן להקטין עוד", Toast.LENGTH_SHORT).show();
				}
		    });
		    
		    
		    // toggle of the background
		    change_bg.setChecked(prefs.getBoolean(ModeToggle, false));
		    change_bg.setTextOff(getResources().getString(R.string.bgColorBlack_button));
            change_bg.setTextOn(getResources().getString(R.string.bgColorWhite_button));
            
		    if (change_bg.isChecked()) 
		    {
		    	change_bg.setTextColor(WHITE);
				bg.setBackgroundColor(BLACK);
				contain.setTextColor(WHITE);
				title.setTextColor(WHITE);
			}
        	else 
        	{
				bg.setBackgroundColor(WHITE);
				contain.setTextColor(BLACK);
				title.setTextColor(BLACK);
				change_bg.setTextColor(BLACK);
			}
            
	        change_bg.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) 
	            {
	            	if (change_bg.isChecked()) 
	            	{
	            		SharedPreferences.Editor editor = prefs.edit();
			            editor.putBoolean(ModeToggle, true);
			            editor.commit();
	            		
			            change_bg.setTextColor(WHITE);
			            reminder.setTextColor(WHITE);
						bg.setBackgroundColor(BLACK);
						contain.setTextColor(WHITE);
						title.setTextColor(WHITE);
					}
	            	else 
	            	{
	            		SharedPreferences.Editor editor = prefs.edit();
			            editor.putBoolean(ModeToggle, false);
			            editor.commit();
						
						bg.setBackgroundColor(WHITE);
						contain.setTextColor(BLACK);
						title.setTextColor(BLACK);
						change_bg.setTextColor(BLACK);
						reminder.setTextColor(BLACK);
					}
	            }
		     });
	        
		    // toggle of the remainder
	        reminder.setOnClickListener(new OnClickListener() {
	            @SuppressLint("NewApi") @Override
	            public void onClick(View v) 
	            {
	            	if (reminder.isChecked()) 
	            	{
	            		SharedPreferences.Editor editor = prefs.edit();
			            editor.putBoolean(RemainderToggle, true);
			            editor.putInt(NumberRemainder, numPrayRemainder);
			            editor.putString(TEXT_TITLE_REMINDER, title.getText().toString());
			            editor.putString(TEXT_CONTAIN_REMINDER, contain.getText().toString());
			            editor.commit();
			            
			            new TimePickerDialog(ContainerPrayerActivity.this, time, myCalendar
			                    .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
			            
			            new DatePickerDialog(ContainerPrayerActivity.this, date, myCalendar
			                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
			                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
					}
	            	else 
	            	{
	            		SharedPreferences.Editor editor = prefs.edit();
			            editor.putBoolean(RemainderToggle, false);
			            editor.putString(TEXT_TITLE_REMINDER, "");
			            editor.putString(TEXT_CONTAIN_REMINDER, "");
			            editor.putInt(NumberRemainder, -1);
			            editor.commit();
						
			            if(scheduleClient != null){
			    	    	scheduleClient.doUnbindService();
						    stopService(serviceIntent);
			            }
			            Toast.makeText(getApplicationContext(), "אתה ביטלת את התזכורת עבור התפילה הקצרה", Toast.LENGTH_LONG).show();
					}
	            	
	            }
		    });
	        
	        if (!isMyServiceRunning()){
			     this.startService(serviceIntent);
			}
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
	        	startActivity(new Intent(ContainerPrayerActivity.this , ButtonSettingActivity.class));
	        	finish();
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	    
	    @Override
	    protected void onStop() {
	    	// When our activity is stopped ensure we also stop the connection to the service
	    	// this stops us leaking our activity into the system *bad*
	    	//if(scheduleClient != null)
	    	//	scheduleClient.doUnbindService();
	    	super.onStop();
	    }
	    
	    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() 
	    {
	        @Override
	        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	            myCalendar.set(Calendar.YEAR, year);
	            myCalendar.set(Calendar.MONTH, monthOfYear);
	            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	        }
	    };
	    
	    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() 
	    {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute ) {
				myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
	            myCalendar.set(Calendar.MINUTE, minute);
	            myCalendar.set(Calendar.SECOND, 0);
	            updateService();
			}
	    };
	    
	    private void updateService() {
	    	String myFormat = "dd/MM/yy , HH:mm:ss"; //In which you need put here
	        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
	        String str = sdf.format(myCalendar.getTime());
	        
	    	scheduleClient.setAlarmForNotification(myCalendar);
	    	// Notify the user what they just did
	    	Toast.makeText(this, "אתה תקבל תזכורת בזמן שקבעת: "+ str, Toast.LENGTH_LONG).show();
	    }
	    
	    private boolean isMyServiceRunning() {
	         ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	         for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	             if (NotifyService.class.getName().equals(service.service.getClassName())) {
	                 return true;
	             }
	         }
	         return false;
	    }
}
