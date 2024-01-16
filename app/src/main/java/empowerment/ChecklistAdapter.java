package empowerment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalhavenus.R;

import java.util.List;

// ChecklistAdapter.java
public class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.ViewHolder> {

    private List<ChecklistItem> checklist;

    public ChecklistAdapter(List<ChecklistItem> checklist) {
        this.checklist = checklist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checklist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChecklistItem item = checklist.get(position);
        holder.checkBox.setChecked(item.isChecked());
        holder.itemText.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return checklist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            itemText = itemView.findViewById(R.id.itemText);
        }
    }
}

