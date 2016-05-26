package mannlex21.com.prestamo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Mannlex21 on 30/04/16.
 */
public class AppSQLiteOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    public AppSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Prestamo (Id_Prestamo INTEGER NOT NULL PRIMARY KEY, NombreP TEXT, Tipo TEXT, Objeto TEXT, Detalle TEXT, Cantidad INTEGER, Fecha_A TEXT, Fecha_F TEXT, CB INTEGER,Estatus TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Prestamo");
        db.execSQL("CREATE TABLE Prestamo (Id_Prestamo INTEGER NOT NULL PRIMARY KEY, NombreP TEXT, Tipo TEXT, Objeto TEXT, Detalle TEXT, Cantidad INTEGER, Fecha_A TEXT, Fecha_F TEXT, CB INTEGER,Estatus TEXT)");
    }
    public ArrayList llenar(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "SELECT*FROM Prestamo";
        Cursor registro= database.rawQuery(sql,null);
        if(registro.moveToFirst()){
            do{
                lista.add("Nombre : "+registro.getString(1)+"\nTipo : "+registro.getString(2)+"\nCantidad : "+registro.getInt(5));
            }while (registro.moveToNext());
        }
        return lista;
    }

}

