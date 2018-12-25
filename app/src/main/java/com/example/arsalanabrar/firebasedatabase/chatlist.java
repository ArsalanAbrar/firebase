package com.example.arsalanabrar.firebasedatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chatlist extends AppCompatActivity {
    List<Userdata> data=new ArrayList<>();
    List<listdata>list;
    private RecyclerView recyclerView;
    private userlist mAdapter;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

         firebaseDatabase = FirebaseDatabase.getInstance();
         databaseReference = firebaseDatabase.getReference("user");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Userdata userdetails = dataSnapshot1.getValue(Userdata.class);
                    listdata listdata = new listdata();
                    String name=userdetails.getUser_name();
                    listdata.setUser_name(name);
                    list.add(listdata);
                    // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                }

                userlist recycler = new userlist(list,getApplicationContext());
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(chatlist.this);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.addItemDecoration(new DividerItemDecoration(chatlist.this, LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(recycler);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                         listdata listdata = list.get(position);
                        Intent i =new Intent(chatlist.this,chatbot.class);
                        i.putExtra("username",listdata.getUser_name());
                        startActivity(i);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        }
}
