package qbo.com.appkea1qbopatitas.repository

import androidx.lifecycle.LiveData
import qbo.com.appkea1qbopatitas.db.dao.PersonaDao
import qbo.com.appkea1qbopatitas.db.entity.PersonaEntity

class PersonaRepository (private val personaDao: PersonaDao) {

    suspend fun insertar(persona: PersonaEntity){
        personaDao.insertar(persona)
    }

    suspend fun actualizar(persona: PersonaEntity){
        personaDao.actualizar(persona)
    }

    suspend fun eliminarTodo(){
        personaDao.eliminarTodo()
    }

    fun obtener(): LiveData<PersonaEntity>{
        return personaDao.obtener()
    }
}