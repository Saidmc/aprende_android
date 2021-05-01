package com.cognitus.myapplication2
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cognitus.myapplication2.databinding.ToolbarBinding

abstract class ToolbarActivity : AppCompatActivity() {

    fun inicializarToolbar(toolbarBinding: ToolbarBinding, titulo: String = "", tipoBtn: Int){
        setSupportActionBar(toolbarBinding.toolbar)
        toolbarBinding.titulo = titulo
        if(tipoBtn==1)
            toolbarBinding.ivBtn1.setImageResource(R.drawable.usuario_solid)

        toolbarBinding.setClickListener {
            when (it!!.id) {
                toolbarBinding.ivBtn1.id -> {
                    Toast.makeText(this,"Botón barra ", Toast.LENGTH_LONG).show()
                }
            }
        }
        toolbarBinding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }
}