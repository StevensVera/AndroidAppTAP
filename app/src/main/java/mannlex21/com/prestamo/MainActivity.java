package mannlex21.com.prestamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----------------------------------------------
        /*Oculta la barra de notificaciones
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);*/
        //----------------------------------------------
        Button boton1 = (Button) findViewById(R.id.btnPrestar);
        boton1.setOnClickListener(this);

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
    }
}
