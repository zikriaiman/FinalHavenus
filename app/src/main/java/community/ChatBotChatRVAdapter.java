package community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalhavenus.R;

import java.util.ArrayList;

public class ChatBotChatRVAdapter extends RecyclerView.Adapter {

    private ArrayList<ChatBotChatsModel> chatBotChatsModelArrayList;
    private Context context;

    public ChatBotChatRVAdapter(ArrayList<ChatBotChatsModel> chatBotChatsModelArrayList, Context context) {
        this.chatBotChatsModelArrayList = chatBotChatsModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbot_user_msg_rv_item, parent, false);
                return new UserViewHolder(view );

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbot_bot_msg_rv_item, parent, false);
                return  new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatBotChatsModel chatBotChatsModel = chatBotChatsModelArrayList.get(position);
        switch (chatBotChatsModel.getSender()) {

            case "user":
                ((UserViewHolder)holder).userTV.setText(chatBotChatsModel.getMessage());
                break;

            case "bot":
                ((BotViewHolder)holder).botMsgTV.setText(chatBotChatsModel.getMessage());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatBotChatsModelArrayList.get(position).getSender()) {

            case "user":
                return 0;

            case "bot":
                return 1;

            default:
                return -1;

        }
    }

    @Override
    public int getItemCount() {
        return chatBotChatsModelArrayList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView userTV;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);
        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder {

        TextView botMsgTV;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTV = itemView.findViewById(R.id.idTVBot);
        }
    }


}
