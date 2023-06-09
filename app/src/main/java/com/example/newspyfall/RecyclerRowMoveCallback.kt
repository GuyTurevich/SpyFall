package com.example.newspyfall

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class RecyclerRowMoveCallback(private val touchHelperContract: RecyclerViewRowTouchHelperContract) :
    ItemTouchHelper.Callback() {
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlag, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        touchHelperContract.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is UserAdapter.UserViewHolder) {
                val myViewHolder: UserAdapter.UserViewHolder? = viewHolder
                touchHelperContract.onRowSelected(myViewHolder)
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is UserAdapter.UserViewHolder) {
            val myViewHolder: UserAdapter.UserViewHolder = viewHolder
            touchHelperContract.onRowClear(myViewHolder)
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    interface RecyclerViewRowTouchHelperContract {
        fun onRowMoved(from: Int, to: Int)
        fun onRowSelected(myViewHolder: UserAdapter.UserViewHolder?)
        fun onRowClear(myViewHolder: UserAdapter.UserViewHolder?)
    }
}