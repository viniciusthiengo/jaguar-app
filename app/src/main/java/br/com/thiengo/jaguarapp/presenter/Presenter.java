package br.com.thiengo.jaguarapp.presenter;

import android.app.Activity;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.jaguarapp.model.Requester;
import br.com.thiengo.jaguarapp.model.SPLocalBase;
import br.com.thiengo.jaguarapp.view.MainActivity;


public class Presenter {
    private static Presenter instance;
    private Requester model;
    private MainActivity activity;
    private ArrayList<Jaguar> jaguars = new ArrayList<>();

    private Presenter(){
        model = new Requester( this );
    }

    public static Presenter getInstance(){
        if( instance == null ){
            instance = new Presenter();
        }
        return instance;
    }

    public void setActivity(MainActivity activity){
        this.activity = activity;
        initLocalBroadcast();
    }

    private void initLocalBroadcast(){
        PresenterLB broadcast = new PresenterLB(this);
        IntentFilter intentFilter = new IntentFilter( PresenterLB.FILTER_KEY );
        LocalBroadcastManager
                .getInstance(activity)
                .registerReceiver( broadcast, intentFilter );
    }

    public Activity getContext() {
        return activity;
    }

    public void retrieveJaguars(Bundle savedInstanceState) {
        if( savedInstanceState != null ){
            jaguars = savedInstanceState.getParcelableArrayList( Jaguar.JAGUARS_KEY );
            return;
        }
        model.retrieveJaguars();
    }

    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        activity.showProgressBar( visibilidade );
    }

    public void updateListaRecycler(Object object) {
        List<Jaguar> postsCarregados = (List<Jaguar>) object;
        jaguars.clear();
        jaguars.addAll( postsCarregados );
        activity.updateListaRecycler();
        showProgressBar( !(jaguars.size() > 0) );
    }

    public ArrayList<Jaguar> getJaguars() {
        return jaguars;
    }

    private PackageInfo packageInfo(){
        PackageInfo pinfo = null;
        try {
            String packageName = getContext().getPackageName();
            pinfo = getContext().getPackageManager().getPackageInfo(packageName, 0);
        }
        catch(PackageManager.NameNotFoundException e){}

        return pinfo;
    }

    public void showUpdateAppDialog( int actuallyAppVersion ){
        int versionNumber = packageInfo().versionCode;

        if( actuallyAppVersion > versionNumber
                && SPLocalBase.is24hrsDelayed(activity) ){

            activity.showUpdateAppDialog();
        }
    }

    public void showUpdateAppDialog( String actuallyAppVersion ){
        String versionName = packageInfo().versionName;

        if( !actuallyAppVersion.equals(versionName)
                && SPLocalBase.is24hrsDelayed(activity) ){

            activity.showUpdateAppDialog();
        }
    }

    public void showUpdateAppDialog(){
        int versionNumber = packageInfo().versionCode;
        int versionToCompare = SPLocalBase.getVersion(getContext());

        if( versionToCompare > versionNumber ){
                //&& SPTimer.is24hrsDelayed(activity) ){

            activity.showUpdateAppDialog();
        }
    }
}



