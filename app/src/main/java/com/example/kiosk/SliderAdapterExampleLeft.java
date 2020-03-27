package com.example.kiosk;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapterExampleLeft extends
        SliderViewAdapter<SliderAdapterExampleLeft.SliderAdapterVH> {
    ArrayList imageArrayList ;
    private Context context;
    private int mCount;

    public SliderAdapterExampleLeft(Context context,ArrayList imageArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
        Log.d("nope","Arraylist"+imageArrayList);
    }

    public void setCount(int count) {
        this.mCount = count;
        Log.d("nope","count in adapter"+count);
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item_left, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(0))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(1))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(2))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(3))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 4:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(4))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 5:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(5))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 6:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(6))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 7:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(7))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 8:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(8))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 9:
                Glide.with(viewHolder.itemView)
                        .load(imageArrayList.get(9))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/test1-47b4f.appspot.com/o/have_a_great_day.jpg?alt=media&token=01614ae0-956a-49d8-a530-88c52127ce36")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);

                break;
        }



    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}