package br.com.thiengo.jaguarapp.model;

import android.os.AsyncTask;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.ref.WeakReference;

import br.com.thiengo.jaguarapp.presenter.Presenter;


public class VersionRequester extends AsyncTask<Void, Void, String> {
    private WeakReference<Presenter> presenter;

    public VersionRequester( Presenter p ){
        presenter = new WeakReference<>( p );
    }

    @Override
    protected String doInBackground(Void... voids) {
        String version = null;
        try{
            version = Jsoup
                .connect("https://play.google.com/store/apps/details?id=br.thiengocalopsita")
                .get()
                .select("div[itemprop=\"softwareVersion\"]")
                .text()
                .trim();
        }
        catch (IOException e){}

        return version;
    }

    @Override
    protected void onPostExecute(String version) {
        super.onPostExecute(version);

        if( presenter.get() != null ){
            presenter.get().showUpdateAppDialog( version );
        }
    }
}
