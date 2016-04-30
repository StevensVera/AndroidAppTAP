package mannlex21.com.prestamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Prestamo extends AppCompatActivity implements View.OnClickListener {
    Spinner Objetos;
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
        if (v.getId() == R.id.btnLimpiar) {
            TextView txtNombre = (TextView) findViewById(R.id.txtNombre);
            TextView txtDetalle = (TextView) findViewById(R.id.txtDetalle);
            TextView txtCantidad = (TextView) findViewById(R.id.txtCantidad);
            TextView txtFechaFin = (TextView) findViewById(R.id.txtFechaFin);
            TextView txtObjeto = (TextView) findViewById(R.id.txtObjeto);
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


}
