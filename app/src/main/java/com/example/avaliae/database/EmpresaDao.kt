package com.example.avaliae.database

import com.example.avaliae.model.Empresa
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface EmpresaDao {

    fun create(empresa: Empresa): Task<DocumentReference>
    fun read(id: String): Task<DocumentSnapshot>
    fun all(): CollectionReference
    fun allDocuments(): Task<QuerySnapshot>
}