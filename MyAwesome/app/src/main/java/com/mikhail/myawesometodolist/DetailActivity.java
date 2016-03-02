package com.mikhail.myawesometodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    TextView toDoListHeaderSecond;
    EditText secondActivityEditText;
    ListView secondActivityListView;
    Intent mainActivityIntent;
    ArrayList<String> mStringListDetailActivity;
    ArrayAdapter mAdapterDetailActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        toDoListHeaderSecond = (TextView) findViewById(R.id.header1);
        secondActivityEditText = (EditText) findViewById(R.id.editText1);
        secondActivityListView = (ListView) findViewById(R.id.list1);
        mStringListDetailActivity = new ArrayList<>();
        mAdapterDetailActivity = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringListDetailActivity);
        secondActivityListView.setAdapter(mAdapterDetailActivity);
        mainActivityIntent = getIntent();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                String takeText = secondActivityEditText.getText().toString();
//                secondActivityEditText.setText(null);


                if (mStringListDetailActivity.size() >= 15) {
                    Toast.makeText(DetailActivity.this, "You've reached maximum To-Do-Lists", Toast.LENGTH_SHORT).show();
                } else if (secondActivityEditText.getText().toString().isEmpty()) {
                    Toast.makeText(DetailActivity.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    mStringListDetailActivity.add(takeText);
                    mAdapterDetailActivity.notifyDataSetChanged();
                }
            }
        });
        setDetailActivityListeners();
    }

//    private void changeHeader(){
//        toDoListHeaderSecond.getText(findViewById(long id));
//    }

    private void setDetailActivityListeners() {

        secondActivityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String toDoS = textView.getText().toString();

            }
        });

        secondActivityListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mStringListDetailActivity.remove(position);
                mAdapterDetailActivity.notifyDataSetChanged();
                Toast.makeText(DetailActivity.this, "DELETED", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }


}
