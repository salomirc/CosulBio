package com.belsoft.cosulbio.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.models.FormItemModel
import com.belsoft.cosulbio.ui.login.LoginViewModel
import com.belsoft.cosulbio.views.ClearFocusEditText
import kotlinx.android.synthetic.main.login_list_item.view.*

class LoginListAdapter (
    private val context: Context,
    override var items: List<FormItemModel>,
    private val viewModel: LoginViewModel
): FormsBaseAdapter<LoginListAdapter.LoginListHolder>(){

    inner class LoginListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView
        val valueEditText: ClearFocusEditText = itemView.valueEditText

        init {
            valueEditText.doAfterTextChanged {
                val currentItemModel = items[adapterPosition]
                currentItemModel.value = it.toString()
                currentItemModel.isValidated = isNotBlankValidation(it.toString())
                viewModel.isLoginButtonEnabled.value = allFieldsAreValidated(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.login_list_item, parent, false)
        return LoginListHolder(itemView)
    }


    override fun onBindViewHolder(holder: LoginListHolder, position: Int) {

        val current = items[position]
        holder.valueEditText.hint = context.getString(current.hint)
        holder.valueEditText.setText(current.value, TextView.BufferType.EDITABLE)
        holder.valueEditText.inputType = current.inputType
        holder.valueEditText.imeOptions = current.imeOptions
        holder.valueEditText.typeface = Typeface.create("sans-serif",Typeface.NORMAL)
    }
}