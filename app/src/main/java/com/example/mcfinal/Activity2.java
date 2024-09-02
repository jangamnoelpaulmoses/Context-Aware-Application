//Description- This is the activity2 java file, here we deal with the second page or the activity which is symptoms
// The symptoms are 10. They are given individual rating out of 5
//there is a submit button added below
//once the submit button is clicked, the symptom with its specific rating will be added to the database
//the database used here is SQLite
//There is a dialogue box or a notification which will pop up once the submit button is clicked
// the alert or the notification pop up indicates the information is submitted and added to the database.
package com.example.mcfinal;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    String[] symptoms = {"fever", "nausea", "cough", "headache", "feeling tired", "shortness of breath", "loss of smell or taste", "muscle ache", "sore throat", "diarrhea"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterSymptoms;
    RatingBar ratingBar;
    Button submitButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterSymptoms = new ArrayAdapter<>(this, R.layout.list_symptoms, symptoms);
        autoCompleteTextView.setAdapter(adapterSymptoms);

        ratingBar = findViewById(R.id.symptom_rating);
        submitButton = findViewById(R.id.submit_button);

        dbHelper = new DBHelper(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sSym- selected symptom
                String sSym= autoCompleteTextView.getText().toString();
                float selectedRating = ratingBar.getRating();

                // Checking a symptom is selected or not
                if (!sSym.isEmpty()) {
                    // Inserting the information into the database
                    iSD(sSym, selectedRating);

                    // Display a success message
                    String m = "Symptom: " + sSym + "\nRating: " + selectedRating + "\nData stored in the database.";
                    Toast.makeText(Activity2.this, m, Toast.LENGTH_SHORT).show();
                    //m- message
                } else {
                    // If no symptom is selected, show a message
                    Toast.makeText(Activity2.this, "Please select a symptom.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void iSD(String symptom, float rating) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("symptom", symptom);
        values.put("rating", rating);
        db.insert("user_data", null, values);
       db.close();
    }
}