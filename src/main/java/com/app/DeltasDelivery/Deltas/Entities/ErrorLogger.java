package com.app.DeltasDelivery.Deltas.Entities;

import javax.swing.text.html.HTMLEditorKit.LinkController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorLogger {
    public static void errorMessage(String e){
        Logger logger = LoggerFactory.getLogger(LinkController.class);
        logger.error("\n ---------- ** -------- \n  "+e+"\n ---------- ** -------- \n  ");
    }
}
