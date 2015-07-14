package unid;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jonatan
 */
public class DropExcel implements DropTargetListener {

    private DropTarget dt;
    private JTable jtable;
    private DefaultTableModel tableModel = new DefaultTableModel();
    DatoGeneral datoGeneral = new DatoGeneral();
    HoraFecha horafecha = new HoraFecha();
    private int contMateriasRepro;
    ArrayList<String> str = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<Object> strig = new ArrayList<>();
    ArrayList<Object> strigs = new ArrayList<>();
    List listMateria = new ArrayList();

    /**
     * DropExcel resive un parametro de tipo jTable el cual activa DropTarget
     */
    public DropExcel(JTable table) {
        this.jtable = table;
        dt = new DropTarget(jtable, this);
        this.jtable.setBackground(Color.WHITE);

    }

    public DropExcel() {

    }

    @Override//cuando el mause se esta sobre!!
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override//cuando el mouse pasa por ensima
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override//cuando es solta
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override//cuando salio
    public void dragExit(DropTargetEvent dte) {
    }

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
     * @readXls resive un parametro de tipo File el cual archivo con extension
     * xls
     */
    public void readXlS(File xls) {
        try {
            //crea un libro, 
            Workbook leerExcel = Workbook.getWorkbook(xls);
            //preguntamos la cantidad de hojas o existen
            if (leerExcel.getNumberOfSheets() > 0) {//si hay por lo menos una hoja
                //obtengo la hoja
                Sheet hoja = leerExcel.getSheet(0);//<-------------------es la primera hoja de excel
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
                            // auxColum[columna] = columNames[columna] = hoja.getCell(columna, fila).getContents();;
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

    /**
     * Guarda solo nombres
     */
    public void guardar() {
        for (int i = 0; i < jtable.getRowCount(); i++) {
            for (int j = 0; j < jtable.getColumnCount(); j++) {
                strig.add(tableModel.getValueAt(i, j).toString());
               /// System.out.println("get 1 "+strig.get(1));

            }
        }
        String mensaje = "los datos se guardaron con exito";
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public ArrayList getguardar() {
        for (int i = 0; i < jtable.getRowCount(); i++) {
            for (int j = 0; j < jtable.getColumnCount(); j++) {
                strig.add(tableModel.getValueAt(i, j).toString());

            }
        }
        String mensaje = "los datos se guardaron con exito";
        JOptionPane.showMessageDialog(null, mensaje);
        return strig;
    }

    public void prueva() {
        if (tableModel.getColumnName(0).equals("ID")) {
            String id = datoGeneral.ConvertirObjToString(tableModel.getValueAt(0, 0));
            datoGeneral.setId(id);
            datoGeneral.CharAts(id);
            if (datoGeneral.getId() == null) {
                System.out.println("El valor es null");
            }

            System.out.println("ID = " + datoGeneral.getId());
        }
    }

    public void exclusive() {
        for (int i = 0; i < jtable.getRowCount(); i++) {
            str.add(tableModel.getValueAt(i, 22).toString());
              //  str.add(tableModel.getValueAt(1, 2).toString());
            // System.out.println("*"+str.get(i));
        }
        for (int i = 0; i < jtable.getRowCount(); i++) {
            title.add(tableModel.getValueAt(i, 10).toString());

            // System.out.println("title "+title.get(i));
        }
    }

    public void verificiacion() {
        ArrayList<String> calInt = new ArrayList();
        String vrf = "";
        int n;
        for (int i = 0; i < str.size(); i++) {
            vrf = str.get(i);
            if (vrf.length() == 0) {

            } else {
                calInt.add(str.get(i));
            }
        }
        for (int j = 0; j < calInt.size(); j++) {
            String mat, cal;
            n = Integer.parseInt(calInt.get(j));
            if (n <=5) {/* menor a 5 o tambien OU*/

                mat = title.get(j);
                cal = calInt.get(j);
                Materias m = new Materias(j,mat,cal);
                listMateria.add(m);
                contMateriasRepro++;
                //System.out.println(""+title.get(j)+" : "+n);
                System.out.println("" + mat + " " + cal);
            }else{
                System.out.println("no hay materias reprobadas");
                
            }
            //System.out.println(""+n);
        }
        System.out.println("Numero de Materias reprobadas :" + contMateriasRepro);
        Materias m = new Materias(0,null,null);
       listMateria.add(m);
    }

    public void Title() {
        int cont = 0;
        int reprobadas = 0, reprobarEStadia = -1;
        String comprobar = "";
        for (int i = 0; i < title.size(); i++) {
            comprobar = title.get(i);
            if (comprobar.equals("Curso Inducción Alum. NI")) {
                // System.out.println(" Curso "+title.remove(i));
                title.remove(i);
            }
            if (comprobar.equals("Basic 1")) {
                title.remove(i);
            }
            if (comprobar.equals("Exento inglés")) {
                title.remove(i);
            }
            if (comprobar.equals("Estadía empresarial")) {
                reprobarEStadia++;
                title.remove(i);
            }
            if (comprobar.equals("Pre-Estadía Empresarial")) {
                title.remove(i);
            }
            if (comprobar.equals("Taller de Pre- Egreso")) {
                title.remove(i);
            }
            if (comprobar.length() == 0) {
                title.remove(i);//contn es el numero de espacios en blanco 
            } else {

                cont++;
            }
        }
        System.out.println("numero de materias " + cont);
        System.out.println("repobadas " + reprobadas);
        System.out.println("Cuantas veces ha llebado estadia " + reprobarEStadia);
        String dato = datoGeneral.ConvertirObjToString((cont * 7));
        datoGeneral.setCreditos(dato);
        System.out.println("Credistos " + datoGeneral.getCreditos());

        for (int i = 0; i < title.size(); i++) {
            System.out.println("title " + title.get(i));

        }
    }

    /**
     * Muestra los datos en consola por ahora!!
     */
    public void mostrar() {
        int conversion, suma = 0, cont = 0;
        for (int i = 1; i < str.size(); i++) {
            if (str.get(i) != null) {
                //System.out.println("*"+str.get(i)); 
                conversion = Integer.parseInt(str.get(i));
                System.out.println("int " + conversion);
                suma = suma + conversion;
                cont++;
                //  datoGeneral.CharAts(str.get(i));
            }

        }

        String mensaje = "se mostraron los datos";
        JOptionPane.showMessageDialog(null, mensaje);

    }
    public void removerdatos(){
        exclusive();
        for (int i = 0; i <str.size(); i++) {
            String da=str.get(i);
            if (da.equals("AC")) {
                System.out.println("remo"+str.remove(i));
            }
        }
    }
    public void GRDE() {
        removerdatos();
        int conversion, suma = 0, cont = 0, contn = 0;
        for (int i = 0; i < str.size(); i++) {
            String comprobar = str.get(i);
            if (comprobar.length() == 0) {
                contn++;//contn es el numero de espacios en blanco 
            } // 
            
            else {
                conversion = Integer.parseInt(str.get(i));
                suma = suma + conversion;
                cont++;
            }
        }
        //System.out.println("Suma total es "+suma);
        System.out.println("espacios ocupados " + cont);
        System.out.println("espacions vacios " + contn);
        //System.out.println("Promedio: "+(suma/cont));
        String dato = datoGeneral.ConvertirObjToString(suma / cont);
        datoGeneral.setGRDE(dato);
    }

    /**
     * bucar cuantos estudiates pertenecen a esa carrera.!!
     */
    public void Buscar() {
        String Lic = JOptionPane.showInputDialog("Lic. a buscar ?");
        int cont = 0;
        for (int i = 0; i < jtable.getRowCount(); i++) {
            for (int j = 0; j < jtable.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j).toString().equals(Lic)) {
                 // model.setColumnCount(i);
                    //    Datos.addColumnSelectionInterval(i, j);

                    jtable.setSelectionBackground(Color.YELLOW);
                    //JOptionPane.showMessageDialog(null, "se Allaron Datos de la ING LISI");
                    cont++;
                }
            }
        }
        //System.out.println("#"+cont);
        JOptionPane.showMessageDialog(null, " " + cont);
    }

    public void identyColors() {
        String dato = JOptionPane.showInputDialog("Column ?");
        int num = Integer.parseInt(dato);
        jtable.getColumnModel().getColumn(num).setCellRenderer(new Colors());
    }

    public void print() {
        prueva();
        GRDE();
        Title();
        verificiacion();
        /**
         * ya busque como bucar o leer la cabecera y tmb como obtener el dato
         *//*int fila, int columna */
        
        if (tableModel.getColumnName(1).equals("Nombre")) {
            String nom = datoGeneral.ConvertirObjToString(tableModel.getValueAt(0, 1));
            datoGeneral.setNombre(nom);
            System.out.println("Nombre  = " + datoGeneral.getNombre());
        }
        if (tableModel.getColumnName(3).equals("Prog")) {
            String lic = datoGeneral.ConvertirObjToString(tableModel.getValueAt(0, 3));
            datoGeneral.setLic(lic);
            System.out.println("Lic = " + datoGeneral.getLic());
        }
        if (tableModel.getColumnName(4).equals("Estatus")) {
            String Estatus = datoGeneral.ConvertirObjToString(tableModel.getValueAt(0, 4));
            datoGeneral.setStatus(Estatus);
            System.out.println("Estatus " + Estatus);
        }
        if (tableModel.getColumnName(6).equals("Materias")) {
            String mat = datoGeneral.ConvertirObjToString(tableModel.getValueAt(1, 6));
            datoGeneral.setMaterias(mat);
            System.out.println("Materia = " + datoGeneral.getMaterias());
        }
        /*Ingreso del estudiante */
        if (tableModel.getColumnName(9).equals("PERIODO")) {
            String periodo = datoGeneral.ConvertirObjToString(tableModel.getValueAt(0, 9));
            datoGeneral.setPeriodo(periodo);
            System.out.println("Periodo = " + datoGeneral.getPeriodo());
        }

        /* if (tableModel.getColumnName(9).equals("TITLE")) {
         String Estatus = datoGeneral.ConvertirObjToString(tableModel.getValueAt(0, 9));
         datoGeneral.setStatus(Estatus);
         System.out.println("Estatus "+Estatus);
         }
         /* 
         Curso Inducción Alum. NI,Pre-Estadía Empresarial,Taller de Pre- Egreso */
    }

    public void imprimir() {

        try {
               
                //   System.out.println("ed"+listMateria.get(i));
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject("Jasper.jasper");
            Map parametros = new HashMap<String, String>();
            print();
            /*en esta  parametros.put("nombre de la etiqueta ","el dato a obtner el get")*/
            parametros.put("ID", datoGeneral.getId());
            parametros.put("Nombre", datoGeneral.getNombre());
            parametros.put("Lic", datoGeneral.getLic());
            parametros.put("Fecha", horafecha.getFecha());
            parametros.put("Promedio", "Promedio:   " + datoGeneral.getGRDE());
            parametros.put("Estatus", "Estatus: " + datoGeneral.getStatus());
            parametros.put("Creditos", "Creditos : " + datoGeneral.getCreditos());
            parametros.put("Ingreso", "INGRESO DEL " + datoGeneral.getPeriodo() + " EN ADELANTE");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listMateria));
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("Reporte de  " + datoGeneral.getNombre());
            view.setVisible(true);

        } catch (JRException ej) {
            System.out.println("e " + ej);
        }

    }

}
