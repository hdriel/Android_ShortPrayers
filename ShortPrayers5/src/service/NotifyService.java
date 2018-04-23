package service;

import hdriel.shortprayers.R;
import hdriel.shortprayers.ContainerPrayerActivity;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * This service is started when an Alarm has been raised
 * 
 * We pop a notification into the status bar for the user to click on
 * When the user clicks the notification a new activity is opened
 * 
 * @author paul.blundell
 */
public class NotifyService extends Service {
	 public static final String MyPREFERENCES  = "MyPrefs";
	 public static final String TEXT_TITLE_REMINDER = "the title of the notification";
	 public static final String TEXT_CONTAIN_REMINDER = "the contain of the notification";
	 public static final String NumberRemainder = "number of pray to remainer to me";
	 private static String str_title = "", str_text = "";
	 private static int numPray = -1;
	 private SharedPreferences prefs;
	/**
	 * Class for clients to access
	 */
	public class ServiceBinder extends Binder {
		NotifyService getService(){
			return NotifyService.this;
		}
	}

	// Unique id to identify the notification.
	private static final int NOTIFICATION = 123;
	// Name of an intent extra we can use to identify if this service was started to create a notification	
	public static final String INTENT_NOTIFY = "com.blundell.tut.service.INTENT_NOTIFY";
	// The system notification manager
	private NotificationManager mNM;

	@Override
	public void onCreate() {
		Log.i("NotifyService", "onCreate()");
		updateData();
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void updateData(){
		prefs     = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		str_title = prefs.getString(TEXT_TITLE_REMINDER, "ישנה בעיה בטעינת טקסט זה").toString();
		str_text  = prefs.getString(TEXT_CONTAIN_REMINDER, "ישנה בעיה בטעינת טקסט זה").toString();
		numPray   = prefs.getInt(NumberRemainder, -1);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("LocalService", "Received start id " + startId + ": " + intent);
		
		// If this service was started by out AlarmTask intent then we want to show our notification
		if(intent.getBooleanExtra(INTENT_NOTIFY, false))
			showNotification();
		
		// We don't care if this service is stopped as we have already delivered our notification
		return START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	// This is the object that receives interactions from clients
	private final IBinder mBinder = new ServiceBinder();

	/**
	 * Creates a notification and shows it in the OS drag-down status bar
	 */
	private void showNotification() {
		updateData();
		// This is the 'title' of the notification
		CharSequence title = str_title;
		// This is the icon to use on the notification
		int icon = R.drawable.book_icon;
		// This is the scrolling text of the notification
		CharSequence text = str_text;		
		// What time to show on the notification
		long time = System.currentTimeMillis();
		
		if(numPray == -1)
			text = title = "";
		
		Notification notification = new Notification(icon, text, time);

		// The PendingIntent to launch our activity if the user selects this notification
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, ContainerPrayerActivity.class), 0);

		// Set the info for the views that show in the notification panel.
		notification.setLatestEventInfo(this, title, text, contentIntent);

		// Clear the notification when it is pressed
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		// Send the notification to the system.
		mNM.notify(NOTIFICATION, notification);
		
		// Stop the service when we are finished
		stopSelf();
	}
}