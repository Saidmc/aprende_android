package com.cognitus.myapplication2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.cognitus.myapplication2.databinding.ActivityOlvideBinding

class OlvideActivity : AppCompatActivity(), Validator.ValidationListener {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityOlvideBinding>(this, R.layout.activity_olvide)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val validator: Validator = Validator(binding)
        validator.setValidationListener(this)
        binding.setClickListener {
            when (it!!.id) {
                binding.btnEva.id -> {
                    validator!!.toValidate()
                }
            }
        }

    }

    override fun onValidationSuccess() {
        Toast.makeText(this, "Todo ok ", Toast.LENGTH_LONG).show()
    }

    override fun onValidationError() {
        Toast.makeText(this@OlvideActivity, "Dados inválidos!", Toast.LENGTH_SHORT).show()
    }
}