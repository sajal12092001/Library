package com.example.alkamamrimt.Student_Return_Book;

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

public class Return_Book_Student_Details_Part2_Adapter extends RecyclerView.Adapter<Return_Book_Student_Details_Part2_Adapter.viewholder> implements Filterable {

    ArrayList<Return_Book_student_Details_Part2_Model> datalist;
    ArrayList<Return_Book_student_Details_Part2_Model> backup;

    public Return_Book_Student_Details_Part2_Adapter(ArrayList<Return_Book_student_Details_Part2_Model> datalist) {
        this.datalist = datalist;
        backup = new ArrayList<>(datalist);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.return_book_student_details_part2_single, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.bookid.setText(datalist.get(position).getBookid());
        holder.name.setText(datalist.get(position).getName());
        holder.course.setText(datalist.get(position).getCourse());
        holder.year.setText(datalist.get(position).getYear());
        holder.fathername.setText(datalist.get(position).getFathername());
        holder.phone.setText(datalist.get(position).getPhone());
        holder.issuedate.setText(datalist.get(position).getIssuedate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.name.getContext(), ReturnBook_details_part3.class);

                intent.putExtra("bookid", datalist.get(position).getBookid());
                intent.putExtra("name", datalist.get(position).getName());
                intent.putExtra("fathername", datalist.get(position).getFathername());
                intent.putExtra("phone", datalist.get(position).getPhone());
                intent.putExtra("course", datalist.get(position).getCourse());
                intent.putExtra("year", datalist.get(position).getYear());
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
            ArrayList<Return_Book_student_Details_Part2_Model> filterdata = new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filterdata.addAll(backup);
            } else {
                for (Return_Book_student_Details_Part2_Model obj : backup) {
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
            datalist.addAll((Collection<? extends Return_Book_student_Details_Part2_Model>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    class viewholder extends RecyclerView.ViewHolder {
        TextView bookid, course, fathername, issuedate, name, phone, year;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            bookid = itemView.findViewById(R.id.bookid);
            name = itemView.findViewById(R.id.name);
            course = itemView.findViewById(R.id.course);
            year = itemView.findViewById(R.id.year);
            fathername = itemView.findViewById(R.id.fname);
            phone = itemView.findViewById(R.id.phone);
            issuedate = itemView.findViewById(R.id.issuedate);
        }
    }
}
