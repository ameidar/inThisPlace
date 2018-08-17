package com.ameidar.inthisplace.utils;

import android.content.Context;
import android.os.Environment;

import com.ameidar.inthisplace.model.DataItem;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
