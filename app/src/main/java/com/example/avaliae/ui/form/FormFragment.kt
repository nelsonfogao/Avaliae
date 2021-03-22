package com.example.avaliae.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.avaliae.R
import com.example.avaliae.database.AppUtil
import com.example.avaliae.database.EmpresaFirestoreDao
import com.example.avaliae.database.UsuarioFirebaseDao
import com.example.avaliae.model.Empresa
import kotlinx.android.synthetic.main.form_fragment.*

class FormFragment : Fragment() {

    private lateinit var formViewModel: FormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_fragment, container, false)

        val application = requireActivity().application
        val formEmpresasViewModelFactory =
            FormEmpresasViewModelFactory(EmpresaFirestoreDao(), application)

        formViewModel = ViewModelProvider(
            this, formEmpresasViewModelFactory)
            .get(FormViewModel::class.java).apply {
                setUpMsgObserver(this)
                setUpStatusObserver(this)
            }

        formViewModel.empresas.observe(viewLifecycleOwner, Observer {
            if (it != null){
                preencherFormulario(it)
            }
        })

        return view
    }

    private fun setUpStatusObserver(formViewModel: FormViewModel) {
        formViewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if (status)
                findNavController().popBackStack()
        })
    }
    private fun setUpMsgObserver(formViewModel: FormViewModel) {
        formViewModel.msg.observe(viewLifecycleOwner, Observer { msg ->
            if (!msg.isNullOrBlank()) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (AppUtil.empresaSelecionada != null) {
            buttonEnviar.visibility = View.GONE
            formViewModel.selectEmpresa(AppUtil.empresaSelecionada!!.id!!)
        }

        else {
            buttonEnviar.visibility = View.VISIBLE
        }
        buttonEnviar.setOnClickListener {

            val nome = formEditTextName.text.toString()
            val bairro = formEditTextBairro.text.toString()
            var pergunta1 = 0
            var pergunta2 = 0
            var pergunta3 = 0
            var pergunta4 = 0
            var pergunta5 = 0
            var nota = 0



            if(radioButtonPergunta1sim.isChecked){
                pergunta1 = 1
            }else if (radioButtonPergunta1nao.isChecked){
                pergunta1 = 0
            }
            if(radioButtonPergunta2sim.isChecked){
                pergunta2 = 1
            }else if (radioButtonPergunta2nao.isChecked){
                pergunta2 = 0
            }
            if(radioButtonPergunta3sim.isChecked){
                pergunta3 = 1
            }else if (radioButtonPergunta3nao.isChecked){
                pergunta3 = 0
            }
            if(radioButtonPergunta4sim.isChecked){
                pergunta4 = 1
            }else if (radioButtonPergunta4nao.isChecked){
                pergunta4 = 0
            }
            if(radioButtonPergunta5sim.isChecked){
                pergunta5 = 1
            }else if (radioButtonPergunta1nao.isChecked){
                pergunta5 = 0
            }

            var autor = formViewModel.getUsuario().toString()


            nota = pergunta1 + pergunta2 + pergunta3 + pergunta4 + pergunta5
            formViewModel.salvarEmpresas(nome,bairro, nota, pergunta1, pergunta2, pergunta3, pergunta4, pergunta5, autor)
        }



    }
    private fun preencherFormulario(empresas: Empresa){
        formEditTextName.setText(empresas.nome)
        formEditTextBairro.setText(empresas.bairro)

        if(empresas.pergunta1 == 0){
            radioButtonPergunta1nao.isChecked = true
        }else {
            radioButtonPergunta1sim.isChecked = true
        }
        if(empresas.pergunta2 == 0){
            radioButtonPergunta2nao.isChecked = true
        }else {
            radioButtonPergunta2sim.isChecked = true
        }
        if(empresas.pergunta3 == 0){
            radioButtonPergunta3nao.isChecked = true
        }else {
            radioButtonPergunta3sim.isChecked = true
        }
        if(empresas.pergunta4 == 0){
            radioButtonPergunta4nao.isChecked = true
        }else {
            radioButtonPergunta4sim.isChecked = true
        }
        if(empresas.pergunta5 == 0){
            radioButtonPergunta5nao.isChecked = true
        }else {
            radioButtonPergunta5sim.isChecked = true
        }
    }
}