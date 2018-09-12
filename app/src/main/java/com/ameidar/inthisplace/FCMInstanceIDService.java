package com.ameidar.inthisplace;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMInstanceIDService extends FirebaseInstanceIdService{
    public static final String TAG= "FCMInstanceIDService" ;

    @Override
    public void onTokenRefresh()
    {
        String newToken = FirebaseInstanceId.getInstance().getToken(  );
        Log.d(TAG , "Refreshed Token :" + newToken);
    }


}
