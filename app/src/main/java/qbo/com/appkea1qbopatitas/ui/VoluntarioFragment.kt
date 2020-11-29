package qbo.com.appkea1qbopatitas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_voluntario.*
import org.json.JSONObject
import qbo.com.appkea1qbopatitas.R


class VoluntarioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /*btnregistrarvoluntario.setOnClickListener {
            if(cbaceptar.isChecked){
                btnregistrarvoluntario.isEnabled = false
                registrarVoluntarioWS(it)
            }else{
                mostrarMensaje(it, "Es necesario aceptar los términos y condiciones")
            }
        }*/
        return inflater.inflate(R.layout.fragment_voluntario, container, false)
    }

    private fun registrarVoluntarioWS(vista: View) {
        val urlwsvoluntario = "http://www.kreapps.biz/patitas/personavoluntaria.php"
        val jsonparametros = JSONObject()
        jsonparametros.put("idpersona", 0)
    }

    private fun actualizarFormulario(){
        btnregistrarvoluntario.visibility = View.GONE
        cbaceptar.visibility = View.GONE
        tvtextovoluntario.visibility = View.GONE
        tvtitulovoluntario.text = getString(R.string.valtextoesvoluntario)
    }

    private fun mostrarMensaje(vista: View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }
}