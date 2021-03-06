package example.codeclan.com.mytasklistapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import example.codeclan.com.mytasklistapp.database.DBHandler;

public class NewTask extends AppCompatActivity {

    private ImageButton saveButton;
    private EditText nameToSave;
    private EditText descriptionToSave;
    private EditText completedToSave;
    private Spinner priorityToSave;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        saveButton = (ImageButton) findViewById(R.id.save_task_button);
        priorityToSave = (Spinner) findViewById(R.id.priority_to_save);
        nameToSave = (EditText) findViewById(R.id.name_to_save);
        descriptionToSave = (EditText) findViewById(R.id.description_to_save);
        addListenerOnSpinnerItemSelection();

        dateView = (TextView) findViewById(R.id.textView4);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }

    public void addListenerOnSpinnerItemSelection() {
        priorityToSave = (Spinner) findViewById(R.id.priority_to_save);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, HomePage.class);
                this.startActivity(intent);
                break;
            case R.id.list:
                Intent intent2 = new Intent(this, TaskListActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.newTask:
                Intent intent3 = new Intent(this, NewTask.class);
                this.startActivity(intent3);
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    arg1 = year;
                    arg2 = month;
                    arg3 = day;
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void onSaveTaskButtonClicked(View view) {
        String priToSave = String.valueOf(priorityToSave.getSelectedItem());
        String stringOneToSave = nameToSave.getText().toString();
        String stringTwoToSave = descriptionToSave.getText().toString();
        String dateToSave = Integer.toString(year) + "-" + Integer.toString(month)+ "-" + Integer.toString(day);
        Log.d("Date4", dateToSave);
        Task task = new Task(priToSave, stringOneToSave, stringTwoToSave, dateToSave);
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.addTask(task);

        Intent intent = new Intent(this, TaskListActivity.class);

        startActivity(intent);
    }
}
