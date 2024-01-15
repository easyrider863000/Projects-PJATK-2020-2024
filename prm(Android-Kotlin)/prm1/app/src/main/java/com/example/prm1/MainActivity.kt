package com.example.prm1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container,listFragment,listFragment.javaClass.name)
            .commit()
    }
}