package com.example.sektion2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Kaffe extends AppCompatActivity implements View.OnClickListener{

    public SeekBar sb1;
    public TextView tvKaffeAnzeige;
    public TextView tvKosten;
    public CheckBox cbSahne;
    public CheckBox cbKeks;
    public Button btnBestellen;
    /*  Variablen deklarienen  */
    int start = 1;
    int valuemax = 10;
    int value = 1;
    int SJa = 0;
    int KJa = 0;

    double KaKosten = 1.5;
    double SaKosten = 0.5;
    double KeKosten = 0.75;
    double Kosten;

    String End = ": ";
    String Kaffe = "Kaffe";
    String Keks = " mit Keks";
    String Sahne = " mit Sahne";
    String und = " und";
    String Text = Kaffe + End;

    public Menu menu2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu2 = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = "https://www.iwi.uni-hannover.de/institut1.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaffe);

        sb1 = (SeekBar) findViewById(R.id.seekBar);
        tvKaffeAnzeige = (TextView) findViewById(R.id.KaffeAnzeige);
        tvKosten = (TextView) findViewById(R.id.Kosten);
        cbSahne = (CheckBox) findViewById(R.id.sahne);
        cbKeks = (CheckBox) findViewById(R.id.keks);
        btnBestellen = (Button) findViewById(R.id.btnBestellen);
        TextView tvTnumber = (TextView) findViewById(R.id.tisch);

        int number = getIntent().getIntExtra(MainActivity.EXTRA_NUMBER, 0);

        sb1.setMax(valuemax);
        sb1.setProgress(start);
        Kosten = KaKosten * start;

        tvTnumber.setText("Tischnummer: " + number);
        tvKaffeAnzeige.setText(Text + Integer.toString(start));
        tvKosten.setText(Kosten + " €");

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = sb1.getProgress();
                tvKaffeAnzeige.setText(Text + Integer.toString(value));
                Kosten = KaKosten * value;
                tvKosten.setText(Kosten + " €");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnBestellen.setOnClickListener(this);

        cbSahne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked){
                    KaKosten = KaKosten + SaKosten;
                    Kosten = KaKosten * value;
                    Text = Kaffe + Sahne + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                    tvKosten.setText(Kosten + " €");
                    SJa = 1;

                }
                else{
                    KaKosten = KaKosten - SaKosten;
                    Kosten = KaKosten * value;
                    Text = Kaffe + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                    tvKosten.setText(Kosten + " €");
                    SJa = 0;
                }
                if (SJa == 1 && KJa == 1){
                    Text = Kaffe + Sahne + und + Keks + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                }
                else if (KJa == 1){
                    Text = Kaffe + Keks + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                }

            }
        });

        cbKeks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked){
                    KaKosten = KaKosten + KeKosten;
                    Kosten = KaKosten * value;
                    Text = Kaffe + Keks + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                    tvKosten.setText(Kosten + " €");
                    KJa = 1;
                }
                else{
                    KaKosten = KaKosten - KeKosten;
                    Kosten = KaKosten * value;
                    Text = Kaffe + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                    tvKosten.setText(Kosten + " €");
                    KJa = 0;
                }
                if (SJa == 1 && KJa == 1){
                    Text = Kaffe + Sahne + und + Keks + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                }
                else if (SJa == 1){
                    Text = Kaffe + Sahne + End;
                    tvKaffeAnzeige.setText(Text + Integer.toString(value));
                }

            }

        });


    }

    @Override
    public void onClick(View view) {

        Toast.makeText(this, "Ihre Bestellung wurde angenommen.", Toast.LENGTH_LONG).show();

    }
}
