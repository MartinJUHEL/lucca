package com.martin.lucca.feature.employee.data.api

import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsResponseDto
import com.martin.lucca.feature.employee.data.dto.EmployeesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EmployeeApi {

    /**
     * Get list of users with specified filters and fields
     * @return list of users with some details
     */
    @GET("/api/v3/users/scope")
    suspend fun getEmployees(
        @Query("appInstanceId") appInstanceId: Int,
        @Query("operations") operations: Int,
        @Query("fields") fields: String,
        @Query("orderBy") orderBy: String
    ): Response<EmployeesResponseDto>

    /**
     * Get an employee details
     */
    @GET("api/v3/users/{userId}")
    suspend fun getEmployee(
        @Path("userId") employeeId: Int,
        @Query("fields") fields: String,
    ): Response<EmployeeDetailsResponseDto>

    /**
     * Get an employees by department
     */
    @GET("api/v3/users")
    suspend fun getEmployeeByDepartment(
        @Query("fields") fields: String,
        @Query("departmentId") departmentId: Int,
    ): Response<EmployeesResponseDto>
}