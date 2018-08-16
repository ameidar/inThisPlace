package com.ameidar.inthisplace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.ameidar.inthisplace.model.DataItem;
import com.ameidar.inthisplace.sample.sampleDataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int SIGNIN_REQUEST = 1001;
    public static final String MY_GLOBAL_PREFS = "my_global_prefs";
    TextView tvOut;
    List<String> itemNames = new ArrayList<>(  );
    List<DataItem> dataItemList = sampleDataProvider.dataItemList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //tvOut = (TextView) findViewById(R.id.out);
        //tvOut.setText("");
        Collections.sort( dataItemList, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                return o1.getItemName().compareTo( o2.getItemName() );
            }
        } );
        /*for (DataItem item: dataItemList
             ) {
            //tvOut.append( item.getItemName() + "\n" );
            itemNames.add( item.getItemName() );
            Collections.sort( itemNames );

        }*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , itemNames  );

        DataItemAdapter adapter = new DataItemAdapter( this , dataItemList );
        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences( this );
        boolean grid = setting.getBoolean( getString( R.string.pref_display_grid ) , false);


        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.rvItems);
        if (grid)
        {
            recyclerView.setLayoutManager( new GridLayoutManager( this , 3 ) );

        }

        recyclerView.setAdapter( adapter );



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

        }
        return super.onOptionsItemSelected( item );
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
}
