package safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.finalhavenus.R;
import androidx.fragment.app.FragmentManager;

import emergency.LocationSharingActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Module2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Module2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Module2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Module2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Module2Fragment newInstance(String param1, String param2) {
        Module2Fragment fragment = new Module2Fragment();
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
        return inflater.inflate(R.layout.fragment_module2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Safety Plan
        Button buttonSafetyPlans = view.findViewById(R.id.buttonSafetyPlans);
        View.OnClickListener OCLbuttonSafetyPlans = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), homeSafetyPlan.class);
                startActivity(i);
            }
        };
        buttonSafetyPlans.setOnClickListener(OCLbuttonSafetyPlans);

        // Safety Check-In
        /*Button buttonSafetyCheckIn = view.findViewById(R.id.buttonSafetyCheckIn);
        View.OnClickListener OCLbuttonSafetyCheckIn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        };
        buttonSafetyCheckIn.setOnClickListener(OCLbuttonSafetyCheckIn);*/

        // SOS Button
        Button buttonSelfDef = view.findViewById(R.id.buttonSelfDef);
        View.OnClickListener OCLbuttonSelfDef = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(view).navigate(R.id.DestSelfDefence);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                SelfDefenceTechniquesFragment fragment = new SelfDefenceTechniquesFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment); // Use your container ID
                fragmentTransaction.addToBackStack(null); // Add this line if you want to enable back navigation
                fragmentTransaction.commit();
            }
        };
        buttonSelfDef.setOnClickListener(OCLbuttonSelfDef);
    }
}