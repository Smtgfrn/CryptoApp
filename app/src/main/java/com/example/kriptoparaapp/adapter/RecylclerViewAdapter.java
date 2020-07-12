package com.example.kriptoparaapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.kriptoparaapp.R;
import com.example.kriptoparaapp.model.CryptoModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecylclerViewAdapter extends RecyclerView.Adapter<RecylclerViewAdapter.RowHolder> implements Filterable {


    private ArrayList<CryptoModel> cryptoList;
    private ArrayList<CryptoModel> cryptoListAll;

    private String[] colors = {"#207878", "#48d09b", "#5488a5", "#7b78b4","#1e84d0","#850ffa", "#ff0000", "#fd3612"};

    public RecylclerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
        this.cryptoListAll = new ArrayList<>(cryptoList);
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

            holder .bind(cryptoList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<CryptoModel> filtredList = new ArrayList<>();

            if(charSequence.toString().isEmpty()){
                filtredList.addAll(cryptoListAll);

            }else{
                for (CryptoModel cryptoName:cryptoListAll){
                     if(cryptoName.currency.toLowerCase().contains(charSequence.toString().toLowerCase())){
                         filtredList.add(cryptoName);
                     }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filtredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cryptoList.clear();
                cryptoList.addAll((Collection<? extends CryptoModel>) filterResults.values);
                notifyDataSetChanged();
        }
    };

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CryptoModel cryptoModel, String[] colors, Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price + " $");

        }
    }
}
