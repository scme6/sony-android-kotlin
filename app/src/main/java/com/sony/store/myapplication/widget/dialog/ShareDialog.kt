package com.sony.store.myapplication.widget.dialog

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sony.store.myapplication.R
import com.sony.store.myapplication.utils.UIUtil.dip2px
import kotlinx.android.synthetic.main.dialog_share.*

class ShareDialog(context: Context) : BottomSheetDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_share)

       val bottomSheetBehavior= BottomSheetBehavior.from(item)
//        text.setOnClickListener {
//            bottomSheetBehavior.state=STATE_COLLAPSED
//        }
    }


}