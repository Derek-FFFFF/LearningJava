package com.gnaf.basic.generic.demo04;

/**
 * @author Derek
 * @date 2019/10/21 21:44
 */
interface Payable<T> {}

class Employee implements Payable<Employee> {}

//public class Hourly extends Employee implements Payable<Hourly> {
public class Hourly extends Employee {
}
