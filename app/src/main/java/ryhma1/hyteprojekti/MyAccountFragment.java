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
public class MyAccountFragment extends Fragment {


    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        DatabaseHelper db = new DatabaseHelper(getActivity());

        User user = db.getUser(1);
        String name = user.getFirstName();
        String lastname = user.getLastName();
        int height = user.getHeight();
        int weight = user.getWeight();
        int points = user.getUserPoints();
        int level = user.getLevel();
        double bound1 = user.getBound1();
        double bound2 = user.getBound2();


        TextView userData = (TextView) view.findViewById(R.id.userData) ;


        userData.setText("Käyttäjä: "+name+ " "+lastname+"\n" +
                "Pituus: "+height+"\n" +
                "Paino: "+weight+"\n" +
                "Pisteet: "+points+"\n" +
                "Taso: "+level+"\n" +
                "Rajat: "+bound1+" - "+bound2+" ");




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
