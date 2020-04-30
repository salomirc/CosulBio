package com.belsoft.cosulbio

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.belsoft.cosulbio.utils.TAG

abstract class BaseActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "${javaClass.simpleName} OnCreate")
        super.onCreate(savedInstanceState)
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onStart() {
        Log.d(TAG, "${javaClass.simpleName} OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "${javaClass.simpleName} OnResume")
        super.onResume()
    }

    override fun onRestart() {
        Log.d(TAG, "${javaClass.simpleName} OnRestart")
        super.onRestart()
    }

    override fun onPause() {
        Log.d(TAG, "${javaClass.simpleName} OnPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "${javaClass.simpleName} OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "${javaClass.simpleName} OnDestroy")
        super.onDestroy()
    }
}