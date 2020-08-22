package com.example.inehemias.testingdemo.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.inehemias.testingdemo.R

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var buttonSave: Button
    private val newWordActivityRequestCode = 1

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editWordView = findViewById(R.id.edit_word)
        buttonSave = findViewById(R.id.button_save)
        getInputText()
        saveWord()
    }

    private fun getInputText() {
        editWordView.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.isNotEmpty())
                    buttonSave.isEnabled = true
            }
        })
    }

    private fun saveWord() {
        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
