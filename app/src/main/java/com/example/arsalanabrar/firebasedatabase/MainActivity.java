package com.example.arsalanabrar.firebasedatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText name,email,pno;
Button signup,login;
TextView logintext;
DatabaseReference databaseuserdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseuserdata=FirebaseDatabase.getInstance().getReference("user");
        initialize_view();

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
                name.setVisibility(View.GONE);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_data();
            }
        });

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            check();
        }
    });
    }


    public void initialize_view(){
        name=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText2);
        pno=(EditText)findViewById(R.id.editText3);
        signup=(Button)findViewById(R.id.button);
        login=(Button)findViewById(R.id.button2);
        logintext=(TextView)findViewById(R.id.textView);

    }
    public void check(){
        String phone=pno.getText().toString().trim();
        databaseuserdata= FirebaseDatabase.getInstance().getReference().child("user");

        databaseuserdata.orderByChild("phoneno").equalTo(phone)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            Intent i=new Intent(MainActivity.this,chatlist.class);
                            startActivity(i);


                        } else {
//                            databaseuserdata.child(id).setValue(userdata);
Toast.makeText(MainActivity.this,"Wrong Number",Toast.LENGTH_SHORT);
                         }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




    }
    public void save_data(){

        String usern=name.getText().toString().trim();
        String pass=email.getText().toString().trim();
        String phone=pno.getText().toString().trim();
        final String id=databaseuserdata.push().getKey();
        final Userdata userdata=new Userdata(id,usern,phone,pass);
        databaseuserdata= FirebaseDatabase.getInstance().getReference().child("user");

        databaseuserdata.orderByChild("phoneno").equalTo(phone)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Toast.makeText(MainActivity.this,"user already exist",Toast.LENGTH_SHORT).show();

                        } else {
                            databaseuserdata.child(id).setValue(userdata);
                            Intent i=new Intent(MainActivity.this,chatlist.class);
                            startActivity(i);
                        }

                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
