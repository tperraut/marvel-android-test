package rocks.flawless.marveltestapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {
    private val _dataset: ArrayList<T> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return _dataset.size
    }

    open fun removeItem(position: Int): T {
        val safePosition: Int = Math.min(position, _dataset.size)
        val model: T = _dataset.removeAt(safePosition)
        notifyItemRemoved(safePosition)
        return model
    }

    open fun removeItem(): T {
        return removeItem(0)
    }

    open fun removeItem(item: T): Boolean {
        val itemIndex = _dataset.indexOf(item)
        if (itemIndex == -1) {
            return false
        }
        removeItem(itemIndex)
        return true
    }

    open fun addItem(position: Int, data: T) {
        val safePosition: Int = Math.min(position, _dataset.size)
        _dataset.add(safePosition, data)
        notifyItemInserted(safePosition)
    }

    open fun addItem(data: T) {
        addItem(0, data)
    }

    open fun addItems(dataList: List<T>) {
        _dataset.addAll(dataList)
        notifyDataSetChanged()
    }

    open fun getItemAtPosition(position: Int): T {
        return _dataset[Math.min(position, _dataset.size)]
    }

    open fun getItemPosition(item: T?): Int {
        return _dataset.indexOf(item)
    }

    open fun setDataset(dataset: List<T>) {
        _dataset.clear()
        addItems(dataset)
    }
}