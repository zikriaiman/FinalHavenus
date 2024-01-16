package empowerment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.finalhavenus.databinding.ActivityAddListBinding;

import java.io.IOException;

public class AddList extends AppCompatActivity {

    private ActivityAddListBinding binding;
    private GoalDatabase db;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddListBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        db = new GoalDatabase(this);


        // Save the post when saveButton is clicked
        binding.saveButton.setOnClickListener(v -> {
            String title = binding.titleeditText.getText().toString();
            String content = binding.contenteditText.getText().toString();

            if (selectedImageBitmap != null) {
                // You can save or process the selectedImageBitmap here
            }

            Goal post = new Goal(0, title, content);
            db.insertPost(post);

            finish();
            Toast.makeText(this, "Posted!", Toast.LENGTH_SHORT).show();
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            handleSelectedImage(data);
        }
    }

    private void handleSelectedImage(Intent data) {
        Uri imageUri = data.getData();
        try {
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
