package com.github.kongpf8848.animation.activity.gif

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.support.rastermill.FrameSequence
import android.support.rastermill.FrameSequenceDrawable
import android.view.View
import android.widget.Toast
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_gif_frame.*
import java.util.*

class FrameSequenceActivity : BaseActivity() {

    var mDrawable: FrameSequenceDrawable? = null
    var mResourceId = 0

    inner class CheckingProvider : FrameSequenceDrawable.BitmapProvider {
        var mBitmaps = HashSet<Bitmap>()
        override fun acquireBitmap(minWidth: Int, minHeight: Int): Bitmap {
            val bitmap = Bitmap.createBitmap(minWidth + 1, minHeight + 4, Bitmap.Config.ARGB_8888)
            mBitmaps.add(bitmap)
            return bitmap
        }

        override fun releaseBitmap(bitmap: Bitmap) {
            check(mBitmaps.contains(bitmap))
            mBitmaps.remove(bitmap)
            bitmap.recycle()
        }

        val isEmpty: Boolean
            get() = mBitmaps.isEmpty()
    }

    private val mProvider = CheckingProvider()

    override fun getLayoutId(): Int {
        return R.layout.activity_gif_frame
    }

    public override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        mResourceId = intent.getIntExtra("resourceId", R.raw.animated_gif)
        start.setOnClickListener { mDrawable?.start() }
        stop.setOnClickListener { mDrawable?.stop() }
        vis.setOnClickListener { mDrawable?.setVisible(true, true) }
        invis.setOnClickListener { mDrawable?.setVisible(false, true) }
        circle_mask.setOnClickListener { mDrawable?.circleMaskEnabled = !mDrawable!!.circleMaskEnabled }
    }

    override fun onResume() {
        super.onResume()
        val drawableView = findViewById<View>(R.id.drawableview)
        val `is` = resources.openRawResource(mResourceId)
        val fs = FrameSequence.decodeStream(`is`)
        mDrawable = FrameSequenceDrawable(fs, mProvider)
        mDrawable?.setLoopBehavior(FrameSequenceDrawable.LOOP_FINITE)
        mDrawable!!.setOnFinishedListener {
            Toast.makeText(applicationContext, "The animation has finished", Toast.LENGTH_SHORT).show()
        }
        drawableView.setBackgroundDrawable(mDrawable)
    }

    override fun onPause() {
        super.onPause()
        val drawableView = findViewById<View>(R.id.drawableview)
        mDrawable!!.destroy()
        check(mProvider.isEmpty) { "All bitmaps not recycled" }
        mDrawable = null
        drawableView.setBackgroundDrawable(null)
    }
}