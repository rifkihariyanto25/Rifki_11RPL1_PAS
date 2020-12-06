package com.example.rifki_11rpl1_29;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailTeam extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelFavourite movieModel;


    Bundle extras;
    String team;
    String stadium;
    String description;
    String path;
    String id;
    String alternate;
    String league;
    String location;

    TextView tvname;
    TextView tvstadium;
    ImageView ivposter;
    TextView tvdesc;
    Button btnbookmark;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);
        getSupportActionBar().hide();

        extras = getIntent().getExtras();
        tvname = (TextView)findViewById(R.id.tvteam);
        tvstadium = (TextView)findViewById(R.id.tvstadium);
        ivposter = (ImageView) findViewById(R.id.ivposter);
        tvdesc = (TextView)findViewById(R.id.tvdescription);
        btnbookmark = (Button) findViewById(R.id.btnbookmark);

        if (extras != null) {
            id = extras.getString("id");
            team = extras.getString("team");
            stadium = extras.getString("stadium");
            path = extras.getString("badge");
            description = extras.getString("description");
            alternate = extras.getString("alternate");
            league = extras.getString("league");
            location = extras.getString("location");

            tvname.setText(team);
            tvdesc.setText(description);
            tvstadium.setText(stadium);
            tvdesc.setText(description);
            Glide.with(DetailTeam.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(DetailTeam.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(configuration);

        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new ModelFavourite();
                movieModel.setTeam_name(team);
                movieModel.setAlternate_name(alternate);
                movieModel.setBadge_path(path);
                movieModel.setStadium(stadium);
                movieModel.setLeague(league);
                movieModel.setStadium_location(location);
                movieModel.setDescription(description);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);
            }
        });
    }
}