package tarikalovebird.money;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import tarikalovebird.money.Picture.ImagePicker;
import tarikalovebird.money.Picture.Mypic;

public class Add_Picture extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private Button SavePic;
    private WallpaperManager iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_picture);
        imageView = (ImageView) findViewById(R.id.image_view);
        textView = (TextView) findViewById(R.id.image_stream_indicator);
        // width and height will be at least 600px long (optional).
        ImagePicker.setMinQuality(600, 600);



        SavePic=(Button)findViewById(R.id.SavePic);

        SavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();

            }
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Bitmap mybitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        if (mybitmap != null) {

            Mypic.bitmap = mybitmap;
            imageView.setImageBitmap(mybitmap);


            //return bitmap;

        }
        InputStream is = ImagePicker.getInputStreamFromResult(this, requestCode, resultCode, data);
        if (is != null) {
            textView.setText("Selected Picture!");

            try {
                is.close();
            } catch (IOException ex) {
                // ignore
            }
        } else {
            textView.setText("Failed to Select Picture!");
        }
        super.onActivityResult(requestCode, resultCode, data);


    }


    public void onPickImage(View view) {
        ImagePicker.pickImage(this, "Select your image:");
    }


    public static void writePhotoPng(Bitmap data, String pathName) {
        File file = new File(pathName);
        try {
            //file.createNewFile();
            // BufferedOutputStream os = new BufferedOutputStream(
            // new FileOutputStream(file));

            FileOutputStream os = new FileOutputStream(file);
            data.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();


        } catch (Exception e) {
            //e.printStackTrace();
        }
    }



}