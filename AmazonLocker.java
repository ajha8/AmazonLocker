import java.util.*;
import java.io.*;
public class AmazonLocker{
    
    
    static  Map<String, Integer> lockerMap = new HashMap<>();
    static Map<String,String> itemLockerAsssociation = new HashMap<>();
    
    
    static enum LockerCategory{
        small,
        medium,
        large
    }
    
    static enum ItemCategory{
        small,
        medium,
        large
    }
    
    static class Item{
        String id;
        String category;
        
        public Item(String id, String category){
            
            this.id = id;
            this.category = category;
            
        }
        
        public String getId(){
            return this.id;
        }
        
        public String getCategory(){
            return this.category;
        }
        
        public String setId(String id){
            this.id = id;
            return id;
        }
        
        public String setCategory(String category){
            this.category = category;
            return category;
        }
        
    }
    

   
    public static void initialiseLocker(){
        lockerMap.put("small",1);
        lockerMap.put("medium",0);
        lockerMap.put("large",5);
            
    }
    
    
    public boolean checkLockerAvailablityForItem(String itemEnum){
        if(lockerMap.get(itemEnum) > 0){
            return true;
        } else if(itemEnum.equals("small") && (lockerMap.get("medium") >0 || lockerMap.get("large") >0)){
            return true;
        } else if(itemEnum.equals("medium") && (lockerMap.get("large") >0)){
            return true;
        }
        return false;
    }
    
    
    public void addItemInLocker(Item item){
        String category = item.getCategory();
        if(checkLockerAvailablityForItem(category)){
            if(category.equals("small")){
            
                // find samll, medium or large locker available
                if(lockerMap.get(category.toString()) > 0){
                    System.out.println("small locker available - adding to it");
                    lockerMap.put(category.toString(), lockerMap.get(category.toString())-1);
                    itemLockerAsssociation.put(item.getId(),item.getCategory().toString()); 
                }else if (lockerMap.get("medium") > 0){
                    System.out.println("small not available but medium locker available - adding to it");
                    lockerMap.put("medium", lockerMap.get("medium")-1);
                     itemLockerAsssociation.put(item.getId(),"medium"); 
                }else{
                    System.out.println("small not available but large locker available - adding to it");
                    lockerMap.put("large", lockerMap.get("large")-1);
                     itemLockerAsssociation.put(item.getId(),"large"); 
                }
            } else if(category.equals("medium")){
                // find medium or large locker available
                if(lockerMap.get(category.toString()) > 0){
                     System.out.println("medium locker available - adding to it");
                    lockerMap.put(category.toString(), lockerMap.get(category.toString())-1);
                     itemLockerAsssociation.put(item.getId(),item.getCategory().toString()); 
                }else if (lockerMap.get("large") > 0){
                    System.out.println("medium not available but large locker available - adding to it");
                    lockerMap.put("large", lockerMap.get("large")-1);
                     itemLockerAsssociation.put(item.getId(),"large"); 
                }
            } else{
                if(lockerMap.get(category.toString()) > 0){
                    System.out.println("large locker available - adding to it");
                    lockerMap.put(category.toString(), lockerMap.get(category.toString())-1);
                     itemLockerAsssociation.put(item.getId(),item.getCategory().toString()); 
                }
            }
        } else{
            System.out.println("No Locker Available to add item");
        }
      
    }
    
    
    public void removeItemFromLocker(Item item){
        System.out.println(" Removing "+ item.getId() + " "+ "item from locker");
        lockerMap.put(itemLockerAsssociation.get(item.getId()),lockerMap.get(itemLockerAsssociation.get(item.getId()))+1);
        itemLockerAsssociation.remove(item.getId());
        
    }
    
    
    public void getCurrentLockerStatus(){
        System.out.println("Current Lockers Available");
        System.out.println(lockerMap);
        
        System.out.println("-------------------------------------");
        System.out.println("Current Item_ID-Locker Association");
        System.out.println(itemLockerAsssociation);
        
    }
    
    public static void main(String[] args){
        AmazonLocker lockerObj = new AmazonLocker();
        Item item1 = new Item("200","small");
        Item item2 = new Item("230","medium");
        Item item3 = new Item("500","small");
        Item item4 = new Item("600","large");
        
        //initialise locker
        initialiseLocker();
        
        // add item in locker
        lockerObj.addItemInLocker(item1);
        lockerObj.addItemInLocker(item2);
        lockerObj.addItemInLocker(item3);
        lockerObj.addItemInLocker(item4);
        
        lockerObj.getCurrentLockerStatus();
        
        lockerObj.removeItemFromLocker(item1);
        
        lockerObj.getCurrentLockerStatus();
    }
    
}