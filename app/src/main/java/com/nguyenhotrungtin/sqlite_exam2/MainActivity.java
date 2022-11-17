package com.nguyenhotrungtin.sqlite_exam2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nguyenhotrungtin.Adapters.ProductAdapter;
import com.nguyenhotrungtin.Models.Product;
import com.nguyenhotrungtin.sqlite_exam2.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DatabaseHelper db;
    ArrayList<Product> products;
    ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CreateDB();
        Loaddata();

    }

    private void Loaddata() {
        products= new ArrayList<>();
        Cursor c=db.getData( " SELECT * FROM " + DatabaseHelper.TBL_NAME );
        while (c.moveToNext()){
            products.add(new Product(c.getInt(0),c.getString(1),c.getDouble(2)));
        }
        c.close();
        adapter=new ProductAdapter(MainActivity.this,R.layout.item_list,products);
        binding.lvhienthi.setAdapter(adapter);
    }

    private void CreateDB() {
        db = new DatabaseHelper(MainActivity.this);
        db.createSampleData();
    }

    //=========menu===========

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu){
            Dialog dialog= new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_add_product);

            Button btnoke= findViewById(R.id.btnoke);
            EditText edtName= dialog.findViewById(R.id.txtname);
            EditText edtPrice= dialog.findViewById(R.id.txtprice);


            btnoke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.execSql("INSERT INTO "+ DatabaseHelper.TBL_NAME + " VALUES(null, '" + edtName.getText().toString() + "', " +Double.parseDouble(edtPrice.getText().toString()) + ")"   );
                    dialog.dismiss();
                    Loaddata();

                }
            });

            Button btncancle= findViewById(R.id.btncancle);
            btncancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}