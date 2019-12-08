package com.example.movieshub.main.activities.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.app.DatePickerDialog
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R
import android.annotation.SuppressLint
import android.text.InputType
import android.view.View
import kotlinx.android.synthetic.main.activity_add_account.*
import java.util.*


class AddAccountActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.movieshub.R.layout.activity_add_account)

        etDateOfBirth.inputType = InputType.TYPE_NULL
        etDateOfBirth.setOnClickListener {
            val cldr = Calendar.getInstance()
            val day = cldr.get(Calendar.DAY_OF_MONTH)
            val month = cldr.get(Calendar.MONTH)
            val year = cldr.get(Calendar.YEAR)
            // date picker dialog
            val picker = DatePickerDialog(this@AddAccountActivity,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    etDateOfBirth.setText(
                        dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                    )
                }, year, month, day
            )
            picker.show()
        }
    }
}
