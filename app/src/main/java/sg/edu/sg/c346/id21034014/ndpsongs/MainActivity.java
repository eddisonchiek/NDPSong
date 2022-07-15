package sg.edu.sg.c346.id21034014.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnShow;
    EditText song;
    EditText singer;
    EditText year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        song = findViewById(R.id.title);
        singer = findViewById(R.id.singers);
        year = findViewById(R.id.year);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1 = song.getText().toString();
                String data2 = singer.getText().toString();
                String data3 = year.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(data1);
                long inserted_id = dbh.insertNote(data2);
                long inserted_id = dbh.insertNote(data3);

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                Intent i = new Intent(MainActivity.this,
                        showSongs.class);

                startActivity(i);

            }
        });



    }

}