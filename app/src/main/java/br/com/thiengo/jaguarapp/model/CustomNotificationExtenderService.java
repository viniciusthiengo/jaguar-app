package br.com.thiengo.jaguarapp.model;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

import org.json.JSONException;

import br.com.thiengo.jaguarapp.presenter.Presenter;
import br.com.thiengo.jaguarapp.presenter.PresenterLB;


public class CustomNotificationExtenderService extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        try {
            Log.i("log", "Version: " + notification.payload.additionalData.getInt("version") );
            SPTimer.saveVersion(this, notification.payload.additionalData.getInt("version") );

            Intent intent = new Intent( PresenterLB.FILTER_KEY );
            LocalBroadcastManager.getInstance( this ).sendBroadcast( intent );
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }
}
