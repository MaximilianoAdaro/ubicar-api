package com.ubicar.ubicar.services.auth

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Service
class FirebaseService {

    @PostConstruct
    fun initializeFirebaseApp() {
        val serviceAccount = this.javaClass.getResourceAsStream("/firebase-service-credentials.json")
        val options: FirebaseOptions = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build()
        FirebaseApp.initializeApp(options)
    }
}
