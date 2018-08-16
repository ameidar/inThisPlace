package com.ameidar.inthisplace;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ameidar.inthisplace.model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataItemAdapterListView extends ArrayAdapter<DataItem> {
    List<DataItem> mDataItems;
    LayoutInflater mInflater ;

    public DataItemAdapterListView(@NonNull Context context, @NonNull List<DataItem> objects) {
        super( context,R.layout.list_items ,  objects );
        mDataItems = objects ;
        mInflater = LayoutInflater.from( context ) ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate( R.layout.list_items , parent , false );
        }
        TextView tvName = (TextView)convertView.findViewById( (R.id.itemNameText) );
        ImageView imageView = (ImageView) convertView.findViewById( R.id.imageView );
        DataItem item = mDataItems.get( position );
        tvName.setText( item.getItemName() );
        //imageView.setImageResource( R.drawable.a1 );
        InputStream inputStream  = null ;
        try {
            String imageFile = item.getImage();
            inputStream = getContext().getAssets().open( imageFile );
            Drawable d = Drawable.createFromStream( inputStream,null );
            imageView.setImageDrawable( d );
        } catch (IOException e) {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return convertView ;
    }
}
