package com.example.kiosk;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Objects;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {
    SliderView sliderViewLeft;
    private FlipperLayout flipperLayout;
     int leftSideSlideCount = 0;
    ArrayList<String> imageArrayList =new ArrayList<>();
    private DatabaseReference mStorageLeftRef, mStorageRighttRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStorageLeftRef = FirebaseDatabase.getInstance().getReference("left");
        mStorageRighttRef = FirebaseDatabase.getInstance().getReference("right");
        sliderViewLeft = findViewById(R.id.imageSliderLeft);
        flipperLayout = findViewById(R.id.flipper_layout);
        getLeftImagesData();
        rightSlides();
    }

    private void getLeftImagesData() {
        final GetImages getImages = new GetImages();
        mStorageLeftRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               int lsc = (int) dataSnapshot.getChildrenCount();
                Log.d("pavan", "left image count: " + leftSideSlideCount);
                for (DataSnapshot snapshot : Objects.requireNonNull(dataSnapshot).getChildren()) {
                    Log.d("pavan", "left image url: " + snapshot.getValue());
                    getImages.setLeft(Objects.requireNonNull(snapshot.getValue()).toString());
                    String stringImages = snapshot.getValue().toString();
                    Log.d("now", "left image urls sting: " + stringImages);
                    imageArrayList.add(stringImages);
                }
                leftSideSlideCount = lsc;
                leftSlides();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void leftSlides() {
         SliderAdapterExampleLeft adapter = new SliderAdapterExampleLeft(this,imageArrayList);
        Log.d("kpp",imageArrayList+"");
        adapter.setCount(leftSideSlideCount);
        sliderViewLeft.setSliderAdapter(adapter);
        sliderViewLeft.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderViewLeft.setSliderTransformAnimation(SliderAnimations.CUBEOUTDEPTHTRANSFORMATION);
        sliderViewLeft.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderViewLeft.setIndicatorSelectedColor(Color.WHITE);
        sliderViewLeft.setIndicatorUnselectedColor(Color.GRAY);
        sliderViewLeft.setScrollTimeInSec(30);
        sliderViewLeft.startAutoCycle();

    }

    private void rightSlides() {
        mStorageRighttRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Log.d("pavan", "right image urls : " + snapshot.getValue());
                    FlipperView view = new FlipperView(getBaseContext());
                    view.setImageUrl(Objects.requireNonNull(snapshot.getValue()).toString())
                            .setImageScaleType(ImageView.ScaleType.FIT_XY)
                            .resetDescriptionTextView();
                    flipperLayout.addFlipperView(view);
                    flipperLayout.setCircleIndicatorHeight(10);
                    flipperLayout.setCircleIndicatorWidth(10);
                    flipperLayout.removeCircleIndicator();
                    flipperLayout.showCircleIndicator();
                    flipperLayout.setScrollTimeInSec(20);
                    view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                        @Override
                        public void onFlipperClick(FlipperView flipperView) {
                            Toast.makeText(MainActivity.this
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
