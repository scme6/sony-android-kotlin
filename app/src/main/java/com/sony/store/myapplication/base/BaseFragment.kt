package com.sony.store.myapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * @Description
 * @author: qiang
 * @date: 2021-06-27
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        return if (setContentView() is View)
            setContentView() as View
        else
            inflater.inflate(setContentView() as Int, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }
    abstract fun init()
    abstract fun setContentView(): Any
    abstract fun initView(view: View)

}