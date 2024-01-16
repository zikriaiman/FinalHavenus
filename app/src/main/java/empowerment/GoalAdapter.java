package empowerment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalhavenus.R;

import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.PostViewHolder> {

    private List<Goal> post;
    private Context context;
    private GoalDatabase db;
    public GoalAdapter(List<Goal> post, Context context) {
        this.post = post;
        this.context = context;
        this.db = new GoalDatabase(context);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView contentTextView;
        public ImageView updateButton;
        public ImageView deleteButton;
        public CheckBox checkBox;



        public PostViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_goal, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {
        final Goal postItem = post.get(position);
        holder.titleTextView.setText(postItem.getTitle());
        holder.contentTextView.setText(postItem.getContent());

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), UpdateGoal.class);
                intent.putExtra("post_id", postItem.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deletePost(postItem.getId());
                refreshData(db.getAllNotes());
                Toast.makeText(holder.itemView.getContext(), "Post Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refreshData(List<Goal> newPost) {
        post = newPost;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return post.size();
    }
}
