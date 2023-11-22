package com.fitfoco.focofit.datasource

import com.fitfoco.focofit.data.model.Objetivo
import com.fitfoco.focofit.listener.ListenerAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataSourceObjetivo @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {

    private val _todosObjetivos = MutableStateFlow<MutableList<Objetivo>>(mutableListOf())
    val todosObjetivos: StateFlow<MutableList<Objetivo>> = _todosObjetivos

    private val _peso = MutableStateFlow("")
    private val peso: StateFlow<String> = _peso

    private val _altura = MutableStateFlow("")
    private val altura: StateFlow<String> = _altura

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

//    fun getObjective(): Flow<MutableList<Objetivo>> {
//        val objetivo: MutableList<Objetivo> = mutableListOf()
//        firestore.collection("goals").get().addOnCompleteListener {
//            if (it.isSuccessful) {
//                for (obj in it.result) {
//                    val x = obj.toObject(Objetivo::class.java)
//                    objetivo.add(x)
//                    _todosObjetivos.value = objetivo
//                }
//            }
//        }
//        return todosObjetivos
//    }

    fun pesoUser(): StateFlow<String>{
        val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        firestore.collection("goals").document(userID).get().addOnCompleteListener {
            if (it.isSuccessful){
                val peso = it.result.getString("peso").toString()
                _peso.value = peso
            }
        }
        return peso
    }

    fun alturaUSer(): StateFlow<String>{
        val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        firestore.collection("goals").document(userID).get().addOnCompleteListener {
            if (it.isSuccessful){
                val altura = it.result.getString("altura").toString()
                _altura.value = altura
            }
        }
        return altura
    }
}