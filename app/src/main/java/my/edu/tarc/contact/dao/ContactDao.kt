package my.edu.tarc.contact.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import my.edu.tarc.contact.model.Contact

@Dao
interface ContactDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun getAll(): LiveData<List<Contact>>

    @Query("SELECT * FROM Contact WHERE phone = :phone")
    fun findByPhone(phone: String): Contact

    @Query("SELECT * FROM contact WHERE name LIKE  :name")
    fun findByName(name: String): Contact
}