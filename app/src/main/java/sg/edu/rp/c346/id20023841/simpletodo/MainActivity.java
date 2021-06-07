package sg.edu.rp.c346.id20023841.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNewToDo;
    Button btnAdd, btnDel;
    Button btnClearAll;
    ListView lvTodo;
    Spinner spinner;

    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNewToDo = findViewById(R.id.editNewToDo);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClearAll = findViewById(R.id.buttonClearItem);
        lvTodo = findViewById(R.id.listViewToDo);
        alTodo = new ArrayList<>();

        btnDel = findViewById(R.id.buttonDeleteItem);
        spinner = findViewById(R.id.spn);

        aaTodo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = etNewToDo.getText().toString();
                alTodo.add(newTodo);
                aaTodo.notifyDataSetChanged();
//                etNewToDo.setText(null);
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int todoNum = alTodo.size();
                    alTodo.clear();
                    aaTodo.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTodo.size() == 0) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(etNewToDo.getText().toString()) >= alTodo.size()) {
                    Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                } else {
                    alTodo.remove(Integer.parseInt(etNewToDo.getText().toString()));
                }
               aaTodo.notifyDataSetChanged();
            }
            });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    btnAdd.setEnabled(true);
                    btnDel.setEnabled(false);
                    etNewToDo.setHint("Type in a new task here");
                }
                else if(position==1){
                    btnAdd.setEnabled(false);
                    btnDel.setEnabled(true);
                    etNewToDo.setHint("Type in the index of the task to be removed");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {  }
        });
    }

}