package com.example.migrationroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = StudentDatabase.getInstance(application).subscriberDAO

        val nameEditText = findViewById<EditText>(R.id.etName)
        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val button = findViewById<Button>(R.id.btnSubmit)
        button.setOnClickListener {
            lifecycleScope.launch(IO) {
                    dao.insertStudent(Student(0, nameEditText.text.toString(), emailEditText.text.toString()))
            }
        }
    }
}
