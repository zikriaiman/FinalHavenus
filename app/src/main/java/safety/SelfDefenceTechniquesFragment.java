package safety;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.finalhavenus.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelfDefenceTechniquesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelfDefenceTechniquesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelfDefenceTechniquesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelfDefenceTechniquesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelfDefenceTechniquesFragment newInstance(String param1, String param2) {
        SelfDefenceTechniquesFragment fragment = new SelfDefenceTechniquesFragment();
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
        return inflater.inflate(R.layout.fragment_self_defence_techniques, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button buttonReadyStance = view.findViewById(R.id.buttonReadyStance);
        View.OnClickListener OCLReadyStance = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TechniqueOneFragment fragment = new TechniqueOneFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        buttonReadyStance.setOnClickListener(OCLReadyStance);

        Button buttonPalmHeelStrike = view.findViewById(R.id.buttonPalmHeelStrike);
        View.OnClickListener OCLPalmHeelStrike = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TechniqueTwoFragment fragment = new TechniqueTwoFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        buttonPalmHeelStrike.setOnClickListener(OCLPalmHeelStrike);

        Button buttonFrontKick = view.findViewById(R.id.buttonFrontKick);
        View.OnClickListener OCLFrontKick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TechniqueThreeFragment fragment = new TechniqueThreeFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        buttonFrontKick.setOnClickListener(OCLFrontKick);

        Button buttonHammerfistPunch = view.findViewById(R.id.buttonHammerfistPunch);
        View.OnClickListener OCLHammerfistPunch = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TechniqueFourFragment fragment = new TechniqueFourFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        buttonHammerfistPunch.setOnClickListener(OCLHammerfistPunch);
    }
}
