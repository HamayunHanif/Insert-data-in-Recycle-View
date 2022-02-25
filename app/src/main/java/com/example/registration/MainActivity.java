package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arrContacts = new ArrayList<>();

    RecyclerContactAdapter adapter;
    FloatingActionButton btnOpenDialog;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialoge);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update);
                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", number = "";
                        if (!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();

                        }else {
                            Toast.makeText(MainActivity.this,"Plese Enter Contact Name",Toast.LENGTH_SHORT).show();
                        }
                        if (!edtNumber.getText().toString().equals("")){
                            number = edtNumber.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this,"Plese Enter Contact Name",Toast.LENGTH_SHORT).show();

                        }

                        arrContacts.add(new ContactModel(name , number));

                        adapter.notifyItemInserted(arrContacts.size()-1);

                        recyclerView.scrollToPosition(arrContacts.size()-1);

                        dialog.dismiss();


                    }
                });
                dialog.show();
            }
        });



        arrContacts.add(new ContactModel(R.drawable.jh,"Hamayun","03004176056"));
        arrContacts.add(new ContactModel(R.drawable.hami,"HamayunB","03124974668"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new RecyclerContactAdapter(this,arrContacts);
        recyclerView.setAdapter(adapter);
    }
}