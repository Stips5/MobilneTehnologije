package hr.stips.mobilnetehnologije.Vjezbe.Zadatak4;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import hr.stips.mobilnetehnologije.R;

public class Display_database extends AppCompatActivity {

    private  EmployeeDB db;
    @Override//adaptirati podatke iz baze za ispis na ekran
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4_activity_display_database);

        this.db = Room.databaseBuilder(getApplicationContext(), EmployeeDB.class, "employees").fallbackToDestructiveMigration().build();
        EmployeeDAO dao = this.db.employeeDAO();

        new AsyncGetAll().execute(dao);

    }

    private class AsyncGetAll extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            EmployeeDAO dao = (EmployeeDAO) objects[0];

            List<Employee> employees = dao.getAll();

            return employees;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            List<Employee> employees = (List<Employee>) o;

            ArrayAdapter<Employee> employees_adapter = new ArrayAdapter<Employee>(Display_database.this, android.R.layout.simple_list_item_1, employees);
            ListView listView = (ListView)findViewById(R.id.display_database_data);
            listView.setAdapter(employees_adapter);

        }
    }

}
