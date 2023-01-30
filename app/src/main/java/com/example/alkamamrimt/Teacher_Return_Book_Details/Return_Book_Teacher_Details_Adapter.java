package com.example.alkamamrimt.Teacher_Return_Book_Details;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkamamrimt.R;

import java.util.ArrayList;

public class Return_Book_Teacher_Details_Adapter extends RecyclerView.Adapter<Return_Book_Teacher_Details_Adapter.viewholder>{
    ArrayList<Return_Book_Teacher_Details_Model> datalist;

    public Return_Book_Teacher_Details_Adapter(ArrayList<Return_Book_Teacher_Details_Model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_return_book_teacher_details2_list_single,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.id.setText(datalist.get(position).getBookid());
        holder.name.setText(datalist.get(position).getName());
        holder.issuedate.setText(datalist.get(position).getIssuedate());
        holder.returndate.setText(datalist.get(position).getReturndate());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class viewholder extends RecyclerView.ViewHolder {

        TextView id,name,issuedate,returndate;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.bookid);
            name=itemView.findViewById(R.id.name);
            issuedate=itemView.findViewById(R.id.issuedate);
            returndate=itemView.findViewById(R.id.returndate);
        }
    }

}
