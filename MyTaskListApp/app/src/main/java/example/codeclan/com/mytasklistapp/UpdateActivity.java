package example.codeclan.com.mytasklistapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import example.codeclan.com.mytasklistapp.database.DBHandler;


public class UpdateActivity extends AppCompatActivity {

    private ImageButton saveUpdateButton;
    EditText nameToSave;
    EditText descriptionToSave;
    Task task;
    private Spinner priorityToSave;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    int yearToSave;
    int monthToSave;
    int dayToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        saveUpdateButton = (ImageButton) findViewById(R.id.save_update_button);
        priorityToSave = (Spinner) findViewById(R.id.priority_to_save);
        nameToSave = (EditText) findViewById(R.id.name_to_save);
        descriptionToSave = (EditText) findViewById(R.id.description_to_save);
        dateView = (TextView) findViewById(R.id.textView3);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("Task");
        DBHandler dbHandler = new DBHandler(this);
        task = dbHandler.getTask(id);
        nameToSave.setText(task.getName());
        descriptionToSave.setText(task.getDescription());

        String dateString = task.getDate();

        String[] dateArray = dateString.split("-");

        int setYear = Integer.parseInt(dateArray[0]);
        int setMonth = Integer.parseInt(dateArray[1]);
        int setDay = Integer.parseInt(dateArray[2]);

        calendar = Calendar.getInstance();
        year = setYear;
        month = setMonth;
        day = setDay;

        showDate(year, month+1, day);
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
                    yearToSave = arg1;
                    monthToSave = arg2;
                    dayToSave = arg3;
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void onSaveUpdateButtonClicked(View view) {
        String priToSave = String.valueOf(priorityToSave.getSelectedItem());
        String stringOneToSave = nameToSave.getText().toString();
        String stringTwoToSave = descriptionToSave.getText().toString();
        String dateToSave = Integer.toString(yearToSave) + "-" + Integer.toString(monthToSave)+ "-" + Integer.toString(dayToSave);

        task.setPriority(priToSave);
        task.setName(stringOneToSave);
        task.setDescription(stringTwoToSave);
        task.setDate(dateToSave);
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.updateTask(task);

        Intent intent = new Intent(this, TaskListActivity.class);

        startActivity(intent);
    }
}
