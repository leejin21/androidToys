package com.example.aboutme

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("jin lee")
    // Create a binding object in the main activity -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        // Create a binding object in the main activity -2
        binding.doneButton.setOnClickListener{
            addNickName(it)
            // it: done_button 그 자체.
        }
        binding.nicknameText.setOnClickListener{
            updateNickname(it)
        }
    }

    private fun addNickName(view: View){
        // DONE button 눌렸을 때: edittext창 없애고, nicknametext에 edittext에서 적었던 text 저장, DONE button 없애기
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
                // myName이 null이면 스킵, null 아니면 스킵 아님.
            invalidateAll()
                // the UI is refreshed with the value in the updated binding object.
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // DONE button의 visibility GONE으로 만들어주기
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun updateNickname(view: View){
        // 1. hides the nickname text view, 2. shows the edit text, 3. shows the DONE button.
        binding.nicknameEdit.visibility = View.VISIBLE
        binding.doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text.
        binding.nicknameText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit, 0)
    }
}