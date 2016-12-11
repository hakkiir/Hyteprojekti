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


        Challenge challenge2 = db.getChallenge(2);
        String name2 = challenge2.getChallengename();
        int kerrat2 = challenge2.getActivity();
        int pisteet2 = challenge2.getChallengePoints();
        int taso2 = challenge2.getChallengeLevel();
        TextView haaste2 = (TextView) view.findViewById(R.id.haaste2) ;

        Challenge challenge3 = db.getChallenge(3);
        String name3 = challenge3.getChallengename();
        int kerrat3 = challenge3.getActivity();
        int pisteet3 = challenge3.getChallengePoints();
        int taso3 = challenge3.getChallengeLevel();
        TextView haaste3 = (TextView) view.findViewById(R.id.haaste3) ;

        Challenge challenge4 = db.getChallenge(4);
        String name4 = challenge4.getChallengename();
        int kerrat4 = challenge4.getActivity();
        int pisteet4 = challenge4.getChallengePoints();
        int taso4 = challenge4.getChallengeLevel();
        TextView haaste4 = (TextView) view.findViewById(R.id.haaste4) ;

        Challenge challenge5 = db.getChallenge(5);
        String name5 = challenge5.getChallengename();
        int kerrat5 = challenge5.getActivity();
        int pisteet5 = challenge5.getChallengePoints();
        int taso5 = challenge5.getChallengeLevel();
        TextView haaste5 = (TextView) view.findViewById(R.id.haaste5) ;

        haaste.setText("Haaste: "+name+"\n" +
                "Mittauskerrat: "+kerrat+"\n" +
                "Pisteet: "+pisteet+"\n" +
                "Taso: "+taso+"");
        haaste2.setText("Haaste: "+name2+"\n" +
                "Mittauskerrat: "+kerrat2+"\n" +
                "Pisteet: "+pisteet2+"\n" +
                "Taso: "+taso2+"");
        haaste3.setText("Haaste: "+name3+"\n" +
                "Mittauskerrat: "+kerrat3+"\n" +
                "Pisteet: "+pisteet3+"\n" +
                "Taso: "+taso3+"");
        haaste4.setText("Haaste: "+name4+"\n" +
                "Mittauskerrat: "+kerrat4+"\n" +
                "Pisteet: "+pisteet4+"\n" +
                "Taso: "+taso4+"");
        haaste5.setText("Haaste: "+name5+"\n" +
                "Mittauskerrat: "+kerrat5+"\n" +
                "Pisteet: "+pisteet5+"\n" +
                "Taso: "+taso5+"");


         /*

        for (int i = 1; i < 6; i++ ){
            Challenge challenge = db.getChallenge(i);

            String name = challenge.getChallengename();
            int kerrat = challenge.getActivity();
            int pisteet = challenge.getChallengePoints();
            int taso = challenge.getChallengeLevel();
            if (i == 0) {
                TextView haaste = (TextView) view.findViewById(R.id.haaste1);
            }else if (i == 1){
                TextView haaste = (TextView) view.findViewById(R.id.haaste2);
            }else if (i == 2){
                TextView haaste = (TextView) view.findViewById(R.id.haaste3);
            }else if (i == 3){
                TextView haaste = (TextView) view.findViewById(R.id.haaste4);
            }else if (i == 4){
                TextView haaste = (TextView) view.findViewById(R.id.haaste5);
        }

        }
        */
        return view;
    }





}
