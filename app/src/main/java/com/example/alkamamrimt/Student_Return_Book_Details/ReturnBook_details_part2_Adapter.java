package com.example.alkamamrimt.Student_Return_Book_Details;

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

public class ReturnBook_details_part2_Adapter extends RecyclerView.Adapter<ReturnBook_details_part2_Adapter.viewholder> implements Filterable {
    ArrayList<ReturnBook_details_part2_Model> datalist;
    ArrayList<ReturnBook_details_part2_Model> backup;

    public ReturnBook_details_part2_Adapter(ArrayList<ReturnBook_details_part2_Model> datalist) {
        this.datalist = datalist;
        backup = new ArrayList<>(datalist);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.returnbook_details_main_part2_single,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.bookid.setText(datalist.get(position).getBookid());
        holder.name.setText(datalist.get(position).getName());
        holder.course.setText(datalist.get(position).getCourse());
        holder.year.setText(datalist.get(position).getYear());
        holder.fathername.setText(datalist.get(position).getFathername());
        holder.phone.setText(datalist.get(position).getPhone());
        holder.issuedate.setText(datalist.get(position).getIssuedate());
        holder.fine.setText(datalist.get(position).getFine());
        holder.returndate.setText(datalist.get(position).getReturndate());
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
            ArrayList<ReturnBook_details_part2_Model> filterdata = new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filterdata.addAll(backup);
            } else {
                for (ReturnBook_details_part2_Model obj : backup) {
                    if (obj.getName().contains(keyword.toString().trim().toUpperCase())) {
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
            datalist.addAll((Collection<? extends ReturnBook_details_part2_Model>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    class viewholder extends RecyclerView.ViewHolder
    {
        TextView bookid,course,fathername,issuedate,name,phone,year,fine,returndate;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            bookid=itemView.findViewById(R.id.bookid);
            name=itemView.findViewById(R.id.name);
            course=itemView.findViewById(R.id.course);
            year=itemView.findViewById(R.id.year);
            fathername=itemView.findViewById(R.id.fname);
            phone=itemView.findViewById(R.id.phone);
            issuedate=itemView.findViewById(R.id.issuedate);
            fine=itemView.findViewById(R.id.fine);
            returndate=itemView.findViewById(R.id.returndate);
        }
    }
}
