package com.example.jvillanueva.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //    ArrayList<String> items;
//    ArrayAdapter<String> itemsAdapter;
//    ListView lvItems;
    ListView lvItems;
    TaskAdapter adapter;
    ArrayList<TaskViewModel> arrayOfTasks;
    private final int REQUEST_CODE = 20;
    private final int RESULT_OK = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrayOfTasks = new ArrayList<TaskViewModel>();
        adapter = new TaskAdapter(this, arrayOfTasks);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(adapter);

        TaskViewModel newTask00 = new TaskViewModel("Task 01", "Trying out new task", "HIGH", "TO-DO", new Date());
        TaskViewModel newTask01 = new TaskViewModel("Task 02", "Trying out new task", "MEDIUM", "TO-DO", new Date());
        TaskViewModel newTask02 = new TaskViewModel("Task 03", "Trying out new task", "LOW", "TO-DO", new Date());
        TaskViewModel newTask03 = new TaskViewModel("Task 04", "Trying out new task", "LOW", "TO-DO", new Date());
        adapter.add(newTask00);
        adapter.add(newTask01);
        adapter.add(newTask02);
        adapter.add(newTask03);

//        lvItems = (ListView) findViewById(R.id.lvItems);
//        readItems();
//        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
//        lvItems.setAdapter(itemsAdapter);
        setupRemoveListViewListener();
        setupEditViewListener();
        //Date time = new Date();
        //TaskViewModel task = new TaskViewModel("New Task01", "Testing new task", "High", "ToDo", new Date());
        //Toast.makeText(getApplicationContext(), "Task time: " + task.time.toString() + " tittle: " + task.tittle, Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(getApplicationContext(), "made it to activity result", Toast.LENGTH_LONG).show();
        if (resultCode == 0 && requestCode == 0) {
            String newText = data.getExtras().getString("newText");
            int positon = data.getExtras().getInt("position", 0);
//            items.set(positon, newText);
//            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }

        if (resultCode == 10 && requestCode == 0) {
            //Toast.makeText(getApplicationContext(), "made it to the right result", Toast.LENGTH_LONG).show();
            Bundle b = data.getExtras();
            if(b != null){
                TaskViewModel task = b.getParcelable("TaskViewModel");
                String method = data.getExtras().getString("method");
                //Toast.makeText(getApplicationContext(), "method: " + method, Toast.LENGTH_LONG).show();
                switch (method){
                    case "new":
                        adapter.add(task);
                        adapter.notifyDataSetChanged();
                        break;

                    case "edit":
                        int position = data.getExtras().getInt("position", -1);
                        if(position > -1){
                            arrayOfTasks.set(position, task);
                            adapter.notifyDataSetChanged();
                        }
                        break;
                }

                //Toast.makeText(getApplicationContext(), "Date : " + task.date, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onAddItem(View view) {
//        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
//        String itemText = etNewItem.getText().toString();
//        //itemsAdapter.add(itemText);
//        etNewItem.setText("");
//        writeItems();


        Intent i = new Intent(MainActivity.this, NewTaskFormActivity.class);
        startActivityForResult(i, 0);
    }

    private void setupRemoveListViewListener(){
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter1, View item, int pos, long id) {
                        arrayOfTasks.remove(pos);
                        adapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                }
        );
    }

    private void setupEditViewListener(){
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                        Intent i = new Intent(MainActivity.this, NewTaskFormActivity.class);
                        TaskViewModel currentTask = arrayOfTasks.get(pos);
                        Bundle b = new Bundle();
                        b.putParcelable("TaskViewModel", currentTask);
                        i.putExtras(b);


                        //i.putExtra("text", items.get(pos));
                        i.putExtra("position", pos);
                        startActivityForResult(i, 0);
                    }
                }
        );
    }

    private void readItems(){
//        File filesDir = getFilesDir();
//        File todoFile = new File(filesDir, "todo.txt");
//        try{
//            items = new ArrayList<String>(FileUtils.readLines(todoFile));
//        } catch(IOException e) {
//            items = new ArrayList<String>();
//        }
    }

    private void writeItems() {
//        File filesDir = getFilesDir();
//        File todoFile = new File(filesDir, "todo.txt");
//        try{
//            FileUtils.writeLines(todoFile, items);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
