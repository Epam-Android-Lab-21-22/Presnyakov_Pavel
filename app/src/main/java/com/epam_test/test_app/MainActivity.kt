package com.epam_test.intent_test

import NetworkMonitorUtil
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar;


class MainActivity : AppCompatActivity()  {

    private val networkMonitor = NetworkMonitorUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val internetButton =  findViewById<Button>(R.id.InternetButton)
        val nextActivityButton =  findViewById<Button>(R.id.GoToLocationActivity)
        val InternetButton =  findViewById<Button>(R.id.InternetButton)
        val parentLayout = findViewById<View>(android.R.id.content);

        nextActivityButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
        InternetButton.setOnClickListener {
            Toast.makeText(this, "Вы в танцах!", Toast.LENGTH_SHORT).show()
        }
        networkMonitor.result = { isAvailable ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        internetButton.isEnabled = true

                    }
                    false -> {
                        internetButton.isEnabled = false
                        Snackbar.make(parentLayout, "Интернетов нету!", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }
    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}

