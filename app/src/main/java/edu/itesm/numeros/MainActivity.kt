package edu.itesm.numeros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.itesm.numeros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var numbers = mutableListOf<Int>()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addButtons()
    }

    fun addButtons() {
        this.binding.agregar.setOnClickListener {
            addNumber()
        }
        this.binding.promedio.setOnClickListener {
            showPromedio()
        }
        this.binding.mayor.setOnClickListener {
            showMayor()
        }
        this.binding.pares.setOnClickListener {
            showEvenNumbers()
        }
    }

    fun addNumber() {
        val newInt = this.binding.editTextNumberSigned.text.toString().toIntOrNull()
        if (newInt == null) {
            Toast.makeText(this, "Error: Add an integer", Toast.LENGTH_SHORT).show()
            return
        }
        numbers.add(newInt)
        Toast.makeText(this, "Added: ${newInt}", Toast.LENGTH_SHORT).show()
        this.binding.editTextNumberSigned.text.clear()
        updateDisplayNumbers()
    }

    fun doNumOperations(op: (MutableList<Int>) -> String) {
        if (this.numbers.isEmpty()) {
            Toast.makeText(this, "Error: Number list is empty", Toast.LENGTH_SHORT).show()
            return
        }
        displayResult(op(this.numbers))
    }

    fun showPromedio() {
        doNumOperations {
            "Promedio: ${it.average().toString()}"
        }
    }

    fun showMayor() {
        doNumOperations {
            "Max: ${it.maxOrNull()!!.toString()}"
        }
    }

    fun showEvenNumbers() {
        doNumOperations {
            val evens = it.filter { it % 2 == 0 }
            "Num pares: ${evens.toString()}"
        }
    }

    fun updateDisplayNumbers() {
        this.binding.textView2.text = "${this.numbers.toString()}"
    }

    fun displayResult(text: String) {
        this.binding.resultados.text = text
    }

}