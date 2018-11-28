package hr.stips.mobilnetehnologije.Vjezbe.Zadatak4;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import hr.stips.mobilnetehnologije.R;

public class Prosirenje extends AppCompatActivity {

    private static final String TAG = "Prosirenje";
    private  EmployeeDB db;
    @Override//adaptirati podatke iz baze za ispis na ekran
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4_activity_display_prosirenje);

        this.db = Room.databaseBuilder(getApplicationContext(), EmployeeDB.class, "employees").fallbackToDestructiveMigration().build();
        final EmployeeDAO dao = this.db.employeeDAO();

        Button filtriraj = findViewById(R.id.buttonFilter);
        final EditText editTextFilter = findViewById(R.id.editViewFilter);

        filtriraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncGetAll().execute(dao, editTextFilter.getText().toString());
            }
        });
    }

    private class AsyncGetAll extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            EmployeeDAO dao = (EmployeeDAO) objects[0];
            String filterString = (String)objects[1];

            List<Employee> employees = dao.getEmployee(filterString);

            for (int i = 0; i < employees.size(); i++) {
                Log.d("Employes", "doInBackground: " + employees.get(i));
            }

            if (employees.isEmpty())
                return dao.getAll();

            return employees;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            List<Employee> employees = (List<Employee>) o;

            ArrayAdapter<Employee> employees_adapter = new ArrayAdapter<Employee>(Prosirenje.this, android.R.layout.simple_list_item_1, employees);
            ListView listView = (ListView)findViewById(R.id.display_database_data);
            listView.setAdapter(employees_adapter);

        }
    }

}


