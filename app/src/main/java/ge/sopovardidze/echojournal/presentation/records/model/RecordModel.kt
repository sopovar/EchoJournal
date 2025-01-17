package ge.sopovardidze.echojournal.presentation.records.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class RecordModel(
    val id: String,
    val date: Long,
    val mood: FilterType.Mood,
    val topics: List<FilterType.Topics>,
    val title: String,
    val description: String,
) {
    fun isSameDay(date1: Long, date2: Long): Boolean {
        val calendar1 = Calendar.getInstance().apply { timeInMillis = date1 }
        val calendar2 = Calendar.getInstance().apply { timeInMillis = date2 }

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }
}

fun RecordModel.formattedDate(): String {
    val currentDate = System.currentTimeMillis()
    val calendar = Calendar.getInstance()

    // Check if the date is today
    if (isSameDay(date, currentDate)) {
        return "Today"
    }

    // Check if the date is yesterday
    if (isSameDay(date, currentDate - 24 * 60 * 60 * 1000)) {
        return "Yesterday"
    }

    // Otherwise, format as 'dd MMM'
    val dateFormat = SimpleDateFormat("d MMM", Locale.getDefault())
    return dateFormat.format(Date(date))
}
