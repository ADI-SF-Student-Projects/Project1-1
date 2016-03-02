package com.mikhail.myawesometodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView toDoListHeaderSecond;
    EditText secondActivityEditText;
    ListView secondActivityListView;
    Intent mainActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toDoListHeaderSecond = (TextView) findViewById(R.id.header1);
        secondActivityEditText = (EditText) findViewById(R.id.editText1);
        secondActivityListView = (ListView) findViewById(R.id.list1);
        mainActivityIntent = getIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

//    private void changeHeader(){
//        toDoListHeaderSecond.getText(findViewById(long id));
//    }

}
