package model;

import exceptions.PublicationException;
import exceptions.StaffException;
import junit.framework.TestCase;

public class StaffMemberTest extends TestCase {


//	Test number: 1
//  Objective: Create Staff Object.
//  Test type: JUint
//  Input: (1, "Karolis", "Valatka", "a00252699", 1);
//  Output: No Faults.

    public void testStaffMember001() {
        try {
            StaffMember test = new StaffMember(1, "Karolis", "Valatka", "a00252699", 1);
            assertEquals(1, test.getStaffId());
            assertEquals("Karolis", test.getfName());
            assertEquals("Valatka", test.getlName());
            assertEquals("a00252699", test.getPassword());
            assertEquals(1, test.getAreaId());
        } catch (StaffException e) {
            fail("Exception not expected.");
        }
    }

//	Test number: 2
//  Objective: Do not create Staff Object.
//  Test type: JUint
//  Input: (1, "", "Valatka", "a00252699", 1);
//  Output: "Not a valid Staff Member."

    public void testStaffMember002() {
        try {
            StaffMember test = new StaffMember(1, "", "Valatka", "a00252699", 1);
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Not a valid Staff Member.", e.getMessage());
        }
    }

//	Test number: 3
//  Objective: Correct name no errors.
//  Test type: JUint
//  Input: "Karolis"
//  Output: No errors.

    public void testValidateName001() {
        try {
            StaffMember.validateName("Karolis");
        } catch (StaffException e) {
            fail("Exception not expected.");
        }
    }

//	Test number: 4
//  Objective: Empty name.
//  Test type: JUint
//  Input: ""
//  Output: "Name can not be empty."

    public void testValidateName002() {
        try {
            StaffMember.validateName("");
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Name can not be empty.", e.getMessage());
        }
    }
//	Test number: 5
//  Objective: Name larger than 20 characters.
//  Test type: JUint
//  Input: "KarolisKarolisKarolisKarolisKarolis"
//  Output: "Staff name can not be more than 20 characters."

    public void testValidateName003() {
        try {
            StaffMember.validateName("KarolisKarolisKarolisKarolisKarolis");
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Staff name can not be more than 20 characters.", e.getMessage());
        }
    }

//	Test number: 6
//  Objective:Last Name larger than 20 characters.
//  Test type: JUint
//  Input: "KarolisKarolisKarolisKarolisKarolis"
//  Output: "Staff last name can not be more than 20 characters."

    public void testValidateLastName001() {
        try {
            StaffMember.validateLastName("KarolisKarolisKarolisKarolisKarolis");
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Staff last name can not be more than 20 characters.", e.getMessage());
        }
    }

//	Test number: 7
//  Objective:Last Name empty.
//  Test type: JUint
//  Input: ""
//  Output: "Last name can not be empty."

    public void testValidateLastName002() {
        try {
            StaffMember.validateLastName("");
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Last name can not be empty.", e.getMessage());
        }
    }

//	Test number: 8
//  Objective:Valid last name.
//  Test type: JUint
//  Input: "Valatka"
//  Output: No errors.

    public void testValidateLastName003() {
        try {
            StaffMember.validateLastName("Valatka");
        } catch (StaffException e) {
            fail("Exception not expected.");
        }
    }

//	Test number: 9
//  Objective:Valid Password.
//  Test type: JUint
//  Input: "Valatka"
//  Output: No errors.

    public void testValidatePassword001() {
        try {
            StaffMember.validatePassword("Valatka");
        } catch (StaffException e) {
            fail("Exception not expected.");
        }
    }
//	Test number: 10
//  Objective: Empty password.
//  Test type: JUint
//  Input: ""
//  Output: "Password must be between 6 and 20 characters"

    public void testValidatePassword002() {
        try {
            StaffMember.validatePassword("");
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Password must be between 6 and 20 characters", e.getMessage());
        }
    }
//	Test number: 11
//  Objective: 5 characters password.
//  Test type: JUint
//  Input: "abcde"
//  Output: "Password must be between 6 and 20 characters"

    public void testValidatePassword003() {
        try {
            StaffMember.validatePassword("abcde");
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Password must be between 6 and 20 characters", e.getMessage());
        }
    }
//	Test number: 12
//  Objective: 21 characters password.
//  Test type: JUint
//  Input: "KarolisKarolisKarolisKarolisKarolisKarolis"
//  Output: "Password must be between 6 and 20 characters"

    public void testValidatePassword004() {
        try {
            StaffMember.validatePassword("KarolisKarolisKarolisKarolisKarolisKarolis");
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Password must be between 6 and 20 characters", e.getMessage());
        }
    }
//	Test number: 13
//  Objective: 0 area id
//  Test type: JUint
//  Input: 0
//  Output: "Area ID can not be 0 or less than 0."

    public void testValidateAreaId001() {
        try {
            StaffMember.validateAreaId(0);
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Area ID can not be 0 or less than 0.", e.getMessage());
        }
    }

//	Test number: 14
//  Objective: -1 area id
//  Test type: JUint
//  Input: 0
//  Output: "Area ID can not be 0 or less than 0."

    public void testValidateAreaId002() {
        try {
            StaffMember.validateAreaId(-1);
            fail("Exception expected.");
        } catch (StaffException e) {
            assertEquals("Area ID can not be 0 or less than 0.", e.getMessage());
        }
    }

//	Test number: 15
//  Objective: Valid area ID.
//  Test type: JUint
//  Input: 1
//  Output: No Errors.

    public void testValidateAreaId003() {
        try {
            StaffMember.validateAreaId(1);
        } catch (StaffException e) {
            fail("Exception not expected.");
        }
    }


}
