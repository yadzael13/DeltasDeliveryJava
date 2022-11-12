package com.app.DeltasDelivery.Deltas.Tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

public class Loggers {
    public static void errorLogger(String path, String error){
        Logger LOGGER = LoggerFactory.getLogger(Slf4j.class);
        LOGGER.error("\n ---------- ** -------- \nPath: "+path+
                     "\nError: "+error
                    +"\n ---------- ** -------- \n\n");
    }
    public static void infoLogger(String path, String info){
        Logger LOGGER = LoggerFactory.getLogger(Slf4j.class);
        LOGGER.info("\n ---------- ** -------- \nPath: "+path+
                        "\nInfo: "+info+"\n ---------- ** -------- \n\n");
    }
}
