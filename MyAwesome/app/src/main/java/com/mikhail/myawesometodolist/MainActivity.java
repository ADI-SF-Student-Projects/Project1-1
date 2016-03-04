package com.mikhail.myawesometodolist;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView toDoListHeaderFirst;
    EditText firstActivityEditText;
    ListView firstActivityListView;
    Intent detailActivityTransition;
    ArrayList<String> mStringList;
    ArrayAdapter mAdapter;
    static final int requestCodeFromDetailActivity = 1; // give some value???


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toDoListHeaderFirst = (TextView) findViewById(R.id.header);
        firstActivityEditText = (EditText) findViewById(R.id.editText);
        firstActivityListView = (ListView) findViewById(R.id.list);
        detailActivityTransition = new Intent(this, DetailActivity.class);
        mStringList = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList);
        firstActivityListView.setAdapter(mAdapter);

        setListeners();
//        onActivityResult();

    }

    private void setListeners() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("setOnClickListener", "Fab button Clicked");

                String takeText = firstActivityEditText.getText().toString();
                Log.d("setText", "Null");
                Log.d("setText", "editTextToString:n " + firstActivityEditText.toString());


                if (mStringList.size() >= 10) {
                    Toast.makeText(MainActivity.this, "You've reached maximum To-Do-Lists!!!" +
                            "\n                 " +
                            "BUY FULL VERSION!!!", Toast.LENGTH_SHORT).show();
                } else if (firstActivityEditText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (firstActivityEditText.getText().toString().length() > 40) {
                    Toast.makeText(MainActivity.this, "Too Long!!!", Toast.LENGTH_SHORT).show();
                } else {
                    mStringList.add(takeText);
                    mAdapter.notifyDataSetChanged();
                    firstActivityEditText.setText(null);

                }

            }
        });
        firstActivityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String myDataFromToDoList = mStringList.get(position);

                detailActivityTransition.putExtra("data", myDataFromToDoList);
                startActivityForResult(detailActivityTransition, requestCodeFromDetailActivity);

            }
        });

        firstActivityListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mStringList.remove(position);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "DELETED", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

//    public void onActivityResult(){
//
//        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
//        pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
//        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
//
//    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == requestCodeFromDetailActivity) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                detailActivityTransition.getStringExtra("Result");

                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }
    }

}


