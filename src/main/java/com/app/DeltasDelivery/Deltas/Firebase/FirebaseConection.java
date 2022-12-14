package com.app.DeltasDelivery.Deltas.Firebase;

import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;



import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FirebaseConection {

    @PostConstruct
    private void firestoreInit() {
        String projectId = "proyecto-bg-v1";
        try {

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setProjectId(projectId)
                .setDatabaseUrl("https://proyecto-bg-v1.firebaseio.com")
                .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            Loggers.infoLog("Firebase Connection","Conectado correctamente a : "+projectId);
            
        } catch (Exception e) {
            Loggers.errorLog("Firebase Connection Error","No se ha podido conectar a proyecto: "+projectId);
        }
        
    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
    
}
