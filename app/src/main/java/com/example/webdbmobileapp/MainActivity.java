package com.example.webdbmobileapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://web-db-mobile-app-7e48d-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = database.getReference("tasks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        EditText editTextToDoTitle = findViewById(R.id.ToDoTitle);
        EditText editTextToDoDescription = findViewById(R.id.ToDoDescription);

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
    }
}