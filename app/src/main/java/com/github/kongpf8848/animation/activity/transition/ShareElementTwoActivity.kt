package com.github.kongpf8848.animation.activity.transition;

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ImageViewTarget
import com.github.chrisbanes.photoview.PhotoView
import com.github.kongpf8848.animation.helper.Constants
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import com.github.kongpf8848.animation.bean.ImageBean
import kotlinx.android.synthetic.main.activity_transition_share_element_two.*


class ShareElementTwoActivity : BaseActivity() {

    private var mCurrentPosition = 0
    private var mStartingPosition = 0
    private var mIsReturning = false

    var imageViewList = arrayListOf<PhotoView>()

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


    override fun getLayoutId(): Int {
        return R.layout.activity_transition_share_element_two
    }

    override fun statusBarColor(): Int {
        return android.R.color.black
    }

    override fun navigationBarColor(): Int {
        return android.R.color.black
    }


    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun navigationBarDarkIcon(): Boolean {
        return false
    }

    override fun fitsSystemWindows(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)


        postponeEnterTransition()
        setEnterSharedElementCallback(mSharedElementCallback)

        mStartingPosition = intent.getIntExtra(Constants.EXTRA_START_POSITION, 0)
        mCurrentPosition = savedInstanceState?.getInt(Constants.EXTRA_CURRENT_POSITION) ?: mStartingPosition
        var list = intent.getSerializableExtra(Constants.EXTRA_IMAGE_LIST) as List<ImageBean>


        list.forEachIndexed { index, imageBean ->
            val imageView = PhotoView(this@ShareElementTwoActivity)
            imageView.id=index
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
                imageView.setOnOutsidePhotoTapListener {
                    finish()
                }
                Glide.with(this@ShareElementTwoActivity)
                        .asBitmap()
                        .load(list.get(index).resId)
                        .apply(RequestOptions().skipMemoryCache(true))
                        .into(object : ImageViewTarget<Bitmap>(imageView) {
                            override fun setResource(resource: Bitmap?) {
                                imageView.setImageBitmap(resource)
                                if (imageView.id == mStartingPosition) {
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

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            override fun onPageSelected(position: Int) {
                if(mCurrentPosition>=0 && mCurrentPosition<imageViewList.size){
                    imageViewList.get(mCurrentPosition).setScale(1.0f,true)
                }
                mCurrentPosition = position
            }

        })
        viewPager.currentItem = mCurrentPosition

        circle_indicator.setUpWithViewPager(viewPager)
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
