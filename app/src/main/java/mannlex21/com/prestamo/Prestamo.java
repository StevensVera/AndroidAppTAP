package mannlex21.com.prestamo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Prestamo extends AppCompatActivity implements View.OnClickListener {
    Spinner Objetos;
    TextView txtNombre,txtDetalle,txtCantidad,txtFechaFin,txtObjeto,txtFechaActual ;
    String nombre=null,detalle=null,fecha_fin=null,fecha_a=null,tipo=null,objeto=null;
    private Spinner Lista;
    Integer cantidad=null;
    int dia, mes, año;
    int dia1, mes1, año1;
    int dia2, mes2, año2;
    static final int DIALOG_ID = 0;
    Integer ids=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_prestamo);

        final Calendar cal = Calendar.getInstance();
        año1=año=cal. get(Calendar.YEAR);
        mes1=mes=cal.get(Calendar.MONTH)+1;
        dia1=dia=cal.get(Calendar.DAY_OF_MONTH);

        Objetos = (Spinner) findViewById(R.id.Lista_Objetos);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ListaObjetos, android.R.layout.simple_spinner_item);
        Objetos.setAdapter(adapter);

        //Le agrega al campo Fecha Actual, la fecha del sistema
        TextView t ;
        t=(TextView)findViewById(R.id.txtFechaActual);
        t.setText(año1+"/"+mes1+"/"+dia1);
        Button boton1 = (Button) findViewById(R.id.btnLimpiar);
        boton1.setOnClickListener(this);
        Button boton2 = (Button) findViewById(R.id.btnAceptar);
        boton2.setOnClickListener(this);
        Button boton3 = (Button) findViewById(R.id.btnCancelar);
        boton3.setOnClickListener(this);
        Button boton4 = (Button) findViewById(R.id.btnFecha);
        boton4.setOnClickListener(this);
        Button boton5 = (Button) findViewById(R.id.btnFecha2);
        boton5.setOnClickListener(this);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtDetalle = (TextView) findViewById(R.id.txtDetalle);
        txtCantidad = (TextView) findViewById(R.id.txtCantidad);
        txtFechaFin = (TextView) findViewById(R.id.txtFechaFin);
        txtObjeto = (TextView) findViewById(R.id.txtObjeto);
        txtFechaActual = (TextView) findViewById(R.id.txtFechaActual);
        Lista = (Spinner) findViewById(R.id.Lista_Objetos);

        Lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==3){
                    txtObjeto.setEnabled(true);
                    txtObjeto.setText("");
                    TextView Obj = (TextView) findViewById(R.id.textView10);
                    Obj.setEnabled(true);
                    txtObjeto.setVisibility(View.VISIBLE);
                    findViewById(R.id.textView10).setVisibility(View.VISIBLE);
                }else{
                    txtObjeto.setText("");
                    txtObjeto.setEnabled(false);

                    TextView Obj = (TextView) findViewById(R.id.textView10);
                    Obj.setEnabled(false);

                    txtObjeto.setVisibility(View.GONE);
                    findViewById(R.id.textView10).setVisibility(View.GONE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent appInfo1 = new Intent(Prestamo.this, MainActivity.class);
                startActivity(appInfo1);
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAceptar:
                guardarDatos();

                break;
            case R.id.btnLimpiar:
                limpiar();
                break;
            case R.id.btnCancelar:
                Intent appInfo1 = new Intent(Prestamo.this, MainActivity.class);
                startActivity(appInfo1);
                finish();
                break;
            case R.id.btnFecha:
                ids=1;
                showDialog();
                break;
            case R.id.btnFecha2:
                ids=2;
                showDialog();
                break;
        }
    }
    public void guardarDatos(){
        nombre = txtNombre.getText().toString();
        detalle= txtDetalle.getText().toString();
        if(txtCantidad.getText().toString().equals("")){
            cantidad=null;
        }else{
            cantidad= Integer.parseInt(txtCantidad.getText().toString());
        }
        fecha_fin = txtFechaFin.getText().toString();
        fecha_a = txtFechaActual.getText().toString();
        tipo = Lista.getSelectedItem().toString();
        Integer cb=0;
        if(nombre==null||detalle.equals(null)||cantidad==null||fecha_a.equals(null)||fecha_fin.equals(null)){
            Toast.makeText(this,"Faltan llenar campos...",Toast.LENGTH_SHORT).show();
        }else{

            objeto = txtObjeto.getText().toString();
            if(objeto.equals("")){
                objeto =tipo;
            }
            AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this,"Prestamo",null,1);
            SQLiteDatabase db = AppSQL.getWritableDatabase();
            try{
                ContentValues valores = new ContentValues();
                valores.put("NombreP",nombre);
                valores.put("Tipo",tipo);
                valores.put("Objeto",objeto);
                valores.put("Detalle",detalle);
                valores.put("Cantidad",cantidad);
                valores.put("Fecha_A",fecha_a);
                valores.put("Fecha_F",fecha_fin);
                valores.put("CB",cb);
                db.insert("Prestamo",null,valores);
                Toast.makeText(this,"Se guardo correctamente",Toast.LENGTH_SHORT).show();
                Intent appInfo = new Intent(Prestamo.this, MainActivity.class);
                startActivity(appInfo);
                finish();
            }catch (SQLException e){
                Toast.makeText(this,"No se pudo guardar",Toast.LENGTH_SHORT).show();
                db.close();
            }

        }
    }
    public void limpiar(){
        txtNombre.setText("");
        txtDetalle.setText("");
        txtCantidad.setText("");
        txtFechaFin.setText("");
        txtObjeto.setText("");
        Lista.setSelection(0);
        TextView t ;
        t=(TextView)findViewById(R.id.txtFechaActual);
        t.setText(año1+"/"+mes1+"/"+dia1);
    }
    DatePickerDialog.OnDateSetListener mDateSetFecha = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            if (ids == 1) {
                dia = dayOfMonth;
                mes = monthOfYear+1;
                año=year;
                txtFechaActual.setText(año+"/"+mes+"/"+dia);
                año1=año;
                mes1=mes;
                dia1=dia;
            } else {
                if(ids==2)
                    dia2=dia = dayOfMonth;
                    mes2=mes = monthOfYear+1;
                    año2=año=year;
                    if(año2>=año1 && mes2>=mes1){
                        if(mes2==mes1){
                            if(dia2>dia1){
                                txtFechaFin.setText(año+"/"+mes+"/"+dia);
                            }else{Toast.makeText(Prestamo.this,"Fecha fin debe ser despues de la fecha de inicio.",Toast.LENGTH_SHORT).show();}
                        }else{
                            if(mes2>mes1){
                                txtFechaFin.setText(año+"/"+mes+"/"+dia);
                            }
                        }
                    }else{Toast.makeText(Prestamo.this,"Fecha fin debe ser despues de la fecha de inicio.",Toast.LENGTH_SHORT).show();}
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
    }

