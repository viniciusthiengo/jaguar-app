package br.com.thiengo.jaguarapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.thiengo.jaguarapp.R;
import br.com.thiengo.jaguarapp.presenter.Jaguar;


public class JaguarAdapter extends RecyclerView.Adapter<JaguarAdapter.ViewHolder> {
    private MainActivity activity;
    private ArrayList<Jaguar> jaguars;


    public JaguarAdapter( MainActivity activity, ArrayList<Jaguar> jaguars){
        this.activity = activity;
        this.jaguars = jaguars;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from( parent.getContext() )
                .inflate(R.layout.item_jaguar, parent, false);
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setDados( jaguars.get( position ) );
    }

    @Override
    public int getItemCount() {
        return jaguars.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivJaguar;
        private TextView tvModelo;
        private TextView tvMotor;
        private TextView tvPreco;

        private ViewHolder(View itemView) {
            super(itemView);

            ivJaguar = (ImageView) itemView.findViewById(R.id.iv_jaguar);
            tvModelo = (TextView) itemView.findViewById(R.id.tv_modelo);
            tvMotor = (TextView) itemView.findViewById(R.id.tv_motor);
            tvPreco = (TextView) itemView.findViewById(R.id.tv_preco);
        }

        private void setDados( Jaguar jaguar ){
            Picasso.with( activity )
                    .load( jaguar.getUrlImagem() )
                    .into( ivJaguar );

            tvModelo.setText( jaguar.getModelo() );
            tvMotor.setText( jaguar.getMotor() );
            tvPreco.setText( jaguar.getPreco() );
        }
    }
}
