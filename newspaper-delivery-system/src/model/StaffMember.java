package model;


import exceptions.StaffException;

public class StaffMember {
    private int staffId;
    private String fName;
    private String lName;
    private String password;
    private int areaId;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public StaffMember(int id, String name, String last_name, String password, int areaId)throws StaffException{
        try{
            validateName(name);
            validateLastName(last_name);
            validatePassword(password);
            validateAreaId(areaId);
        }catch (StaffException e){
            throw new StaffException("Not a valid Staff Member.");
        }
        this.staffId = id;
        this.fName = name;
        this.lName = last_name;
        this.password = password;
        this.areaId = areaId;
    }

    public static void validateName(String name)throws StaffException{
        if(name.length() > 20){
            throw new StaffException("Staff name can not be more than 20 characters.");
        }
        if(name.isEmpty() || name.equals("")){
            throw  new StaffException("Name can not be empty.");
        }
    }

    public static void validateLastName(String lastName)throws StaffException{
        if(lastName.length() > 20){
            throw new StaffException("Staff last name can not be more than 20 characters.");
        }
        if(lastName.isEmpty() || lastName.equals("")){
            throw  new StaffException("Last name can not be empty.");
        }
    }

    public static void validatePassword(String password)throws StaffException{
        if(password.length() < 6 || password.length() > 20){
            throw new StaffException("Password must be between 6 and 20 characters");
        }
        if(password.isEmpty()){
            throw  new StaffException("Password can not be empty.");
        }
    }

    public static void validateAreaId(int id)throws StaffException{
        if(id <= 0){
            throw new StaffException("Area ID can not be 0 or less than 0.");
        }
    }







}
