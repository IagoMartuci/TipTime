package com.example.tiptime

import android.icu.util.IslamicCalendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main) "Não estava retornando o resultado da função calculateTip(), troquei pelo setContentView(binding.root) e deu certo."
        binding.botaoCalcular.setOnClickListener{calculateTip()}
    }

    fun calculateTip() {
        val stringInTextField = binding.valorServico.text.toString()
        val custoServico = stringInTextField.toDouble()
        val selectedId = binding.grupoBotoes.checkedRadioButtonId
        val tipPercentage = when (selectedId){
            R.id.botaoAmazing -> 0.20
            R.id.botaoGood -> 0.18
            else -> 0.15
        }
        var tipResult = tipPercentage * custoServico
        val roundUp = binding.switchArredondamento.isChecked
        if (roundUp){
            tipResult = kotlin.math.ceil(tipResult)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipResult)
        binding.exibirResultado.text = formattedTip
        //binding.exibirResultado.text = getString(R.string.tip_amount, formattedTip)
    }

}