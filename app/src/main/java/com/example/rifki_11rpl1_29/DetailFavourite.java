package com.example.rifki_11rpl1_29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

public class DetailFavourite extends AppCompatActivity {
    Bundle extras;
    String team;
    String stadium;
    String description;
    String path;
    String id;

    TextView tvname;
    TextView tvstadium;
    ImageView ivposter;
    TextView tvdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite);
        setTitle("Detail Team");
        extras = getIntent().getExtras();
        tvname = (TextView)findViewById(R.id.tvteam);
        tvstadium = (TextView)findViewById(R.id.tvstadium);
        tvdesc = (TextView)findViewById(R.id.tvdescription);
        ivposter = (ImageView) findViewById(R.id.ivposter);

        if (extras != null) {
            id = extras.getString("id");
            team = extras.getString("team");
            stadium = extras.getString("stadium");
            description = extras.getString("description");
            path = extras.getString("badge");

            tvname.setText(team);
            tvstadium.setText(stadium);
            tvdesc.setText(description);
            Glide.with(DetailFavourite.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }
    }
}