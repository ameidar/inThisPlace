package com.ameidar.inthisplace.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.ameidar.inthisplace.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private Context mContext ;
    private SQLiteDatabase mDatabase ;
    private SQLiteOpenHelper mDbHelper ;

    public DataSource(Context mContext) {
        this.mContext = mContext;
        mDbHelper = new DBHelper( mContext );
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public  void open()
    {
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public  void close()
    {
        mDbHelper.close();

    }

    public DataItem createItem(DataItem item)
    {
        ContentValues values = item.getValues();
        mDatabase.insert( ItemsTable.TABLE_ITEMS , null , values );
        return item ;

    }

    public long getDataItemCount()
    {
        return DatabaseUtils.queryNumEntries( mDatabase,ItemsTable.TABLE_ITEMS );
    }


    public void seedDatabase(List<DataItem> dataItemList) {
        long numItems = getDataItemCount();
        if (numItems == 0) {
            for (DataItem item : dataItemList) {
                try {
                    createItem( item );
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public List<DataItem> getAllItems(String category)
    {
        List<DataItem> dataItems = new ArrayList<>(  );
        Cursor cursor;
        if (category == null) {
            cursor = mDatabase.query( ItemsTable.TABLE_ITEMS, ItemsTable.ALL_COLUMNS,
                    null, null, null, null, ItemsTable.COLUMN_NAME );
        }
        else {
            String[] categories = {category};
            cursor = mDatabase.query( ItemsTable.TABLE_ITEMS, ItemsTable.ALL_COLUMNS,
                    ItemsTable.COLUMN_CATEGORY + "=?", categories, null, null, ItemsTable.COLUMN_NAME );
        }

        while (cursor.moveToNext()) {

            DataItem item = new DataItem();
            item.setItemId(cursor.getString( cursor.getColumnIndex( ItemsTable.COLUMN_ID ) ));
            item.setItemName(cursor.getString( cursor.getColumnIndex( ItemsTable.COLUMN_NAME ) ));
            item.setDescription(cursor.getString( cursor.getColumnIndex( ItemsTable.COLUMN_DESCRIPTION ) ));
            item.setCategory(cursor.getString( cursor.getColumnIndex( ItemsTable.COLUMN_CATEGORY ) ));
            item.setSortPosition(cursor.getInt( cursor.getColumnIndex( ItemsTable.COLUMN_POSITION ) ));
            item.setPrice(cursor.getDouble( cursor.getColumnIndex( ItemsTable.COLUMN_PRICE ) ));
            item.setImage(cursor.getString( cursor.getColumnIndex( ItemsTable.COLUMN_IMAGE ) ));
            dataItems.add( item );
        }
        cursor.close();
        return  dataItems ;
    }
}
