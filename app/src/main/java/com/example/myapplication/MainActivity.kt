package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var fm: FragmentManager
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager
        findViewById<Button>(R.id.button).setOnClickListener(this::onClick)
        findViewById<Button>(R.id.button2).setOnClickListener(this::onClick)
        val fragments_arrays = arrayOf(FirstFragment(),SecondFragment())
        val job: Job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(3000)
                val transaction = fm.beginTransaction()
                val f = fragments_arrays[counter++%2]
                transaction.replace(R.id.fragmentContainerView,f)
                transaction.commit()
            }
        }
        // job.cancel() - отмена корутины
    }

    override fun onClick(v: View?) {
        val transaction = fm.beginTransaction()
        val bundle = Bundle()
        bundle.putString("param1","123123")
        transaction.apply {
            if (v?.id == R.id.button) {
                val f = FirstFragment()
                f.arguments = bundle
                this.replace(R.id.fragmentContainerView, f)
            } else {

            }


             //   MyDialog().show(fm,MyDialog.TAG)
              //  this.replace(R.id.fragmentContainerView, SecondFragment())
            }

        transaction.addToBackStack(null)
        transaction.commit()
    }
}