package model;


import exceptions.StaffException;

public class StaffMember {
    private String fName;
    private String lName;
    private int areaId;
    private int docketId;

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getAreaId() {
        return areaId;
    }

    public int getDocketId() {
        return docketId;
    }

    public StaffMember(String fName, String lName, int areaId, int docketId) throws StaffException {
        // Validate the inputs
        try {
            validateFirstName(fName);
            validateLastName(lName);
            validateAreaId(areaId);
            validateDocketId(docketId);
        } catch (StaffException e) {
            throw e;
        }
        this.fName = fName;
        this.lName = lName;
        this.areaId = areaId;
        this.docketId = docketId;
    }

    public static void validateFirstName(String fName) throws StaffException {
            if(fName.isEmpty()||fName.equals("")) {throw new StaffException("Employee name, must be from 1 to 15 characters.");}
            if(fName.length() <=2 || fName.length() >15) {throw new StaffException("Employee name, must be from 3 to 15 characters.");}
    }

    public static void validateAreaId(int areaId) throws StaffException{
        if(areaId <=0){throw new StaffException("Area Id can not equal or be less than 0.");}
        if(areaId >=100){throw new StaffException("Area Id can not equal or be more than 100.");}

    }

    public static void validateLastName(String lName) throws StaffException{
        if(lName.isEmpty()||lName.equals("")) {throw new StaffException("Employee last name, must be from 1 to 15 characters.");}
        if(lName.length() <=2 || lName.length() >15) {throw new StaffException("Employee last name, must be from 3 to 15 characters.");}

    }

    public static void validateDocketId(int docketId) throws StaffException{

    }




}
