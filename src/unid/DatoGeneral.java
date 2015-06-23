/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unid;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import sun.util.calendar.LocalGregorianCalendar;

/**
 *
 * @author jonatan
 */
public class DatoGeneral {
    private String id , nombre ,Lic , materias, CRSE,Periodo;
    private String GRDE;//CALIFICACION FINAL
    public DatoGeneral(){}

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLic() {
        return Lic;
    }
    public String getMaterias() {
        return materias;
    }

    public String getCRSE() {
        return CRSE;
    }

    public String getGRDE() {
        return GRDE;
    }
    public String getPeriodo() {
        String ingreso="";
        for (int i = 0; i < 4; i++) {
            ingreso = ingreso+Periodo.charAt(i);
        }
        
        return ingreso;
    }

    public void setPeriodo(String periodo) {
        this.Periodo = periodo;
        
    }
    public void setId(String id) {
        this.id = id;
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; 
       
    }

    public void setLic(String Lic) {
        this.Lic = Lic;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public void setCRSE(String CRSE) {
        this.CRSE = CRSE;
    }

    public void setGRDE(String GRDE) {
        this.GRDE = GRDE;
    }
    public String ConvertirObjToString(Object obj){
        String str = "";
        if (obj!=null) {
            str = obj.toString();
        }
        return str;
    }
    public int ConvertirObjToInt(Object obj){
        int n = 0;
        if (obj!=null) {
           n = ((Integer)obj).intValue();
        }
        return n;
    }
    public void CharAts(String s){
        if (s.length()== 0) {
            System.out.println("el tamaño es- "+ s.length());
        }else{
            System.out.println("el tamaño es+ "+ s.length());
        }
         
    }
    public static String Fecha(){
    Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return  formatoFecha.format(fecha);
    }
}
