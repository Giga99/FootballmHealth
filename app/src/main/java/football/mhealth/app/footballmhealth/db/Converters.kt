package football.mhealth.app.footballmhealth.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import football.mhealth.app.footballmhealth.entities.Gender
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    fun toBitmap(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    @TypeConverter
    fun fromBitmap(bmp: Bitmap): ByteArray {
        val outputStream  = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toGender(gender: String): Gender {
        return if(gender == "M") Gender.Male else Gender.Female
    }

    @TypeConverter
    fun fromGender(gender: Gender): String {
        return if(gender == Gender.Male) "M" else "F"
    }
}