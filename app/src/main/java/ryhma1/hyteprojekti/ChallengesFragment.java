package ryhma1.hyteprojekti;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment {


    public ChallengesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_challenges, container, false);
        DatabaseHelper db = new DatabaseHelper(getActivity());

        Challenge challenge = db.getChallenge(1);
        String name = challenge.getChallengename();
        int kerrat = challenge.getActivity();
        int pisteet = challenge.getChallengePoints();
        int taso = challenge.getChallengeLevel();
        TextView haaste = (TextView) view.findViewById(R.id.haaste1) ;

        haaste.setText("Haaste: "+name+"\n" +
                "Mittauskerrat: "+kerrat+"\n" +
                "Pisteet: "+pisteet+"\n" +
                "Taso: "+taso+"");

        return view;
    }





}
