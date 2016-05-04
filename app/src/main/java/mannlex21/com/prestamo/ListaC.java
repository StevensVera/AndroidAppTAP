package mannlex21.com.prestamo;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaC extends AppCompatActivity {
    ListView listaDatos;
    ArrayAdapter adapter;
    CheckBox cb;
    Integer po;
    ListView lista;
    Boolean i;
    ArrayList<Datos> arraydatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        cb = (CheckBox)findViewById(R.id.checkBox);
        try {
            lista = (ListView) findViewById(R.id.list);
            arraydatos = new ArrayList<>();

            Datos datos;
            AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);

            SQLiteDatabase database = AppSQL.getWritableDatabase();
            String sql = "SELECT*FROM Prestamo";
            Cursor registro = database.rawQuery(sql, null);
           if (registro.moveToFirst()) {
                do {
                    datos = new Datos(registro.getInt(0), registro.getString(1), registro.getString(2), registro.getString(3), registro.getString(4), registro.getString(6), registro.getString(7), registro.getInt(5), registro.getInt(8));
                    arraydatos.add(datos);

                } while (registro.moveToNext());
            }
            AdapterDatos adapter = new AdapterDatos(this, arraydatos);
            lista.setAdapter(adapter);
        }catch (SQLException e){

        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                TextView Id_ = (TextView) v.findViewById(R.id.ID1);
                Integer Id = Integer.parseInt(Id_.getText().toString());
                AbrirEdit(Id);
                finish();
            }
        });

    }
    public void AbrirEdit(Integer id){
        Intent appInfo = new Intent(ListaC.this, EditView.class);
        appInfo.putExtra("ID",id);
        startActivity(appInfo);


    }


}
