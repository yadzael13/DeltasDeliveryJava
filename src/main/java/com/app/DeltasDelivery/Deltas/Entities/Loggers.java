package com.app.DeltasDelivery.Deltas.Entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

public class Loggers {
    public static void errorLogger(String path, String error){
        Logger LOGGER = LoggerFactory.getLogger(Slf4j.class);
        LOGGER.error("\n ---------- ** -------- \n  Path: "+path+
                     "\nError: "+error
                    +"\n ---------- ** -------- \n\n");
    }
    public static void infoLogger(String path, String info){
        Logger LOGGER = LoggerFactory.getLogger(Slf4j.class);
        LOGGER.info("\n ---------- ** -------- \n  Path: "+path+
                        "\nInfo: "+info+"\n ---------- ** -------- \n\n");
    }
}
