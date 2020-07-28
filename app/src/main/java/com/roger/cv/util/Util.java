package com.roger.cv.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.roger.cv.R;

import java.util.Random;

public class Util {

    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView imageView, String imageName){
        if(imageName != null) {
            CircularProgressDrawable circularProgressDrawable = getCircularProgressDrawable(imageView.getContext());

            RequestOptions options = new RequestOptions()
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.ic_work);

            GlideApp.with(imageView.getContext())
                    .setDefaultRequestOptions(options)
                    .load(getFirebaseImageLocation(imageName))
                    .into(imageView);
        }
    }

    @BindingAdapter("android:circularImageUrl")
    public static void loadCircularImage(ImageView imageView, String imageName){
        if(imageName != null) {
            CircularProgressDrawable circularProgressDrawable = getCircularProgressDrawable(imageView.getContext());

            RequestOptions options = new RequestOptions()
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.ic_work);

            GlideApp.with(imageView.getContext())
                    .setDefaultRequestOptions(options)
                    .load(getFirebaseImageLocation(imageName))
                    .circleCrop()
                    .into(imageView);
        }
    }

    public static int getRandomNumber(int max) {
        int min = 0;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private static StorageReference getFirebaseImageLocation(String imageUrl){
        String storage = "gs://roger-cv.appspot.com";
        FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
        return mFirebaseStorage.getReferenceFromUrl(storage).child(imageUrl);
    }

    private static CircularProgressDrawable getCircularProgressDrawable(Context context){
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();
        return cpd;
    }

}
