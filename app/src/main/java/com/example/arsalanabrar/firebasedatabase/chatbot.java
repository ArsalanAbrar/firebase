package com.example.arsalanabrar.firebasedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class chatbot extends AppCompatActivity {
TextView name,conversation;
EditText message;
Button send;
String username;
DatabaseReference databaseuserdata;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        name=(TextView)findViewById(R.id.textView3);
        conversation=(TextView)findViewById(R.id.textView4);
        message=(EditText)findViewById(R.id.editText4);
        send=(Button)findViewById(R.id.button4);
    databaseuserdata= FirebaseDatabase.getInstance().getReference("usermessage");
    Bundle extras = getIntent().getExtras();
        username=extras.getString("username");
        name.setText(username);
        send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String id=databaseuserdata.push().getKey();
            databaseuserdata= FirebaseDatabase.getInstance().getReference().child("user");
            String s= message.getText().toString();
            final chat chat=new chat(username,s);
            databaseuserdata.child(id).setValue(chat);
            conversation.setText(chat.getMessage());
            }
    });


}
}
