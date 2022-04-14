package com.ds.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ds.tiptime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
     // lateinit -> demora/espera o inflate para fazer o binding como se fosse o defer do javascript
    //binding -> faz a conexão do layout com o codigo(deixa visivel pro codigo os componentes do layout)
    //binding usado no lugar do findViewById(R.id.[id do componente])
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{calcularTip()}
    }

    fun calcularTip(){
        val stringInTextField = binding.costOfService.text.toString()//editable ->texto que pode ser modificado
        //o editText é um editable então para depois tranforma-lo em decimal(double) converte o editable em string
        val cost = stringInTextField.toDouble()

        val selectId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.08
        }
        //Observe que var é usado em vez de val. Isso ocorre porque pode ser necessário arredondar
        // o valor se o usuário tiver selecionado essa opção. Assim, o valor poderá mudar.

        var tip = tipPercentage * cost
        //usa o isChecked para saber se o elemento ta marcado ou não
        //ceil -> arredondar para cima
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }


    }
}