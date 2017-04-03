package com.cyris.recyclerview;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.*;
import android.support.design.BuildConfig;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.Random;

import static android.R.attr.bitmap;

public class DetailActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbar;
    int imageList[]={R.drawable.john, R.drawable.miwallpaper, R.drawable.rocky, R.drawable.whitehousedown};
    Random crazy;
    int randomInt;
    private ImageLoader imageLoader;
    NetworkImageView networkImageView;
    int chosenColor;
    String urlList[]={"http://image.tmdb.org/t/p/w500/////x4cycTgAtBIy4TP0DBD2RhtKpol.jpg", "http://image.tmdb.org/t/p/w500//////w1WqcS6hT0PUWC3adG37NSUOGX5.jpg"
    , "http://image.tmdb.org/t/p/w500//////vdP8OadGAAIlfI1Ri0Y73Unphl7.jpg", "http://image.tmdb.org/t/p/w500//////xDEOxA01480uLTWuvQCw61VmDBt.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        chosenColor=R.color.colorPrimary;
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Detail View");
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));

       // collapsingToolbar.setBackgroundColor(R.color.transparentBlack);

        crazy=new Random();
        randomInt=crazy.nextInt(4);

        networkImageView = (NetworkImageView) findViewById(R.id.backdrop);
        Random crazy=new Random();

        String imageurl=urlList[crazy.nextInt(4)];

        imageLoader = CustomVolleyRequest.getInstance(this)
                .getImageLoader();
        imageLoader.get(imageurl, ImageLoader.getImageListener(networkImageView,
                R.drawable.whitehousedown, android.R.drawable
                        .ic_dialog_alert));
        networkImageView.setImageUrl(imageurl, imageLoader);


        //working for palette
        imageLoader.get(imageurl, new ImageLoader.ImageListener() {

            public void onErrorResponse(VolleyError arg0) {
                networkImageView.setImageResource(R.drawable.error_image); // set an error image if the download fails
                Toast.makeText(getApplicationContext(), "Cannot fetch movie poster", Toast.LENGTH_SHORT).show();
            }

            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    networkImageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                    networkImageView.setImageBitmap(response.getBitmap());
                    changeStatusAndActionBarColorUsingPalette();
                } else
                    networkImageView.setImageResource(R.drawable.movie_icon); // set the loading image while the download is in progress
            }
        });



    }


    private void changeStatusAndActionBarColorUsingPalette() {

        Bitmap bitmap = ((BitmapDrawable)networkImageView.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                //work with the palette here
                int defaultValue = 0x000000;
                chosenColor = palette.getDarkMutedColor(defaultValue);
                collapsingToolbar.setContentScrimColor(chosenColor);

                //changing the status bar color

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    int darkerColorChosen=getDarkerColor(chosenColor, 0.8f);
                    window.setStatusBarColor(darkerColorChosen);
                }

            }
        });


    }

    public static int getDarkerColor(int color, float factor) {

        //user factor less than 1.0f , try 0.8f
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }


}
