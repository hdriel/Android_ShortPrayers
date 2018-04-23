package hdriel.shortprayers;


import hdriel.shortprayers.R;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ZoomControls;
import android.widget.AdapterView.OnItemSelectedListener;



public class ButtonSettingActivity extends Activity {
	 
	 Button ok , cancel;
	 ToggleButton change_bg; 
	 TextView txtSimple , txtSimple2_title , txtFont , txtSize , txtToggle ;     
	 LinearLayout bgSet , bgSetSimpleText;
	 Spinner spinnerFont , spinnerSize ;
	 ArrayAdapter<CharSequence> adapter_fonts, adapter_sizes, adapter_white_fonts, adapter_white_sizes;
	 
	 SharedPreferences prefs;
	 Typeface typeComix2 , typeSimple , typeStam ,typeTrashim,typeDavid , curentType , prefType;
	 
	 String font = "";
	 int curentSize , curentColorText, curentColorGround;
	 
	 public final String LARGE = "גדול";
	 public final String NORMAL = "בנוני";
	 public final String SMALL = "קטן";
	 public final int small = 14 , normal = 22 , large = 30;
	 public final int BLACK = 0xff000000;
     public final int WHITE = 0xFFF3F3F3;
     
	 public static final String MyPREFERENCES   = "MyPrefs";
	 public static final String SizeText        = "SizeOfText";
	 public static final String ModeToggle      = "ModeOfToggleButton";
	 public static final String FontText        = "TypeFontOfText";
	 public static final String ColorText       = "Color of text";
	 public static final String ColorBackground = "Color of background";
	 public static final String PositionSelectItemSpinnerFont = "Position of Select Item at Spinner Font";
	 public static final String PositionSelectItemSpinnerSize = "Position of Select Item at Spinner Size";
	 
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.button_setting);
	        
	        
	        typeComix2  = Typeface.createFromAsset(getAssets(),"comixno2clm_bold-webfont.ttf"); 
	        typeSimple  = Typeface.createFromAsset(getAssets(),"SimpleCLM-BoldOblique.ttf"); 
	        typeStam    = Typeface.createFromAsset(getAssets(),"stamashkenazclm-webfont.ttf");
	        typeTrashim = Typeface.createFromAsset(getAssets(),"trashimclm-bold-webfont.ttf"); 
	        typeDavid   = Typeface.createFromAsset(getAssets(),"david.ttf"); 
	        
	        change_bg = (ToggleButton) findViewById(R.id.night_mode_setting);
	        ok        = (Button) findViewById(R.id.button_OK);
	        cancel    = (Button) findViewById(R.id.button_Cancel);
	        txtSimple = (TextView) findViewById(R.id.textsimple_setting); // text example
	        txtSimple2_title = (TextView) findViewById(R.id.textsimple2_setting); // title of text example
	        txtFont   = (TextView) findViewById(R.id.textfonts_setting); // title
	        txtSize   = (TextView) findViewById(R.id.textsizes_setting); // title
	        txtToggle = (TextView) findViewById(R.id.text_view_toggleButton); // title description
	        	
	        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	               
	        spinnerFont = (Spinner) findViewById(R.id.spinnerfont);
	        adapter_fonts = ArrayAdapter.createFromResource(this, R.array.fonts, R.layout.dropdown_font);
	        spinnerFont.setAdapter(adapter_fonts);
	        
	        spinnerSize = (Spinner) findViewById(R.id.spinnersizes);
	        adapter_sizes = ArrayAdapter.createFromResource(this, R.array.sizes, R.layout.dropdown_sizes);
	        spinnerSize.setAdapter(adapter_sizes);
	        
	        adapter_white_fonts = ArrayAdapter.createFromResource(this, R.array.fonts, R.layout.dropdown_font_white);
	        adapter_white_sizes = ArrayAdapter.createFromResource(this, R.array.sizes, R.layout.dropdown_sizes_white);
	        
	        bgSet           = (LinearLayout) findViewById(R.id.linearLayout_bg_button_setting);
	        bgSetSimpleText = (LinearLayout) findViewById(R.id.linearLayout2_bg_button_setting);
	        
	        
	        getPrefDetail();
	        
	        
	        spinnerFont.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

					// Display a Toast message indicating the currently selected item
					//Toast.makeText(parent.getContext(),"בחרת את הגופן:  "+ spinnerFont.getSelectedItem().toString() /*parent.getItemAtPosition(pos).toString()*/ , Toast.LENGTH_LONG).show();
					switch  (spinnerFont.getSelectedItem().toString()) {
					case "דויד":
						curentType = typeDavid;
						font = ""+ spinnerFont.getSelectedItem().toString();
						break;
					case "פשוט":
						curentType = typeSimple;
						font = "" + spinnerFont.getSelectedItem().toString();
						break;
					case "קומיקס מס\' 2":
						curentType = typeComix2;
						font = ""+ spinnerFont.getSelectedItem().toString();
						break;
					case "כתב סתם":
						curentType = typeStam;
						font = ""+ spinnerFont.getSelectedItem().toString();
						break;
					case "טרשים":
						curentType = typeTrashim;
						font = ""+ spinnerFont.getSelectedItem().toString();
						break;
					default: 
						curentType = typeDavid;
						break;
					}
					txtSimple.setTypeface(curentType);
					txtSimple2_title.setTypeface(curentType);
					//Toast.makeText(parent.getContext(),"בחרת את הגופן: \'"+ spinnerFont.getSelectedItem().toString() + "\'" /*parent.getItemAtPosition(pos).toString()*/ , Toast.LENGTH_LONG).show();
				}
				public void onNothingSelected(AdapterView<?> parent) {
				}
			});
	        
	        spinnerSize.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

					// Display a Toast message indicating the currently selected// item
					//Toast.makeText(parent.getContext(),"בחרת שהכתב יהיה בגודל "	+ parent.getItemAtPosition(pos).toString(),	Toast.LENGTH_LONG).show();
					switch  (spinnerSize.getSelectedItem().toString()) {
					case SMALL:
						curentSize = small;
						break;
					case NORMAL:
						curentSize = normal;
						break;
					case LARGE:
						curentSize = large;
						break;
					default: 
						curentSize = normal;
						break;
					}
					txtSimple.setTextSize(curentSize);	
				}
				
				public void onNothingSelected(AdapterView<?> parent) {
				}
			});
	        
	        
	        change_bg.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                // TODO Auto-generated method stub
	            	int i = 0;
	            	if (change_bg.isChecked()) 
	            	{
			            curentColorText = WHITE; 
			            setTextColorForAll(curentColorText);
			            
			            curentColorGround = BLACK;
			            bgSet.setBackgroundColor(BLACK);
			            bgSetSimpleText.setBackgroundColor(BLACK);
			         		            
			            i = spinnerFont.getSelectedItemPosition();
			            spinnerFont.setAdapter(adapter_white_fonts);
			            spinnerFont.setSelection(i);
			            
			            i = spinnerSize.getSelectedItemPosition();
			            spinnerSize.setAdapter(adapter_white_sizes);
			            spinnerSize.setSelection(i);
					}
	            	else 
	            	{
			            curentColorText = BLACK; 
			            setTextColorForAll(curentColorText);
			           
			            curentColorGround = WHITE;
			            bgSet.setBackgroundColor(WHITE);
			            bgSetSimpleText.setBackgroundColor(WHITE);
			         
			            i = spinnerFont.getSelectedItemPosition();
			            spinnerFont.setAdapter(adapter_fonts);
			            spinnerFont.setSelection(i);
			            
			            i = spinnerSize.getSelectedItemPosition();
			            spinnerSize.setAdapter(adapter_sizes);
			            spinnerSize.setSelection(i);
					}
	            
	            }
	        });
	        
	        ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SharedPreferences.Editor editor = prefs.edit();
					
					if (change_bg.isChecked()) 
					{
			            editor.putBoolean(ModeToggle, true);
					}
					else 
					{
			            editor.putBoolean(ModeToggle, false);
					}
					
		            editor.putInt(SizeText, curentSize);
		            editor.putInt(PositionSelectItemSpinnerSize, spinnerSize.getSelectedItemPosition());
		            editor.putInt(PositionSelectItemSpinnerFont, spinnerFont.getSelectedItemPosition());
		            editor.putInt(ColorText, curentColorText);
		            editor.putInt(ColorBackground, curentColorGround);	
		            editor.putString(FontText , spinnerFont.getSelectedItem().toString());
		            editor.commit();
		            Toast.makeText(getApplicationContext(), "הנתונים נשמרו", Toast.LENGTH_SHORT).show();
					finish();
				}
			});
	    
		    cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "יצאת בלי לשמור את ההגדרות", Toast.LENGTH_SHORT).show();
					finish();
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
	        		ButtonSettingActivity.this);
	        // Setting Dialog Title
	        alertDialog.setTitle("דיגלת שמירת נתונים");
	        // Setting Dialog Message
	        alertDialog.setMessage("אתה בטוח שאתה רוצה לחזור לתפריט הראשית בלי לשמור את ההגדרות שהזנת?");
	        // Setting Icon to Dialog
	        alertDialog.setIcon(R.drawable.warning_error);
	        // Setting Positive "Yes" Button
	        alertDialog.setPositiveButton("כן, אני בטוח.",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                        finish();
	                    }
	                });
	        // Setting Negative "NO" Button
	        alertDialog.setNegativeButton("לא, אני רוצה לשמור את ההגדרות שהזנתי.",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                        // Write your code here to invoke NO event
	                        dialog.cancel();
	                    }
	                });
	        // Showing Alert Message
	        alertDialog.show();
	    }
	          
        private void setTextColorForAll (int color){
   	    	 txtToggle.setTextColor(color);
   	    	 change_bg.setTextColor(color);
   	    	 txtSimple.setTextColor(color);
   	    	 txtSimple2_title.setTextColor(color);
   	    	 txtFont.setTextColor(color);
   	    	 txtSize.setTextColor(color);
   	    	 ok.setTextColor(color);
   	    	 cancel.setTextColor(color);
   	    }	 
        
		private void getPrefDetail(){
			curentSize = prefs.getInt(SizeText, normal);
			curentColorText = prefs.getInt(ColorText, BLACK);
			curentColorGround = prefs.getInt(ColorBackground, WHITE);
        	
        	change_bg.setChecked(prefs.getBoolean(ModeToggle, false));       	
        	if (change_bg.isChecked()) 
        	{
	            curentColorText = WHITE; 
	            setTextColorForAll(curentColorText);
	            
	            curentColorGround = BLACK;
	            bgSet.setBackgroundColor(BLACK);
	            bgSetSimpleText.setBackgroundColor(BLACK);
	         		            
	            spinnerFont.setAdapter(adapter_white_fonts);
	            spinnerSize.setAdapter(adapter_white_sizes);
			}
        	else 
        	{
	            curentColorText = BLACK; 
	            setTextColorForAll(curentColorText);
	           
	            curentColorGround = WHITE;
	            bgSet.setBackgroundColor(WHITE);
	            bgSetSimpleText.setBackgroundColor(WHITE);
	         
	            spinnerFont.setAdapter(adapter_fonts);
	            spinnerSize.setAdapter(adapter_sizes);
			}
			spinnerFont.setSelection(prefs.getInt(PositionSelectItemSpinnerFont, 0));
	        spinnerSize.setSelection(prefs.getInt(PositionSelectItemSpinnerSize, 0));
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
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
}
