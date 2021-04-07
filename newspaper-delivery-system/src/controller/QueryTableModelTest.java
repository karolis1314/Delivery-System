package controller;

import junit.framework.TestCase;

public class QueryTableModelTest extends TestCase {

		// Test #: 1
		// Test Objective: Testing if the connection is valid
		// Inputs:
		// Expected Output: True

		public void testOpenConnection001() {
			try {
				QueryTableModel testObj = new QueryTableModel();
				assertEquals(true, testObj.openConnection());
			} catch (Exception e) {
				fail("Exception is not expected!");
			}
		}
		// Test #: 2
		// Test Objective: Testing if the connection is not valid
		// Inputs:
		// Expected Output: false

		public void testOpenConnection002() {
			try {
				QueryTableModel testObj = new QueryTableModel();
				assertEquals(false, testObj.openConnection());
			} catch (Exception e) {
				fail("Exception is not expected!");
			}
		}

	}
