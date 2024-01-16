package community;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.finalhavenus.databinding.ActivityMainjBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainjBinding binding;
    private PostDatabase db;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainjBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new PostDatabase(this);
        postAdapter = new PostAdapter(db.getAllNotes(), this);

        binding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.postRecyclerView.setAdapter(postAdapter);

        binding.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPost.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        postAdapter.refreshData(db.getAllNotes());
    }
}
