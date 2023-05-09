import androidx.room.Dao
import androidx.room.Query
import com.example.tenisapp.data.model.Tournament

@Dao
interface TournamentDao {
    @Query("SELECT * FROM tournament")
    fun getAll(): List<Tournament>
}