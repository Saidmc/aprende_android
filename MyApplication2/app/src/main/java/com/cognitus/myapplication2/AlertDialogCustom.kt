package com.cognitus.myapplication2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.cognitus.myapplication2.databinding.DialogAlertBinding

object AlertDialogCustom {

    fun createAlert(context: Context,
                    title: String?,
                    message: String) {
        val builder = AlertDialog.Builder(context)

        val binding = DialogAlertBinding.inflate(LayoutInflater.from(context))
        binding.titulo = title
        binding.texto = message

        builder.setView(binding.root)
        val d = builder.create()
        binding.btnDAcp.setOnClickListener(View.OnClickListener {
            d.dismiss()
        })


        d.setCancelable(false)
        d.setCanceledOnTouchOutside(true)
        d.show()
    }
}