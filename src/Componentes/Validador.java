/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Componentes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Highlander
 */
public class Validador {

    private static SimpleDateFormat formatoFecha;
    private static Calendar calendar = Calendar.getInstance();

    final public static String getFecha() {

        if (formatoFecha == null) {


              formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
              formatoFecha.setLenient(false);

            }

        
        return formatoFecha.format(calendar.getTime());

    }

      /**
   * Retorna true si la fecha es correcta false si no
   * @param fechaValidar Fecha a validar
   * @return boolean true: es correcta false: es incorrecta
   */

   final static public boolean esFecha(String fechaValidar) {

   	    boolean resultadoBoolean = false;

   	 	//metodo para validar si la fecha es correcta
        try {

            if (formatoFecha == null) {


              formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
              formatoFecha.setLenient(false);

            }


            Date fecha = formatoFecha.parse(fechaValidar);

            resultadoBoolean = true;


        } catch (Exception e) {}

        return resultadoBoolean;
    }


}
