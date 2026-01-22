//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.forms;

import java.util.Locale;
import javax.swing.UIManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.instance.InstanceQuery;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;

/**
 *
 * @author adrianromero
 */
public class StartPOS {

    private static Logger logger = Logger.getLogger("com.openbravo.pos.forms.StartPOS");
    
    /** Creates a new instance of StartPOS */
    private StartPOS() {
    }
    
    
    public static boolean registerApp() {
                       
        // vemos si existe alguna instancia        
        InstanceQuery i = null;
        try {
            i = new InstanceQuery();
            i.getAppMessage().restoreWindow();
            return false;
        } catch (Exception e) {
            return true;
        }  
    }
    
    public static void main (final String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                if (!registerApp()) {
                    System.exit(1);
                }
                
                AppConfig config = new AppConfig(args);
                config.load();
                
                int seleccion = JOptionPane.showOptionDialog( null,"Seleccione una opcion",
                         "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
                         JOptionPane.QUESTION_MESSAGE,null,
                         new Object[] { "Artesanos", "Esteban Alatorre", "La Villa",
                         "Mesa","Batan1","Rancho Nuevo","La Experiencia", "San Eugenio", "Batan2"
                         },"Artesanos");
                if (seleccion != -1){
                    // Artesanos - 25.82.111.21
                    //esteban - 25.100.197.155
                    //la villa - 25.7.56.206
                    //mesa - 25.3.80.139
                    //batan 1 - 25.73.155.214
                    //Rancho Nuevo - 25.93.231.212
                    //Experiencia - 25.81.228.243
                    //san eugenio -- 25.94.170.70
                    //batan2 -- 25.71.211.108
                    if(seleccion==0)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.82.111.21/pv");
                    }
                    else if(seleccion==1)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.100.197.155/pv");
                    }
                    else if(seleccion==2)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.7.56.206/pv");
                    }
                    else if(seleccion==3)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.3.80.139/pv");
                    }
                    else if(seleccion==4)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.73.155.214/pv");
                    }
                    else if(seleccion==5)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.61.107.224/pv");
                    }
                    else if(seleccion==6)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.81.228.243/pv");
                    }
                    else if(seleccion==7)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.71.235.115/pv");
                    }
                    else if(seleccion==8)
                    {
                        config.setProperty("db.URL", "jdbc:mysql://25.43.6.50/pv");
                    }
                }

                //System.out.println(config.getProperty("db.URL"));
                // set Locale.
                String slang = config.getProperty("user.language");
                String scountry = config.getProperty("user.country");
                String svariant = config.getProperty("user.variant");
                if (slang != null && !slang.equals("") && scountry != null && svariant != null) {                                        
                    Locale.setDefault(new Locale(slang, scountry, svariant));
                }
                
                // Set the format patterns
                Formats.setIntegerPattern(config.getProperty("format.integer"));
                Formats.setDoublePattern(config.getProperty("format.double"));
                Formats.setCurrencyPattern(config.getProperty("format.currency"));
                Formats.setPercentPattern(config.getProperty("format.percent"));
                Formats.setDatePattern(config.getProperty("format.date"));
                Formats.setTimePattern(config.getProperty("format.time"));
                Formats.setDateTimePattern(config.getProperty("format.datetime"));               
                
                // Set the look and feel.
                try {             
                    
                    Object laf = Class.forName(config.getProperty("swing.defaultlaf")).newInstance();
                    
                    if (laf instanceof LookAndFeel){
                        UIManager.setLookAndFeel((LookAndFeel) laf);
                    } else if (laf instanceof SubstanceSkin) {                      
                        SubstanceLookAndFeel.setSkin((SubstanceSkin) laf);                   
                    }
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Cannot set look and feel", e);
                }
                
                String screenmode = config.getProperty("machine.screenmode");
                if ("fullscreen".equals(screenmode)) {
                    JRootKiosk rootkiosk = new JRootKiosk();
                    rootkiosk.initFrame(config);
                } else {
                    JRootFrame rootframe = new JRootFrame(); 
                    rootframe.initFrame(config);
                }
            }
        });    
    }    
}
