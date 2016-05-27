package mannlex21.com.prestamo;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by STEVENS VERA on 27/05/2016.
 */
public class SQLite {

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Prestamo (Id_Prestamo INTEGER NOT NULL PRIMARY KEY, NombreP TEXT, Tipo TEXT, Objeto TEXT, Detalle TEXT, Cantidad INTEGER, Fecha_A TEXT, Fecha_F TEXT, CB INTEGER,Estatus TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Prestamo");
        db.execSQL("CREATE TABLE Prestamo (Id_Prestamo INTEGER NOT NULL PRIMARY KEY, NombreP TEXT, Tipo TEXT, Objeto TEXT, Detalle TEXT, Cantidad INTEGER, Fecha_A TEXT, Fecha_F TEXT, CB INTEGER,Estatus TEXT)");
    }





}
