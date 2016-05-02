package mannlex21.com.prestamo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {
    ListView listaDatos;
    ArrayAdapter adapter;
    ArrayList<String> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listaDatos = (ListView) findViewById(R.id.listaDatos);
        AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this,"Prestamo",null,1);
        lista = AppSQL.llenar();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        listaDatos.setAdapter(adapter);
    }
}
