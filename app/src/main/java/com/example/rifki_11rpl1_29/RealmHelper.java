package com.example.rifki_11rpl1_29;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper extends AppCompatActivity {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final ModelFavourite movieModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(ModelFavourite.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setId(nextId);
                    ModelFavourite model = realm.copyToRealm(movieModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<ModelFavourite> getAllMovie() {
        RealmResults<ModelFavourite> results = realm.where(ModelFavourite.class).findAll();
        return results;
    }

    public void delete(Integer id) {
        final RealmResults<ModelFavourite> model = realm.where(ModelFavourite.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });

    }
}