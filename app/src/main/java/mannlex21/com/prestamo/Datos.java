package mannlex21.com.prestamo;

import android.widget.CheckBox;

/**
 * Created by Mannlex21 on 02/05/16.
 */
public class Datos {
    protected String nombre, tipo,objeto,descripcion,fecha_i,fecha_f;
    protected Integer cantidad,ID;
    protected Integer check;

    public Datos(Integer ID,String nombre, String tipo, String objeto,String descripcion,String fecha_i, String fecha_f,Integer cantidad, Integer check){
        this.ID=ID;
        this.nombre = nombre;
        this.tipo = tipo;
        this.objeto = objeto;
        this.descripcion = descripcion;
        this.fecha_i = fecha_i;
        this.fecha_f = fecha_f;
        this.cantidad = cantidad;
        this.check = check;
    }
    public Integer getID(){return ID;}
    public void setID(Integer ID){this.ID=ID;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion=descripcion;}
    public String getTipo(){return tipo;}
    public void setTipo(String tipo){this.tipo=tipo;}
    public String getObjeto(){return objeto;}
    public void setObjeto(String objeto){this.objeto=objeto;}
    public Integer getCantidad(){return cantidad;}
    public void setCantidad(Integer cantidad){this.cantidad=cantidad;}
    public String getFecha_I(){return fecha_i;}
    public void setFecha_I(String fecha_i){this.fecha_i=fecha_i;}
    public String getFecha_F(){return fecha_f;}
    public void setFecha_F(String fecha_f){this.fecha_f=fecha_f;}
    public Integer getCheck(){return check;}
    public void setCheck(Integer check){this.check=check;}
}