package com.example.employeedetailslist.data

data class Employee(
    var empId: Int =-1,
    var empName: String ="",
    var empEmail: String ="",
    var empDesignation: String="",
    var empSalary: Int =-1,
    var empPhotoURL: String =""
)