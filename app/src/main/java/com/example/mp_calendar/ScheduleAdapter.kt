package com.example.mp_calendar

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_calendar.ScheduleData
import com.example.mp_calendar.databinding.RowBinding

class ScheduleAdapter(val items:ArrayList<Schedule>, val onClickDelete :(Int)->Unit): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private var listData=items
    lateinit var myDBHelper: MyDBHelper
    lateinit var context:RowBinding

    //var item_name=""
    //var item_time=""
    //var item_location=""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val binding= RowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        Log.d("schedule size",items.size.toString())
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        holder.binding.scheduleRvTitle.text=items[position].name
        holder.binding.scheduleRvTime.text=items[position].time.toString()
        //item_name=items[position].name
        //item_time=items[position].time
        //item_location=items[position].location
        holder.bind(position)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    /*fun addItemToList(name: String, time: String, location: String) {
        Log.d("item배열",items.size.toString())
        listData.add(Schedule(name,time,location))
    }*/

    fun listClear() {
        listData.clear()
    }

    fun setIems(itemss: ArrayList<Schedule>) {
        listData =itemss
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowBinding):RecyclerView.ViewHolder(binding.root){
        //private val context=binding.root.context
        val context=binding.root.context
        init{
           binding.LinearLayoutTimeTitle.setOnClickListener {
               val intent=Intent(context,InfoActivity::class.java)
               intent.putExtra("scheduleInfo",
                   Schedule(items[position].date,items[position].time,items[position].name,
                   items[position].location,items[position].prev,
                   items[position].next,items[position].travelTime))
               intent.run{context.startActivity(this)}
           }
       }
        fun bind(index:Int){
            val deleteBtn=binding.scheduleRvDelete
            deleteBtn.setOnClickListener { onClickDelete(index) }
        }

    }
}