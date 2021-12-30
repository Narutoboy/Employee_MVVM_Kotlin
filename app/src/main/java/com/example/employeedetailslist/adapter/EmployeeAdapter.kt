package com.example.employeedetailslist.adapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.employeedetailslist.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.employeedetailslist.data.Employee

class EmployeeAdapter(applicationContext: Context, arrayListEmployee: ArrayList<Employee>) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {


private val  arrayListEmplyee:ArrayList<Employee>
private val context:Context
init {
    this.context= applicationContext
    this.arrayListEmplyee=arrayListEmployee
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_employee, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee: Employee =arrayListEmplyee.get(position)
        holder.empId.setText(""+employee.empId)
        holder.empName.setText(employee.empName)
        holder.empEmail.setText(employee.empEmail)
        holder.empSalary.setText(""+employee.empSalary)
        holder.empDesignation.setText(employee.empDesignation)
        val stringURL:String=employee.empPhotoURL;
        Log.d("TAG", "onBindViewHolder: url "+stringURL)
        Glide.with(context)
            .load(stringURL)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(android.R.drawable.star_on)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return arrayListEmplyee.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var empId: TextView
        var empName: TextView
        var empSalary: TextView
        var empDesignation: TextView
        var empEmail: TextView
        var imageView:ImageView

        init {
            empId = itemView.findViewById(R.id.tv_employee_id)
            empName = itemView.findViewById(R.id.tv_emp_name)
            empEmail = itemView.findViewById(R.id.tv_emp_email)
            empSalary = itemView.findViewById(R.id.tv_emp_salary)
            empDesignation = itemView.findViewById(R.id.tv_emp_designation)
            imageView=itemView.findViewById(R.id.iv_emp_profile)
        }
    }
}