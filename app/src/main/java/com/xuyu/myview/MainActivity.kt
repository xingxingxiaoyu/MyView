package com.xuyu.myview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.xuyu.myview.bezier_curve.SeventhActivity
import com.xuyu.myview.bitmap.NinthActivity
import com.xuyu.myview.pay_password.EighthActivity
import com.xuyu.myview.side_slipe.FourthActivity
import com.xuyu.myview.animator_view.FifthActivity
import com.xuyu.myview.automaticball.FirstActivity
import com.xuyu.myview.ferris_wheel.SixthActivity
import com.xuyu.myview.identifying_code.ThirdActivity
import com.xuyu.myview.clock.SecondActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClick()
    }

    private fun setOnClick() {
        findViewById(R.id.button_automatic_ball).setOnClickListener(this)
        findViewById(R.id.button_clock).setOnClickListener(this)
        findViewById(R.id.button_identifying_code).setOnClickListener(this)
        findViewById(R.id.button_side_menu).setOnClickListener(this)
        findViewById(R.id.button_animator).setOnClickListener(this)
        findViewById(R.id.button_ferris_wheel).setOnClickListener(this)
        findViewById(R.id.button_bezier_curver).setOnClickListener(this)
        findViewById(R.id.button_pay_password).setOnClickListener(this)
        findViewById(R.id.button_bitmap).setOnClickListener(this)
        findViewById(R.id.button_broken_line).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = Intent()
        when (v.id) {
            R.id.button_automatic_ball -> intent.setClass(this, FirstActivity::class.java)
            R.id.button_clock -> intent.setClass(this, SecondActivity::class.java)
            R.id.button_identifying_code -> intent.setClass(this, ThirdActivity::class.java)
            R.id.button_side_menu -> intent.setClass(this, FourthActivity::class.java)
            R.id.button_animator -> intent.setClass(this, FifthActivity::class.java)
            R.id.button_ferris_wheel -> intent.setClass(this, SixthActivity::class.java)
            R.id.button_bezier_curver -> intent.setClass(this, SeventhActivity::class.java)
            R.id.button_pay_password -> intent.setClass(this, EighthActivity::class.java)
            R.id.button_bitmap -> intent.setClass(this, NinthActivity::class.java)
            R.id.button_broken_line -> intent.setClass(this, NinthActivity::class.java)
        }
        startActivity(intent)
    }
}
