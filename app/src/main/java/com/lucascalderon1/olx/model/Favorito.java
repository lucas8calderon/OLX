package com.lucascalderon1.olx.model;


import com.google.firebase.database.DatabaseReference;
import com.lucascalderon1.olx.helper.FirebaseHelper;

import java.util.List;

public class Favorito {

    private List<String> favoritos;

    public void salvar(){
        DatabaseReference favoritosRef = FirebaseHelper.getDatabaseReference()
                .child("favoritos")
                .child(FirebaseHelper.getIdFirebase());
        favoritosRef.setValue(getFavoritos());
    }

    public List<String> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<String> favoritos) {
        this.favoritos = favoritos;
    }
}
