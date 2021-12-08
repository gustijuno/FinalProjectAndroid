package org.aplas.frozenfood.makanan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import org.aplas.frozenfood.Database.OrderContract;
import org.aplas.frozenfood.R;
import org.aplas.frozenfood.SummaryActivity;

public class UdangActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    //get views dari layout

    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, foodName, productPrice, description;
    CheckBox addSauce1, addSauce2;
    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        foodName = findViewById(R.id.FoodNameinInfo);
        productPrice = findViewById(R.id.productPrice);
        addSauce1 = findViewById(R.id.addSauce1);
        addSauce2 = findViewById(R.id.addSauce2);
        addtoCart = findViewById(R.id.addtocart);
        description = findViewById(R.id.descriptioninfo);

        //setting nama produk
        foodName.setText("Paket Udang");
        imageView.setImageResource(R.drawable.udang);
        description.setText("Udang fresh");


        //klik order
        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UdangActivity.this, SummaryActivity.class);
                startActivity(intent);
                //ketika di klik akan mengirim value ke database dan langsung tampil ke summary
                SaveCart();
            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //harga produk
                int basePrice = 1;
                quantity++;
                displayQuantity();
                int productsPrice = basePrice * quantity;
                String setnewPrice = String.valueOf(productsPrice);
                productPrice.setText(setnewPrice);


                //checkbox function
                int ifCheckBox = CalculatePrice(addSauce1,addSauce2);
                productPrice.setText("Rp. "+ifCheckBox);

            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice=10000;
                if(quantity == 0){
                    Toast.makeText(UdangActivity.this, "Tidak bisa kurang dari 0", Toast.LENGTH_SHORT).show();
                }else{
                    quantity--;
                    displayQuantity();
                    int productsPrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(productsPrice);
                    productPrice.setText(setnewPrice);

                    //checkbox function
                    int ifCheckBox = CalculatePrice(addSauce1,addSauce2);
                    productPrice.setText("Rp. "+ifCheckBox);
                }
            }
        });
    }

    private boolean SaveCart() {
        //mendapatkan value dari views
        String name = foodName.getText().toString();
        String price = productPrice.getText().toString();
        String quantity = quantitynumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);

        if (addSauce1.isChecked()){
            values.put(OrderContract.OrderEntry.COLUMN_SAUCE1, "Tambah Saus1: YES");
        }else {
            values.put(OrderContract.OrderEntry.COLUMN_SAUCE1, "Tambah Saus1: NO");
        }

        if (addSauce2.isChecked()){
            values.put(OrderContract.OrderEntry.COLUMN_SAUCE2, "Tambah saus2: YES");
        }else {
            values.put(OrderContract.OrderEntry.COLUMN_SAUCE2, "Tambah saus2: NO");
        }

        if (mCurrentCartUri == null){
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri == null){
                Toast.makeText(this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Success adding to cart", Toast.LENGTH_SHORT).show();
            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;
    }

    private int CalculatePrice(CheckBox addSauce1, CheckBox addSauce2) {
        int basePrice = 30000;
        if(addSauce1.isChecked()){
            //tambah harga saos 1
            basePrice = basePrice + 1000;
        }

        if(addSauce2.isChecked()){
            //tambah harga saos 2
            basePrice = basePrice + 2000;
        }

        return basePrice * quantity;
    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,
                OrderContract.OrderEntry.COLUMN_SAUCE1,
                OrderContract.OrderEntry.COLUMN_SAUCE2
        };
        return new CursorLoader(this, mCurrentCartUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1){
            return;
        }

        if (cursor.moveToFirst()){

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
            int sauce1 = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_SAUCE1);
            int sauce2 = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_SAUCE2);

            String nameoffood = cursor.getString(name);
            String priceoffood = cursor.getString(price);
            String quantityoffood = cursor.getString(quantity);
            String yeshasSauce1 = cursor.getString(sauce1);
            String yeshasSauce2 = cursor.getString(sauce2);

            foodName.setText(nameoffood);
            productPrice.setText(priceoffood);
            quantitynumber.setText(quantityoffood);



        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        foodName.setText("");
        productPrice.setText("");
        quantitynumber.setText("");

    }
}