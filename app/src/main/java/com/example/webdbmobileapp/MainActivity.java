package com.example.webdbmobileapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://web-db-mobile-app-7e48d-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = database.getReference("tasks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        EditText editTextToDoTitle = findViewById(R.id.ToDoTitle);
        EditText editTextToDoDescription = findViewById(R.id.ToDoDescription);
        ListView listView = findViewById(R.id.ToDoDisplay);

        Button submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(v -> {
            Log.i("Submit Button Pressed", "The Submit Button successfully was called");

            String ToDoTitleText = editTextToDoTitle.getText().toString().trim();
            String ToDoDescription = editTextToDoDescription.getText().toString().trim();

            String id = dbRef.push().getKey();
            Task task = new Task(id, ToDoTitleText, ToDoDescription);

            try {
                dbRef.child(id).setValue(task);
                Log.i("Submitted Task", "Task Submitted Successfully");
            } catch (Exception e) {
                Log.i("Task Submission Failure", "Task Failed to Submit");
            }
        });

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this ,R.layout.list_view, list);
        listView.setAdapter(adapter);

        DatabaseReference retrieve = FirebaseDatabase.getInstance("https://web-db-mobile-app-7e48d-default-rtdb.firebaseio.com/").getReference().child("tasks");
        retrieve.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}