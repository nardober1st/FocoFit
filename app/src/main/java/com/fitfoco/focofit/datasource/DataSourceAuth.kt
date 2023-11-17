package com.fitfoco.focofit.datasource

import com.fitfoco.focofit.listener.ListenerAuth
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DataSourceAuth @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    fun signup(
        email: String,
        senha: String,
        apelido: String,
        nome: String,
        listenerAuth: ListenerAuth
    ) {
        if (email.isEmpty() || senha.isEmpty() || apelido.isEmpty() || nome.isEmpty()) {
            listenerAuth.onFailure("Complete todos os campos!")
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener { signup ->
                    if (signup.isSuccessful) {
                        val userId = firebaseAuth.currentUser?.uid.toString()
                        val map = hashMapOf(
                            "email" to email,
                            "apelido" to apelido,
                            "nome" to nome
                        )
                        firestore.collection("user").document(userId).set(map)
                            .addOnCompleteListener {
                                listenerAuth.onSuccess("Conta criada com sucesso", "loginScreen")
                            }
                            .addOnFailureListener {
                                listenerAuth.onFailure("Erro inesperado!")
                            }
                    }
                }
                .addOnFailureListener {
                    val error = when (it) {
                        is FirebaseAuthUserCollisionException -> "This account has already been registered"
                        is FirebaseAuthWeakPasswordException -> "The password must contain at least 6 characters"
                        is FirebaseNetworkException -> "No internet connection"
                        else -> "Invalid email"
                    }
                    listenerAuth.onFailure(error)
                }
        }
    }
}