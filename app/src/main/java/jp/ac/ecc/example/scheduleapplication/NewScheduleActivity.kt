package jp.ac.ecc.example.scheduleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.TextView
import jp.ac.ecc.example.scheduleapplication.ui.DatePickerFragment
import jp.ac.ecc.example.scheduleapplication.ui.TimePickerFragment
import java.util.*

class NewScheduleActivity : AppCompatActivity(),
    DatePickerFragment.OnDateSelectedListener,
    TimePickerFragment.OnTimeSelectedListener {

    lateinit var dateText : TextView
    lateinit var timeText : TextView

    lateinit var createButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_schedule)

        supportActionBar?.let {
            it.title = "スケジュール新規登録"
        }

        dateText = findViewById(R.id.dateText)
        timeText = findViewById(R.id.timeText)
        createButton = findViewById(R.id.createButton)

        val c = Calendar.getInstance()
        val hourOfDay = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // 画面起動時は、現在日付,時刻を表示する
        dateText.text = DateFormat.format("yyyy/MM/dd",c)
        timeText.text = "%1$02d:%2$02d".format(hourOfDay, minute)

        // 日付テキストクリックで、日付ダイアログを表示
        dateText.setOnClickListener {
            val dialog = DatePickerFragment()
            dialog.show(supportFragmentManager, "date_dialog")
        }

        // 時刻テキストクリックで、時刻ダイアログを表示
        timeText.setOnClickListener {
            val dialog = TimePickerFragment()
            dialog.show(supportFragmentManager, "time_dialog")
        }

        createButton.setOnClickListener {

            // 予定登録時は、アクティビティのスタッククリアして一覧表示
            val intent = Intent(this, ScheduleListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }

    // 日付ダイアログ選択
    override fun onSelected(year: Int, month: Int, date: Int) {
        val c = Calendar.getInstance()
        c.set(year,month, date)
        dateText.text = DateFormat.format("yyyy/MM/dd",c)
    }

    // 時刻ダイアログ選択
    override fun onSelected(hourOfDay: Int, minute: Int) {
        timeText.text = "%1$02d:%2$02d".format(hourOfDay, minute)
    }
}