package br.com.thiengo.jaguarapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.thiengo.jaguarapp.R;
import br.com.thiengo.jaguarapp.presenter.Jaguar;
import br.com.thiengo.jaguarapp.presenter.Presenter;
import me.drakeet.materialdialog.MaterialDialog;


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


    private MaterialDialog mMaterialDialog;
    public void showUpdateAppDialog(){
        mMaterialDialog = new MaterialDialog(this)
            .setTitle("Nova versão App")
            .setMessage("Está disponível a nova versão do aplicativo Jaguar App, clique no botão abaixo para realizar a atualização. Essa nova versão é mais leve e segura.")
            .setCanceledOnTouchOutside(false)
            .setPositiveButton("Atualizar", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String packageName = getPackageName();
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=br.thiengocalopsita")));
                        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
                    }
                    catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=br.thiengocalopsita")));
                        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
                    }
                }
            })
            .setNegativeButton("Depois", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog.dismiss();
                }
            });

        mMaterialDialog.show();
    }
}
