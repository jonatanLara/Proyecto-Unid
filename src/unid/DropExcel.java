package unid;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author jonatan
 */
public class DropExcel implements DropTargetListener {

    private DropTarget dt;
    private JTable jtable;
    private DefaultTableModel tableModel = new DefaultTableModel();
    /**
     * DropExcel resive un parametro de tipo jTable
     * el cual activa DropTarget 
     */
    public DropExcel(JTable table) {
        this.jtable = table;
        dt = new DropTarget(jtable, this);

    }

    @Override//cuando el mause se esta sobre!!
    public void dragEnter(DropTargetDragEvent dtde) {}
    @Override//cuando el mouse pasa por ensima
    public void dragOver(DropTargetDragEvent dtde) {}

    @Override//cuando es solta
    public void dropActionChanged(DropTargetDragEvent dtde) {}
    
    @Override//cuando salio
    public void dragExit(DropTargetEvent dte) {}
     
    @Override//cuando se suelta algo .
    public void drop(DropTargetDropEvent dtde) {
        try {
            
            Transferable tr = dtde.getTransferable();
            //devuelve un array de objetos de DataFavors
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            if (flavors.length > 0) {
                //si existe una lista de objetos de archivos
                if (flavors[0].isFlavorJavaFileListType()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    //obtine una list con los archivos arratrados
                    java.util.List list = (java.util.List) tr.getTransferData(flavors[0]);
                    if (!list.isEmpty()) {//abre primero el archivo
                        File file = new File(list.get(0).toString());
                        //
                        if (file.exists()) {
                            //si el archivo es igual a xls
                            if (file.getName().endsWith("xls")) {
                                //lee e importa los datos//
                                readXlS(file);

                            } else {
                                JOptionPane.showMessageDialog(null, "no es un archivo compaible");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "el archivo no existe");
                        }
                    }
                    dtde.dropComplete(true);
                        //JOptionPane.showMessageDialog(null," el archivo cargo correctamente");

                    return;
                }
            }
            System.err.println("Drop fallido " + dtde);
            dtde.rejectDrop();
            //  }HeadlessException | UnsupportedFlavorException | IO
        } catch (Exception e) {
            System.err.println("Error en el try catch " + e.getMessage());
            dtde.rejectDrop();
        }
    }
    /**
     * @readXls resive un parametro de tipo File el cual archivo con extension xls
     */
    public void readXlS(File xls) {
        try {
            //crea un libro, 
            Workbook leerExcel = Workbook.getWorkbook(xls);
            //preguntamos la cantidad de hojas o existen
            if (leerExcel.getNumberOfSheets() > 0) {//si hay por lo menos una hoja
                //obtengo la hoja
                Sheet hoja = leerExcel.getSheet(0);
                //forma el array para los nombre de las columnas
                String[] columNames = new String[hoja.getColumns()];
                //forma la matris para los datos
                Object[][] data = new String[hoja.getRows()][hoja.getColumns()];
                //recorre todas las celdas
                
                for (int fila = 0; fila < hoja.getRows(); fila++) {
                    for (int columna = 0; columna < hoja.getColumns(); columna++) {
                        //asigana los nombre de la columna
                        if (fila == 0) {
                            //asigna nombre de columna
                            columNames[columna] = hoja.getCell(columna, fila).getContents();//hoja.getCell(columna, fila).getContents();
                          //  strig.add(columNames[columna]);
                        } else {
                            //lee celda y coloca en el array
                            data[fila][columna] = hoja.getCell(columna, fila).getContents();
                           // strigs.add(data[fila][columna]);
                            
                        }

                    }
                    // aqui iba antes
                    tableModel = new DefaultTableModel(data, columNames);
                    jtable.setModel(tableModel);
                    
                    tableModel.removeRow(0);
                    // guardar();
                }

            }//HeadlessException | UnsupportedFlavorException | IO
        } catch (IOException e) {
            System.err.println("Error en el try cacth de leer el excel \n" + e.getMessage());
            JOptionPane.showMessageDialog(null, "forato no compatible \n" + e.getMessage());
        } catch (BiffException e) {
            System.err.println(e.getMessage());
        }

    }

    public DefaultTableModel getTableModel() {
        return this.tableModel;
    }

    public DropTarget getDropTarget() {
        return this.dt;
    }
    ArrayList<Object>strig = new ArrayList<>();
   
    ArrayList<Object>strigs = new ArrayList<>();
   /**
    * Guarda solo nombres
    */
    public void guardar(){
        for (int i = 0; i < jtable.getRowCount(); i++) {
            for (int j = 0; j <jtable.getColumnCount(); j++) {
                strig.add(tableModel.getValueAt(i, j).toString());
                
            }
        }
        String mensaje = "los datos se guardaron con exito";
        JOptionPane.showMessageDialog(null, mensaje);
    }/**
     * Muestra los datos en consola por ahora!!
     */
    public void mostrar(){
        for (int i = 0; i < jtable.getRowCount(); i++) {
            for (int j = 0; j <jtable.getColumnCount(); j++) {
            // strig.add(tableModel.getValueAt(i, j).toString());
                System.out.println(tableModel.getValueAt(i, j));
            }}
        String mensaje = "se mostraron los datos";
            JOptionPane.showMessageDialog(null, mensaje);
    }/**
     * bucar cuantos estudiates pertenecen a esa carrera.!!
     */
    public void Buscar(){
        String Lic = JOptionPane.showInputDialog("Lic. a buscar ?");
        int cont =0;
        for (int i = 0; i < jtable.getRowCount(); i++) {
            for (int j = 0; j <jtable.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j).toString().equals(Lic)) {
                 // model.setColumnCount(i);
                //    Datos.addColumnSelectionInterval(i, j);
                  
                     jtable.setSelectionBackground(Color.YELLOW);
                    //JOptionPane.showMessageDialog(null, "se Allaron Datos de la ING LISI");
                    cont ++;
                }
            }
        }
        //System.out.println("#"+cont);
        JOptionPane.showMessageDialog(null, " "+ cont);
    }
    public void identyColors(){
            String dato = JOptionPane.showInputDialog("Column ?");
            int num = Integer.parseInt(dato);
            jtable.getColumnModel().getColumn(num).setCellRenderer(new  Colors());
    }
}
