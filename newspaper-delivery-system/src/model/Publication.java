package model;

import exceptions.PublicationException;

public class Publication {
    private int id;
    private String frequencyInDays;
    private String name;
    private double price;
    private int stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrequencyInDays() {
        return frequencyInDays;
    }

    public void setFrequencyInDays(String frequencyInDays) {
        this.frequencyInDays = frequencyInDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Publication(int id, String frequencyInDays, String name, double price, int stock) throws PublicationException{
        try{
            validateFrequencyInDays(frequencyInDays);
            validateName(name);
            validatePrice(price);
            validateStock(stock);
        }catch (PublicationException e){
            throw new PublicationException(e.getMessage());
        }
        this.id = id;
        this.frequencyInDays = frequencyInDays;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static void validateFrequencyInDays(String frequencyInDays) throws PublicationException{
        if (Integer.parseInt(frequencyInDays)==1) {
        	System.out.println("Ok");
        }else if(Integer.parseInt(frequencyInDays)==7) {
        	System.out.println("Ok");
        }else if(Integer.parseInt(frequencyInDays)==14) {
        	System.out.println("Ok");
        }else if(Integer.parseInt(frequencyInDays)==30){
        	System.out.println("Ok");
            }else   throw new PublicationException("Frequency of the publication must be valid, 1-7-14-30.");
    }

    public static void validateName(String name) throws PublicationException{
        if(name.length() > 25){
            throw new PublicationException("Name of Publication can not be larger than 25 characters.");
        }
        if(name.isEmpty() || name.equals("")){
            throw new PublicationException("Name of Publication can not be empty.");
        }
    }

    public static void validatePrice(double price) throws PublicationException{
        if(price <=0 || price >20.00){
            throw new PublicationException("Price of the Publication can be 0 or larger than ?20.00.");
        }
    }

    public static void validateStock(int stock) throws PublicationException{
        if(stock < 0){
            throw new PublicationException("Stock can not be less than 0.");
        }
    }
}
