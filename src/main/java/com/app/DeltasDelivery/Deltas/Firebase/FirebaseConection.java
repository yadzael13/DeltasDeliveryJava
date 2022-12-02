package com.app.DeltasDelivery.Deltas.Firebase;

import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;


@Service
public class FirebaseConection {

    @PostConstruct
    private void firestoreInit() throws IOException{
        String projectId = "proyecto-bg-v1";
        try{
            String reciclable_path = "C:/Users/hvargasc/Documents/Nuevos_Deltas_Java/DeltasDeliveryJava/src/main/java/com/app/DeltasDelivery/Deltas/Firebase";
            FileInputStream credentials = new FileInputStream(reciclable_path+"/key.json");
            System.out.println(credentials);
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentials))
                .setProjectId(projectId)
                .setDatabaseUrl("https://"+projectId+".firebaseio.com")
                .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            Loggers.infoLogger("Firebase Connection", "Conection to project \""+projectId+"\" Sucessfully!");
        } catch(IOException e){
            Loggers.errorLogger(" Error en conexion a Firebase"+"\n Proyecto FB = "+projectId,e.toString());
        }
            
    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
    
}
