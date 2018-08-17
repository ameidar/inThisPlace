package com.ameidar.inthisplace.utils;

import android.content.Context;
import android.os.Environment;

import com.ameidar.inthisplace.R;
import com.ameidar.inthisplace.model.DataItem;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JSONHelper {

    private static final String FILE_NAME = "Inthisplace.json";

    public static boolean exportToJson(Context context, List<DataItem> dataItemList) {
        DataItems menudata = new DataItems();
        menudata.setDataItems(dataItemList);
        Gson gson = new Gson();
        String strData = gson.toJson(menudata);

        FileOutputStream fileOutputStream = null;
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);

        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(strData.getBytes());
            return  true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static  List<DataItem> importFromJson(Context context) {
        FileReader reader = null;


        try {
            File file = new File( Environment.getExternalStorageDirectory(), FILE_NAME );
            reader = new FileReader( file );
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson( reader, DataItems.class );
            return dataItems.getDataItems();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
        }


    public static  List<DataItem> importFromResource(Context context) {
        InputStreamReader reader = null;
        InputStream inputStream = null;
        //File file = new File( Environment.getExternalStorageDirectory(), FILE_NAME );

        try {
            inputStream = context.getResources().openRawResource( R.raw.menuitem ) ;
            reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson( reader, DataItems.class );
            return dataItems.getDataItems();
        }
         finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static class DataItems {
        List<DataItem> dataItems;

        public List<DataItem> getDataItems() {
            return dataItems;
        }

        public void setDataItems(List<DataItem> dataItems) {
            this.dataItems = dataItems;
        }
    }
}
