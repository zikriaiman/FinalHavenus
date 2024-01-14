package community;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalhavenus.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBotMainActivity extends AppCompatActivity {

    private RecyclerView chatsRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<ChatBotChatsModel> chatBotChatsModelArrayList;
    private ChatBotChatRVAdapter chatBotChatRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot_activity_main);
        chatsRV = findViewById(R.id.idRVChats);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        sendMsgFAB = findViewById(R.id.idFABSend);
        chatBotChatsModelArrayList = new ArrayList<>();
        chatBotChatRVAdapter = new ChatBotChatRVAdapter(chatBotChatsModelArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatBotChatRVAdapter);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMsgEdt.getText().toString().isEmpty()) {
                    Toast.makeText(ChatBotMainActivity.this, "Please enter your message", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");
            }
        });
    }

    private void getResponse(String message) {
        chatBotChatsModelArrayList.add(new ChatBotChatsModel(message, USER_KEY));
        chatBotChatRVAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=179974&key=3oZyeBuQkfNhebGm&uid=[uid]&msg=" + message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatBotRetrofitAPI chatBotRetrofitAPI = retrofit.create(ChatBotRetrofitAPI.class);
        Call<ChatBotMsgModel> call = chatBotRetrofitAPI.getMessage(url);
        call.enqueue(new Callback<ChatBotMsgModel>() {
            @Override
            public void onResponse(Call<ChatBotMsgModel> call, Response<ChatBotMsgModel> response) {
                if (response.isSuccessful()) {
                    ChatBotMsgModel model = response.body();
                    chatBotChatsModelArrayList.add(new ChatBotChatsModel(model.getCnt(), BOT_KEY));
                    chatBotChatRVAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ChatBotMsgModel> call, Throwable t) {
                chatBotChatsModelArrayList.add(new ChatBotChatsModel("Please revert your question", BOT_KEY));
                chatBotChatRVAdapter.notifyDataSetChanged();
            }
        });
    }


}