package football.mhealth.app.footballmhealth.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import football.mhealth.app.footballmhealth.utils.GAME_TABLE

@Entity(tableName = GAME_TABLE)
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val team1Name: String = "",
    val team1PlayerNames: List<String>? = null,
    val team1NumberOfGoals: Int = 0,
    val team2Name: String = "",
    val team2PlayerNames: List<String>? = null,
    val team2NumberOfGoals: Int = 0
)
