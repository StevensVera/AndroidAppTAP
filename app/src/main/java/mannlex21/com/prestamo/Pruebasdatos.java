package mannlex21.com.prestamo;

/**
 * Created by STEVENS VERA on 27/05/2016.
 */
public class Pruebasdatos {
    protected String nombre, tipo,objeto,descripcion,fecha_i,fecha_f,estatus;
    protected Integer cantidad,ID;
    protected Integer check;

    public Pruebasdatos(Integer ID,String nombre, String tipo, String objeto,String descripcion,String fecha_i, String fecha_f,Integer cantidad, Integer check,String estatus){
        this.ID=ID;
        this.nombre = nombre;
        this.tipo = tipo;
        this.objeto = objeto;
        this.descripcion = descripcion;
        this.fecha_i = fecha_i;
        this.fecha_f = fecha_f;
        this.cantidad = cantidad;
        this.check = check;
        this.estatus=estatus;
    }

}
