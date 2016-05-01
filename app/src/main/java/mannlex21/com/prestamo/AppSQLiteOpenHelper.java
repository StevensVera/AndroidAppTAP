package mannlex21.com.prestamo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mannlex21 on 30/04/16.
 */
public class AppSQLiteOpenHelper extends SQLiteOpenHelper {

    public AppSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Prestamo (Id_Prestamo INTEGER NOT NULL PRIMARY KEY,NombreP TEXT,Tipo TEXT,Objeto TEXT,Detalle TEXT,Cantidad INTEGER,Fecha_A TEXT,Fecha_F TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Prestamo");
        db.execSQL("CREATE TABLE Prestamo (Id_Prestamo INTEGER NOT NULL PRIMARY KEY,NombreP TEXT,Tipo TEXT,Objeto TEXT,Detalle TEXT,Cantidad INTEGER,Fecha_A TEXT,Fecha_F TEXT)");
    }
}

