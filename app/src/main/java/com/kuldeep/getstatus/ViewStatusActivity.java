package com.kuldeep.getstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewStatusActivity extends AppCompatActivity {

    private static final String TAG = "FireLog" ;
    private RecyclerView mListRecycler;
    private FirebaseFirestore firebaseFirestore;

    private List<Users> usersList;
    private ViewStatusAdapter mViewStatusAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);

        firebaseFirestore = FirebaseFirestore.getInstance();

        usersList = new ArrayList<>();
        mViewStatusAdapter = new ViewStatusAdapter(getApplicationContext(),usersList);

        mListRecycler = findViewById(R.id.status_recycle_view);
        mListRecycler.setHasFixedSize(true);
        mListRecycler.setLayoutManager(new LinearLayoutManager(this));
        mListRecycler.setAdapter(mViewStatusAdapter);

        firebaseFirestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(e != null){

                    Log.d(TAG, "Error : "+e.getMessage());

                } else{

                    Toast.makeText(ViewStatusActivity.this,"E is null",Toast.LENGTH_SHORT).show();
                }

                int pos = 0;

                for(DocumentChange doc : documentSnapshots.getDocumentChanges()){

                    if(doc.getType() == DocumentChange.Type.ADDED){

                        String name = doc.getDocument().getString("name");
                        String status = doc.getDocument().getString("status");
                        String userId = doc.getDocument().getId();

                        Log.d(TAG, "name and status: "+ name +"  "+ status);

                        Users users = doc.getDocument().toObject(Users.class).withId(userId);

                        usersList.add(users);



                        String size = String.valueOf(usersList.get(pos).getUserName());

                        Log.d( "usr liST Size: ",doc.getDocument().toObject(Users.class).toString());

                        mViewStatusAdapter.notifyDataSetChanged();



                    }
                }

            }
        });

    }
}
