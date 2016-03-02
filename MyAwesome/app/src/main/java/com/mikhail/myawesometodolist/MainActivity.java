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

    }

   private void setListeners(){

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
               Log.d("setOnClickListener", "Fab button Clicked");

               String takeText = firstActivityEditText.getText().toString();
               firstActivityEditText.setText(null);
               Log.d("setText", "Null");


               if (mStringList.size() >= 10) {
                   Toast.makeText(MainActivity.this, "You've reached maximum To-Do-Lists", Toast.LENGTH_SHORT).show();
               } else {
                   mStringList.add(takeText);
                   mAdapter.notifyDataSetChanged();
               }

           }
       });
       firstActivityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               startActivity(detailActivityTransition);

//                String toDoS = textView.getText().toString();

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

}








//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }