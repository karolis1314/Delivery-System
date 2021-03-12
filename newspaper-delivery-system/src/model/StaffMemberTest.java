package model;

import exceptions.StaffException;
import junit.framework.TestCase;

public class StaffMemberTest extends TestCase {

    // Test #: 1
    // Test Objective: Creating a new Staff Member.
    // Inputs: Staff Member = "Karolis, smartGuy", 1, 1
    // Expected Output: Staff Member Object created."

    public void testStaffMember001(){
        try{
            StaffMember testObj = new StaffMember("Karolis","smartGuy", 1, 1);
            assertEquals("Karolis", testObj.getfName());
            assertEquals("smartGuy", testObj.getlName());
            assertEquals(1, testObj.getAreaId());
            assertEquals(1, testObj.getDocketId());

        }catch(StaffException e){
            fail("Exception is not expected");

        }
    }

    // Test #: 2
    // Test Objective: Prevent empty first name.
    // Inputs: "null"
    // Expected Output: "Employee name, must be from 1 to 15 characters."

    public void testValidateFirstName001() {
        try {
            StaffMember.validateFirstName("");
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Employee name, must be from 1 to 15 characters.", e.getMessage());
        }
    }

    // Test #: 3
    // Test Objective: Prevent false first name.
    // Inputs: "Ab"
    // Expected Output: "Employee name, must be from 3 to 15 characters."

    public void testValidateFirstName002() {
        try {
            StaffMember.validateFirstName("Ab");
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Employee name, must be from 3 to 15 characters.", e.getMessage());
        }
    }
    // Test #: 4
    // Test Objective: Prevent false first name.
    // Inputs: "karolis karolis karolis karolis karolis karolis"
    // Expected Output: "Employee name, must be from 3 to 15 characters."

    public void testValidateFirstName003() {
        try {
            StaffMember.validateFirstName("karolis karolis karolis karolis karolis karolis");
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Employee name, must be from 3 to 15 characters.", e.getMessage());
        }
    }

    // Test #: 5
    // Test Objective: Prevent false Area Id
    // Inputs: 0
    // Expected Output: "Area Id can not equal or be less than 0."

    public void testValidateAreaId001() {
        try {
            StaffMember.validateAreaId(0);
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Area Id can not equal or be less than 0.", e.getMessage());
        }
    }

    // Test #: 6
    // Test Objective: Prevent false Area Id
    // Inputs: -1
    // Expected Output: "Area Id can not equal or be less than 0."

    public void testValidateAreaId002() {
        try {
            StaffMember.validateAreaId(-1);
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Area Id can not equal or be less than 0.", e.getMessage());
        }
    }
    // Test #: 7
    // Test Objective: Prevent false Area Id
    // Inputs: 100
    // Expected Output: "Area Id can not equal or be more than 100."

    public void testValidateAreaId003() {
        try {
            StaffMember.validateAreaId(100);
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Area Id can not equal or be more than 100.", e.getMessage());
        }
    }
    // Test #: 8
    // Test Objective: Prevent false Area Id
    // Inputs: 101
    // Expected Output: "Area Id can not equal or be more than 100."

    public void testValidateAreaId004() {
        try {
            StaffMember.validateAreaId(101);
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Area Id can not equal or be more than 100.", e.getMessage());
        }
    }

    // Test #: 9
    // Test Objective: Prevent empty last name.
    // Inputs: ""
    // Expected Output: "Employee last name, must be from 1 to 15 characters."

    public void testValidateLastName001() {
        try {
            StaffMember.validateLastName("");
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Employee last name, must be from 1 to 15 characters.", e.getMessage());
        }
    }
    // Test #: 10
    // Test Objective: Prevent empty last name.
    // Inputs: "Ab"
    // Expected Output: "Employee last name, must be from 3 to 15 characters."

    public void testValidateLastName002() {
        try {
            StaffMember.validateLastName("Ab");
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Employee last name, must be from 3 to 15 characters.", e.getMessage());
        }
    }
    // Test #: 11
    // Test Objective: Prevent empty last name.
    // Inputs: "karolis karolis karolis karolis karolis karolis karolis"
    // Expected Output: "Employee last name, must be from 3 to 15 characters."

    public void testValidateLastName003() {
        try {
            StaffMember.validateLastName("karolis karolis karolis karolis karolis karolis karolis");
            fail("Expected exception.");

        } catch (StaffException e) {
            assertEquals("Employee last name, must be from 3 to 15 characters.", e.getMessage());
        }
    }





}