package com.example.sarthak.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    TextView go;
    TextView timer;
    GridLayout options;
    TextView score;
    Button playagain;
    TextView question;
    boolean gameison;
    CountDownTimer gametimer;
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    int answer;
    TextView finalscore;
    MediaPlayer mplayer;

    int tapped;
    int loc;


    public void updateques()
    {
        if(gameison) {
            int num1;
            int num2;
            int answer;
            Random rand = new Random();
            num1 = rand.nextInt(50);
            num2 = rand.nextInt(50);
            answer = num1 + num2;
            question.setText((num1 + " + " + num2));


            loc = rand.nextInt(4);
            int i;
            int wrong;
            ArrayList<Integer> ans = new ArrayList<Integer>();

            for (i = 0; i < 4; i++) {
                if (i == loc) {
                    ans.add(answer);
                } else {
                    wrong = rand.nextInt(100);
                    if (wrong == answer) {
                        ans.add(rand.nextInt(100));
                    } else {
                        ans.add(wrong);
                    }
                }
            }

            a.setText(Integer.toString(ans.get(0)));
            b.setText(Integer.toString(ans.get(1)));
            c.setText(Integer.toString(ans.get(2)));
            d.setText(Integer.toString(ans.get(3)));

        }
        else {
            showscore();
        }
    }

    public void check(View view)
    {
        if(gameison) {
            finalscore.setVisibility(View.VISIBLE);
            TextView kaunsa = (TextView) view;
            tapped = Integer.parseInt(kaunsa.getTag().toString());
            mplayer.start();
            if (tapped == loc + 1) {

                    finalscore.setText("CORRECT!");

                    upadteScoreplus();
                    updateques();

            }
            else {

                    finalscore.setText("WRONG");
                    updatescoreminus();
                    updateques();

            }

        }
        else
        {
            showscore();
        }
    }

    public void upadteScoreplus()
    {
        int currentScore;
        currentScore = Integer.parseInt((score.getText().toString()));
        currentScore++;
        score.setText(Integer.toString(currentScore));
    }

    public void updatescoreminus()
    {
        int currentscore;
        currentscore = Integer.parseInt((score.getText().toString()));
        currentscore--;
        score.setText(Integer.toString(currentscore));
    }
    public void showscore()
    {
        int fscore;
        fscore = Integer.parseInt((score.getText().toString()));
        finalscore.setText("YOUR SCORE: "+Integer.toString(fscore));
        finalscore.setVisibility(View.VISIBLE);
    }

    public void play(View view)
    {

        go.setVisibility(GONE);
        playagain.setVisibility(GONE);
        timer.setVisibility(View.VISIBLE);
        options.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        gametimer.start();
        gameison=true;

        finalscore.setVisibility(GONE);
        score.setText("0");
        updateques();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = findViewById(R.id.go);
        timer = findViewById(R.id.timer);
        options = findViewById(R.id.options);
        score = findViewById(R.id.score);
        playagain = findViewById(R.id.playagain);
        question = findViewById(R.id.question);
        a = (TextView) findViewById(R.id.opt1);
        b = (TextView) findViewById(R.id.opt2);
        c = (TextView) findViewById(R.id.opt3);
        d = (TextView) findViewById(R.id.opt4);
        finalscore = (TextView) findViewById(R.id.finalscore);
        mplayer = (MediaPlayer.create(this, R.raw.tick));


        gameison=true;

        timer.setVisibility(GONE);
        options.setVisibility(GONE);
        playagain.setVisibility(GONE);
        score.setVisibility(GONE);
        question.setVisibility(GONE);
        finalscore.setVisibility(GONE);

       gametimer = new CountDownTimer(30100, 1000)
        {
            @Override
            public void onTick(long millisecondsleft)
            {
                timer.setText(Integer.toString((int) (millisecondsleft/1000)));
            }
            @Override
            public void onFinish()
            {
                playagain.setVisibility(View.VISIBLE);
                timer.setText("0");
                gameison=false;
                showscore();
            }


        };

       updateques();




    }
}
