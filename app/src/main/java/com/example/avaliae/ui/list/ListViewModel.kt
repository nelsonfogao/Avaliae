package com.example.avaliae.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliae.database.EmpresaDao
import com.example.avaliae.database.UsuarioFirebaseDao
import com.example.avaliae.model.Empresa
import com.example.avaliae.model.Usuario

class ListViewModel (
    private val empresaDao: EmpresaDao
) : ViewModel() {

    private val _empresas = MutableLiveData<List<Empresa>>()
    val empresas: LiveData<List<Empresa>> = _empresas

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: LiveData<Usuario?> = _usuario

    fun atualizarListaEmpresas() {

        empresaDao.all().addSnapshotListener { snapshot, error ->
            if (error != null)
                Log.i("ListViewModel", "${error.message}")
            else
                if (snapshot != null && !snapshot.isEmpty) {
                    val empresas =
                        snapshot.toObjects(Empresa::class.java)
                    _empresas.value = empresas
                }
        }

    }

    fun encerrarSessao() {
        UsuarioFirebaseDao.encerrarSessao()
        _usuario.value = null
    }
}