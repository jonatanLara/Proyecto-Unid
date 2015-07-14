/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unid;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author jonatan
 */
public class MateriaDatasource implements JRDataSource{
  private List<Materias> listaMateria = new ArrayList<Materias>();
    private int indiceMateriaActual = -1;
    @Override
    public boolean next() throws JRException {
        return ++indiceMateriaActual < listaMateria.size();
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;  

    if("materia".equals(jrField.getName())) 
    { 
        valor = listaMateria.get(indiceMateriaActual).getMateria(); 
    } 
    else if("calificacion".equals(jrField.getName())) 
    { 
        valor = listaMateria.get(indiceMateriaActual).getCalificacion(); 
    }
 
    return valor;  
    }
    public void addParticipante(Materias materia)
    {
        this.listaMateria.add(materia);
    }
       
}
