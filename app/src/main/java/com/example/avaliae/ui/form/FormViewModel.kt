package com.example.avaliae.ui.form

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.avaliae.database.EmpresaDao
import com.example.avaliae.database.UsuarioFirebaseDao
import com.example.avaliae.model.Empresa
import com.example.avaliae.model.Usuario

class FormViewModel (
    private val empresaDao: EmpresaDao,
    application: Application
) : AndroidViewModel(application) {

    private val app = application


    private val _empresas = MutableLiveData<Empresa>()
    val empresas: LiveData<Empresa> = _empresas

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String?>()
    val msg: LiveData<String?> = _msg

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: LiveData<Usuario?> = _usuario

    init {
        _status.value = false
        _msg.value = null
    }

    fun salvarEmpresas(nome:String, bairro:String, nota:Int, pergunta1:Int, pergunta2:Int, pergunta3:Int, pergunta4:Int, pergunta5:Int, autor: String) {
        _status.value = false
        _msg.value = "Por favor, aguarde a persistência!"

        val empresas = Empresa(nome,bairro, nota, pergunta1, pergunta2, pergunta3, pergunta4, pergunta5, autor)

        empresaDao.create(empresas)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada!"
            }
            .addOnFailureListener {
                _msg.value = "Persistência falhou!"
                Log.e("EmpresaDaoFirebase", "${it.message}")
            }
    }


    fun getUsuario(): String? {
        return UsuarioFirebaseDao.mAuth.currentUser.email
    }

    fun selectEmpresa(id: String) {
        empresaDao.read(id)
            .addOnSuccessListener {
                val empresas = it.toObject(Empresa::class.java)
                if (empresas != null)
                    _empresas.value = empresas!!
                else
                    _msg.value = "A empresa foi encontrada."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }

    }
}