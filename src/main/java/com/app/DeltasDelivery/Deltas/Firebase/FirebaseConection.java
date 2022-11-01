package com.app.DeltasDelivery.Deltas.Firebase;

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

        try {

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setProjectId("proyecto-bg-v1")
                .setDatabaseUrl("https://proyecto-bg-v1.firebaseio.com")
                .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            
        } catch (Exception e) {
            System.out.println("Error al consultar Firestore: " + e.getMessage() );
        }
        
    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
    
}
