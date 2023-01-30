package com.example.alkamamrimt.Student_Issue_Book;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class Issue_Book_Student_Details_Part2_Adapter extends RecyclerView.Adapter<Issue_Book_Student_Details_Part2_Adapter.viewholder> implements Filterable {

    ArrayList<Issue_Book_student_Details_Part2_Model> datalist;
    ArrayList<Issue_Book_student_Details_Part2_Model> backup;

    public Issue_Book_Student_Details_Part2_Adapter(ArrayList<Issue_Book_student_Details_Part2_Model> datalist) {
        this.datalist = datalist;
        backup = new ArrayList<>(datalist);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isuue_book_student_details_part2_single, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.t1.setText(datalist.get(position).getName());
        holder.t5.setText(datalist.get(position).getFathername());
        holder.t2.setText(datalist.get(position).getCourse());
        holder.t3.setText(datalist.get(position).getYear());
        holder.t4.setText(datalist.get(position).getPhone());

        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(holder.t1.getContext(), IssueBook_details_part3.class);

            intent.putExtra("name",datalist.get(position).getName());
            intent.putExtra("fathername",datalist.get(position).getFathername());
            intent.putExtra("phone",datalist.get(position).getPhone());
            intent.putExtra("course",datalist.get(position).getCourse());
            intent.putExtra("year",datalist.get(position).getYear());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            holder.t1.getContext().startActivity(intent);

        });


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
            ArrayList<Issue_Book_student_Details_Part2_Model> filterdata = new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filterdata.addAll(backup);
            } else {
                for (Issue_Book_student_Details_Part2_Model obj : backup) {
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
            datalist.addAll((Collection<? extends Issue_Book_student_Details_Part2_Model>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    static class viewholder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3, t4, t5;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
            t5 = itemView.findViewById(R.id.t5);

        }
    }
}
