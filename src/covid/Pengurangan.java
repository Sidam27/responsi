/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author SIDAM
 */
public class Pengurangan extends Statistik{
    private static int odp, pdp, positif, hari, id;
    
    @Override
    public void tambahData() {
               try{   
            String sql = "INSERT INTO pengurangan VALUES(NULL,'"+id+"','"+hari+"','"+odp+"','"+pdp+"','"+positif+"')";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = db.prepareStatement(sql);
            pstm.execute(); 
             System.out.println("sql class pengurangan success ");
        }catch(HeadlessException | SQLException e){
              System.out.println("Error sql class pengurangan : "+e.getMessage());
        } catch (ClassNotFoundException ex) {
             System.out.println("Error  class pengurangan : "+ex.getMessage());
        }
    }

    @Override
    public void setData(int newodp, int newpdp, int newpositif, int newhari, int newid) {
        odp = newodp;
        pdp = newpdp;
        positif = newpositif;
        hari = newhari;
        id = newid;
        tambahData();
    }

    @Override
    public void tampilHarian(int newhari) {
      try{
            String sql = "SELECT sum(odp), sum(pdp), sum(positif)  FROM pengurangan WHERE hari ='"+newhari+"'";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                
                System.out.println("ODP -"+result.getInt(1));
                System.out.println("PDP -"+result.getInt(2));
                System.out.println("Positif -"+result.getInt(3));
                
            }
        }catch(SQLException e){
            System.out.println("Error sql methode tampilharian pengurangan: "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error  methode tampilharian pengurangan : "+ex.getMessage());
        }
    }
    
}
