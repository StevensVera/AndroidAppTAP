package mannlex21.com.prestamo;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lista);
        CheckNotifi();
        TextView text1 = (TextView) findViewById(R.id.textView16);
        Typeface titulo = Typeface.createFromAsset(getAssets(), "fonts/angrybirds-regular.ttf");
        text1.setTypeface(titulo);

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
                    datos = new Datos(registro.getInt(0), registro.getString(1), registro.getString(2), registro.getString(3), registro.getString(4), registro.getString(6), registro.getString(7), registro.getInt(5), registro.getInt(8),registro.getString(9));
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent appInfo1 = new Intent(ListaC.this, MainActivity.class);
                startActivity(appInfo1);
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void CheckNotifi(){
        String fecha_i,fecha_f,estado;
        int añofi,mesfi,diafi,añoff,mesff,diaff,id_prestamo;
        AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);
        SQLiteDatabase database = AppSQL.getWritableDatabase();
        String sql = "SELECT*FROM Prestamo";
        Cursor registro = database.rawQuery(sql, null);
        if (registro.moveToFirst()) {
            do {
                final Calendar cal = Calendar.getInstance();
                añofi=cal.get(Calendar.YEAR);

                mesfi=cal.get(Calendar.MONTH)+1;
                diafi=cal.get(Calendar.DAY_OF_MONTH);

                fecha_f=registro.getString(7);
                id_prestamo=registro.getInt(0);
                estado=registro.getString(9);
                String[] separatedFF = fecha_f.split("/");
                añoff=Integer.parseInt(separatedFF[0]);
                mesff=Integer.parseInt(separatedFF[1]);
                diaff=Integer.parseInt(separatedFF[2]);
                if(añofi>=añoff){
                    if(añofi==añoff){
                        if(mesfi>=mesff){
                            if(mesfi==mesff){
                                if(diafi>=diaff){
                                    if(estado.equals("Entregado")){

                                    }else{
                                        estado(id_prestamo);
                                    }
                                }
                            }
                            if(mesfi>mesff){
                                if(estado.equals("Entregado")){

                                }else{
                                    estado(id_prestamo);
                                }
                            }
                        }
                    }
                    if(añofi>añoff){
                        if(estado.equals("Entregado")){

                        }else{
                            estado(id_prestamo);
                        }
                    }
                }
            } while (registro.moveToNext());
        }
    }

    public void estado(Integer Id){
        AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);
        SQLiteDatabase database = AppSQL.getWritableDatabase();
        String sql = "UPDATE Prestamo SET Estatus='Retrasado'  Where Id_Prestamo=" + Id;
        database.execSQL(sql);
    }

}
