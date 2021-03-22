package com.example.avaliae.database

import com.example.avaliae.model.Empresa
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

class EmpresaFirestoreDao: EmpresaDao {

    private val collection = FirebaseFirestore
        .getInstance()
        .collection("empresas")



    override fun create(empresa: Empresa): Task<DocumentReference> {
        return collection
            .add(empresa)
    }


    override fun read(id: String): Task<DocumentSnapshot> {
        return collection
            .document(id)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }

    override fun allDocuments(): Task<QuerySnapshot> {
        return collection.get()
    }

}