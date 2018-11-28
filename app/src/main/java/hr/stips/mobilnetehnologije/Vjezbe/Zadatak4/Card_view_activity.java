package hr.stips.mobilnetehnologije.Vjezbe.Zadatak4;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hr.stips.mobilnetehnologije.R;

public class Card_view_activity extends AppCompatActivity {
    private  EmployeeDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4_activity_card_view_activity);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rec_view);//recycler view
        recyclerView.setHasFixedSize(true);//optimizacija

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // ponasa se kao list view

        this.db = Room.databaseBuilder(getApplicationContext(), EmployeeDB.class, "employees").fallbackToDestructiveMigration().build();
        EmployeeDAO dao = this.db.employeeDAO();
        new AsyncGetAll().execute(dao);


    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<Employee> employees;
        public MyAdapter(List<Employee> employees) {
            this.employees = employees;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View cellView = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.p4_recyler_cell, viewGroup, false);//inflatanje xml-a
            MyViewHolder vh = new MyViewHolder(cellView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {//za celiju koja se salje popuni me podacima
            Employee e = employees.get(i);
            myViewHolder.firstNameTextView.setText(e.getFirstName());
            myViewHolder.lastNameTextView.setText(e.getLastName());
            myViewHolder.positionTextView.setText(e.getPosition());
        }

        @Override
        public int getItemCount() {
            return employees.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView firstNameTextView, lastNameTextView, positionTextView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                firstNameTextView = itemView.findViewById(R.id.firstName);
                lastNameTextView = itemView.findViewById(R.id.lastName);
                positionTextView = itemView.findViewById(R.id.position);
            }
        }
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


            RecyclerView rv = (RecyclerView) findViewById(R.id.rec_view);
            rv.setLayoutManager(new LinearLayoutManager(Card_view_activity.this));
            MyAdapter adapter = new MyAdapter(employees);
            rv.setAdapter(adapter);

        }
    }
}

