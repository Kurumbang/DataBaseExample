package com.example.bishal.database;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputData;
    Button addButton, deleteButton;
    TextView showInput;
    MyDataBaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputData = (EditText) findViewById(R.id.input_data);
        addButton = (Button) findViewById(R.id.add_button);
        deleteButton = (Button) findViewById(R.id.delete_button);
        showInput = (TextView) findViewById(R.id.show_input);


        //printDataBase();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products product = new Products(inputData.getText().toString());
                dbHandler = new MyDataBaseHandler(getApplicationContext());
                dbHandler.addProduct(product);
                dbHandler.close();
                printDataBase();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputData.getText().toString();
                dbHandler = new MyDataBaseHandler(getApplicationContext());
                 dbHandler.deleteProduct(input);
                dbHandler.close();
                printDataBase();
            }
        });

    }

    public void printDataBase() {
        String dbString = dbHandler.dataBaseToString();
        showInput.setText(dbString);
        Log.v("Print", "I was here!!!!!!!!!!!");
        inputData.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
