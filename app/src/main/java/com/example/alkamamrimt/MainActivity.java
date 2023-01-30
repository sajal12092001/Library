package com.example.alkamamrimt;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alkamamrimt.Add_Student.Add_Student;
import com.example.alkamamrimt.Books.Book_Fragment;
import com.example.alkamamrimt.Missing_book.Missing_Book_Fragment;
import com.example.alkamamrimt.Student_Issue_Book.IssueBook_course_year_part1;
import com.example.alkamamrimt.Student_Return_Book.ReturnBook_course_year_part1;
import com.example.alkamamrimt.Student_Return_Book_Details.ReturnBook_details_part1;
import com.example.alkamamrimt.Teacher_Issue_Book.Teacher_Issuebook_part1;
import com.example.alkamamrimt.Teacher_Return_Book.Return_Book_Teacher_Details_part1;
import com.example.alkamamrimt.Teacher_Return_Book_Details.Return_Book_Teacher_Details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    LinearLayout addbook, addstudent, issuebook, returnbook, missingbookdetails, returnbookdetails, teacherissuebook, teacherreturnbook, teachersreturnbookdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addstudent = findViewById(R.id.addstudent);
        addbook = findViewById(R.id.addbook);
        issuebook = findViewById(R.id.issuebook);
        returnbook = findViewById(R.id.returnbook);
        missingbookdetails = findViewById(R.id.missingbookdetails);
        returnbookdetails = findViewById(R.id.returnbookdetails);
        teacherissuebook = findViewById(R.id.teacherissuebook);
        teacherreturnbook = findViewById(R.id.teacherreturnbook);
        teachersreturnbookdetails = findViewById(R.id.teacherreturnbookdetails);
        Conn conn = new Conn(this);
        conn.getReadableDatabase();


        addbook.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Book_Fragment.class);
            startActivity(intent);
        });
        addstudent.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Add_Student.class);
            startActivity(intent);
        });
        issuebook.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IssueBook_course_year_part1.class);
            startActivity(intent);
        });
        returnbook.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReturnBook_course_year_part1.class);
            startActivity(intent);
        });
        missingbookdetails.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Missing_Book_Fragment.class);
            startActivity(intent);
        });
        returnbookdetails.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReturnBook_details_part1.class);
            startActivity(intent);
        });
        teacherissuebook.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Teacher_Issuebook_part1.class);
            startActivity(intent);
        });
        teacherreturnbook.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Return_Book_Teacher_Details_part1.class);
            startActivity(intent);
        });
        teachersreturnbookdetails.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Return_Book_Teacher_Details.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.backup_restore, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup: {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to backup ?");
                builder.setTitle("Alert !");

                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        backup();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();


            }
            break;
            case R.id.restore: {
                // startActivity(new Intent(this, Restore.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to restore ?");
                builder.setTitle("Alert !");

                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            restore();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();

            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void restore() throws Exception {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Restoring...");
        progressDialog.show();


        StorageReference storageReference = FirebaseStorage.getInstance("gs://alkamamrimtsqlite.appspot.com").getReference().child("database.db");
        final String inFileName = "/data/com.example.alkamamrimt/databases/database.db";
        File dbFile = new File(Environment.getDataDirectory(), inFileName);
        storageReference.getFile(dbFile)
                .addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull FileDownloadTask.TaskSnapshot snapshot) {
                        double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        //  Toast.makeText(Backup.this, "Upload is " + progress + "% done", Toast.LENGTH_SHORT).show();;
                        progressDialog.setMessage((int) progress + "% Please wait....");

                    }
                })
                .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                        progressDialog.dismiss();
                        Toasty.success(MainActivity.this, "Data Restored Successfully", Toasty.LENGTH_SHORT).show();
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, ""+e , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void backup() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();


        final String inFileName = "/data/com.example.alkamamrimt/databases/database.db";
        File dbFile = new File(Environment.getDataDirectory(), inFileName);
        try {
            FileInputStream inputStream = new FileInputStream(dbFile);
            StorageReference storageReference = FirebaseStorage.getInstance("gs://alkamamrimtsqlite.appspot.com").getReference().child("database.db");
            UploadTask uploadTask = storageReference.putStream(inputStream);
            uploadTask
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            //  Toast.makeText(Backup.this, "Upload is " + progress + "% done", Toast.LENGTH_SHORT).show();;
                            progressDialog.setMessage((int) progress + "% Please wait....");

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, ""+e , Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    progressDialog.dismiss();
                    Toasty.success(MainActivity.this, "Uploaded successfully to the server", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}