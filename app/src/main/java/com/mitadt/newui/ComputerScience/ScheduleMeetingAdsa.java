package com.mitadt.newui.ComputerScience;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mitadt.newui.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ScheduleMeetingAdsa extends AppCompatActivity {


    public static final String TAG = "TAG";
    EditText date,time,meeting,discussion;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String timeset,dateset,link,topic,randomPostkey,UserId;
    Button schedule;
    FirebaseFirestore fstore;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_meeting_adsa);

        //FIRESTORE
        fstore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        //DATEPICKER
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ScheduleMeetingAdsa.this,onDateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateset = dayOfMonth + "/" + month + "/" + year;
                date.setText(dateset);

            }
        };

        time = findViewById(R.id.time);
        //TIMEPICKER
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(ScheduleMeetingAdsa.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if(hourOfDay<12){

                            timeset = checkDigit(hourOfDay) + ":" + checkDigit(minute) + " AM";
                        }
                        else {
                            timeset =checkDigit(hourOfDay) + ":" + checkDigit(minute) + " PM";
                        }

                        time.setText(timeset);
                    }
                },hour,minute,true);
                timePickerDialog.setTitle("Choose Time");
                timePickerDialog.show();
            }
        });

        //MEETING LINK
        meeting = findViewById(R.id.meetinglink);
        link = meeting.getText().toString().trim();

        //topic of discussion
        discussion = findViewById(R.id.topic);
        topic = discussion.getText().toString().trim();
        UserId = firebaseAuth.getCurrentUser().getUid();



        //SCHEDULE MEETING BUTTON
        schedule = findViewById(R.id.scheduleButton);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link = meeting.getText().toString().trim();
                topic = discussion.getText().toString().trim();

                randomPostkey = UserId+""+new Random().nextInt(1000);
                String lec = "Adsa";
                //firestore -> storing meeting info
                DocumentReference documentReference = fstore.collection("LECTURES").document(randomPostkey);
                Map<String,Object> user = new HashMap<>();
                user.put("LectureDate",dateset);
                user.put("LectureTime",timeset);
                user.put("Topic",topic);
                user.put("MeetingLink",link);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "On success: Lecture has Been Scheduled ");
                        startActivity(new Intent(getApplicationContext(),ZoomLecture.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "On failure " + e.toString());

                    }
                });

            }
        });
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}