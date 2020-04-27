package com.belsoft.cosulbio.adapters

import android.util.Patterns
import androidx.recyclerview.widget.RecyclerView
import com.belsoft.cosulbio.models.FormItemModel

abstract class FormsBaseAdapter<T : RecyclerView.ViewHolder>: RecyclerView.Adapter<T>() {
    abstract var items: List<FormItemModel>

    override fun getItemCount(): Int {
        return items.size
    }

    internal fun setItems(items: List<FormItemModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    protected fun isNotBlankValidation(text: String): Boolean {
        return text.trim().isNotBlank()
    }

    protected fun isEmailAddressValidation(text: String): Boolean {
        return (text.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(text).matches())
    }

    protected fun allFieldsAreValidated(items: List<FormItemModel>): Boolean{
        for (item in items){
            if (item.isValidated == null || item.isValidated == false){
                return false
            }
        }
        return true
    }
}