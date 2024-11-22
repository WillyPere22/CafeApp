package com.example.cafeapp

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class OrderActivity : AppCompatActivity() {

    private lateinit var orderMessageTextView: TextView
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setupEdgeToEdge()

        // Retrieve the order message from the intent, if available
        val orderMessage = intent.getStringExtra("ORDER_MESSAGE") ?: "No order message provided"

        // Display the order message
        orderMessageTextView = findViewById(R.id.orderMessageTextView)
        orderMessageTextView.text = orderMessage

        // Initialize RadioGroup and set listener for delivery method selection
        radioGroup = findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            onRadioButtonClicked(selectedRadioButton)
        }
    }

    // Enables edge-to-edge display
    private fun setupEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Handles logic for radio button clicks in the RadioGroup
    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton && view.isChecked) {
            val deliveryOptionMessage = when (view.id) {
                R.id.sameday -> {
                    "Same Day Delivery selected"
                }
                R.id.nextday -> {
                    "Next Day Delivery selected"
                }
                R.id.pickup -> {
                    "Pickup selected"
                }
                else -> "No delivery option selected"
            }

            // Display a toast with the selected option
            Toast.makeText(this, deliveryOptionMessage, Toast.LENGTH_SHORT).show()

            // Update the order message TextView with the selected option
            orderMessageTextView.text = deliveryOptionMessage
        }
    }
}
