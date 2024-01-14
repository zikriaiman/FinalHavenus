package empowerment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalhavenus.R;

import emergency.LocationSharingActivity;
import emergency.SOSButtonActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Module4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Module4Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Module4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Module4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Module4Fragment newInstance(String param1, String param2) {
        Module4Fragment fragment = new Module4Fragment();
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
        return inflater.inflate(R.layout.fragment_module4, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Empowerment Resources
        Button buttonEmpowermentResources = view.findViewById(R.id.buttonEmpowermentResources);
        View.OnClickListener OCLbuttonEmpowermentResources = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), empResc.class);
                startActivity(i);
            }
        };
        buttonEmpowermentResources.setOnClickListener(OCLbuttonEmpowermentResources);

        // Menstrual Health Game
        Button buttonMenstrualHealthGame = view.findViewById(R.id.buttonMenstrualHealthGame);
        View.OnClickListener OCLbuttonMenstrualHealthGame = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MenstrualHealthGame.class);
                startActivity(i);
            }
        };
        buttonMenstrualHealthGame.setOnClickListener(OCLbuttonMenstrualHealthGame);
    }
}