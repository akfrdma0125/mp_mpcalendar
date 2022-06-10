package com.example.mp_calendar

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mp_calendar.Schedule
import com.example.mp_calendar.databinding.ActivityDayBinding
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class DayActivity : AppCompatActivity() , PopupMenu.OnMenuItemClickListener{
    lateinit var binding: ActivityDayBinding
    var data=arrayListOf<Schedule>()
    lateinit var today:LocalDate
    lateinit var adapter:ScheduleAdapter
    lateinit var myDBHelper:MyDBHelper

    //EditActivity에서 DayActivity로 돌아왔을 떄 호출되는 함수(콜백함수)
    val activityResultLauncher=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode== RESULT_OK){
            val schedule=it.data?.getSerializableExtra("schedule") as Schedule
            Toast.makeText(this,schedule.name+" 일정 추가됨", Toast.LENGTH_SHORT).show()
            Log.d("push",schedule.name.toString())
            data.add(schedule)
            InsertInDatabase(schedule)
            //displayList()//데이터베이스

            binding.dayRv.adapter!!.notifyDataSetChanged()
        }
    }

    private fun InsertInDatabase(schedule: Schedule) {
        myDBHelper=MyDBHelper(this)
        myDBHelper.insertSchedule(schedule)
    }

    /*private fun displayList() {

        val db=myDBHelper.readableDatabase

        val cursor=db.rawQuery("select * from ${MyDBHelper.TABLE_NAME};",null)
        adapter.listClear()
        while(cursor.moveToNext()){
            adapter.addItemToList(cursor.getString(1),cursor.getString(2),cursor.getString(3))
        }

        cursor.close()
        db.close()


    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        today=intent.getSerializableExtra("date") as LocalDate
        initData()
        initRecyclerView()
        //initDisplay()
        initPopupMenu()
    }

    /*private fun initDisplay() {
        myDBHelper=MyDBHelper(this)
        val db=myDBHelper.readableDatabase
        val cursor=db.rawQuery("select * from ${MyDBHelper.TABLE_NAME};",null)
        adapter.listClear()
        while(cursor.moveToNext()){
            adapter.addItemToList(cursor.getString(0),cursor.getString(2),cursor.getString(3),
            cursor.getInt(4),cursor.getInt(5),cursor.)
        }
    }*/

    private fun initPopupMenu() {
        binding.plusIb.setOnClickListener{showPopup(binding.plusIb)}
    }

    private fun initData() {
//        data.add(ScheduleData("데베수업","12:00","새천년관"))
//        data.add(ScheduleData("내과진료","3:00","건국대학교병원"))
    }

    private fun initRecyclerView() {
        myDBHelper=MyDBHelper(this)
        data= myDBHelper.getdateRecord(today) as ArrayList<Schedule>
        binding.dayRv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        adapter= ScheduleAdapter(data){index->deleteItem(index)}
        binding.dayRv.adapter=adapter
        adapter.listClear()
    }

    private fun deleteItem(index: Int) {
        val alertBuilder= AlertDialog.Builder(this)
        alertBuilder.setTitle("삭제")
        alertBuilder.setMessage("삭제하시겠습니까?")
        alertBuilder.setPositiveButton("예"){_,_->
            DeleteInDatabase(data.get(index))
            data.removeAt(index)
            adapter.setIems(data)
        }
        alertBuilder.setNegativeButton("아니오"){
                _,_->
        }
        alertBuilder.show()

    }

    private fun DeleteInDatabase(schedule: Schedule) {
        val result=myDBHelper.deleteProduct(schedule.name)
        if(result){
            Toast.makeText(this,"DELETE SUCCESS", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"DELETE FAILED", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initClickListener(){
        binding.backIb.setOnClickListener{
            //캘린더 Activity로 돌아가기
        }
        binding.plusIb.setOnClickListener{

        }
    }

    private fun showPopup(v: View){
        val popup= PopupMenu(this,v)//PopupMenu 객체 선언
        popup.menuInflater.inflate(R.menu.popup_menu,popup.menu)

        popup.setOnMenuItemClickListener(this)//메뉴 아이템 클릭 리스너 달아주기
        popup.show()//팝업 보여주기
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId) {
            //R.id.plusByDefault -> Toast.makeText(this, "plusByDefault", Toast.LENGTH_LONG).show()
            R.id.plusByText -> Toast.makeText(this, "plusByText", Toast.LENGTH_LONG).show()
            R.id.plusByImage -> Toast.makeText(this, "plusByImage", Toast.LENGTH_LONG).show()
            R.id.plusByDefault -> {
                val intent= Intent(this,EditActivity::class.java)
                activityResultLauncher.launch(intent)

            }
        }
        return item!=null
    }

}