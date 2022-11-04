package com.app.DeltasDelivery.Deltas.Firebase;

import com.app.DeltasDelivery.Deltas.Entities.ErrorLogger;
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
            String real_path = "C:/Users/hvargasc/Documents/Nuevos_Deltas_Java/DeltasDelivery/src/main/java/com/app/DeltasDelivery/Deltas/Firebase/key.json";
            FileInputStream credentials = new FileInputStream(real_path);
            System.out.println(credentials);
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentials))
                .setProjectId(projectId)
                .setDatabaseUrl("https://"+projectId+".firebaseio.com")
                .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch(IOException e){
            ErrorLogger.errorMessage("\n Error en conexion a Firebase"+"\n Proyecto FB = "+projectId+"\n \n"+e.toString());
        }
            
    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
    
}
