package com.github.kongpf8848.animation.activity.transition;

import android.app.SharedElementCallback
import android.content.Intent
import android.os.Bundle

import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kongpf8848.animation.utils.Constants
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import com.github.kongpf8848.animation.adapter.ShareElementListAdapter
import com.github.kongpf8848.animation.bean.ImageBean
import kotlinx.android.synthetic.main.activity_transition_share_element_one.*

class ShareElementOneActivity : BaseActivity() {



    private lateinit var mListData: ArrayList<ImageBean>

    private lateinit var mAdapter: ShareElementListAdapter
    private var mTmpReenterState: Bundle? = null


    private val mSharedElementCallback: SharedElementCallback = object : SharedElementCallback() {
        override fun onMapSharedElements(names: MutableList<String>, sharedElements: MutableMap<String, View>) {
            if (mTmpReenterState != null) {
                val startingPosition = mTmpReenterState!!.getInt(Constants.EXTRA_START_POSITION)
                val currentPosition = mTmpReenterState!!.getInt(Constants.EXTRA_CURRENT_POSITION)
                if (startingPosition != currentPosition) {
                    val newTransitionName: String = mListData.get(currentPosition).name
                    val viewHolder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(currentPosition)
                    if (viewHolder != null) {
                        names.clear()
                        names.add(newTransitionName)
                        sharedElements.clear()
                        sharedElements[newTransitionName] = viewHolder.itemView.findViewById(R.id.item_img)
                    }
                }
                mTmpReenterState = null
            }
        }
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_transition_share_element_one
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        setExitSharedElementCallback(mSharedElementCallback)
        init()
    }

    override fun onActivityReenter(requestCode: Int, data: Intent) {
        super.onActivityReenter(requestCode, data)
        mTmpReenterState = Bundle(data.extras)
        val startingPosition: Int = mTmpReenterState!!.getInt(Constants.EXTRA_START_POSITION)
        val currentPosition: Int = mTmpReenterState!!.getInt(Constants.EXTRA_CURRENT_POSITION)
        if (startingPosition != currentPosition) {
            recyclerView.scrollToPosition(currentPosition)
        }
        postponeEnterTransition();
        recyclerView.viewTreeObserver.addOnPreDrawListener(object: ViewTreeObserver.OnPreDrawListener{
            override fun onPreDraw(): Boolean {
                recyclerView.viewTreeObserver.removeOnPreDrawListener(this)
                recyclerView.requestLayout()
                startPostponedEnterTransition()
                return true;
            }
        });
    }

    private fun init() {
        initListData()
        mAdapter = ShareElementListAdapter(this@ShareElementOneActivity, mListData)

        mAdapter.setOnClickListener(object : ShareElementListAdapter.OnCallBack {
            override fun onClick(view: View, position: Int) {
                val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this@ShareElementOneActivity, Pair<View,String>(view, ViewCompat.getTransitionName(view)))
                startActivity(Intent(this@ShareElementOneActivity, ShareElementTwoActivity::class.java).apply {
                    putExtra(Constants.EXTRA_START_POSITION, position)
                    putExtra(Constants.EXTRA_IMAGE_LIST, mListData)
                }, transitionActivityOptions.toBundle())
            }
        })

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = mAdapter
    }

    private fun initListData() {
        mListData = ArrayList()
        mListData.add(ImageBean(R.drawable.scene_0, "shareImg0"))
        mListData.add(ImageBean(R.drawable.scene_1, "shareImg1"))
        mListData.add(ImageBean(R.drawable.scene_2, "shareImg2"))
        mListData.add(ImageBean(R.drawable.scene_3, "shareImg3"))
        mListData.add(ImageBean(R.drawable.scene_4, "shareImg4"))
        mListData.add(ImageBean(R.drawable.scene_5, "shareImg5"))
        mListData.add(ImageBean(R.drawable.scene_6, "shareImg6"))
        mListData.add(ImageBean(R.drawable.scene_7, "shareImg7"))
        mListData.add(ImageBean(R.drawable.scene_8, "shareImg8"))
    }



}
