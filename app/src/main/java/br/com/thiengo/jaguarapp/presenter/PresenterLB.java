package br.com.thiengo.jaguarapp.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by viniciusthiengo on 27/02/17.
 */

public class PresenterLB extends BroadcastReceiver {
    public static final String FILTER_KEY = "PresenterLB";
    private Presenter presenter;

    public PresenterLB(Presenter p ){
        presenter = p;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        presenter.showUpdateAppDialog();
    }
}
