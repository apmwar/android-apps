package io.github.apmwar.textdetect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
    }

    public void detect(View v) {
        if(bitmap != null) {
            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
            FirebaseVisionTextRecognizer text = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

            text.processImage(image)
                    .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                        @Override
                        public void onSuccess(FirebaseVisionText result) {
                            processText(result);
                        }
                    })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    String s = e.getMessage();
                                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                                }
                            });

        } else {
            Toast.makeText(getApplicationContext(), "Bitmap is null", Toast.LENGTH_LONG).show();
        }
    }

    private void processText(FirebaseVisionText result) {
        List<FirebaseVisionText.TextBlock> blocks = result.getTextBlocks();
        if(blocks.size() == 0) {
            Toast.makeText(getApplicationContext(), "text not detected", Toast.LENGTH_LONG).show();
        } else {

            for(FirebaseVisionText.TextBlock block: result.getTextBlocks()){
                String textract = block.getText();
                textView.setText(textract);
            }
        }
    }

    public void pickImage(View v) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}