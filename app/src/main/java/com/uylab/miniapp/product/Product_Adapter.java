package com.uylab.miniapp.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.uylab.miniapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ViewHolder>{

    List<Product> productList = new ArrayList<>();
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Product_Adapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from ( parent.getContext () );
        View view = inflater.inflate ( R.layout.product_item_layout,parent,false );
        ViewHolder viewHolder = new ViewHolder ( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView5.setText(""+productList.get(position).getId());
        holder.textView.setText (""+ productList.get ( position ).getName () );
        holder.textView2.setText (""+ productList.get ( position ).getCatagory () );
        holder.textView3.setText (""+ productList.get ( position ).getDescription () );
        holder.textView4.setText (""+ String.valueOf ( productList.get ( position ).getPrice () )+" Tk" );
        holder.button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                   listener.OnItemClick ( productList.get ( position ),position );
                }
            }
        } );
        holder.cardView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.OnItemLongClick ( productList.get ( position ),position );
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return productList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textView5;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private ImageView button;
        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            textView=itemView.findViewById ( R.id.textView5 );
            textView2=itemView.findViewById ( R.id.textView6 );
            textView3=itemView.findViewById ( R.id.textView7 );
            textView4=itemView.findViewById ( R.id.textView8 );
            textView5=itemView.findViewById ( R.id.textView4 );
            cardView=itemView.findViewById ( R.id.cardView );
            button=itemView.findViewById ( R.id.imageView );
        }
    }
    public interface OnItemClickListener {
        public void OnItemClick (Product item, int position);
        public void OnItemLongClick (Product item, int position);
    }
}
