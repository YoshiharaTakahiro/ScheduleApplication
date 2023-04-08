package jp.ac.ecc.example.scheduleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailScheduleActivity : AppCompatActivity() {

    lateinit var closeButton : Button
    lateinit var deleteButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_schedule)

        supportActionBar?.let {
            it.title = "スケジュール詳細"
        }

        closeButton = findViewById(R.id.closeButton)
        deleteButton = findViewById(R.id.deleteButton)

        closeButton.setOnClickListener {
            // 閉じるは、何もせずに画面を終了させる
            finish()
        }

        deleteButton.setOnClickListener {

            // 予定完了時は、アクティビティのスタッククリアして一覧表示
            val intent = Intent(this, ScheduleListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }
}