package com.tolikavr.shoppinglist.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tolikavr.shoppinglist.R

class ShopItemActivity : AppCompatActivity() {

  private lateinit var saveButton: Button
  private lateinit var etName: TextView
  private lateinit var etCount: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_shop_item)

    saveButton = findViewById(R.id.save_button)
    etName = findViewById(R.id.et_name)
    etCount = findViewById(R.id.et_count)

    saveButton.setOnClickListener {
      val intent = Intent(this, MainActivity::class.java)
      intent.putExtra(NAME, etName.text)
      intent.putExtra(COUNT, etCount.text)
      startActivity(intent)
    }
  }

  companion object {
    const val NAME = "et_name"
    const val COUNT = "et_count"
  }


}