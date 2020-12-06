package com.example.rifki_11rpl1_29;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AdapterFavourite extends RecyclerView.Adapter<AdapterFavourite.DatakuViewHolder> {
    private List<ModelFavourite> dataList;
    private Callback callback;
    View viewku;
    int posku;

    Realm realm;
    RealmHelper realmHelper;

    interface Callback {
        void onClick(int position);
        void test();
    }


    public AdapterFavourite(List<ModelFavourite> dataList, Callback callback) {
        this.callback = callback;
        this.dataList = dataList;
        Log.d("makanan", "MahasiswaAdapter: "+dataList.size()+"");
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
    }

    @Override
    public DatakuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapterrv, parent, false);
        return new DatakuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DatakuViewHolder holder, final int position) {
        holder.txtNama.setText(dataList.get(position).getTeam_name());
        holder.txtNpm.setText(dataList.get(position).getAlternate_name());
        Log.d("makananku", "onBindViewHolder: "+dataList.get(position).getBadge_path());
        //pakai glide karena untuk nampilkan data gambar dari URL / permission / graddle
        Glide.with(holder.itemView)
                .load(dataList.get(position).getBadge_path())
                //.override(Target.SIZE_ORIGINAL)
                .apply(new RequestOptions().override(600, 200))
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivprofile);

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DatakuViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView txtNama, txtNpm;
        CardView card;
        ImageView ivprofile;

        public DatakuViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardku);
            ivprofile = (ImageView) itemView.findViewById(R.id.ivprofile);
            txtNama = (TextView) itemView.findViewById(R.id.tvname);
            txtNpm = (TextView) itemView.findViewById(R.id.tvdesc);
            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
            posku=getAdapterPosition();
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

    }
    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 1:
                    //Do stuff
                    Toast.makeText(viewku.getContext(), ""+posku, Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    //Do stuff
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    realmHelper.delete(dataList.get(posku).getId());
                                    notifyDataSetChanged();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(viewku.getContext());
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                    break;
            }
            return true;
        }
    };
}