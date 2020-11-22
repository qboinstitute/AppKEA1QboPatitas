package qbo.com.appkea1qbopatitas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import qbo.com.appkea1qbopatitas.R

class LoginActivity : AppCompatActivity() {
    //Definimos el objeto RequestQueue
    private lateinit var colaPeticiones: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Haciendo la instancia al objeto RequestQueue
        colaPeticiones = Volley.newRequestQueue(this)

        btnregistrar.setOnClickListener {
            startActivity(Intent(this,
                    RegistroActivity::class.java))
        }

        btnlogin.setOnClickListener {
            btnlogin.isEnabled = false
            pblogin.visibility = View.VISIBLE
            if(validarUsuarioPassword()){
                //Realizamos la petición a la API Rest de Login
                autenticarUsuarioApiRest(it,
                        etusuario.text.toString(),
                        etpassword.text.toString())
            }else{
                btnlogin.isEnabled = true
                pblogin.visibility = View.INVISIBLE
                mostrarMensaje(it, getString(R.string.msgerrorusuriopass))
            }
        }
    }

    private fun autenticarUsuarioApiRest(vista: View, usuario: String, password: String){
        val urlapirestlogin = "http://www.kreapps.biz/patitas/login.php"
        val parametros = JSONObject()
        parametros.put("usuario", usuario)
        parametros.put("password", password)
        val request = JsonObjectRequest(
                Request.Method.POST,
                urlapirestlogin,
                parametros,
                { response ->
                    if(response.getBoolean("rpta")){
                        startActivity(Intent(this,
                                MainActivity::class.java))
                        finish()
                    }else{
                        mostrarMensaje(vista, response.getString("mensaje"))
                    }
                    btnlogin.isEnabled = true
                    pblogin.visibility = View.INVISIBLE
                },{ error ->
            Log.i("ErorLogin", error.message.toString())
            mostrarMensaje(vista, getString(R.string.msgerrorapilogin))
            btnlogin.isEnabled = true
            pblogin.visibility = View.INVISIBLE
        })

        colaPeticiones.add(request)
    }

    fun validarUsuarioPassword(): Boolean{
        var respuesta = true
        if(etusuario.text.toString().trim().isEmpty()){
            etusuario.isFocusableInTouchMode = true
            etusuario.requestFocus()
            respuesta = false
        }else if(etpassword.text.toString().trim().isEmpty()){
            etpassword.isFocusableInTouchMode = true
            etpassword.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun mostrarMensaje(vista: View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }
}