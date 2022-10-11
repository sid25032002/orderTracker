package com.example.ordertracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.baoyachi.stepview.VerticalStepView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    VerticalStepView step_view;
    String value;
    Integer value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Data");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                value=snapshot.getValue().toString();
                step_view=findViewById(R.id.Step_view);
                setStepView(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this,"Failed to get data",Toast.LENGTH_SHORT).show();

            }
        });

        //step_view=findViewById(R.id.Step_view);
        //setStepView(value);
    }

    private void setStepView(String value){
        //int value1=Integer.parseInt(value);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            step_view.setStepsViewIndicatorComplectingPosition(getList().size())
                    .reverseDraw(false)
                    .setStepViewTexts(getList())
                    .setLinePaddingProportion(0.85f)
                    .setStepsViewIndicatorCompletedLineColor(getColor(com.google.android.material.R.color.design_default_color_primary))
                    .setStepViewComplectedTextColor(getColor(R.color.black))
                    .setStepViewUnComplectedTextColor(Color.WHITE)
                    .setStepsViewIndicatorCompleteIcon(getDrawable(com.baoyachi.stepview.R.drawable.complted))
                    .setStepsViewIndicatorAttentionIcon(getDrawable(com.baoyachi.stepview.R.drawable.attention))
                    .setStepsViewIndicatorDefaultIcon(getDrawable(com.baoyachi.stepview.R.drawable.default_icon));

            //value1=Integer.parseInt(value);
            //step_view.setStepsViewIndicatorComplectingPosition(value1);
            step_view.setStepsViewIndicatorComplectingPosition(Integer.parseInt(value)-1);

        }

    }

    private List<String> getList(){
        List<String> list=new ArrayList<>();
        list.add("First Step");
        list.add("Second Step");
        list.add("Third Step");
        list.add("Fourth Step");
        list.add("Fifth Step");
        return list;
    }
}