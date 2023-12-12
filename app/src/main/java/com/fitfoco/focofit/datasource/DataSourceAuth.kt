package com.fitfoco.focofit.datasource

import android.util.Log
import com.fitfoco.focofit.data.model.User
import com.fitfoco.focofit.listener.ListenerAuth
import com.fitfoco.focofit.navigation.authnavgraph.AuthRoutes
import com.fitfoco.focofit.navigation.rootnavgraph.RootGraphRoutes
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DataSourceAuth @Inject constructor(
    private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore
) {

    private val _checkUserLogged = MutableStateFlow(false)
    private val checkUserLogged: StateFlow<Boolean> = _checkUserLogged

    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name

    private val _imc = MutableStateFlow("")
    private val imc: StateFlow<String> = _imc

    fun googleSignIn(credential: AuthCredential, listenerAuth: ListenerAuth) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                listenerAuth.onSuccess("Logado com sucesso!", RootGraphRoutes.MainGraphRoute.route)
            }
        }.addOnFailureListener { exception ->
            val error = when (exception) {
                is FirebaseNetworkException -> "No internet connection"
                else -> "Unkown Error"
            }
            listenerAuth.onFailure(error)
        }

    }

    fun signup(
        user: User,
        senha: String,
        listenerAuth: ListenerAuth
    ) {
        if (user.email.isEmpty() || senha.isEmpty() || user.apelido.isEmpty() || user.name.isEmpty()) {
            listenerAuth.onFailure("Complete todos os campos!")
        } else {
            firebaseAuth.createUserWithEmailAndPassword(user.email, senha)
                .addOnCompleteListener { signup ->
                    if (signup.isSuccessful) {
                        val userId = firebaseAuth.currentUser?.uid.toString()
                        val map = hashMapOf(
                            "email" to user.email,
                            "apelido" to user.apelido,
                            "nome" to user.name,
                            "gender" to user.gender,
                            "data de nascimento" to user.dataNascimento
                        )
                        firestore.collection("user").document(userId).set(map)
                            .addOnCompleteListener {
                                listenerAuth.onSuccess(
                                    "Conta criada com sucesso",
                                    AuthRoutes.LoginRoute.route
                                )
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
                    Log.d("TAGY", "User: ${firebaseAuth.currentUser?.email.toString()}")
                }
            }.addOnFailureListener { exception ->
                val error = when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect password"
                    is FirebaseNetworkException -> "No internet connection"
                    else -> "Invalid email"
                }
                listenerAuth.onFailure(error)
                Log.d("TAGY", "User: ${firebaseAuth.currentUser.toString()}")
            }
        }
    }

    fun forgotPassword(email: String, listenerAuth: ListenerAuth) {
        if (email.isEmpty()) {
            listenerAuth.onFailure("Email nao pode estar vazio")
        } else {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    listenerAuth.onSuccess("Cheque seu email!", AuthRoutes.LoginRoute.route)
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

//    fun isUserSignedIn(): Flow<Boolean> {
//        return callbackFlow {
//            val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//                val user = firebaseAuth.currentUser
//                trySend(user != null)
//            }
//
//            // Add the listener to FirebaseAuth
//            firebaseAuth.addAuthStateListener(authStateListener)
//
//            // Remove the listener when the flow is cancelled
//            awaitClose {
//                firebaseAuth.removeAuthStateListener(authStateListener)
//            }
//        }
//    }

    fun isUserSignedIn(): Flow<Boolean> {
        return callbackFlow {
            // Immediate check for the current user's sign-in status
            val isSignedIn = firebaseAuth.currentUser != null
            trySend(isSignedIn)

            val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                trySend(user != null)
            }

            // Add the listener to FirebaseAuth
            firebaseAuth.addAuthStateListener(authStateListener)

            // Remove the listener when the flow is cancelled
            awaitClose {
                firebaseAuth.removeAuthStateListener(authStateListener)
            }
        }
    }

    fun userName(): StateFlow<String> {
        val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        firestore.collection("user").document(userID).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val name = it.result.getString("apelido").toString()
                _name.value = name
            }
        }
        return name
    }

    fun imcResult(): StateFlow<String> {
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

        firestore.collection("goals").document(userId).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val imc = it.result.getString("imc").toString()
                _imc.value = imc
            }
        }
        return imc
    }

    fun signUserOut() {
        val user = firebaseAuth.currentUser?.uid
        if (user != null) {
            firebaseAuth.signOut()
        }
        Log.d("TAGY", "User: ${user.toString()}")
    }
}
