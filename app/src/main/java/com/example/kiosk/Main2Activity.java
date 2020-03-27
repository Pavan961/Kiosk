package com.example.kiosk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Main2Activity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private ImageView imageView;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.imageView);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mStorageRef.child("left/have_a_great_day.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                url = uri.getPath();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                url = e.getMessage();
            }
        });
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        Glide.with(getBaseContext())
                .load("https://firebasestorage.googleapis.com/v0/b/kiosk-8885d.appspot.com/o/left%2Fhave_a_great_day.jpg?alt=media&token=6c191fdc-fd6f-4d48-a100-19b988486445")
                .into(imageView);


    }
}
