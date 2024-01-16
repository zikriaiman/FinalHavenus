package empowerment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalhavenus.databinding.ActivityGoalUpdateBinding;
public class UpdateGoal extends AppCompatActivity {

    private ActivityGoalUpdateBinding binding;
    private GoalDatabase db;
    private int postId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGoalUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new GoalDatabase(this);

        postId = getIntent().getIntExtra("post_id", -1);
        if (postId == -1) {
            finish();
            return;
        }

        Goal post = db.getPostByID(postId);
        binding.updatetitleeditText.setText(post.getTitle());
        binding.updatecontenteditText.setText(post.getContent());

        binding.updateSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = binding.updatetitleeditText.getText().toString();
                String newContent = binding.updatecontenteditText.getText().toString();
                Goal updatePost = new Goal(postId, newTitle, newContent);
                db.updatePost(updatePost);
                finish();
                Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
