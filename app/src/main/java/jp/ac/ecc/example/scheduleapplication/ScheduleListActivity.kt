package jp.ac.ecc.example.scheduleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScheduleListActivity : AppCompatActivity() {

    lateinit var scheduleList : ListView
    lateinit var newScheduleFab : FloatingActionButton

    lateinit var schedules : MutableList<Map<String, Any>>
    // リストビューの仮データ表示用
    var dummyList : MutableList<Map<String, Any>> = mutableListOf(
        mapOf("id" to 1 ,"main" to "2023/02/11 15:00" ,"sub" to "映画"),
        mapOf("id" to 5 ,"main" to "2023/02/13 19:00" ,"sub" to "飲み会"),
        mapOf("id" to 3 ,"main" to "2023/02/20 10:30" ,"sub" to "勉強会"),
        mapOf("id" to 2 ,"main" to "2023/02/21 13:20" ,"sub" to "バイト面接"),
        mapOf("id" to 4 ,"main" to "2023/03/01 10:00" ,"sub" to "東京旅行")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_list)

        supportActionBar?.let {
            it.title = "スケジュール一覧"
        }

        scheduleList = findViewById(R.id.scheduleList)
        newScheduleFab = findViewById(R.id.newScheduleFab)

        scheduleList.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this, DetailScheduleActivity::class.java)
            startActivity(intent)
        }

        val adapter = SimpleAdapter(
            this,
            dummyList,
            android.R.layout.simple_list_item_2,
            arrayOf("main","sub"),//第2引数のmapのキー(配列)
            intArrayOf(android.R.id.text1,android.R.id.text2)//第3引数のレイアウトのビューのid(int配列)
        )
        scheduleList.adapter = adapter

        newScheduleFab.setOnClickListener {
            var intent = Intent(this, NewScheduleActivity::class.java)
            startActivity(intent)
        }

    }
}