package com.test.testinnowi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.test.testinnowi.ui.activities.BaseActivity

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    abstract fun fragmentLayout(): Int
    abstract fun initViews()

    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, fragmentLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    protected fun showProgressDialog() {
        (activity as BaseActivity).showProgressDialog()
    }

    protected fun hideProgressDialog() {
        (activity as BaseActivity).hideProgressDialog()
    }

}