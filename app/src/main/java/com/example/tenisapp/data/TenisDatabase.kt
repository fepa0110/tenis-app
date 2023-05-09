import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tenisapp.data.model.Tournament

@Database(
    entities = [
        Tournament::class
    ],
    version = 1
)
abstract class TenisDatabase : RoomDatabase() {
    abstract fun tournamentsDao(): TournamentDao
}