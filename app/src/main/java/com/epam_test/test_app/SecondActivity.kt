package com.epam_test.intent_test

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class SecondActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQ_CODE = 1000;
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE);

        val ButtonWhereMe = findViewById<Button>(R.id.ButtonWhereMe)
        ButtonWhereMe.setOnClickListener{ getCurrentLocation()}
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }
    private fun getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //если вдруг отозвали разрешение
            Toast.makeText(this, "У приложения нет разрешений на определение местоположения!", Toast.LENGTH_LONG).show()
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                latitude = location.latitude
                longitude = location.longitude
                val YourLocationTextView =  findViewById<TextView>(R.id.TextViewMyCoordination)
                YourLocationTextView.text = "Твои координаты:\nШирота - ${latitude.toFloat()}\nДолгота - ${longitude.toFloat()} "
            }
            .addOnFailureListener {
                Toast.makeText(this, "Не смогли получить твои координаты.",
                    Toast.LENGTH_SHORT).show()
            }
    }




}