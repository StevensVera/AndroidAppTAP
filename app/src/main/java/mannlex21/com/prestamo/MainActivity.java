package mannlex21.com.prestamo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Button boton1 = (Button) findViewById(R.id.btnPrestar);
        boton1.setOnClickListener(this);
        Button boton2 = (Button) findViewById(R.id.btnConsultar);
        boton2.setOnClickListener(this);
        Button boton3 = (Button) findViewById(R.id.btnAcerca);
        boton2.setOnClickListener(this);

        TextView text = (TextView) findViewById(R.id.titulo);
        Typeface titulo = Typeface.createFromAsset(getAssets(), "fonts/angrybirds-regular.ttf");
        text.setTypeface(titulo);
        Typeface botones = Typeface.createFromAsset(getAssets(), "fonts/angrybirds-regular.ttf");
        boton1.setTypeface(botones);
        boton2.setTypeface(botones);
        boton3.setTypeface(botones);



    }
    public String getDatePhone(){
        Calendar calendarNow = Calendar.getInstance();
        int dia =calendarNow.get(Calendar.DAY_OF_MONTH);
        int mes= calendarNow.get(Calendar.MONTH);
        int año = calendarNow.get(Calendar.YEAR);
        String fecha = dia+"/"+mes+"/"+año;
        return fecha;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPrestar) {
            Intent PrestarL = new Intent(getApplicationContext(), Prestamo.class);
            startActivity(PrestarL);
        }
        if (v.getId() == R.id.btnConsultar) {
            Intent Consultar = new Intent(getApplicationContext(), ListaC.class);
            startActivity(Consultar);
        }
    }



}
