package football.mhealth.app.footballmhealth.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import football.mhealth.app.footballmhealth.utils.PLAYER_TABLE

@Entity(tableName = PLAYER_TABLE)
data class Player(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val img: Bitmap? = null,
    val firstName: String = "",
    val lastName: String = "",
    val numOfYears: Int = 0,
    val gender: Gender? = null,
    val numOfYearsPlaying: Int = 0,
    val averageHoursWeekly: Double = 0.0,
    val averageRating: Double = 0.0
)

sealed class Gender {

    object Male : Gender()

    object Female : Gender()
}
