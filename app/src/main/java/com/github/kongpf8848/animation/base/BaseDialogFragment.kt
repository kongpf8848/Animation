package com.github.kongpf8848.animation.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.github.kongpf8848.animation.R

/**
 * DialogFragment基类
 */
abstract class BaseDialogFragment(
        val ctx: Context,
        val style:Int= R.style.DialogStyle,
        val width:Int=WindowManager.LayoutParams.WRAP_CONTENT,
        val height:Int=WindowManager.LayoutParams.WRAP_CONTENT,
        val gravity:Int=Gravity.CENTER,
        val animationId:Int=0,
        val cancelOutside:Boolean= true,
        val cancelable: Boolean = true,
        val backgroundDimEnabled:Boolean=true,
        val dimAmount:Float=0.6f
) : DialogFragment(), View.OnClickListener {

    protected val TAG = javaClass.simpleName

    lateinit var binding: ViewDataBinding
    protected var rootView: View? = null

    abstract fun getLayoutId():Int

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, style)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false)
            rootView = binding.root
            binding.lifecycleOwner = this
        } else {
            val parent = rootView!!.parent as? ViewGroup
            parent?.removeView(rootView)
        }
        return rootView
    }

    override fun onStart() {
       super.onStart()
        Log.d(TAG, "onStart() called")
        isCancelable = cancelable
        dialog?.apply {
            window?.apply {
                attributes.width=width
                attributes.height=height
                attributes.gravity=gravity
                if (animationId != 0) {
                    setWindowAnimations(animationId)
                }
                if(backgroundDimEnabled){
                    addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    attributes.dimAmount=dimAmount
                }
            }
            setCanceledOnTouchOutside(cancelOutside)
            setCancelable(cancelable)
        }

        initView()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d(TAG, "onHiddenChanged() called with: hidden = $hidden")
    }

    open fun show(tag: String? = null) {
        if (ctx is AppCompatActivity) {
            showInternal(ctx,tag)
        } else if (ctx is ContextThemeWrapper) {
            var tempContext = ctx
            while (tempContext is ContextThemeWrapper) {
                tempContext = tempContext.baseContext
                if (tempContext is AppCompatActivity) {
                    showInternal(tempContext,tag)
                    break
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d(TAG,"onDismissListener onDismiss")
    }


    private fun showInternal(activity: AppCompatActivity, tag: String?){
        if(isAdded) {
            activity.supportFragmentManager.beginTransaction().remove(this).commitNowAllowingStateLoss()
        }
        show(activity.supportFragmentManager,tag)
        activity.supportFragmentManager.executePendingTransactions()
    }


    open fun initView() {}

    override fun onClick(v: View?) {}
}