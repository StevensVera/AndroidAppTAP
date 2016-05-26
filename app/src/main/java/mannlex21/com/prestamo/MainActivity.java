package mannlex21.com.prestamo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

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
        boton3.setOnClickListener(this);

        TextView text = (TextView) findViewById(R.id.titulo);
        Typeface titulo = Typeface.createFromAsset(getAssets(), "fonts/angrybirds-regular.ttf");
        text.setTypeface(titulo);
        Typeface botones = Typeface.createFromAsset(getAssets(), "fonts/angrybirds-regular.ttf");
        boton1.setTypeface(botones);
        boton2.setTypeface(botones);
        boton3.setTypeface(botones);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPrestar) {
            Intent PrestarL = new Intent(getApplicationContext(), Prestamo.class);
            startActivity(PrestarL);
            finish();
        }
        if (v.getId() == R.id.btnConsultar) {
            Intent Consultar = new Intent(getApplicationContext(), ListaC.class);
            startActivity(Consultar);
            finish();
        }
        if (v.getId() == R.id.btnAcerca) {
            Intent Consultar = new Intent(getApplicationContext(), Acercade.class);
            startActivity(Consultar);
            finish();
        }
    }
}
