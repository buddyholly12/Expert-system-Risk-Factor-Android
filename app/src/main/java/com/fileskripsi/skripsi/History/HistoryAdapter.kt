package com.fileskripsi.skripsi.History

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.fileskripsi.skripsi.Data_class_Value.AnswerSheets
import com.fileskripsi.skripsi.R
import kotlinx.android.synthetic.main.listview.view.*

class HistoryAdapter(val mctx:Context,val LayoutRes :Int, val historyList:List<AnswerSheets>):ArrayAdapter<AnswerSheets>(mctx,LayoutRes,historyList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater= LayoutInflater.from(mctx)
        val view:View = layoutInflater.inflate(LayoutRes,null)

        val tvBackward:TextView = view.findViewById(R.id.Backward_chain)
        val Tv_Cf :TextView = view.findViewById(R.id.Cf)
        val history = historyList[position]

        tvBackward.text = history.hasil_backward.trim()
        Tv_Cf.text = history.cf.toString()
        return view
    }
}