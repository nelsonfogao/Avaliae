package com.example.avaliae.ui.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avaliae.database.EmpresaDao

class FormEmpresasViewModelFactory (
    val empresaDao: EmpresaDao,
    val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)){
            return FormViewModel(empresaDao, application) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }

}