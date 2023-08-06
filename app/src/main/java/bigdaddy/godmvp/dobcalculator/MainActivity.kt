package bigdaddy.godmvp.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView?=null
    private var tvAgeInMinutes: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button =findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {clickDatePicker()}

    }

    private fun clickDatePicker(){
        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(
            this,
            { view, SelectedYear, SelectedMonth, SelectedDayOfMonth ->

                Toast.makeText(this,
                    "Selecetd year $SelectedYear, Selected Month ${SelectedMonth+1}, Selected Day of month $SelectedDayOfMonth",
                    Toast.LENGTH_LONG).show()
                val SelectedDate="$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"

                tvSelectedDate?.text=SelectedDate

                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate=sdf.parse(SelectedDate)
                theDate?.let{
                    val SelectedDateInMinutes=theDate.time/60000

                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes=currentDate.time/60000

                        val differnceInMinutes=currentDateInMinutes-SelectedDateInMinutes

                        tvAgeInMinutes?.text=differnceInMinutes.toString()

                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}

