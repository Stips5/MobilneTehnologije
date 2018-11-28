package hr.stips.mobilnetehnologije.Vjezbe.Zadatak4;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EmployeeDAO {

    @Query("SELECT * FROM employee")
    public List<Employee> getAll();

    @Query("SELECT * FROM employee WHERE first_name LIKE :name and last_name LIKE :last_name LIMIT 1")
    public Employee getEmployee(String name, String last_name);

    @Query("SELECT * FROM employee WHERE position LIKE :filteredPosition")
    public List<Employee> getEmployee(String filteredPosition);

    @Insert
    public void insert(Employee employee);//moze i public void insert(Employee... employees) - jedan ili vise odvojenih zarezima za konkretnu metodu

    @Update
    public void update(Employee employee);

    @Delete
    public void delete(Employee employee);


}
