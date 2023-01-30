package com.example.alkamamrimt.Missing_book.Fragments.Missing_Book_Details;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkamamrimt.R;

import java.util.ArrayList;
import java.util.Collection;

public class Missing_book_details_Adapter extends RecyclerView.Adapter<Missing_book_details_Adapter.myviewholder> implements Filterable {
    ArrayList<Missing_book_details_Model> datalist;
    ArrayList<Missing_book_details_Model> backup;

    public Missing_book_details_Adapter(ArrayList<Missing_book_details_Model> datalist) {
        this.datalist = datalist;
        backup = new ArrayList<>(datalist);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.missing_book_details_single, parent, false);
        return new myviewholder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.t1.setText(datalist.get(position).getName());
        holder.t2.setText(datalist.get(position).getPublication());
        holder.t3.setText(datalist.get(position).getId());
        holder.t4.setText(datalist.get(position).getAuthor());
        holder.t6.setText(datalist.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Missing_book_details_Model> filterdata = new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filterdata.addAll(backup);
            } else {
                for (Missing_book_details_Model obj : backup) {
                    if (obj.getId().contains(keyword.toString().trim())) {
                        filterdata.add(obj);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdata;
            results.count = filterdata.size();
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            datalist.clear();
            datalist.addAll((Collection<? extends Missing_book_details_Model>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    static class myviewholder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3, t4, t6;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
            t6 = itemView.findViewById(R.id.t6);
        }
    }
}
