package com.github.kongpf8848.animation.activity.transition;

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ImageViewTarget
import com.github.kongpf8848.animation.Constants
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.bean.ImageBean
import com.github.kongpf8848.animation.widget.MyImageView
import kotlinx.android.synthetic.main.activity_transition_share_element_two.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator


class ShareElementTwoActivity : AppCompatActivity() {

    private var mCurrentPosition = 0
    private var mStartingPosition = 0
    private var mIsReturning = false

    var imageViewList = arrayListOf<MyImageView>()

    private val mSharedElementCallback: SharedElementCallback = object : SharedElementCallback() {
        override fun onMapSharedElements(names: MutableList<String>, sharedElements: MutableMap<String, View>) {
            if (mIsReturning) {
                if (mStartingPosition != mCurrentPosition) {
                    val sharedElement: ImageView = imageViewList[mCurrentPosition]
                    names.clear()
                    names.add(sharedElement.transitionName)
                    sharedElements.clear()
                    sharedElements[sharedElement.transitionName] = sharedElement
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_share_element_two)
        window.statusBarColor= Color.BLACK

        postponeEnterTransition()
        setEnterSharedElementCallback(mSharedElementCallback)

        mStartingPosition = intent.getIntExtra(Constants.EXTRA_START_POSITION, 0)
        mCurrentPosition = savedInstanceState?.getInt(Constants.EXTRA_CURRENT_POSITION) ?: mStartingPosition
        var list = intent.getSerializableExtra(Constants.EXTRA_IMAGE_LIST) as List<ImageBean>


        list.forEachIndexed { index, imageBean ->
            val imageView = MyImageView(this@ShareElementTwoActivity)
            imageView.index = index
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            ViewCompat.setTransitionName(imageView, list.get(index).name)
            imageViewList.add(imageView)
        }

        viewPager.adapter = object : PagerAdapter() {

            override fun getCount(): Int {
                return list.size
            }

            override fun instantiateItem(container: ViewGroup, index: Int): Any {
                var imageView = imageViewList.get(index);
                container.addView(imageView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                Glide.with(this@ShareElementTwoActivity)
                        .asBitmap()
                        .load(list.get(index).resId)
                        .apply(RequestOptions().skipMemoryCache(true))
                        .into(object : ImageViewTarget<Bitmap>(imageView) {
                            override fun setResource(resource: Bitmap?) {
                                imageView.setImageBitmap(resource)
                                if (imageView.index == mStartingPosition) {
                                    startPostponedEnterTransition()
                                }
                            }
                            override fun onLoadFailed(errorDrawable: Drawable?) {
                                super.onLoadFailed(errorDrawable)
                                startPostponedEnterTransition()
                            }
                        })

                return imageView
            }


            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }

        }

        val circleNavigator = CircleNavigator(this).apply {
            circleColor = Color.WHITE
            strokeWidth = 2
            circleCount = list.size
            radius = 10
            circleSpacing = 20
            isTouchable = false
        }
        magic_indicator.setNavigator(circleNavigator)
        ViewPagerHelper.bind(magic_indicator, viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            override fun onPageSelected(position: Int) {
                mCurrentPosition = position
            }

        })
        viewPager.currentItem = mCurrentPosition
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Constants.EXTRA_CURRENT_POSITION, mCurrentPosition)
    }

    override fun finishAfterTransition() {
        mIsReturning = true
        val data = Intent()
        data.putExtra(Constants.EXTRA_START_POSITION, mStartingPosition)
        data.putExtra(Constants.EXTRA_CURRENT_POSITION, mCurrentPosition)
        setResult(Activity.RESULT_OK, data)
        super.finishAfterTransition()
    }
}
