package com.test.testinnowi.ui.activities

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.testinnowi.R
import com.test.testinnowi.ui.fragments.AlbumListFragment

abstract class BaseActivity : AppCompatActivity() {
    abstract fun layoutRes(): Int
    abstract fun initViews()
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCutoutView()
        setContentView(layoutRes())
        initProgressDialog()
        initViews()
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setCancelable(false)
        progressDialog?.setTitle("loading")
    }

    fun showProgressDialog() {
        progressDialog?.let {
            if (it.isShowing) {
                hideProgressDialog()
            }
            progressDialog!!.show()
        }
    }

    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }

    protected fun setCutoutView() {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }
}

