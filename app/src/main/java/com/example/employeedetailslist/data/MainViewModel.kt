package com.example.employeedetailslist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var  employeeList:List<Employee> =ArrayList<Employee>()
    fun getEmployeeList(): List<Employee>{
        return employeeList;
    }
    fun setEmployeeList(list:ArrayList<Employee>){
        employeeList=list

    }
}