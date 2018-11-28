package hr.stips.mobilnetehnologije.Predavanja.Predavanje5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import hr.stips.mobilnetehnologije.R;

public class Predavanje5_MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeThumbnail();
            }
        });

        Button button_fullimage = (Button)findViewById(R.id.button_fullimage);
        button_fullimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
    }

    Uri imageUri;
    private void takeThumbnail() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(intent);umjesto ovoga se koristi start activity for result
        startActivityForResult(intent,0);


    }



    private void takePhoto(){
        try {

            //iskljucuju strickt mode koji komplicira kod :))
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());

            File image = File.createTempFile("MyPhoto", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            imageUri = Uri.fromFile(image);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//sajemo informaciju gdje ce se spremiti slika
            startActivityForResult(intent,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//sistemski se poziva cim se zavrsi intent
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bitmap image = (Bitmap)data.getExtras().get("data");
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(image);
        }

        //hvatanje slike i spremanje u direktorij predvidejn za nju
        if(resultCode == Activity.RESULT_OK && requestCode == 1) {
            File imageFile = new File(imageUri.getPath());//trenutno je binary, treba ga convertati u image
            Bitmap imageBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            String a = imageFile.getAbsolutePath();
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(imageBitmap);
        }
    }


}
