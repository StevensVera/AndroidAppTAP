package mannlex21.com.prestamo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private static final int NOTIFICACION_ID=1;
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
        CheckNotifi();





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


public void notifi(){
    Intent intent = new Intent(getApplicationContext(), ListaC.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    builder.setSmallIcon(R.drawable.ic_action_error);
    builder.setContentIntent(pendingIntent);
    builder.setAutoCancel(true);
    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.remindersicon));
    builder.setContentTitle("Y mis cosas?!");
    builder.setContentText("Hay cosas que prestaste y aun no te las regresan");
    builder.setSubText("1 notificación");

    NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(NOTIFICACION_ID,builder.build());

}
    public void CheckNotifi(){
        String fecha_i,fecha_f;
        int añofi,mesfi,diafi,añoff,mesff,diaff;
        AppSQLiteOpenHelper AppSQL = new AppSQLiteOpenHelper(this, "Prestamo", null, 1);
        SQLiteDatabase database = AppSQL.getWritableDatabase();
        String sql = "SELECT*FROM Prestamo";
        Cursor registro = database.rawQuery(sql, null);
        if (registro.moveToFirst()) {
            do {
                final Calendar cal = Calendar.getInstance();
                añofi=cal.get(Calendar.YEAR);
                mesfi=cal.get(Calendar.MONTH);
                diafi=cal.get(Calendar.DAY_OF_MONTH);

                fecha_f=registro.getString(7);
                String[] separatedFF = fecha_f.split("/");
                añoff=Integer.parseInt(separatedFF[0]);
                mesff=Integer.parseInt(separatedFF[1]);
                mesff=Integer.parseInt(separatedFF[2]);
                if(añofi>=añoff){
                    if(añofi==añoff){
                        if(mesfi>=mesff){
                            if(mesfi==mesff){
                                if(diafi>=diafi){
                                    notifi();
                                }
                            }
                            if(mesfi>mesff){
                                notifi();
                            }
                        }
                    }
                    if(añofi>añoff){
                        notifi();
                    }
                }
            } while (registro.moveToNext());
        }
    }


}
