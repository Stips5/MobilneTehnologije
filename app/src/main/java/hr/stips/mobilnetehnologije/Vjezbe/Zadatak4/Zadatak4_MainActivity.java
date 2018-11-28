package hr.stips.mobilnetehnologije.Vjezbe.Zadatak4;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hr.stips.mobilnetehnologije.R;

public class Zadatak4_MainActivity extends AppCompatActivity {

    private  EmployeeDB db;
    private Button button;
    private Button button_display_db;
    private Button button_recycler;
    private Button prosirenje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4_activity_main);

        this.db = Room.databaseBuilder(getApplicationContext(), EmployeeDB.class, "employees").fallbackToDestructiveMigration().build();//ukoliko dodje do migracije da se podaci s koima ne znamo sto cemo uniste
        button = findViewById(R.id.save);
        button_display_db = findViewById(R.id.database_display_button);
        button_recycler = findViewById(R.id.button);
        prosirenje = findViewById(R.id.prosirenjeButton);

    }

    @Override
    protected void onStart() {
        super.onStart();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_employee();
            }
        });

        button_display_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display();
            }
        });

        button_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_recycler();
            }
        });

        prosirenje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_prosirenje();
            }
        });
    }

    private void save_employee(){

        //podaci koje zelomo spremiti u bazu
        EditText firstName = (EditText)findViewById(R.id.first_name);
        EditText lastName = (EditText)findViewById(R.id.last_name);
        EditText position = (EditText)findViewById(R.id.position);

        Employee employee = new Employee();
        employee.setFirstName(firstName.getText().toString());
        employee.setLastName(lastName.getText().toString());
        employee.setPosition(position.getText().toString());

        EmployeeDAO dao = db.employeeDAO();
        new AsyncInsert().execute(dao, employee);





    }

    private class AsyncInsert extends AsyncTask{


        @Override
        protected Object doInBackground(Object[] objects) {
            EmployeeDAO dao = (EmployeeDAO) objects[0];
            Employee employee = (Employee) objects[1];

            dao.insert(employee);

            return null;
        }
    }

    private void display(){
        Intent intent = new Intent(this, Display_database.class);

        startActivity(intent);

    }

    private void display_recycler(){
        Intent intent = new Intent(this, Card_view_activity.class);

        startActivity(intent);

    }

    private void display_prosirenje(){
        Intent intent = new Intent(this, Prosirenje.class);

        startActivity(intent);

    }


}
