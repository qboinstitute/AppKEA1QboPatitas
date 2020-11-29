package qbo.com.appkea1qbopatitas.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_lista_mascota.*
import qbo.com.appkea1qbopatitas.R
import qbo.com.appkea1qbopatitas.adapters.MascotaAdapter
import qbo.com.appkea1qbopatitas.model.Mascota

class ListaMascotaFragment : Fragment() {

    private lateinit var queue: RequestQueue

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        queue = Volley.newRequestQueue(context)
        val vista = inflater.inflate(R.layout.fragment_lista_mascota, container, false)
        val rvmascota: RecyclerView = vista.findViewById(R.id.rvmascota)
        rvmascota.layoutManager = LinearLayoutManager(context)
        listarMascotaPerdidasWS(vista.context)
        return vista
    }

    private fun listarMascotaPerdidasWS(context: Context){
        val lstmascota : ArrayList<Mascota> = ArrayList()
        val urlwslista = "http://www.kreapps.biz/patitas/mascotaperdida.php"
        val request = JsonArrayRequest(
            Request.Method.GET,
            urlwslista,
            null,
            {response ->
                for(i in 0 until response.length()){
                    val item = response.getJSONObject(i)
                    lstmascota.add(
                        Mascota(
                        item["nommascota"].toString(),
                            item["fechaperdida"].toString(),
                            item["urlimagen"].toString(),
                            item["lugar"].toString(),
                            item["contacto"].toString()))
                }
                rvmascota.adapter = MascotaAdapter(lstmascota, context)
            },{
                Log.e("LISTAR", it.message.toString())
            }
        )
        queue.add(request)
    }

}