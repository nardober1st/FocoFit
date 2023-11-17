package com.fitfoco.focofit.datasource

import com.fitfoco.focofit.listener.ListenerAuth
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataSourceAuth @Inject constructor(
    private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore
) {

    private val _checkUserLogged = MutableStateFlow(false)
    private val checkUserLogged: StateFlow<Boolean> = _checkUserLogged

    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name

    fun signup(
        email: String, senha: String, apelido: String, nome: String, listenerAuth: ListenerAuth
    ) {
        if (email.isEmpty() || senha.isEmpty() || apelido.isEmpty() || nome.isEmpty()) {
            listenerAuth.onFailure("Complete todos os campos!")
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener { signup ->
                    if (signup.isSuccessful) {
                        val userId = firebaseAuth.currentUser?.uid.toString()
                        val map = hashMapOf(
                            "email" to email, "apelido" to apelido, "nome" to nome
                        )
                        firestore.collection("user").document(userId).set(map)
                            .addOnCompleteListener {
                                listenerAuth.onSuccess("Conta criada com sucesso", "loginScreen")
                            }.addOnFailureListener {
                                listenerAuth.onFailure("Erro inesperado!")
                            }
                    }
                }.addOnFailureListener {
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

    fun login(email: String, senha: String, listenerAuth: ListenerAuth) {
        if (email.isEmpty() || senha.isEmpty()) {
            listenerAuth.onFailure("Complete todos os campos!")
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
                if (it.isSuccessful) {
                    listenerAuth.onSuccess("Logado com sucesso!", "homeScreen")
                }
            }.addOnFailureListener { exception ->
                val error = when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect password"
                    is FirebaseNetworkException -> "No internet connection"
                    else -> "Invalid email"
                }
                listenerAuth.onFailure(error)
            }
        }
    }

    fun forgotPassword(email: String, listenerAuth: ListenerAuth) {
        if (email.isEmpty()) {
            listenerAuth.onFailure("Email nao pode estar vazio")
        } else {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    listenerAuth.onSuccess("Cheque seu email!", "loginScreen")
                }
            }
                .addOnFailureListener { exception ->
                    val error = when (exception) {
                        is FirebaseAuthUserCollisionException -> "This account has already been registered"
                        is FirebaseAuthInvalidCredentialsException -> "Invalid email"
                        is FirebaseNetworkException -> "No internet connection"
                        else -> "Error"
                    }
                    listenerAuth.onFailure(error)
                }
        }
    }

    fun checkUser(): Flow<Boolean> {
        val userCheck = FirebaseAuth.getInstance().currentUser

        _checkUserLogged.value = userCheck != null
        return checkUserLogged
    }

    fun userName(): StateFlow<String>{
        val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        firestore.collection("user").document(userID).get().addOnCompleteListener {
            if (it.isSuccessful){
                val name = it.result.getString("apelido").toString()
                _name.value = name
            }
        }
        return name
    }
}