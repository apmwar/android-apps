package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int a_team = 0;
    int b_team = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void threeA(android.view.View view) {
        a_team += 3;
        displayTeamAScore();
    }

    public void twoA(android.view.View view) {
        a_team += 2;
        displayTeamAScore();
    }

    public void oneA(android.view.View view) {
        a_team += 1;
        displayTeamAScore();
    }

    public void threeB(android.view.View view) {
        b_team += 3;
        displayTeamBScore();
    }

    public void twoB(android.view.View view) {
        b_team += 2;
        displayTeamBScore();
    }

    public void oneB(android.view.View view) {
        b_team += 1;
        displayTeamBScore();
    }

    public void displayTeamAScore() {
        TextView scoreA = (TextView) findViewById(R.id.team_a_score);
        scoreA.setText(String.valueOf(a_team));
    }


    public void displayTeamBScore() {
        TextView scoreA = (TextView) findViewById(R.id.team_b_score);
        scoreA.setText(String.valueOf(b_team));
    }

    public void resetall(android.view.View view) {
        a_team = 0;
        b_team = 0;
        displayTeamAScore();
        displayTeamBScore();
    }
}
