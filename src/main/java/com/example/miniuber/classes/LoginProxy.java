package com.example.miniuber.classes;

import java.util.HashMap;
import java.util.Map;

public class LoginProxy {
    private static final Map<String, Object> driverCache = new HashMap<>();
    private static final Map<String, Object> customerCache = new HashMap<>();
    private static final Map<String, Object> employeeCache = new HashMap<>();

    public static  Person PersonLogin(String email, String password) {
        String driverCacheKey = email + password + "driver";
        String customerCacheKey  = email + password + "customer";
        String employeeCacheKey  = email + password + "employee";

        if (driverCache.containsKey(driverCacheKey))
        {
            System.out.println("Retrieving Driver from cache...");
            return (Driver) driverCache.get(driverCacheKey);
        }
        else if (customerCache.containsKey(customerCacheKey))
        {
            System.out.println("Retrieving Customer from cache...");
            return (Customer) customerCache.get(customerCacheKey);
        }
        else if (employeeCache.containsKey(employeeCacheKey))
        {
            System.out.println("Retrieving Employee from cache...");
            return (Employee) employeeCache.get(employeeCacheKey);
        }


        Driver driver = Login.DriverLogin(email,password);
        if(driver != null )
        {
            driverCache.put(driverCacheKey, driver);
            return  driver;
        }
        Customer customer = Login.CustomerLogin(email,password);
        if(customer!= null )
        {
            customerCache.put(customerCacheKey, customer);
            return customer;
        }
        Employee employee = Login.EmployeeLogin(email,password);
        if(employee != null )
        {
            employeeCache.put(employeeCacheKey, employee);
            return employee;
        }

        return  null;
    }

}
