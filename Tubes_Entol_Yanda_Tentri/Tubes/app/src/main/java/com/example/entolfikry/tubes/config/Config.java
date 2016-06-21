package com.example.entolfikry.tubes.config;

/**
 * Created by Tentri-Ali on 6/7/2016.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD= "http://10.0.2.2/Android/CRUD/addAng.php";
    public static final String URL_GET_ALL = "http://10.0.2.2/Android/CRUD/getAllAng.php";
    public static final String URL_GET_ANG = "http://10.0.2.2/Android/CRUD/getAng.php?id=";
    public static final String URL_UPDATE_ANG = "http://10.0.2.2/Android/CRUD/updateAng.php";
    public static final String URL_DELETE_ANG = "http://10.0.2.2/Android/CRUD/deleteAng.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_ANG_ID = "id";
    public static final String KEY_ANG_NAMA = "nama";
    public static final String KEY_ANG_USERNAME = "username";
    public static final String KEY_ANG_PASSWORD = "password";
    public static final String KEY_ANG_POSISI = "posisi";
    public static final String KEY_ANG_PENYAKIT = "penyakit";
    public static final String KEY_ANG_TINGGI = "tinggi";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_POSISI = "posisi";
    public static final String TAG_PENYAKIT = "penyakit";
    public static final String TAG_TINGGI = "tinggi";

    //employee id to pass with intent
    public static final String ANG_ID = "angid";
}

