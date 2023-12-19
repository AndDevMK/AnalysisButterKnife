package com.pengmj.butterknife

import android.app.Activity
import com.pengmj.bindview.IBindView

/**
 * @author MinKin
 * @date 2022/10/15
 * @desc
 */
object BindView {

    fun bind(activity: Activity) {
        val activityName = "${activity.javaClass.canonicalName}_BindView"
        val newInstance: IBindView<Activity> = Class.forName(activityName).newInstance() as IBindView<Activity>
        newInstance.bind(activity)
    }
}