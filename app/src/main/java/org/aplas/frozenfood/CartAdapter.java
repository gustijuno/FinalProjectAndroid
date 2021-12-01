package org.aplas.frozenfood;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.aplas.frozenfood.Database.OrderContract;

public class CartAdapter extends CursorAdapter {

    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //get views
        TextView foodName, sauces1, sauces2, price, quantity;

        foodName = view.findViewById(R.id.foodNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        sauces1 = view.findViewById(R.id.sauce1);
        sauces2 = view.findViewById(R.id.sauce2);
        quantity = view.findViewById(R.id.quantityinOrderSummary);


        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceoffood = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityoffood = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
        int sauce1 = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_SAUCE1);
        int sauce2 = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_SAUCE2);

        String nameoffood = cursor.getString(name);
        String pricesoffood = cursor.getString(priceoffood);
        String quantitysoffood = cursor.getString(quantityoffood);
        String yeshasSauce1 = cursor.getString(sauce1);
        String yeshasSauce2 = cursor.getString(sauce2);

        foodName.setText(nameoffood);
        price.setText(pricesoffood);
        sauces1.setText(yeshasSauce1);
        sauces2.setText(yeshasSauce2);
        quantity.setText(quantitysoffood);





    }
}
