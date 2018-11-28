package hr.stips.mobilnetehnologije.Vjezbe.Zadatak4;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 2)//named argumenti
public abstract class  EmployeeDB extends RoomDatabase {//zbog toga sto se ne zeli izloziti konstruktor
    public abstract EmployeeDAO employeeDAO();//getter za DAO
}
