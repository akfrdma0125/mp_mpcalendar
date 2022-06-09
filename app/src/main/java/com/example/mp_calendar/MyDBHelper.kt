package com.example.mp_calendar

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import com.example.day.ScheduleData

class MyDBHelper(val context: Context?):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {
    companion object{
        val DB_NAME="mydb.db"
        val DB_VERSION=1
        val TABLE_NAME="schedules"
        val SID="sid"
        val SNAME="sname"
        val STIME="stime"
        val SLOCATION="slocation"


    }

    fun getAllRecord(){
        val strsql="select * from $TABLE_NAME;"
        val db = readableDatabase
        val cursor=db.rawQuery(strsql,null)

        cursor.close()
        db.close()
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val create_table="create table if not exists $TABLE_NAME("+
                "$SID integer primary key autoincrement, "+
                "$SNAME text, "+
                "$STIME text," +
                "$SLOCATION text);"
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val drop_table="drop table if exists $TABLE_NAME; "
        db!!.execSQL(drop_table)
        onCreate(db)
    }

    fun insertSchedule(schedule: ScheduleData):Boolean{
        val values = ContentValues()
        values.put(SNAME,schedule.name)
        values.put(STIME,schedule.time)
        values.put(SLOCATION,schedule.location)
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