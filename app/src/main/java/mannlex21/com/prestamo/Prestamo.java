package mannlex21.com.prestamo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamo);
        Objetos = (Spinner) findViewById(R.id.Lista_Objetos);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ListaObjetos, android.R.layout.simple_spinner_item);
        Objetos.setAdapter(adapter);

        //Le agrega al campo Fecha Actual, la fecha del sistema
        TextView t ;
        t=(TextView)findViewById(R.id.txtFechaActual);
        t.setText(getDatePhone());
        Button boton1 = (Button) findViewById(R.id.btnLimpiar);
        boton1.setOnClickListener(this);
        Button boton2 = (Button) findViewById(R.id.btnAceptar);
        boton2.setOnClickListener(this);
        Button boton3 = (Button) findViewById(R.id.btnCancelar);
        boton3.setOnClickListener(this);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtDetalle = (TextView) findViewById(R.id.txtDetalle);
        txtCantidad = (TextView) findViewById(R.id.txtCantidad);
        txtFechaFin = (TextView) findViewById(R.id.txtFechaFin);
        txtObjeto = (TextView) findViewById(R.id.txtObjeto);
        txtFechaActual = (TextView) findViewById(R.id.txtFechaActual);
        Lista = (Spinner) findViewById(R.id.Lista_Objetos);
    }
    //Obtiene la fecha actual del sistema
    private String getDatePhone() {
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formatteDate = df.format(date);
        return formatteDate;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAceptar:
                guardarDatos();
                //Toast.makeText(this,"Se presiono aceptar",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLimpiar:
                limpiar();
                //Toast.makeText(this,"Se presiono limpiar",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCancelar:
                Toast.makeText(this,"Se presiono cancelar",Toast.LENGTH_LONG).show();
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
        objeto = txtObjeto.getText().toString();
        Integer cb=0;
        if(nombre==null||detalle.equals(null)||cantidad==null||fecha_a.equals(null)||fecha_fin.equals(null) || objeto.equals(null)){
            Toast.makeText(this,"Faltan llenar campos...",Toast.LENGTH_LONG).show();
        }else{
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
                Toast.makeText(this,"Se guardo correctamente",Toast.LENGTH_LONG).show();
            }catch (SQLException e){
                Toast.makeText(this,"No se pudo guardar",Toast.LENGTH_LONG).show();
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
        TextView t ;
        t=(TextView)findViewById(R.id.txtFechaActual);
        t.setText(getDatePhone());
    }


}
