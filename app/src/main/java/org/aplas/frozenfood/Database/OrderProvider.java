package org.aplas.frozenfood.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderProvider extends ContentProvider {

    public static final int ORDER = 100;

    public static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(OrderContract.CONTENT_AUTORITY,OrderContract.PATH,ORDER);
    }

    public OrderHelper mHelper;
    @Override
    public boolean onCreate() {
        mHelper = new OrderHelper(getContext());
        return true;
    }




    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //query dataabase
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case ORDER:
                cursor = database.query(OrderContract.OrderEntry.TABLE_NAME, projection, selection, selectionArgs, null,null,sortOrder);
                break;

            default:
                throw new IllegalArgumentException("CANT QUERY");
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }


    public String getType(Uri uri) {
        return null;
    }


    public Uri insert(Uri uri, ContentValues values) {

        int match = sUriMatcher.match(uri);
        switch (match){
            case ORDER:
                return insertCart(uri, values);

            default:
                throw new IllegalArgumentException("Cant insert data");
        }
    }

    private Uri insertCart(Uri uri, ContentValues values) {
        String name = values.getAsString(OrderContract.OrderEntry.COLUMN_NAME);
        if(name == null){
            throw new IllegalArgumentException("Name is Required");
        }

        String quantity = values.getAsString(OrderContract.OrderEntry.COLUMN_QUANTITY);
        if(quantity == null){
            throw new IllegalArgumentException("Quantity is Required");
        }

        String price = values.getAsString(OrderContract.OrderEntry.COLUMN_PRICE);
        if(price == null){
            throw new IllegalArgumentException("Price is Required");
        }

        //writing database
        SQLiteDatabase database = mHelper.getWritableDatabase();
        long id = database.insert(OrderContract.OrderEntry.TABLE_NAME, null, values);

        if (id == -1){
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int rowDeleted;
        SQLiteDatabase database = mHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match){
            case ORDER:
                rowDeleted = database.delete(OrderContract.OrderEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Cannot delete");
        }
        if (rowDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowDeleted;

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}