package com.exa.android.quotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

  // the data needed to insert in room we annotate it with Insert and specify the fun
  @Insert
  suspend fun insertData(user : User)


  // -> takes the updated data, or sometimes the particular id data needed to update then the
  // updated id data is provided then the specific id data is passed.
  // for every fun we need to specify the annotation for its type
  @Update
  suspend fun updateUser(user : User)

  @Update
  suspend fun updataAllUser(allUser : List<User>)

  // if we want the data to delete we annotate with Delete
  @Delete
  suspend fun deleteUser(user : User)

  @Query("SELECT  * FROM User")
  fun getAllUser() : LiveData<List<User>>


  // Retrieves Users with an ID between the specified minId and maxId.

  @Query("SELECT * FROM User WHERE id BETWEEN :minId AND :maxId")
  fun getUsersWithinIdRange(minId: Int, maxId: Int): List<User>

  // Finds Users whose first name or last name matches the search term.
  @Query("SELECT * FROM user  WHERE first_name LIKE :search OR lastName LIKE :search")
  fun findUserWithName(search: String): List<User>
}

/* NOTE: SQL queries in Room are case-insensitive, so you can write them in uppercase or lowercase.

* So this is the interface that is implemented by the android to provide ease and remove boilder plate code
so here we just define fun and there return type. the whole implementation is done by android itself.

We have created every fun suspend to run it on background except for Query fun because it auto runs on backthread

So query is used to retrive data from database based on our different needs we can get its
output in form of our customized class ie if we have a large data set and if we need to get the user name
with name starts with 'A' and its mobile no then we specify data class and query based on that
or without creating data class we can use multimap for it.
To learn more see the notes Dao Access in Room folder or prefer android Docs

* */
