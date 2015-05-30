/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unid;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jonatan
 */
public class Colors extends DefaultTableCellRenderer{
    public Colors(){}

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
       // String idet = JOptionPane.showInputDialog("Lic. ");
        if (value.equals("LISI")) {
                setBackground(Color.MAGENTA);

        } else {    //if(table.getValueAt(row, 3).toString().equals("Masculino")){
                setBackground(Color.GREEN);

        }

        return this;


}



}
