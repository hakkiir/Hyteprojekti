package ryhma1.hyteprojekti;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{
    EditText mittaustulos;
    Button addData;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final DatabaseHelper db = new DatabaseHelper(getActivity());

        addData = (Button)view.findViewById(R.id.addData);
        mittaustulos = (EditText)view.findViewById(R.id.mittaustulos);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted =  db.insertData(1, Double.parseDouble(mittaustulos.getText().toString()));
                if(isInserted = true){
                    Toast.makeText(getActivity(), "Data lähetetty", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getActivity(), "Datan lähetys epäonnistui", Toast.LENGTH_LONG).show();

            }
        });



        return view;
    }



   /* public void addData(){
        addData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper db = new DatabaseHelper(getActivity());
                        boolean isInserted =  db.insertData(1, Double.parseDouble(mittaustulos.getText().toString()));
                        if(isInserted = true){
                            Toast.makeText(getActivity(), "Data lähetetty", Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(getActivity(), "Datan lähetys epäonnistui", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }*/
}
