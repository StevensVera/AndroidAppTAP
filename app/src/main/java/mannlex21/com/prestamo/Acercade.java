package mannlex21.com.prestamo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

public class Acercade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_acercade);
        TextView text = (TextView) findViewById(R.id.textView17);
        TextView text1 = (TextView) findViewById(R.id.textView18);
        TextView text2 = (TextView) findViewById(R.id.textView19);
        TextView text3 = (TextView) findViewById(R.id.textView20);
        TextView text4 = (TextView) findViewById(R.id.textView21);
        TextView text5 = (TextView) findViewById(R.id.textView22);
        TextView text6 = (TextView) findViewById(R.id.hora);
        Typeface titulo = Typeface.createFromAsset(getAssets(), "fonts/angrybirds-regular.ttf");
        text.setTypeface(titulo);
        text1.setTypeface(titulo);
        text2.setTypeface(titulo);
        text3.setTypeface(titulo);
        text4.setTypeface(titulo);
        text5.setTypeface(titulo);
        text6.setTypeface(titulo);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent appInfo1 = new Intent(Acercade.this, MainActivity.class);
                startActivity(appInfo1);
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
