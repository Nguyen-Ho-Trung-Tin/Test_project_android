package com.nguyenhotrungtin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhotrungtin.Models.Product;
import com.nguyenhotrungtin.sqlite_exam2.MainActivity;
import com.nguyenhotrungtin.sqlite_exam2.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    MainActivity activity;
    int item_layout;
    List<Product> products;

    public ProductAdapter(MainActivity activity, int item_layout, List<Product> products) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(item_layout,null);

            holder.txtProductinf= convertView.findViewById(R.id.txtname);
            holder.imvEdit=convertView.findViewById(R.id.imv_Edit);
            holder.imvDelete=convertView.findViewById(R.id.imv_Delete);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();


        }
        Product p=products.get(position);
        holder.txtProductinf.setText(p.getProductName()+ " - " +p.getProductPrice());

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
    public static class ViewHolder{
        TextView txtProductinf;
        ImageView imvEdit,imvDelete;
    }
}
