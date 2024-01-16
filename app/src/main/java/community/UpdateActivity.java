package community;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.finalhavenus.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    private ActivityUpdateBinding binding;
    private PostDatabase db;
    private int postId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new PostDatabase(this);

        postId = getIntent().getIntExtra("post_id", -1);
        if (postId == -1) {
            finish();
            return;
        }

        Post post = db.getPostByID(postId);
        binding.updatetitleeditText.setText(post.getTitle());
        binding.updatecontenteditText.setText(post.getContent());

        binding.updateSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = binding.updatetitleeditText.getText().toString();
                String newContent = binding.updatecontenteditText.getText().toString();
                Post updatePost = new Post(postId, newTitle, newContent);
                db.updatePost(updatePost);
                finish();
                Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_SHORT).show();
            }
        });

        // Load the image using the post's image path
        Bitmap imagePath = post.getImageBitmap();
        if (imagePath != null) {
            // Use Glide or Picasso to load the image into the ImageView
            Glide.with(this).load(imagePath).into(binding.updateImageView);
            // Picasso.with(this).load(imagePath).into(binding.updateImageView);
        }
    }
}
