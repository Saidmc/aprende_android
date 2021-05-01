package com.cognitus.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.cognitus.myapplication2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), Validator.ValidationListener {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val validator: Validator = Validator(binding)
        validator.setValidationListener(this)
        binding.setClickListener {
            when (it!!.id) {
                binding.btnEva.id -> {
                    validator!!.toValidate()
                    //AlertDialogCustom.createAlert(this,"Titulo","Hola es un mensaje")
                }
            }
        }

    }

    override fun onValidationSuccess() {
        //Toast.makeText(this, "Todo ok ", Toast.LENGTH_LONG).show()
        val intent = Intent(this, RegistroActivity::class.java).apply {
            AlertDialogCustom.createAlert(this@LoginActivity,"Validación de campos","Hola, Todo OK")
        }
        startActivity(intent)
    }

    override fun onValidationError() {
        Toast.makeText(this@LoginActivity, "Dados inválidos!", Toast.LENGTH_SHORT).show()
    }
}