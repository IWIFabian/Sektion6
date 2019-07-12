package com.example.sektion2;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private Menu menu;
    public static final String EXTRA_NUMBER = MainActivity.EXTRA_NUMBER;

    int Inumber = 0;

    EditText eTnumber;
    Intent intent;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
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
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.btnWeiter);
        button.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKaffe();
            }
        });
    }

    public void openKaffe() {

        eTnumber =(EditText) findViewById(R.id.eTnumber);
        Inumber = Integer.parseInt(eTnumber.getText().toString());

        intent = new Intent(this, Kaffe.class);
        intent.putExtra(EXTRA_NUMBER, Inumber);

        startActivity(intent);
    }


}