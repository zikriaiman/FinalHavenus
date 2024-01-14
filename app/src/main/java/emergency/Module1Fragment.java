package emergency;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalhavenus.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Module1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Module1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Module1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Module1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Module1Fragment newInstance(String param1, String param2) {
        Module1Fragment fragment = new Module1Fragment();
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
        return inflater.inflate(R.layout.fragment_module1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Emergency Contacts
        Button buttonEmergencyContacts = view.findViewById(R.id.buttonEmergencyContacts);
        View.OnClickListener OCLbuttonEmergencyContacts = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ContactActivity.class);
                startActivity(i);
            }
        };
        buttonEmergencyContacts.setOnClickListener(OCLbuttonEmergencyContacts);


        // Live Location
        Button buttonLiveLocation = view.findViewById(R.id.buttonLiveLocation);
        View.OnClickListener OCLbuttonLiveLocation = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LocationSharingActivity.class);
                startActivity(i);
            }
        };
        buttonLiveLocation.setOnClickListener(OCLbuttonLiveLocation);

        // SOS Button
        Button buttonSOS = view.findViewById(R.id.buttonSOS);
        View.OnClickListener OCLbuttonSOS = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SOSButtonActivity.class);
                startActivity(i);
            }
        };
        buttonSOS.setOnClickListener(OCLbuttonSOS);
    }
}