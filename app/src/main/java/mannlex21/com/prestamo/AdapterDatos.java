package mannlex21.com.prestamo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Mannlex21 on 02/05/16.
 */
public class AdapterDatos extends BaseAdapter {
    protected Activity activity;
    protected CheckBox cb;
    protected ArrayList<Datos> items;

    TextView estado;
    public AdapterDatos(Activity activity, ArrayList<Datos> items){
        this.activity=activity;
        this.items=items;
    }
    @Override
    public int getCount() {
        //Retorna el tamaño del objeto item
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        //Retornamos la posicion del item
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        //Asociamos el layout de la lista que hemos creado
        if(convertView==null){
            LayoutInflater inf=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.itemlista,null);
        }
        Datos dir = items.get(position);
        TextView nombre = (TextView) v.findViewById(R.id.txtNombre);
        nombre.setText(dir.getNombre());
        TextView tipo = (TextView) v.findViewById(R.id.txtTipo);
        tipo.setText(dir.getTipo());
        TextView objeto = (TextView) v.findViewById(R.id.txtObjeto);
        objeto.setText(dir.getObjeto());
        TextView descripcion= (TextView) v.findViewById(R.id.txtDescripcion);
        descripcion.setText(dir.getDescripcion());
        TextView cantidad= (TextView) v.findViewById(R.id.txtCantidad);
        cantidad.setText(String.valueOf(dir.getCantidad()));
        TextView fecha_i= (TextView) v.findViewById(R.id.txtFechaI);
        fecha_i.setText(dir.getFecha_I());
        TextView fecha_f= (TextView) v.findViewById(R.id.txtFechaF);
        fecha_f.setText(dir.getFecha_F());

        estado = (TextView) v.findViewById(R.id.status);

        TextView Id= (TextView) v.findViewById(R.id.ID1);
        Id.setText(String.valueOf(dir.getID()));
        Integer c = dir.getCheck();
        if(c==0){
            cb = (CheckBox) v.findViewById(R.id.checkBox);
            cb.setChecked(false);
            estado.setText(dir.getEstatus());
        }else{
            cb = (CheckBox) v.findViewById(R.id.checkBox);
            cb.setChecked(true);
            estado.setText(dir.getEstatus());
        }
        return v;
    }
}
