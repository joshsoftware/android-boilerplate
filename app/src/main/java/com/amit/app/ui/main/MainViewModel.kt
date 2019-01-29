package com.amit.app.ui.main

import com.amit.app.BR
import com.amit.app.data.model.local.db.Student
import com.amit.app.ui.base.BaseViewModel
import java.util.*

class MainViewModel : BaseViewModel() {
    var mDataList: MutableList<Student> = ArrayList()
    val TYPE_STUDENT_1 = 1
    val TYPE_STUDENT_2 = 2
    val TYPE_STUDENT_3 = 3
    val TYPE_STUDENT_4 = 4

    init {
        setData()
    }

    private fun setData() {
        mDataList.clear()
        for (i in 0..15) {
            var student: Student
            if (i % 2 == 0) {
                student = Student(TYPE_STUDENT_1, "Student 1", i)
            } else if (i % 3 == 0) {
                student = Student(TYPE_STUDENT_2, "Student 2", i)
            } else if (i % 5 == 0) {
                student = Student(TYPE_STUDENT_3, "Student 3", i)
            } else {
                student = Student(TYPE_STUDENT_4, "Student 4", i)
            }
            mDataList.add(student)
        }
        notifyPropertyChanged(BR._all)
    }

    fun getDataList(): List<Student> {
        mDataList.clear()
        for (i in 0..15) {
            var student: Student
            if (i % 2 == 0) {
                student = Student(TYPE_STUDENT_1, "Student 4", i)
            } else if (i % 3 == 0) {
                student = Student(TYPE_STUDENT_2, "Student 5", i)
            } else if (i % 5 == 0) {
                student = Student(TYPE_STUDENT_3, "Student 6", i)
            } else {
                student = Student(TYPE_STUDENT_4, "Student 7", i)
            }
            mDataList.add(student)
        }
        return mDataList
    }
}