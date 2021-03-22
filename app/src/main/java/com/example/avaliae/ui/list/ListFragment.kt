package com.example.avaliae.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avaliae.R
import com.example.avaliae.RecyclerAdapter
import com.example.avaliae.database.AppUtil
import com.example.avaliae.database.EmpresaFirestoreDao
import com.example.avaliae.database.UsuarioFirebaseDao
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        verificarUsuario()

        val view =  inflater.inflate(R.layout.list_fragment, container, false)

        val listEmpresasViewModelFactory = ListEmpresasViewModelFactory(EmpresaFirestoreDao())
        listViewModel = ViewModelProvider(this, listEmpresasViewModelFactory)
            .get(ListViewModel::class.java)


        listViewModel.empresas.observe(viewLifecycleOwner, Observer {
            recyclerViewRestaurantes.adapter = RecyclerAdapter(it) {
                AppUtil.empresaSelecionada = it
                findNavController().navigate(R.id.formFragment)
            }
            recyclerViewRestaurantes.layoutManager = LinearLayoutManager(requireContext())
        })
        listViewModel.atualizarListaEmpresas()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fabCriar.setOnClickListener {
            AppUtil.empresaSelecionada = null
            findNavController().navigate(R.id.formFragment)
        }
    }

    fun verificarUsuario(){
        if (UsuarioFirebaseDao.mAuth.currentUser == null)
            findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogout.setOnClickListener {
            listViewModel.encerrarSessao()
            findNavController().navigate(R.id.loginFragment)

        }
    }

}