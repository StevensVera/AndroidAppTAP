package mannlex21.com.prestamo;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditView extends AppCompatActivity implements View.OnClickListener {
    private String nombre, objeto, descripcion, cantidad, fecha_i, fecha_f;
    private Spinner t;
    private Integer Id;
    private Spinner Objetos;
    private Spinner Lista1;
    TextView txtNombre;
    TextView txtDetalle;
    TextView txtCantidad;
    TextView txtFechaFin;
    TextView txtObjeto;
    TextView txtFechaActual;
    Integer Chk;
    Boolean mod=false;
    int dia, mes, año;
    static final int DIALOG_ID = 0;
    Integer ids=0,modi=1;
    Integer lc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_view);

        TextView text = (TextView) findViewById(R.id.textView15);
        Typeface titulo = Typeface.createFromAsset(getAssets(), "fonts/angrybirds-regular.ttf");
        text.setTypeface(titulo);

        final Calendar cal = Calendar.getInstance();
        año=cal.get(Calendar.YEAR);
        mes=cal.get(Calendar.MONTH);
        dia=cal.get(Calendar.DAY_OF_MONTH);
        Button boton2 = (Button) findViewById(R.id.btnDevuelto);
        boton2.setOnClickListener(this);
        Button boton3 = (Button) findViewById(R.id.btnBorrar);
        boton3.setOnClickListener(this);
        Button boton4 = (Button) findViewById(R.id.btnModificar);
        boton4.setOnClickListener(this);
        Button boton5 = (Button) findViewById(R.id.button);
        boton5.setOnClickListener(this);
        Button boton6 = (Button) findViewById(R.id.button2);
        boton6.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        Id = bundle.getInt("ID");
        AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);

        SQLiteDatabase database = AppSQL.getWritableDatabase();
        String sql = "SELECT*FROM Prestamo Where Id_Prestamo=" + Id;
        Cursor registro = database.rawQuery(sql, null);
        Objetos = (Spinner) findViewById(R.id.Lista_Objetos1);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ListaObjetos, android.R.layout.simple_spinner_item);
        Objetos.setAdapter(adapter);
        if (registro.moveToFirst()) {
            do {
                txtNombre = (TextView) findViewById(R.id.txtNombre1);
                nombre = registro.getString(1);
                txtNombre.setText(registro.getString(1));
                txtDetalle = (TextView) findViewById(R.id.txtDetalle1);
                descripcion = registro.getString(4);
                txtDetalle.setText(registro.getString(4));
                txtCantidad = (TextView) findViewById(R.id.txtCantidad1);
                cantidad = String.valueOf(registro.getInt(5));
                txtCantidad.setText(String.valueOf(registro.getInt(5)));
                txtFechaFin = (TextView) findViewById(R.id.txtFechaFin1);
                fecha_f=registro.getString(7);
                txtFechaFin.setText(registro.getString(7));
                txtObjeto = (TextView) findViewById(R.id.txtObjeto1);
                objeto = registro.getString(3);
                txtObjeto.setText(registro.getString(3));
                txtFechaActual = (TextView) findViewById(R.id.txtFechaActual1);
                fecha_i = registro.getString(6);
                txtFechaActual.setText(registro.getString(6));

                Lista1 = (Spinner) findViewById(R.id.Lista_Objetos1);


                Chk = registro.getInt(8);
                if (registro.getString(2).equals("Dinero")) {
                    Lista1.setSelection(1);
                    lc= Lista1.getSelectedItemPosition();
                } else {
                    if (registro.getString(2).equals("Libro")) {
                        Lista1.setSelection(0);
                        lc= Lista1.getSelectedItemPosition();
                    } else {
                        if (registro.getString(2).equals("Ropa")) {
                            Lista1.setSelection(2);
                            lc= Lista1.getSelectedItemPosition();
                        } else {
                            Lista1.setSelection(3);
                            lc= Lista1.getSelectedItemPosition();
                        }
                    }
                }
            } while (registro.moveToNext());
        }
        Bloquear(mod);
    }

    public void Bloquear(Boolean d) {
        txtNombre = (TextView) findViewById(R.id.txtNombre1);
        txtNombre.setEnabled(d);
        txtDetalle = (TextView) findViewById(R.id.txtDetalle1);
        txtDetalle.setEnabled(d);
        txtCantidad = (TextView) findViewById(R.id.txtCantidad1);
        txtCantidad.setEnabled(d);
        txtFechaFin = (TextView) findViewById(R.id.txtFechaFin1);
        txtFechaFin.setEnabled(false);
        txtObjeto = (TextView) findViewById(R.id.txtObjeto1);
        txtObjeto.setEnabled(d);
        txtFechaActual = (TextView) findViewById(R.id.txtFechaActual1);
        txtFechaActual.setEnabled(false);
        Lista1.setEnabled(d);
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setEnabled(d);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setEnabled(d);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDevuelto) {
            AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);
            SQLiteDatabase database = AppSQL.getWritableDatabase();
            String sql;
            if (Chk == 0) {
                sql = "UPDATE Prestamo SET CB=" + 1 + "  Where Id_Prestamo=" + Id;
                database.execSQL(sql);
                sql = "UPDATE Prestamo SET Estatus='Entregado'  Where Id_Prestamo=" + Id;
                database.execSQL(sql);
                Intent Consultar = new Intent(getApplicationContext(), ListaC.class);
                startActivity(Consultar);
                finish();

            } else {
                sql = "UPDATE Prestamo SET CB=" + 0 + "  Where Id_Prestamo=" + Id;
                database.execSQL(sql);
                sql = "UPDATE Prestamo SET Estatus='Prestado'  Where Id_Prestamo=" + Id;
                database.execSQL(sql);
                Intent Consultar = new Intent(getApplicationContext(), ListaC.class);
                startActivity(Consultar);
                finish();
            }

        }
        if (v.getId() == R.id.btnBorrar) {
            AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);
            SQLiteDatabase database = AppSQL.getWritableDatabase();
            String sql;
            sql = "DELETE FROM Prestamo Where Id_Prestamo=" + Id;
            database.execSQL(sql);
            Intent Consultar = new Intent(getApplicationContext(), ListaC.class);
            startActivity(Consultar);
            finish();
        }
        if (v.getId() == R.id.btnModificar) {

            if(modi==1){
                Bloquear(true);
                modi=2;
            }else{
                //
                if(nombre.equals(txtNombre.getText().toString())&&objeto.equals(txtObjeto.getText().toString())&&descripcion.equals(txtDetalle.getText().toString())&& cantidad.equals(txtCantidad.getText().toString())&& fecha_f.equals(txtFechaFin.getText().toString())&&fecha_i.equals(txtFechaActual.getText().toString())&&lc==Lista1.getSelectedItemPosition()){

                }else{
                    if(modi==2){
                        AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);
                        SQLiteDatabase database = AppSQL.getWritableDatabase();
                        String sql;
                        sql = "UPDATE Prestamo SET NombreP ='"+txtNombre.getText()+"' Where Id_Prestamo=" + Id;
                        database.execSQL(sql);
                        sql = "UPDATE Prestamo SET Tipo ='"+Lista1.getSelectedItem().toString()+"' Where Id_Prestamo=" + Id;
                        database.execSQL(sql);
                        sql = "UPDATE Prestamo SET Objeto ='"+txtObjeto.getText()+"' Where Id_Prestamo=" + Id;
                        database.execSQL(sql);
                        sql = "UPDATE Prestamo SET Detalle ='"+txtDetalle.getText()+"' Where Id_Prestamo=" + Id;
                        database.execSQL(sql);
                        sql = "UPDATE Prestamo SET Cantidad ="+Integer.parseInt(txtCantidad.getText().toString())+" Where Id_Prestamo=" + Id;
                        database.execSQL(sql);
                        sql = "UPDATE Prestamo SET Fecha_A ='"+txtFechaActual.getText()+"' Where Id_Prestamo=" + Id;
                        database.execSQL(sql);
                        sql = "UPDATE Prestamo SET Fecha_F ='"+txtFechaFin.getText()+"' Where Id_Prestamo=" + Id;
                        database.execSQL(sql);
                        Toast.makeText(this, "Se modifico correctamente", Toast.LENGTH_SHORT).show();
                        Intent Consultar = new Intent(getApplicationContext(), ListaC.class);
                        startActivity(Consultar);
                        finish();
                    }
                }
            }
        }
        if (v.getId() == R.id.button) {
            ids=1;
            showDialog();
        }
        if (v.getId() == R.id.button2) {
            ids=2;
            showDialog();
        }

    }
    DatePickerDialog.OnDateSetListener mDateSetFecha = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dia = dayOfMonth;
            mes = monthOfYear;
            año=year;
            if (ids == 1) {
                txtFechaActual.setText(año+"/"+mes+"/"+dia);
            } else {
                if(ids==2)
                    txtFechaFin.setText(año+"/"+mes+"/"+dia);
            }
        }
    };

    public void showDialog(){
        showDialog(DIALOG_ID);
    }
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, mDateSetFecha, año, mes, dia);
        } else {
            return null;
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent appInfo1 = new Intent(EditView.this, ListaC.class);
                startActivity(appInfo1);
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
