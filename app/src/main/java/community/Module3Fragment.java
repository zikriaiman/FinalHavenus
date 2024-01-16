package community;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalhavenus.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import emergency.SOSButtonActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Module3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Module3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView postRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    public Module3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Module3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Module3Fragment newInstance(String param1, String param2) {
        Module3Fragment fragment = new Module3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module3, container, false);

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Chat Bot
        Button buttonChatBot = view.findViewById(R.id.buttonChatBot);
        View.OnClickListener OCLbuttonChatBot = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ChatBotMainActivity.class);
                startActivity(i);
            }
        };
        buttonChatBot.setOnClickListener(OCLbuttonChatBot);


        // Journal
        Button buttonPersonalisedJournal = view.findViewById(R.id.buttonPersonalisedJournal);
        buttonPersonalisedJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}