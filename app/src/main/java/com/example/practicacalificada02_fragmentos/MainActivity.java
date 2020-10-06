package com.example.practicacalificada02_fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements SimpleFragment.OnFragmentInteractionListener {


    private Button mButton;
    private boolean isFragmentDisplayed = false;
    private int mRadioButtonChoice = 2;

    private int votosMalefica = 0;
    private int votosCenicienta = 0;
    private int totalVotos = 0;
    static final String STATE_FRAGMENT = "state_of_fragment";

    private ProgressBar pgsBarMaleficent;
    private ProgressBar pgsBarCinderella;

    private TextView txtVotosMaleficent;
    private TextView txtVotosCinderella;
    private TextView txtVotosTotales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.open_button);


        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton.setText(R.string.close);
            }
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });


    }

    public void displayFragment() {
        SimpleFragment fragment = SimpleFragment.newInstance(mRadioButtonChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container,
                fragment).addToBackStack(null).commit();
// Update the Button text.
        mButton.setText(R.string.close);
// Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;

    }

    public void closeFragment() {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        // Update the Button text.
        mButton.setText(R.string.open);
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }



    @Override
    public void onRadioButtonChoice(int choice) {
    }




    @Override
    public void onButtonChoice(int choice) {
        pgsBarMaleficent = (ProgressBar) findViewById(R.id.pgb_Malefica);

        pgsBarCinderella = (ProgressBar) findViewById(R.id.pgb_Cenicienta);


        txtVotosMaleficent = (TextView) findViewById(R.id.txt_VotosMaleficent);
        txtVotosCinderella = (TextView) findViewById(R.id.txtVotosCindelerella);
        txtVotosTotales = (TextView) findViewById(R.id.txt_totalVotos);

        totalVotos++;
        txtVotosTotales.setText("Total de votos: " + totalVotos);
        if(choice==0)
        {
            votosMalefica++;
            int pg0 = (votosMalefica * 100) / totalVotos;
            int pg1 = (votosCenicienta * 100) / totalVotos;
            pgsBarMaleficent.setProgress(pg0);
            pgsBarCinderella.setProgress(pg1);
            txtVotosMaleficent.setText("Votos: " + votosMalefica);
        }
        else if(choice==1)
        {
            votosCenicienta++;
            int pg0 = (votosMalefica * 100) / totalVotos;
            int pg1 = (votosCenicienta * 100) / totalVotos;
            pgsBarMaleficent.setProgress(pg0);
            pgsBarCinderella.setProgress(pg1);
            txtVotosCinderella.setText("Votos: " + votosCenicienta);
        }

    }


}