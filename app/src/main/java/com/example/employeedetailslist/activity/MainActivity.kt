package com.example.employeedetailslist.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.employeedetailslist.R
import com.example.employeedetailslist.data.Employee
import com.example.employeedetailslist.adapter.EmployeeAdapter
import com.example.employeedetailslist.data.MainViewModel
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val BASE_URL: String =
        "http://203.112.132.70:8013/api/AndroidSys" +
                "temTest/GetEmployeeData"
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var mAdapter: RecyclerView.Adapter<*>
    var arrayListEmployee: ArrayList<Employee> = ArrayList()
    private lateinit var model:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        getData()
        manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
         model= ViewModelProvider(this).get(MainViewModel::class.java)


    }


    public fun getData() {
        val queue = Volley.newRequestQueue(this)
        val jsonobject: JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            BASE_URL,
            null,
            Response.Listener { response -> fetchResult(response) },
            Response.ErrorListener { error ->

                //tv_recyclerView.text = "that didn't worked ${error.message}"
            })

        queue.add(jsonobject)
    }


    private fun fetchResult(response: JSONObject) {
        var i: Int = 0
        var jsonarray: JSONArray = response.get("Data") as JSONArray
        var size: Int = jsonarray.length()
        for (i in 0..size - 1) {
            var empobject: JSONObject = jsonarray.getJSONObject(i)
            var employee: Employee = Employee()
            employee.empId = empobject.getInt("EmpId")
            employee.empName = empobject.getString("EmpName")
            employee.empEmail = empobject.getString("Email")
            employee.empDesignation = empobject.getString("Designation")
            employee.empSalary = empobject.getInt("Salary")
            employee.empPhotoURL=empobject.getString("PhotoUrl")
            arrayListEmployee.add(employee)
            Log.d("TAG", "fetchResult: employee" + arrayListEmployee)
        }
        model.setEmployeeList(arrayListEmployee)
        mAdapter = EmployeeAdapter(applicationContext,
            model.getEmployeeList() as ArrayList<Employee>
        )
        recyclerView.adapter = mAdapter
        //if(jsonData.contains(""))

    }

    private fun errorResult(error: VolleyError) {
        Log.d("TAG", "errorResult: ${error.message}")
        //tv_recyclerView.text = "Error Occured" + error
    }
}