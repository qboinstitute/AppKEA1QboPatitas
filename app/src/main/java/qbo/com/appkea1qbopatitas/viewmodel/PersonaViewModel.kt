package qbo.com.appkea1qbopatitas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import qbo.com.appkea1qbopatitas.db.PatitasRoomDatabase
import qbo.com.appkea1qbopatitas.db.entity.PersonaEntity
import qbo.com.appkea1qbopatitas.repository.PersonaRepository

class PersonaViewModel(application: Application)
    : AndroidViewModel(application) {

        private val repository : PersonaRepository

        init {
            val personaDao
            = PatitasRoomDatabase.getDatabase(application).personaDao()
            repository = PersonaRepository(personaDao)
        }

    fun insertar(persona: PersonaEntity) =
        viewModelScope.launch(Dispatchers.IO) {
        repository.insertar(persona)
    }

    fun actualizar(persona: PersonaEntity) =
        viewModelScope.launch(Dispatchers.IO) {
        repository.actualizar(persona)
    }

    fun eliminarTodo() = viewModelScope.launch(Dispatchers.IO) {
        repository.eliminarTodo()
    }

    fun obtener(): LiveData<PersonaEntity>{
        return repository.obtener()
    }

}