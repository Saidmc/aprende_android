package com.cognitus.myapplication2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cognitus.myapplication2.databinding.ActivityCerrarBinding

class CerrarActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityCerrarBinding>(this, R.layout.activity_cerrar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cerrar)

        binding.setClickListener {
            when (it!!.id) {
                binding.btnPerfil.id -> {
                    val intent = Intent(this, CameraActivity::class.java).apply {

                    }
                    startActivity(intent)
                }

                binding.btnTareas.id -> {
                    val intent = Intent(this, ListadoActivity::class.java).apply {

                    }
                    startActivity(intent)
                }

                binding.btnCheckin.id -> {
                    val intent = Intent(this, FirmaActivity::class.java).apply {

                    }
                    startActivity(intent)
                }
            }
        }

    }

}
