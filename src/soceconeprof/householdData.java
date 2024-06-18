package soceconeprof;

import java.sql.Date;

public class householdData {
    
    private String householdRepresentative;
    private Integer houseNumber;
    private String zone;
    private String phoneNumber;
    private Integer houseMembers;
    private String employed;
    private String image;
    private Date date;
    private String income;
    
    
    public householdData(String householdRepresentative, Integer houseNumber, String zone, String phoneNumber, Integer houseMembers, String employed, String image, Date date){
        this.householdRepresentative = householdRepresentative;
        this.houseNumber = houseNumber;
        this.zone = zone;
        this.phoneNumber = phoneNumber;
        this.houseMembers = houseMembers;
        this.employed = employed;
        this.image = image;
        this.date = date;
    }
    
    public householdData(String householdRepresentative, Integer houseNumber, String zone, String phoneNumber, Integer houseMembers, String employed, String income){
    
        this.householdRepresentative = householdRepresentative;
        this.houseNumber = houseNumber;
        this.zone = zone;
        this.phoneNumber = phoneNumber;
        this.houseMembers = houseMembers;
        this.employed = employed;
        this.income = income;
        
    }
    
    public String getHouseholdRepresentative(){
        return householdRepresentative;
    }
    
    public Integer getHouseNumber(){
        return houseNumber;
    }
    
    public String getZone(){
        return zone;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public Integer getHouseMembers(){
        return houseMembers;
    }
    
    public String getEmployed(){
        return employed;
    }
    
    public String getImage(){
        return image;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getIncome(){
        return income;
    }
    
}
