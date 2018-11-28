package hr.stips.mobilnetehnologije.Predavanja.Predavanje4;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hr.stips.mobilnetehnologije.R;

public class Predavanje4_MainActivity extends AppCompatActivity {

    private  EmployeeDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4_activity_main);

        this.db = Room.databaseBuilder(getApplicationContext(), EmployeeDB.class, "employees").fallbackToDestructiveMigration().build();//ukoliko dodje do migracije da se podaci s koima ne znamo sto cemo uniste
        Button button = (Button)findViewById(R.id.save);
        Button button_display_db = (Button)findViewById(R.id.database_display_button);
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

        Button button_recycler = (Button)findViewById(R.id.button);
        button_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_recycler();
            }
        });


        Button prosirenje = findViewById(R.id.prosirenjeButton);
        prosirenje.setVisibility(View.GONE);



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

}
