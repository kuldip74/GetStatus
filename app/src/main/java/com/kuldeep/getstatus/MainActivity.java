package com.kuldeep.getstatus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mWelcomeTV;
    private EditText mName;
    private EditText mStatus;
    private Button mSaveBtn;
    private Button mViewStatusBtn;

    private StorageReference mStorageRef;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mFirestore = FirebaseFirestore.getInstance();


        mWelcomeTV = findViewById(R.id.welcome_text);
        mName = findViewById(R.id.name);
        mStatus = findViewById(R.id.status);
        mSaveBtn = findViewById(R.id.save_btn);
        mViewStatusBtn = findViewById(R.id.view_status_btn);

        mViewStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewStatusIntent = new Intent(MainActivity.this,ViewStatusActivity.class);
                startActivity(viewStatusIntent);
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(mName.getText().toString()) && !TextUtils.isEmpty(mStatus.getText().toString())){

                    final String userName = mName.getText().toString();
                    String userStatus = mStatus.getText().toString();
                    Map<String,String> userMap = new HashMap<>();
                    userMap.put("name",userName);
                    userMap.put("status",userStatus);
                    mFirestore.collection("users").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(MainActivity.this,"Status Saved",Toast.LENGTH_SHORT).show();
                            mWelcomeTV.setText("Welcome " + userName);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage();
                            Toast.makeText(MainActivity.this,"Error : "+ error,Toast.LENGTH_LONG).show();

                        }
                    });

                }else{

                    Toast.makeText(MainActivity.this,"Name or Status Missing..",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
