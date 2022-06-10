package com.example.mp_calendar

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.mp_calendar.Schedule
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)
class MyDBHelper(val context: Context?):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {
    companion object{
        val DB_NAME="mydb.db"
        val DB_VERSION=1
        val TABLE_NAME="schedules"
        val SID="sid"
        val SDATE="sdate"
        val STIME="stime"
        val SNAME="sname"
        val SLOCATION="slocation"
        val SPREV="sprev"
        val SNEXT="snext"
        val STRAVEL="stravel"
    }

    fun getAllRecord(): List<Schedule>{
        val list = mutableListOf<Schedule>()
        val timeFormatter= DateTimeFormatter.ISO_TIME
        val dateFormatter=DateTimeFormatter.ISO_DATE
        val strsql="select * from $TABLE_NAME;"
        val db = readableDatabase
        val cursor=db.rawQuery(strsql,null)
        cursor.moveToFirst()
        while(cursor.moveToNext()){
            val date=LocalDate.parse(cursor.getString(1),dateFormatter)
            val time=LocalTime.parse(cursor.getString(2),timeFormatter)
            list.add(Schedule(date,time,cursor.getString(3),cursor.getString(4),
            cursor.getInt(5),cursor.getInt(6),cursor.getInt(7)))
        }
        cursor.close()
        db.close()
        return list
    }

    fun getdateRecord(date: LocalDate):List<Schedule>{
        val list = mutableListOf<Schedule>()
        val timeFormatter= DateTimeFormatter.ISO_TIME
        val strsql="select * from $TABLE_NAME where $SDATE='$date';"
        val db = readableDatabase
        val cursor=db.rawQuery(strsql,null)
        cursor.moveToFirst()
        while(cursor.moveToNext()){
            val time=LocalTime.parse(cursor.getString(2),timeFormatter)
            list.add(Schedule(date,time,cursor.getString(3),cursor.getString(4),
                cursor.getInt(5),cursor.getInt(6),cursor.getInt(7)))
        }
        cursor.close()
        db.close()
        return list
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val create_table="create table if not exists $TABLE_NAME("+
                "$SID integer primary key autoincrement, "+
                "$SDATE text," +
                "$STIME text," +
                "$SNAME text,"+
                "$SLOCATION text,"+
                "$SPREV integer,"+
                "$SNEXT integer, "+
                "$STRAVEL integer);"
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val drop_table="drop table if exists $TABLE_NAME; "
        db!!.execSQL(drop_table)
        onCreate(db)
    }

    fun insertSchedule(schedule: Schedule):Boolean{
        val values = ContentValues()
        values.put(SPREV,schedule.prev)
        values.put(SNEXT,schedule.next)
        values.put(SDATE,schedule.date.toString())
        values.put(STIME,schedule.time.toString())
        values.put(SNAME,schedule.name)
        values.put(SLOCATION,schedule.location)
        values.put(STRAVEL,schedule.travelTime)
        Log.d("serr",values.toString())
        val db =writableDatabase
        val flag=db.insert(TABLE_NAME,null,values)>0
        db.close()
        return flag
    }

    fun deleteProduct(sname: String): Boolean {
        val strsql="select * from $TABLE_NAME where $SNAME='$sname'"
        val db = readableDatabase
        val cursor=db.rawQuery(strsql,null)
        val flag=cursor.count!=0
        if(flag){
            cursor.moveToFirst()
            db.delete(TABLE_NAME,"$SNAME=?",arrayOf(sname))
        }

        cursor.close()
        db.close()
        return flag
    }

}