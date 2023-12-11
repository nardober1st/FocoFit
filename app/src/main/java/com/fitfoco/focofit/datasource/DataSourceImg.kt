package com.fitfoco.focofit.datasource

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.fitfoco.focofit.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.UUID

class DataSourceImg {

    private val storage = FirebaseStorage.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val nomeArquivo = UUID.randomUUID().toString()
    private val auth = FirebaseAuth.getInstance()
    private val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

    fun salvePic(pic: Uri, context: Context) {
        Glide.with(context).asBitmap().load(pic).apply(RequestOptions.overrideOf(34, 34))
            .listener(object : RequestListener<Bitmap> {
                @SuppressLint("CheckResult")
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onResourceReady(
                    bitmap: Bitmap,
                    model: Any,
                    target: Target<Bitmap>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val data = baos.toByteArray()

                    val storageRef = storage.getReference("/image/${nomeArquivo}")
                    storageRef.putBytes(data).addOnCompleteListener {
                        storageRef.downloadUrl.addOnSuccessListener { uri ->
                            val picture = uri.toString()

                            val map = hashMapOf(
                                "pic" to picture
                            )

                            val docRef = db.collection("image").document(userID)
                            docRef.set(map).addOnCompleteListener {

                            }
                        }
                    }
                    return false
                }

            }).submit()
    }
//    fun getImage(image: Uri, context: Context){
//        val docRef = db.collection("image").document(userID)
//        docRef.addSnapshotListener { doc, _ ->
//            if (doc != null){
//                Glide.with(context).load(doc.getString("pic")).placeholder(R.drawable.ic_about).into(image)
//            }
//        }
//    }
}