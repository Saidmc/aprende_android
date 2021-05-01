package com.cognitus.myapplication2

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.cognitus.myapplication2.databinding.ActivityFirmaBinding

import com.github.gcacace.signaturepad.views.SignaturePad
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FirmaActivity : AppCompatActivity(), Validator.ValidationListener {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityFirmaBinding>(this, R.layout.activity_firma)
    }

    companion object {

        val TAG = "PermissionDemo"
        private val REQUEST_PERMISSION = 2000
        private const val REQUEST_INTERNET = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firma)

        val validator: Validator = Validator(binding)
        validator.setValidationListener(this)
        binding.setClickListener {
            when (it!!.id) {
                binding.mSaveButton.id -> {
                    validator!!.toValidate()
                }
            }
        }

        revisaPermiso()

        binding.signaturePad.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() { //Toast.makeText(SignActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            override fun onSigned() {
                binding.mSaveButton.setEnabled(true)
                binding.mClearButton.setEnabled(true)
            }

            override fun onClear() {
                binding.mSaveButton.setEnabled(false)
                binding.mClearButton.setEnabled(false)
            }
        })

        binding.mSaveButton.setOnClickListener {
            val signatureBitmap: Bitmap = binding.signaturePad.getTransparentSignatureBitmap()
            if (addJpgSignatureToGallery(signatureBitmap)) { //Toast.makeText(SignActivity.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(
                        this,
                        "Unable to store the signature",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.mClearButton.setOnClickListener { binding.signaturePad.clear() }

    }

    override fun onValidationSuccess() {
        Toast.makeText(this, "Todo ok ", Toast.LENGTH_LONG).show()
    }

    override fun onValidationError() {
        Toast.makeText(this@FirmaActivity, "Dados inválidos!", Toast.LENGTH_SHORT).show()
    }


    fun revisaPermiso(){
        if (ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_INTERNET
            )
            Log.i(TAG, "Pide permiso")
        }

    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_INTERNET -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Si dio permiso")
            } else {
                Log.i(TAG, "No dio permiso")
            }
        }
    }

    fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
        var result = false
        try {
            val path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
            )
            val file = File(path, "/firma.png")
            path.mkdirs();

            val fOut = FileOutputStream(file)

            signature.compress(Bitmap.CompressFormat.PNG, 85, fOut)
            fOut.flush()
            fOut.close()
            result = true

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}
