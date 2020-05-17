package com.coding.smkcoding_project_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.coding.smkcoding_project_2.R
import kotlinx.android.synthetic.main.subtitle_layout.view.*
import kotlinx.android.synthetic.main.title_layout.view.*

class ExpandableListViewAdapter(var context: Context, var title: MutableList<String>, var subTitle: MutableList<MutableList<String>>)
    :BaseExpandableListAdapter(){

    override fun getGroup(groupPosition: Int): String {
        return title[groupPosition]
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        if (convertView == null){
            var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.title_layout, null)
        }
        var mTitle = convertView!!.iv_title
        mTitle.text = getGroup(groupPosition)
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return subTitle[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return subTitle[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        if (convertView == null){
            var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.subtitle_layout, null)
        }
        var mSubTitle = convertView!!.iv_subtitle
        mSubTitle.text = getChild(groupPosition, childPosition)
        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return title.size
    }
}