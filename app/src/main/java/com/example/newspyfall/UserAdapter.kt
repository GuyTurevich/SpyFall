package com.example.newspyfall

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections


class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(),
    RecyclerRowMoveCallback.RecyclerViewRowTouchHelperContract {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        // bind data to the views in the user_item layout
        holder.nameTextView.text = user.name
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.player_name)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

        init {
            deleteButton.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    (itemView.context as MainActivity).deleteUser(adapterPosition)
                }
            }
        }

    }

    override fun onRowMoved(from: Int, to: Int) {
        if (from < to) {
            for (i in from until to) {
                Collections.swap(userList, i, i + 1)
            }
        } else {
            for (i in from downTo to + 1) {
                Collections.swap(userList, i, i - 1)
            }
        }
        notifyItemMoved(from, to)
    }

    override fun onRowSelected(myViewHolder: UserViewHolder?) {
        myViewHolder?.cardView?.setCardBackgroundColor(Color.GRAY)
    }

    override fun onRowClear(myViewHolder: UserViewHolder?) {
        myViewHolder?.cardView?.setCardBackgroundColor(Color.parseColor("#BB86FC"))
    }

}

