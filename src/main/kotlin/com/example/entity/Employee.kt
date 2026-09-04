package com.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "employees")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "employee_id", nullable = false, unique = true)
    var employeeId: String = ""

    @Column(nullable = false)
    var name: String = ""

    @ManyToOne
    @JoinColumn(name = "department_id")
    var department: Department? = null

    var position: String? = null

    var email: String? = null

    @Column(nullable = false)
    var password: String = ""
}