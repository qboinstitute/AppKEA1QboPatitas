package qbo.com.appkea1qbopatitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro.*
import org.json.JSONObject
import qbo.com.appkea1qbopatitas.R

class RegistroActivity : AppCompatActivity() {

    private lateinit var colaPeticiones : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        colaPeticiones = Volley.newRequestQueue(this)
        btnregistrarusuario.setOnClickListener {
            btnregistrarusuario.isEnabled = false
            pbregistro.visibility = View.VISIBLE
            if(validarFormulario(it)){
                registrarUsuarioApiRest(it)
            }else{
                btnregistrarusuario.isEnabled = true
                pbregistro.visibility = View.GONE
            }
        }
    }

    private fun registrarUsuarioApiRest(vista: View){
        val urlapirestregistro = "http://www.kreapps.biz/patitas/persona.php"
        val parametro = JSONObject()
        parametro.put("nombres", etnombrereg.text.toString())
        parametro.put("apellidos", etapellidoreg.text.toString())
        parametro.put("email", etemailreg.text.toString())
        parametro.put("celular", etcelularreg.text.toString())
        parametro.put("usuario", etusuarioreg.text.toString())
        parametro.put("password", etpasswordreg.text.toString())
        val request = JsonObjectRequest(
            Request.Method.PUT,
            urlapirestregistro,
            parametro,
            { response ->
                if(response.getBoolean("rpta")){
                    setearControles()
                }
                mostrarMensaje(vista, response.getString("mensaje"))
                btnregistrarusuario.isEnabled = true
                pbregistro.visibility = View.GONE
            },{ error->
                Log.e("ErrorRegUsu", error.message.toString())
                btnregistrarusuario.isEnabled = true
                pbregistro.visibility = View.GONE
            }
        )
        colaPeticiones.add(request)
    }

    private fun setearControles(){
        etnombrereg.setText("")
        etapellidoreg.setText("")
        etemailreg.setText("")
        etcelularreg.setText("")
        etusuarioreg.setText("")
        etpasswordreg.setText("")
        etpasswordreg2.setText("")
    }

    private fun validarFormulario(vista: View): Boolean{
        var respuesta = true
        when{
            etnombrereg.text.toString().trim().isEmpty() -> {
                etnombrereg.isFocusableInTouchMode = true
                etnombrereg.requestFocus()
                mostrarMensaje(vista, getString(R.string.msgerrordatosreg))
                respuesta = false
            }
            etapellidoreg.text.toString().trim().isEmpty() -> {
                etapellidoreg.isFocusableInTouchMode = true
                etapellidoreg.requestFocus()
                mostrarMensaje(vista, getString(R.string.msgerrordatosreg))
                respuesta = false
            }
            etemailreg.text.toString().trim().isEmpty() -> {
                etemailreg.isFocusableInTouchMode = true
                etemailreg.requestFocus()
                mostrarMensaje(vista, getString(R.string.msgerrordatosreg))
                respuesta = false
            }
            etcelularreg.text.toString().trim().isEmpty() -> {
                etcelularreg.isFocusableInTouchMode = true
                etcelularreg.requestFocus()
                mostrarMensaje(vista, getString(R.string.msgerrordatosreg))
                respuesta = false
            }
            etusuarioreg.text.toString().trim().isEmpty() -> {
                etusuarioreg.isFocusableInTouchMode = true
                etusuarioreg.requestFocus()
                mostrarMensaje(vista, getString(R.string.msgerrordatosreg))
                respuesta = false
            }
            etpasswordreg.text.toString().trim().isEmpty() -> {
                etpasswordreg.isFocusableInTouchMode = true
                etpasswordreg.requestFocus()
                mostrarMensaje(vista, getString(R.string.msgerrordatosreg))
                respuesta = false
            }
            etpasswordreg.text.toString().trim().isNotEmpty() -> {
                if(etpasswordreg.text.toString() != etpasswordreg2.text.toString()){
                    etpasswordreg.isFocusableInTouchMode = true
                    etpasswordreg.requestFocus()
                    mostrarMensaje(vista, getString(R.string.msgerrorpasswordreg))
                    respuesta = false
                }

            }
        }
        return respuesta
    }

    private fun mostrarMensaje(vista: View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }
}