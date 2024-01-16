package empowerment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.finalhavenus.databinding.ActivityGoalBinding;


public class GoalTracker extends AppCompatActivity {

    private ActivityGoalBinding binding;
    private GoalDatabase db;
    private GoalAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new GoalDatabase(this);
        postAdapter = new GoalAdapter(db.getAllNotes(), this);

        binding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.postRecyclerView.setAdapter(postAdapter);

        binding.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddList.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        postAdapter.refreshData(db.getAllNotes());
    }
}
