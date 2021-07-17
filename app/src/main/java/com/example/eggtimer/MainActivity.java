package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public void resettimer(){
        timer.setText("0:30");
        eggtimerseekbar.setProgress(30);
        countDownTimer.cancel();
        controllerbutton.setText("GO");
        eggtimerseekbar.setEnabled(true);
        timeractive=false;
    }
    SeekBar eggtimerseekbar;
    Button controllerbutton;
    boolean timeractive=false;
    TextView timer;
    CountDownTimer countDownTimer;
    public void updatetimer(int secondsleft){
        int minutes=(int) secondsleft/60;
        int seconds=secondsleft-minutes*60;
        String secondstring=Integer.toString(seconds);
        if (seconds<=9){
            secondstring="0"+secondstring;
        }
        timer.setText(Integer.toString(minutes)+":"+secondstring);
    }
    public void clicked(View view){
        if(timeractive==false) {
            timeractive=true;
            eggtimerseekbar.setEnabled(false);
            controllerbutton.setText("Stop");
            countDownTimer=new CountDownTimer(eggtimerseekbar.getProgress() * 1000 + 100, 1000) {
                public void onTick(long millisecondfinished) {
                    updatetimer((int) millisecondfinished / 1000);

                }

                public void onFinish() {
                    resettimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();


                }
            }.start();
        }else{
            resettimer();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eggtimerseekbar=(SeekBar)findViewById(R.id.eggtimerseekbar);
        timer=(TextView)findViewById(R.id.timer);
        eggtimerseekbar.setMax(600);
        eggtimerseekbar.setProgress(30);
        eggtimerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}