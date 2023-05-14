package com.example.newspyfall

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var startButton: Button
    private lateinit var settingsButton: Button
    private lateinit var addPlayerButton: Button
    private lateinit var resetButton: Button

    private val userList = mutableListOf<User>(
        User("גיא"),
        User("טסט1"),
        User("טסט2"),
        User("טסט3"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersRecyclerView = findViewById(R.id.users_recycler_view)
        usersRecyclerView.adapter = UserAdapter(userList)

        startButton = findViewById(R.id.start_button)
        startButton.setOnClickListener {
            val gameActivity = GameActivity(userList, this)
            gameActivity.startGame()
        }

        settingsButton = findViewById(R.id.settings_button)

        addPlayerButton = findViewById(R.id.add_button)
        addPlayerButton.setOnClickListener {
            val dialog = AddUserDialogFragment()
            dialog.show(supportFragmentManager, "AddUserDialogFragment")
        }
        resetButton = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            resetList()
        }


    }

    fun deleteUser(position: Int) {
        userList.removeAt(position)
        usersRecyclerView.adapter?.notifyItemRemoved(position)
    }

    private fun resetList() {
        userList.clear()
        usersRecyclerView.adapter?.notifyDataSetChanged()
    }

    fun addUser(name: String) {
        userList.add(User(name))
        usersRecyclerView.adapter?.notifyItemInserted(userList.size - 1)
    }
}
