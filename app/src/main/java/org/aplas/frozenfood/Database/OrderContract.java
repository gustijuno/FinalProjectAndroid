package org.aplas.frozenfood.Database;

import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract {

    public  OrderContract(){

    }
    //Database memerlukan nama package
    public static final String CONTENT_AUTORITY = "org.aplas.frozenfood";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTORITY);
    //table name
    public static final String PATH = "ordering";

    public static abstract class OrderEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);
        public static final String TABLE_NAME = "ordering";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE= "price";
        public static final String COLUMN_SAUCE1= "sauce1";
        public static final String COLUMN_SAUCE2= "sauce2";
    }
}
