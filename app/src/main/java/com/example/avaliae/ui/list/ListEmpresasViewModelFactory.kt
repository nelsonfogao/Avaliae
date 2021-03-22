package com.example.avaliae.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avaliae.database.EmpresaDao

class ListEmpresasViewModelFactory (
    private val empresaDao: EmpresaDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java))
            return ListViewModel(empresaDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}