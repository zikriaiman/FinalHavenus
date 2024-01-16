package community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalhavenus.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> post;
    private Context context;
    private PostDatabase db;

    public PostAdapter(List<Post> post, Context context) {
        this.post = post;
        this.context = context;
        this.db = new PostDatabase(context);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView contentTextView;
        public ImageView updateButton;
        public ImageView deleteButton;
        public ImageView userImageView;

        public PostViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            userImageView = itemView.findViewById(R.id.userImageView);  // Add this line
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {
        final Post postItem = post.get(position);
        holder.titleTextView.setText(postItem.getTitle());
        holder.contentTextView.setText(postItem.getContent());

        // Check if there is an image, and set it to the ImageView
        if (postItem.getImageBitmap() != null) {
            holder.userImageView.setImageBitmap(postItem.getImageBitmap());
        } else {
            holder.userImageView.setImageResource(R.drawable.ic_launcher_foreground); // Set a default image
        }
        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), UpdateActivity.class);
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

    public void refreshData(List<Post> newPost) {
        post = newPost;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return post.size();
    }
}
