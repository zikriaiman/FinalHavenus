package community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.finalhavenus.databinding.ActivityAddPostBinding;

public class AddPost extends AppCompatActivity {

    private ActivityAddPostBinding binding;
    private PostDatabase db;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new PostDatabase(this);

        // Open gallery when image saveButton is clicked
        binding.imagesaveButton.setOnClickListener(v -> openGallery());

        // Save the post when saveButton is clicked
        binding.saveButton.setOnClickListener(v -> {
            String title = binding.titleeditText.getText().toString();
            String content = binding.contenteditText.getText().toString();

            if (selectedImageBitmap != null) {
                // You can save or process the selectedImageBitmap here
            }

            Post post = new Post(0, title, content);
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

        Glide.with(this)
                .asBitmap()
                .load(imageUri)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        // Set the loaded bitmap to the ImageView
                        binding.userImageView.setImageBitmap(resource);
                        selectedImageBitmap = resource; // Save the bitmap for later use
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Placeholder, if needed
                    }
                });
    }
}
