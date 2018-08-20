package com.ameidar.inthisplace;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.ameidar.inthisplace.database.DBHelper;
import com.ameidar.inthisplace.database.DataSource;
import com.ameidar.inthisplace.model.DataItem;
import com.ameidar.inthisplace.sample.sampleDataProvider;
import com.ameidar.inthisplace.utils.JSONHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int SIGNIN_REQUEST = 1001;
    public static final String MY_GLOBAL_PREFS = "my_global_prefs";
    private static final  String WEB_URL = "https://www.etsy.com/uk/shop/INTHISPLACEphoto?ref=l2-shop-header-avatar&section_id=20719880";
    public static final String email = "info@inthisplace.com";
    public static final  int REQUEST_PERMISSION_WRITE = 1002;
    private static final String TAG = "MainActivity";
    private static final String FILE_NAME = "test.json";
    private boolean permissionGranted;
    private GestureDetector gestureDetector;
    TextView tvOut;
    List<String> itemNames = new ArrayList<>(  );
    List<DataItem> dataItemList = sampleDataProvider.dataItemList ;
    float dX;
    float dY;
    int lastAction;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    String[] mCategories;


    List<DataItem>listfromDB;
    DataSource mDataSource ;
    RecyclerView recyclerView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mDrawerLayout = findViewById( R.id.drawer_layout);
        mDrawerList = findViewById( R.id.left_drawer );
        mCategories = getResources().getStringArray( R.array.categories );

        mDrawerList.setAdapter( new ArrayAdapter<>(this,R.layout.drawer_list_item ,mCategories ) );
        mDrawerList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = mCategories[position];
                displayDataItems(category);
            }
        } );

        mDataSource = new DataSource( this );
        mDataSource.open();
        mDataSource.seedDatabase( dataItemList);

        Toast.makeText( this, "Database acquired!", Toast.LENGTH_SHORT ).show();
        gestureDetector = new GestureDetector(this, new SingleTapConfirm());
        checkPermissions();

//        Collections.sort( dataItemList, new Comparator<DataItem>() {
//            @Override
//            public int compare(DataItem o1, DataItem o2) {
//                return o1.getItemName().compareTo( o2.getItemName() );
//            }
//        } );


        //listfromDB = mDataSource.getAllItems();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , itemNames  );

        //DataItemAdapter adapter = new DataItemAdapter( this , dataItemList );

        // DataItemAdapter adapter = new DataItemAdapter( this , listfromDB );

        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences( this );
        boolean grid = setting.getBoolean( getString( R.string.pref_display_grid ) , false);



        recyclerView = (RecyclerView) findViewById( R.id.rvItems);
        if (grid)
        {
            recyclerView.setLayoutManager( new GridLayoutManager( this , 3 ) );

        }

        displayDataItems(null);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (gestureDetector.onTouchEvent( event )) {
                    String[] address = {email};
                    Intent intent = new Intent( Intent.ACTION_SENDTO );
                    intent.setData( Uri.parse( "mailto:" ) );
                    intent.putExtra( Intent.EXTRA_EMAIL, address );
                    intent.putExtra( Intent.EXTRA_SUBJECT, "info request" );
                    intent.putExtra( Intent.EXTRA_TEXT, "Please send some info request" );
                    if (intent.resolveActivity( getPackageManager() ) != null) {
                        startActivity( intent );
                    }
                } else {

                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            dX = view.getX() - event.getRawX();
                            dY = view.getY() - event.getRawY();
                            lastAction = MotionEvent.ACTION_DOWN;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            view.setY( event.getRawY() + dY );
                            view.setX( event.getRawX() + dX );
                            lastAction = MotionEvent.ACTION_MOVE;
                            break;
                        case MotionEvent.ACTION_UP:
                            if (lastAction == MotionEvent.ACTION_DOWN)
                                Toast.makeText( MainActivity.this, "Clicked", Toast.LENGTH_SHORT ).show();

                            break;
                        default:
                            return false;
                    }

                }
                return true;
            }

            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main_menu , menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_signin:
                Intent intent = new Intent( this , SigninActivity.class );
                startActivityForResult( intent , SIGNIN_REQUEST );
                return true;
            case R.id.action_settings:
                Intent settingIntent = new Intent( this , PrefsActivity.class );
                startActivity( settingIntent );
                return true ;
            case R.id.action_import:
                //List<DataItem> dataItems = JSONHelper.importFromResource( this );
                List<DataItem> dataItems = JSONHelper.importFromJson( this );
                if (dataItems != null)
                {

                    for (DataItem dataItem:
                            dataItems) {
                        Log.i(TAG , "onOptionItemSelected:" + dataItem.getItemName() );
                    }
                }

                return true ;
            case R.id.action_export:

                boolean result = JSONHelper.exportToJson(this , dataItemList);
                if (result)
                {
                    Toast.makeText(this, "Data exported to file", Toast.LENGTH_SHORT).show();
                    
                }
                else
                {
                    Toast.makeText(this, "export failed", Toast.LENGTH_SHORT).show();
                }
                return  true;
            case R.id.onWeb:
                Intent webIntent = new Intent(Intent.ACTION_VIEW , Uri.parse(WEB_URL)  );
                if (webIntent.resolveActivity( getPackageManager() ) != null)
                {
                    startActivity( webIntent );

                }
            case R.id.category:
                //open the drawer
                mDrawerLayout.openDrawer(mDrawerList);

                return true;

            case R.id.display_all:
                displayDataItems(null);
                return true;


        }
        return super.onOptionsItemSelected( item );
    }

    private void displayDataItems(String category)
    {
        mDrawerLayout.closeDrawer(mDrawerList );
        listfromDB = mDataSource.getAllItems(category);
        DataItemAdapter mItemAdapter = new DataItemAdapter(this, listfromDB);
        recyclerView.setAdapter(mItemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode == RESULT_OK && requestCode == SIGNIN_REQUEST)
        {
            String email = data.getStringExtra( SigninActivity.EMAIL_KEY );
            Toast.makeText( this, "You signed in as " +  email , Toast.LENGTH_SHORT ).show();
            SharedPreferences.Editor editor =
                    getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();
            editor.putString(SigninActivity.EMAIL_KEY, email);
            editor.apply();

        }
        else
            Toast.makeText( this, "request code error", Toast.LENGTH_SHORT ).show();
    }



    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    // Initiate request for permissions.
    private boolean checkPermissions() {

        if (!isExternalStorageReadable() || !isExternalStorageWritable()) {
            Toast.makeText(this, "This app only works on devices with usable external storage",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_WRITE);
            return false;
        } else {
            return true;
        }
    }

    // Handle permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_WRITE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
                    Toast.makeText(this, "External storage permission granted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You must grant permission!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }
}
