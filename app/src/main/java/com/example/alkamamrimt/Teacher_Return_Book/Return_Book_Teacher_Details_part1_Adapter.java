package com.example.alkamamrimt.Teacher_Return_Book;

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

public class Return_Book_Teacher_Details_part1_Adapter extends RecyclerView.Adapter<Return_Book_Teacher_Details_part1_Adapter.viewholder> implements Filterable {

    ArrayList<Return_Book_Teacher_Details_part1_Model> datalist;
    ArrayList<Return_Book_Teacher_Details_part1_Model> backup;

    public Return_Book_Teacher_Details_part1_Adapter(ArrayList<Return_Book_Teacher_Details_part1_Model> datalist) {
        this.datalist = datalist;
        backup=new ArrayList<>(datalist);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_return_book_teacher_details_single,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.id.setText(datalist.get(position).getBookid());
        holder.name.setText(datalist.get(position).getName());
        holder.issuedate.setText(datalist.get(position).getIssuedate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.name.getContext(),Return_Book_Teacher_part2.class);
                intent.putExtra("bookid", datalist.get(position).getBookid());
                intent.putExtra("name", datalist.get(position).getName());
                intent.putExtra("issuedate", datalist.get(position).getIssuedate());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                holder.name.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Return_Book_Teacher_Details_part1_Model> filterdata = new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filterdata.addAll(backup);
            } else {
                for (Return_Book_Teacher_Details_part1_Model obj : backup) {
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
            datalist.addAll((Collection<? extends Return_Book_Teacher_Details_part1_Model>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    class viewholder extends RecyclerView.ViewHolder
    {
        TextView id,name,issuedate;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.bookid);
            name=itemView.findViewById(R.id.name);
            issuedate=itemView.findViewById(R.id.issuedate);
        }
    }
}
