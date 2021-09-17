package com.sammuiga.emergram.activities

import android.app.Dialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sammuiga.emergram.R
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog

        fun showErrorSnackBar(message: String, errorMessage: Boolean) {
            val snackBar =
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            val snackBarView = snackBar.view

            if(errorMessage) {
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BaseActivity,
                        R.color.colorSnackBarError
                        )
                    )
            }else{
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BaseActivity,
                        R.color.colorSnackBarSuccess
                    )
                )
            }
            snackBar.show()
        }
        fun showProgressDialog(text : String) {

            mProgressDialog = Dialog(this)
            mProgressDialog.setContentView(R.layout.dialog_progress)

            mProgressDialog.tv_progress_text.setText(text)

            mProgressDialog.setCancelable(false)
            mProgressDialog.setCanceledOnTouchOutside(false)

            mProgressDialog.show()
        }
        fun hideProgressDialog() {
            mProgressDialog.dismiss()
        }
    }