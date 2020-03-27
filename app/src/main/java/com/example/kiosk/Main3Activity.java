package com.example.kiosk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class Main3Activity extends AppCompatActivity {
    private FlipperLayout flipperLayout;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        flipperLayout = findViewById(R.id.flipper_layout2);
        mDatabase = FirebaseDatabase.getInstance().getReference("left");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
//                    Log.d("pavan", "images count: " + snapshot.getValue());
                    FlipperView view = new FlipperView(getBaseContext());
                    view.setImageUrl(snapshot.getValue().toString())
                            .setImageScaleType(ImageView.ScaleType.FIT_XY)
                            .resetDescriptionTextView();
                    flipperLayout.addFlipperView(view);
                    flipperLayout.setCircleIndicatorHeight(10);
                    flipperLayout.setCircleIndicatorWidth(10);
                    flipperLayout.removeCircleIndicator();
                    flipperLayout.showCircleIndicator();
                    flipperLayout.setScrollTimeInSec(15);
                    view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                        @Override
                        public void onFlipperClick(FlipperView flipperView) {
                            Toast.makeText(Main3Activity.this
                                    , "Here " + (flipperLayout.getCurrentPagePosition() + 1)
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("pavan", "error: " + databaseError.getMessage() );
            }
        });
    }
}