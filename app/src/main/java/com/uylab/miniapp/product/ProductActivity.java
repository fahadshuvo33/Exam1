package com.uylab.miniapp.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uylab.miniapp.R;
import com.uylab.miniapp.util.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements Product_Adapter.OnItemClickListener, View.OnClickListener {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private RecyclerView recyclerView;
    private Button button;
    private SqliteHelper sqliteHelper;
    private int toUpdate=0;
    private List<Product> productList;
    Product_Adapter product_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_product );
        productList=new ArrayList<> ();

        textView=findViewById ( R.id.textView4 );
        textView2=findViewById ( R.id.textView5 );
        textView3=findViewById ( R.id.textView6 );
        textView4=findViewById ( R.id.textView7 );
        editText4=findViewById ( R.id.editText4 );
        editText=findViewById ( R.id.editText );
        editText2=findViewById ( R.id.editText2 );
        editText3=findViewById ( R.id.editText3 );
        recyclerView=findViewById ( R.id.recyclerView );
        button=findViewById ( R.id.button );

        product_adapter = new Product_Adapter ( productList );
        recyclerView.setAdapter ( product_adapter );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( ProductActivity.this ) );

        button.setOnClickListener ( this );
        product_adapter.setListener ( this );

        sqliteHelper=new SqliteHelper(this);
        showList();

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");

    }

    @Override
    public void OnItemClick(Product item, int position) {

        sqliteHelper.delete ( item.getId () );
        productList.remove ( position );
        product_adapter.notifyDataSetChanged();

    }

    @Override
    public void OnItemLongClick(Product item, int position) {
        editText.setText(item.getName());
        editText2.setText(""+item.getCatagory());
        editText3.setText(""+item.getDescription());
        editText4.setText(""+item.getPrice());
        toUpdate=item.getId();
        product_adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        String pName = editText.getText ().toString ().trim ();
        String pCatagory = editText2.getText ().toString ().trim ();
        String pDescription = editText3.getText ().toString ().trim ();
        String pPrice = editText4.getText ().toString ().trim ();

        if(toUpdate==0)
        {
            sqliteHelper.save(pName,pCatagory,pDescription,Integer.parseInt (pPrice));
        }
        else {

            sqliteHelper.update(new Product(toUpdate,pName,pCatagory,pDescription,Integer.parseInt(pPrice)));
            toUpdate=0;

        }

        showList();
        clearAll();
        Toast.makeText(ProductActivity.this,"Data Saved!",Toast.LENGTH_SHORT).show();
    }

    public void showList(){
        productList.clear();
        productList.addAll(sqliteHelper.getAllProducts());
        product_adapter.notifyDataSetChanged();
    }

    public void clearAll()
    {
        editText.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        toUpdate=0;
    }
}
