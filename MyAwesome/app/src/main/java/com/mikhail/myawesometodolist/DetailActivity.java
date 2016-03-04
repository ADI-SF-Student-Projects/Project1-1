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
    Intent toReturnBackMyDataToMainActivity;
    private ArrayList<String> mStringListDetailActivity;
    ArrayAdapter mAdapterDetailActivity;
    public int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        toDoListHeaderSecond = (TextView) findViewById(R.id.header1);
        secondActivityEditText = (EditText) findViewById(R.id.editText1);
        secondActivityListView = (ListView) findViewById(R.id.list1);
        mainActivityIntent = getIntent();

        mStringListDetailActivity = getDataList();
        toReturnBackMyDataToMainActivity =  new Intent(DetailActivity.this, MainActivity.class);
        if (mStringListDetailActivity == null) {
            mStringListDetailActivity = new ArrayList<>();
        }


        mAdapterDetailActivity = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringListDetailActivity);
        secondActivityListView.setAdapter(mAdapterDetailActivity);


        String message = mainActivityIntent.getStringExtra("data");

        toDoListHeaderSecond.setText(message);


        setDetailActivityListeners();
        index = getIndex();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String takeText = secondActivityEditText.getText().toString();


//                if (mStringListDetailActivity.size() >= 25) {
//                    Toast.makeText(DetailActivity.this, "You've reached maximum To-DoS allowed!", Toast.LENGTH_SHORT).show();
                if (secondActivityEditText.getText().toString().isEmpty()) {
                    Toast.makeText(DetailActivity.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (secondActivityEditText.getText().toString().length() > 40) {
                    Toast.makeText(DetailActivity.this, "Too Long!!!", Toast.LENGTH_SHORT).show();
                } else {
                    mStringListDetailActivity.add(takeText);
                    mAdapterDetailActivity.notifyDataSetChanged();
                    secondActivityEditText.getText().clear();
                }
            }
        });


    }


    private ArrayList<String> getDataList() {
//        Intent toReturnBackMyDataToMainActivity = getIntent();
        if (mainActivityIntent == null) {
            return null;
        }
        return mainActivityIntent.getStringArrayListExtra(MainActivity.DATA_KEY);

    }

    private int getIndex() {
//        Intent mainActivityIntent = getIntent();
        if (mainActivityIntent == null) {
            return MainActivity.ERROR_INDEX;
        }
        return mainActivityIntent.getIntExtra(MainActivity.DATA_KEY_INDEX, -1);
    }


    private void addItemsToList(){
        if (mStringListDetailActivity == null){
            return;
        }
        mAdapterDetailActivity.notifyDataSetChanged();
    }

    private void sendNewListBack() {

        if (mStringListDetailActivity == null) {
            return;
        }
        toReturnBackMyDataToMainActivity.putExtra(MainActivity.DATA_KEY, mStringListDetailActivity);
        toReturnBackMyDataToMainActivity.putExtra(MainActivity.DATA_KEY_INDEX, index);
        setResult(RESULT_OK, toReturnBackMyDataToMainActivity);
        finish();
    }


    @Override
    public void onBackPressed() {

        sendDataBack();
    }

    private void sendDataBack() {
        // modify the list
        addItemsToList();
        // send the data back
        sendNewListBack();
    }


    private void setDetailActivityListeners() {


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
