package com.cognitus.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.cognitus.myapplication2.databinding.ActivityRegistroBinding

class RegistroActivity : ToolbarActivity(), Validator.ValidationListener {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityRegistroBinding>(this, R.layout.activity_registro)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreference = SharedPreference(this)

        inicializarToolbar(binding.barraP, "Registro", 0)
        binding.barraP.ivBtn1.setOnClickListener {
            Toast.makeText(this, "Botón de barra ", Toast.LENGTH_LONG).show()
        }

        val validator: Validator = Validator(binding)
        validator.setValidationListener(this)
        binding.setClickListener {
            when (it!!.id) {
                binding.btnEva.id -> {
                    validator!!.toValidate()
                }
                binding.btnSave.id -> {
                    sharedPreference.save("name", "prueba de nombre")
                    Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
                }
                binding.btnVer.id -> {
                    if (sharedPreference.getValueString("name") != null) {
                        Toast.makeText(
                            this,
                            "Datos guardados->" + sharedPreference.getValueString("name"),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }

    override fun onValidationSuccess() {
        Toast.makeText(this, "Todo ok ", Toast.LENGTH_LONG).show()

        val intent = Intent(this, CerrarActivity::class.java).apply {

        }
        startActivity(intent)
    }

    override fun onValidationError() {
        Toast.makeText(this@RegistroActivity, "Dados inválidos!", Toast.LENGTH_SHORT).show()
    }
}