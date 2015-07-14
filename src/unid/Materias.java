/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unid;

/**
 *
 * @author jonatan
 */
public class Materias {
   int id;
   String materia;
   String calificacion;

    public Materias() {
    }

    public Materias(int id, String materia, String calificacion) {
        this.id = id;
        this.materia = materia;
        this.calificacion = calificacion;
    }
   
    public int getId() {
        return id;
    }

    public String getMateria() {
        return materia;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
    
   
   
}
