package com.fitfoco.focofit.datasource

import com.fitfoco.focofit.data.model.Objetivo
import com.fitfoco.focofit.listener.ListenerAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DataSourceObjetivo @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {

    fun saveObjetivos(objetivo: Objetivo, listenerAuth: ListenerAuth) {
        if (objetivo.peso.isEmpty() || objetivo.altura.isEmpty()) {
            listenerAuth.onFailure("Complete todos os campos!")
        } else {
            val userId = firebaseAuth.currentUser?.uid.toString()
            val map = hashMapOf(
                "peso" to objetivo.peso,
                "altura" to objetivo.altura,
                "goal" to objetivo.goal,
                "exercise" to objetivo.exerciseFrequency
            )
            firestore.collection("goals").document(userId).set(map)
                .addOnCompleteListener {
                    listenerAuth.onSuccess("Salvo com sucesso", "homeScreen")
                }
                .addOnFailureListener {
                    listenerAuth.onFailure("Erro inesperado!")
                }
        }
    }
}