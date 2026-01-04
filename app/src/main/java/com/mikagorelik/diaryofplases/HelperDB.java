package com.mikagorelik.diaryofplases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperDB extends SQLiteOpenHelper {

    public static final String DB_NAME="info.db";

    public static final String TABLE_MY_CONTACT="myContacts";
    public static final String MY_CONTACT_ID="myContact_ID";
    public static final String MY_CONTACT_NAME="myContact_name";
    public static final String MY_CONTACT_COMMENT="myContact_comment";
    public static final String MY_CONTACT_PHONE="myContact_phone";
    public static final String MY_CONTACT_EMAIL="myContact_email";

    public static final String TABLE_PLACE="Places";
    public static final String PLACE_NAME="Place_name";
    public static final String PLACE_COMMENT="Place_Comment";
    public static final String PLACE_IMAGE="Place_image";
    public static final String PLACE_LINK="Place_link";
    public static final String PLACE_GPS="Place_GPS";


    public HelperDB(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String st1="CREATE TABLE IF NOT EXISTS "+TABLE_MY_CONTACT+" ( ";
        st1+=MY_CONTACT_ID+" TEXT, ";
        st1+=MY_CONTACT_NAME+" TEXT, ";
        st1+=MY_CONTACT_COMMENT+" TEXT, ";
        st1+=MY_CONTACT_PHONE+" TEXT, ";
        st1+=MY_CONTACT_EMAIL+" TEXT);";
        db.execSQL(st1);

        String st2="CREATE TABLE IF NOT EXISTS "+TABLE_PLACE+" ( ";
        st2+=PLACE_NAME+" TEXT, ";
        st2+=PLACE_COMMENT+" TEXT, ";
        st2+=PLACE_IMAGE+" TEXT, ";
        st2+=PLACE_LINK+" TEXT, ";
        st2+=PLACE_GPS+" TEXT);";
        db.execSQL(st2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
