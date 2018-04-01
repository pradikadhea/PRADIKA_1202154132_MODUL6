package com.example.user.pradika_1202154132_modul6;

import com.google.firebase.database.IgnoreExtraProperties;

//Enkapsulasi data comment
@IgnoreExtraProperties
public class DBComment {
    String yangkomen, komennya, fotoyangdikomen;

    //Dibutuhkan kosong untuk membaca data
    public DBComment(){
    }

    //Constructor dari class ini
    public DBComment(String yangkomen, String komennya, String fotoyangdikomen) {
        this.yangkomen = yangkomen;
        this.komennya = komennya;
        this.fotoyangdikomen = fotoyangdikomen;
    }

    //Sisany getter untuk variabel dari class ini
    public String getFotoyangdikomen() {
        return fotoyangdikomen;
    }

    public String getYangkomen() {
        return yangkomen;
    }

    public String getKomennya() {
        return komennya;
    }

}
