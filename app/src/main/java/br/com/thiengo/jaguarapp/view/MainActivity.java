package br.com.thiengo.jaguarapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.thiengo.jaguarapp.R;
import br.com.thiengo.jaguarapp.presenter.Jaguar;
import br.com.thiengo.jaguarapp.presenter.Presenter;


public class MainActivity extends AppCompatActivity {

    private Presenter presenter;
    private JaguarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = Presenter.getInstance();
        presenter.setActivity( this );
        initRecycler();

        presenter.retrieveJaguars( savedInstanceState );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Jaguar.JAGUARS_KEY, presenter.getJaguars());
        super.onSaveInstanceState(outState);
    }

    private void initRecycler(){
        RecyclerView rvMotos = (RecyclerView) findViewById(R.id.rv_jaguars);
        rvMotos.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMotos.setLayoutManager( layoutManager );

        adapter = new JaguarAdapter( this, presenter.getJaguars() );
        rvMotos.setAdapter( adapter );
    }

    public void updateListaRecycler(){
        adapter.notifyDataSetChanged();
    }

    public void showProgressBar( int visibilidade ){
        findViewById(R.id.pb_loading).setVisibility( visibilidade );
    }
}
