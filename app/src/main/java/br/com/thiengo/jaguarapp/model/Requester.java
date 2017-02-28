package br.com.thiengo.jaguarapp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.jaguarapp.presenter.Presenter;


public class Requester {
    private AsyncHttpClient asyncHttpClient;
    private Presenter presenter;

    public Requester(Presenter presenter ){
        asyncHttpClient = new AsyncHttpClient();
        this.presenter = presenter;
    }

    public void retrieveJaguars() {
        RequestParams requestParams = new RequestParams();
        requestParams.put(
            JsonHttpRequest.METODO_KEY,
            JsonHttpRequest.METODO_JAGUARS);

        asyncHttpClient.post(
            presenter.getContext(),
            JsonHttpRequest.URI,
            requestParams,
            new JsonHttpRequest( presenter ) );
    }
}
