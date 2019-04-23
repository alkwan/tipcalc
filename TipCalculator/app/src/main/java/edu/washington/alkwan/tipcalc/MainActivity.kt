package edu.washington.alkwan.tipcalc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher

import kotlinx.android.synthetic.main.content_main.*
import java.math.BigDecimal
import java.math.RoundingMode
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amount.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s : Editable) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button.isEnabled = !amount.text.isNullOrEmpty()
                val inputText = amount.text.toString()
                if (inputText.contains(".") && inputText.substring(inputText.indexOf(".") + 1).length > 2) {
                    amount.setText(inputText.substring(0, inputText.length - 1))
                    amount.setSelection(amount.text.toString().length)
                }
            }
        })

        button.setOnClickListener{
            calculateTip()
        }
    }

    private fun calculateTip() {
        val inputAmount = amount.text.toString().toDouble()
        val tipAmount = BigDecimal(inputAmount * 0.15).setScale(2, RoundingMode.HALF_UP)
        val text = "You should tip \$$tipAmount"
        val duration = Toast.LENGTH_LONG

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}
