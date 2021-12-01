package org.aplas.frozenfood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.awt.font.TextAttribute;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nameofFood = modelList.get(position).getmFoodName();
        String descriptionofFood = modelList.get(position).getmFoodDetail();
        int images = modelList.get(position).getmFoodPhoto();

        holder.mFoodName.setText(nameofFood);
        holder.mFoodDescription.setText(descriptionofFood);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return modelList.size();

    }
    //kode untuk bisa di klik

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // find the views dan inflate data
        TextView mFoodName, mFoodDescription;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mFoodName = itemView.findViewById(R.id.productName);
            mFoodDescription = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.productImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //menentukan posisi array supaya bisa di klik
            int position = getAdapterPosition();
            if(position == 0){
                Intent intent = new Intent(context, InfoActivity.class);
                context.startActivity(intent);
            }
        }
    }
}
