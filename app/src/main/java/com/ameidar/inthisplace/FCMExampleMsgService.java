package com.ameidar.inthisplace;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMExampleMsgService extends FirebaseMessagingService {
    public static final String TAG = "FCMExampleMsgService" ;

    @Override
    public void  onMessageReceived(RemoteMessage theMessage)
    {
        Log.d(TAG , "Message received from " + theMessage.getFrom());
        if (theMessage.getData().size() > 0)
        {
            Log.d(TAG , "Message size: " + theMessage.getData().size());
        }

        for (String key: theMessage.getData().keySet())
        {
            Log.d( TAG , "key: " + key + "data : " + theMessage.getData().get( key ) );
        }


    }

    @Override
    public  void  onDeletedMessages()
    {


    }
}
