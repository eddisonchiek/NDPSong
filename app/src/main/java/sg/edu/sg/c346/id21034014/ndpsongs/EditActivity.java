package sg.edu.sg.c346.id21034014.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class EditActivity extends AppCompatActivity {
    ListView  lv;
    ArrayList<Note> al;
    ArrayAdapter<Note> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        lv = findViewById(R.id.lv);


        //initialize the variables with UI here

        al = new ArrayList<Note>();
        aa = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = al.get(position);
                Intent i = new Intent(EditActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }
}