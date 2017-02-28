package br.com.thiengo.jaguarapp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.jaguarapp.model.Requester;
import br.com.thiengo.jaguarapp.model.SPTimer;
import br.com.thiengo.jaguarapp.view.MainActivity;
import me.drakeet.materialdialog.MaterialDialog;


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

    public void showUpdateAppDialog( int actuallyAppVersion ){
        try{
            String packageName = getContext().getPackageName();
            PackageInfo pinfo = getContext().getPackageManager().getPackageInfo(packageName, 0);
            int versionNumber = pinfo.versionCode;

            if( actuallyAppVersion > versionNumber
                    && SPTimer.is24hrsDelayed(activity) ){

                activity.showUpdateAppDialog();
            }
        }
        catch(PackageManager.NameNotFoundException e){}
    }
}
