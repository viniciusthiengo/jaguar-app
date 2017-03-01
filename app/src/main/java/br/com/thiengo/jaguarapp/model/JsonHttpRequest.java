package br.com.thiengo.jaguarapp.model;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.thiengo.jaguarapp.presenter.Jaguar;
import br.com.thiengo.jaguarapp.presenter.Presenter;
import cz.msebera.android.httpclient.Header;


public class JsonHttpRequest extends JsonHttpResponseHandler {
    public static final String URI = "http://192.168.25.221:8888/jaguar-app/ctrl/CtrlAdmin.php";
    public static final String METODO_KEY = "metodo";
    public static final String METODO_JAGUARS = "get-jaguars";
    private Presenter presenter;

    public JsonHttpRequest( Presenter presenter ){
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        presenter.showProgressBar( true );
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try{
            presenter.showUpdateAppDialog( response.getInt("version") );
            onSuccess(statusCode, headers, response.getJSONArray("jaguars"));
        }
        catch(JSONException e){}
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Gson gson = new Gson();
        ArrayList<Jaguar> jaguars = new ArrayList<>();
        Jaguar j;

        for( int i = 0; i < response.length(); i++ ){
            try{
                j = gson.fromJson( response.getJSONObject( i ).toString(), Jaguar.class );
                jaguars.add( j );
            }
            catch(JSONException e){}
        }

        presenter.updateListaRecycler( jaguars );
        //new VersionRequester(presenter).execute();
    }

    @Override
    public void onFinish() {
        presenter.showProgressBar( false );
    }
}
