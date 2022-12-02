package com.app.DeltasDelivery.Deltas.Tools;

import javax.swing.text.html.HTMLEditorKit.LinkController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loggers {

    public static void errorLog(String path, String error){
        Logger logger = LoggerFactory.getLogger(LinkController.class);
        logger.error("\n----------------------------------\n"+
                "Error en : "+path
                +"\nDescripcion: "+error
                +"\n----------------------------------\n");
    }

    public static void infoLog(String path, String info){
        Logger logger = LoggerFactory.getLogger(LinkController.class);
        logger.info("\n----------------------------------\n"+
                "Actividad en : "+path
                +"\nDescripcion: "+info
                +"\n----------------------------------\n");
    }
}