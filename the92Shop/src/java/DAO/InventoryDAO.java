/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import db.DatabaseConnection;
import entity.Inventory;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Brendo Austin
 */
public class InventoryDAO {
    private ArrayList<Inventory> inventoryList; 
    
    public Inventory retrieve(int skuId) {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        Inventory result = null;
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from inventory where SKU_Id = ?");
            stmt.setInt(1, skuId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int skuID = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                String dateOfPurchase = rs.getString(4);
                double costPrice = rs.getDouble(5);
                double sellingPrice = rs.getDouble(6);
                result = new Inventory (skuID, name, quantity, dateOfPurchase, costPrice, sellingPrice );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConn();
        return result;
    }
    
    public String retrieveInventoryNameById(int skuId) {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        String result = null;
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from inventory where SKU_Id = ?");
            stmt.setInt(1, skuId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                result = rs.getString(2);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConn();
        return result;
    }
    
    public Inventory retrieveInventoryByName(String skuName) {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        Inventory result = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory where Name = ?");
            stmt.setString(1, skuName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int skuID = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                String dateOfPurchase = rs.getString(4);
                double costPrice = rs.getDouble(5);
                double sellingPrice = rs.getDouble(6);
                result = new Inventory(skuID, name, quantity, dateOfPurchase, costPrice, sellingPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.closeConn();
        return result;
    }

    public ArrayList<Inventory> retrieveInventoryList() {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        ArrayList<Inventory> result = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement("select * from inventory");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int skuID = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                String dateOfPurchase = rs.getString(4);
                double costPrice = rs.getDouble(5);
                double sellingPrice = rs.getDouble(6);
                result.add(new Inventory(skuID, name, quantity, dateOfPurchase, costPrice, sellingPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.closeConn();
        return result;
    }

    public ArrayList<Inventory> retrieveInventoryWithQuantityAtLeast(int number) {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        ArrayList<Inventory> toReturn = new ArrayList<>();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from inventory where quantity >= '" + number + "'");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int skuID = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                String dateOfPurchase = rs.getString(4);
                double costPrice = rs.getDouble(5);
                double sellingPrice = rs.getDouble(6);
                toReturn.add(new Inventory (skuID, name, quantity, dateOfPurchase, costPrice, sellingPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConn();
        return toReturn;
    }
    
    public ArrayList<Inventory> retrieveInventoryWithQuantityBelow(int number){
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        ArrayList<Inventory> toReturn = new ArrayList<>();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from inventory where quantity < '" + number + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int skuID = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                String dateOfPurchase = rs.getString(4);
                double costPrice = rs.getDouble(5);
                double sellingPrice = rs.getDouble(6);
                toReturn.add(new Inventory (skuID, name, quantity, dateOfPurchase, costPrice, sellingPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConn();
        return toReturn;
        
    }
    
    public ArrayList<Inventory> retrieveInventoryPurchasedBefore(String date){
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        ArrayList<Inventory> toReturn = new ArrayList<>();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from inventory where Date_Of_Purchase < '" + date + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int skuID = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                String dateOfPurchase = rs.getString(4);
                double costPrice = rs.getDouble(5);
                double sellingPrice = rs.getDouble(6);
                toReturn.add(new Inventory (skuID, name, quantity, dateOfPurchase, costPrice, sellingPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConn();
        return toReturn;
    }
    
    public ArrayList<Inventory> retrieveInventoryPurchasedBetween(String startDate, String endDate){
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConn();
        ArrayList<Inventory> toReturn = new ArrayList<>();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from inventory where Date_Of_Purchase Between '" + startDate + "' and '" + endDate + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int skuID = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                String dateOfPurchase = rs.getString(4);
                double costPrice = rs.getDouble(5);
                double sellingPrice = rs.getDouble(6);
                toReturn.add(new Inventory (skuID, name, quantity, dateOfPurchase, costPrice, sellingPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConn();
        return toReturn;
    }
    
    public LinkedHashMap <Integer, String[]> sortByMonthYear() {

        LinkedHashMap <Integer, String[]> result = new LinkedHashMap<>();
        String[] array;
        int count = 1;

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("select name,sum(cp.quantity) as totalQty,SUBSTRING(Date_Of_Purchase, 6,2) as month,SUBSTRING(Date_Of_Purchase, 1, 4) as year from inventory i inner join customer_purchase cp on i.SKU_Id = cp.SKU_Id group by cp.sku_id,month order by month ASC,year ASC,totalQty DESC");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                String quantity = rs.getString(2);
                String month = rs.getString(3);
                String year = rs.getString(4);
                
                array = new String[]{name,quantity, month, year};
                result.put(count,array);
                count++;
                
            }
            db.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public LinkedHashMap <Integer, String[]> sortByYear() {

        LinkedHashMap <Integer, String[]> result = new LinkedHashMap<>();
        String[] array;
        int count = 1;

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("select name,sum(cp.quantity) as totalQty,SUBSTRING(Date_Of_Purchase, 1, 4) as year from inventory i inner join customer_purchase cp on i.SKU_Id = cp.SKU_Id group by cp.sku_id,year order by year ASC,totalQty DESC");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                String quantity = rs.getString(2);
                String year = rs.getString(3);
                
                array = new String[]{name,quantity, year};
                result.put(count,array);
                
                count++;
            }
            db.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ArrayList<Inventory> retrieveLowInventory() {

        Inventory inv = null;
        ArrayList<Inventory> invList = new ArrayList<>();

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT Name, Quantity FROM inventory WHERE Quantity < 30 order by quantity ASC");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                String name = rs.getString(1);
                int quantity = rs.getInt(2);

                inv = new Inventory(name, quantity);
                invList.add(inv);
                
                for(Inventory i: invList){
                    out.println(i);
                }
            }
            db.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invList;
    }

    public int retrieveInventoryAlert() {

        int quantity = 0;

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT count(Quantity) FROM inventory WHERE Quantity < 30");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                quantity = rs.getInt(1);
            }
            db.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quantity;
    }
    
    
    //NAME + EXPENSES from purchasing of goods from supplier
    public HashMap<String,Double> retrieveGoodsNCost (int month, String year) {
        HashMap<String,Double> goodsList = new HashMap<String, Double>();
         try{
             DatabaseConnection db = new DatabaseConnection();
             Connection conn = db.getConn();
             PreparedStatement stmt = conn.prepareStatement("select name, ip.quantity * Cost_Price as goods_cost from inventory i inner join inventory_purchase ip on i.SKU_Id = ip.SKU_Id where EXTRACT(month FROM Date_Of_Purchase)= '"+ month +"' AND EXTRACT(year FROM Date_Of_Purchase) = '"+ year +"' group by ip.sku_id") ;
             ResultSet rs = stmt.executeQuery();
             
             
             while(rs.next()) {
                 String goodsType = rs.getString(1);
                 double goodsCost = rs.getDouble(2);
                 goodsList.put(goodsType, goodsCost);
             }
                     
         } catch (Exception e) {
            e.printStackTrace();
        }
         
         return goodsList;
    }
    // DOUBLE EXPENSES from purchasing of goods from supplier
    public double retreiveGoodsCost(int month, String year){
         double totalCost = 0.0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("select ip.quantity * Cost_Price as goods_cost from inventory i inner join inventory_purchase ip on i.SKU_Id = ip.SKU_Id where EXTRACT(month FROM Date_Of_Purchase)= '"+ month +"' AND EXTRACT(year FROM Date_Of_Purchase) = '"+ year +"'");           
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                double cost= rs.getDouble(1);
                totalCost += cost;
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalCost;
    
    }
    
    
    //NAME + Sales from purchasing of goods from supplier
    public HashMap<String,Double> retrieveSellingProductsNCost (int month, String year) {
        HashMap<String,Double> sellingList = new HashMap<String, Double>();
         try{
             DatabaseConnection db = new DatabaseConnection();
             Connection conn = db.getConn();
             PreparedStatement stmt = conn.prepareStatement("select name, sum(cp.quantity)* Selling_Price as Sales from inventory i inner join customer_purchase cp on i.SKU_Id = cp.SKU_Id where EXTRACT(month FROM Date_Of_Purchase)= '"+ month +"' AND EXTRACT(year FROM Date_Of_Purchase)= '"+ year +"' group by cp.sku_id") ;
             ResultSet rs = stmt.executeQuery();
             
             
             while(rs.next()) {
                 String goodsType = rs.getString(1);
                 double goodsCost = rs.getDouble(2);
                 sellingList.put(goodsType, goodsCost);
             }
                     
         } catch (Exception e) {
            e.printStackTrace();
        }
         
         return sellingList;
    }
    
    
     //Sales from purchasing of goods from supplier
    public double retrieveSales(int month, String year){
        double totalSales = 0.0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("select sum(cp.quantity)* Selling_Price as Sales from inventory i inner join customer_purchase cp on i.SKU_Id = cp.SKU_Id where EXTRACT(month FROM Date_Of_Purchase)= '"+ month +"' AND EXTRACT(year FROM Date_Of_Purchase)= '"+ year +"'");           
            
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                double cost= rs.getDouble(1);
                totalSales += cost;
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalSales;
    
    }
    
    public int deleteSpecifiedInventory(String skuId) {
        int updateQuery = 0;
        
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("delete from inventory where SKU_Id = ?");
            stmt.setString(1, skuId);
            updateQuery = stmt.executeUpdate();
            db.closeConn();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateQuery;

    }
    
    public int editInventory(int id, String name, int qty, String date, double cost, double price) {
        
        int updateQuery = 0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET Name = ?, Quantity = ?, Updated_Date = ?, Cost_Price = ?, Selling_Price = ? where SKU_Id = ?");
            
            stmt.setString(1, name);
            stmt.setInt(2, qty);
            stmt.setString(3, date);
            stmt.setDouble(4, cost);
            stmt.setDouble(5, price);
            stmt.setInt(6, id);
            
            updateQuery = stmt.executeUpdate();
            db.closeConn();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateQuery;
    }
    
    public int addInventory(Inventory i) {
        int updateQuery = 0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory VALUES (?,?,?,?,?,?)");
            
            stmt.setInt(1, i.getSKUID());
            stmt.setString(2, i.getName());
            stmt.setInt(3, i.getQuantity());
            stmt.setString(4, i.getPurchaseDate());
            stmt.setDouble(5, i.getCostPrice());
            stmt.setDouble(6, i.getSellingPrice());
            

            updateQuery = stmt.executeUpdate();

            db.closeConn();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateQuery;

    }
    //also serve as deducting inventory
    public int addInventoryQty(String name, int qty, String date) {
        int updateQuery = 0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET Quantity = ?, Updated_Date = ? where name = ?");
            
            stmt.setInt(1, qty);
            stmt.setString(2, date);
            stmt.setString(3, name);

            updateQuery = stmt.executeUpdate();

            db.closeConn();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateQuery;

    }
    
     public ArrayList<Inventory> retrievePromoItem(int month, String year){
         Inventory inv = null;
        ArrayList<Inventory> promoItemList = new ArrayList<>();

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
     
            PreparedStatement stmt = conn.prepareStatement("select i.SKU_ID, i.Name, sum(cp.Quantity) from inventory i inner join customer_purchase cp where i.SKU_ID = cp.SKU_ID and i.name LIKE '0%' and EXTRACT(MONTH from Date_Of_Purchase)= '"+ month +"'and extract(YEAR FROM Date_Of_Purchase) = '"+ year +"'group by SKU_ID ASC ");
                    
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int SKUId = rs.getInt(1);
                String name = rs.getString(2);
                int quantity = rs.getInt(3);
                
                inv = new Inventory(SKUId,name,quantity);

                promoItemList.add(inv);
            }
            db.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return promoItemList;
    }
    
     public ArrayList<String> retrievePromoName(int month, String year){
         
        ArrayList<String> promoNameList = new ArrayList<>();

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("select i.Name from inventory i inner join customer_purchase cp "
                    + "where i.SKU_ID = cp.SKU_ID and i.name LIKE '0%' and EXTRACT(MONTH from Date_Of_Purchase)='"+ month +"' and extract(YEAR FROM Date_Of_Purchase) = '"+ year +"' group by SKU_ID");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                promoNameList.add(name);
            }
            db.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return promoNameList;
    }
     
    public LinkedHashMap<String, String> retrievePromoInfo(int month, String year){
        LinkedHashMap<String, String>resultList = new LinkedHashMap<>();
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConn();
            PreparedStatement stmt = conn.prepareStatement("select i.Name, sum(cp.quantity) as totalQuantity from inventory i inner join customer_purchase cp where i.SKU_ID = cp.SKU_ID and i.name LIKE '0%' and EXTRACT(MONTH from Date_Of_Purchase)='"+ month +"' and extract(YEAR FROM Date_Of_Purchase) = '"+ year +"' group by i.SKU_ID ASC");                 
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                int quantity = rs.getInt(2);
                String totalQty = Integer.toString(quantity);
                
               resultList.put(name, totalQty);
            }
            db.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    } 
    
    

}
