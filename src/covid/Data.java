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
public class Data {
    private static String nama, status;
    private static int id, p_odp, p_pdp,  p_positif, m_pdp, m_odp, m_positif;
    
    public void setDaerah(String newnama){
        nama = newnama;
        tambahData();
    }
    private void tambahData(){
        try{   
            String sql = "INSERT INTO total VALUES(NULL,'"+nama+"','0','0','0','hijau')";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = db.prepareStatement(sql);
            pstm.execute(); 
             System.out.println("sql methode tambahdata success ");
        }catch(HeadlessException | SQLException e){
              System.out.println("Error sql methode tambahdata : "+e.getMessage());
        } catch (ClassNotFoundException ex) {
             System.out.println("Error  methode tambahdata : "+ex.getMessage());
        }
        
    }
    
public void updateNama(){
    try{   

            String sql = "UPDATE total SET nama_daerah = '"+nama+"' WHERE id_daerah='"+id+"'";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = db.prepareStatement(sql);
            pstm.execute();
            System.out.println("sql methode updatenama success ");
        }catch(HeadlessException | SQLException e){
              System.out.println("Error sql methode updatenama : "+e.getMessage());
        } catch (ClassNotFoundException ex) {
               System.out.println("Error  methode updatenama : "+ex.getMessage());
        }
}
private void ambilData(){
    try{
            String sql = "SELECT sum(odp), sum(pdp), sum(positif) FROM penambahan WHERE id_daerah='"+id+"'";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                p_odp = result.getInt(1);
                p_pdp = result.getInt(2);
                p_positif = result.getInt(3);
            }
            System.out.println("sql methode ambildata penambahan success");
        }catch(SQLException e){
            System.out.println("Error sql methode ambildata : "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error methode ambildata : "+ex.getMessage());
        }
    try{
            String sql = "SELECT sum(odp), sum(pdp), sum(positif) FROM pengurangan WHERE id_daerah='"+id+"'";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                m_odp = result.getInt(1);
                m_pdp = result.getInt(2);
                m_positif = result.getInt(3);
            }
            System.out.println("sql methode ambildata pengurangan success");
        }catch(SQLException e){
            System.out.println("Error sql methode ambildata pengurangan : "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error methode ambildata pengurangan : "+ex.getMessage());
        }
    
    if(p_positif-m_positif>0)
        status = "merah";
    else
        status = "hijau";

}

public void updateStatus(int newid){
    id  = newid;
    ambilData();
    int odp = p_odp-m_odp;
    int pdp = p_pdp-m_pdp;
    int positif = p_positif-m_positif;
    try{   

            String sql = "UPDATE total SET odp = '"+odp+"', pdp = '"+pdp+"', positif = '"+positif+"', status = '"+status+"' WHERE id_daerah='"+id+"'";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = db.prepareStatement(sql);
            pstm.execute();
            System.out.println("sql methode updatestatus success : ");
        }catch(HeadlessException | SQLException e){
              System.out.println("Error sql methode update status : "+e.getMessage());
        } catch (ClassNotFoundException ex) {
               System.out.println("Error methode update status : "+ex.getMessage());
        }
}

public void outputAll(){
    try{
            String sql = "SELECT * FROM total";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            System.out.println("ID      Nama        ODP     PDP     Positif     Status");
            while(result.next()){
                System.out.println(result.getInt(1)+"   "+result.getString(2)+" "+result.getInt(3)+"   "+result.getInt(4)+"   "+result.getInt(5)+"   "+result.getString(6));
            }
        }catch(SQLException e){
            System.out.println("Error sql methode outputall : "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error methode outputall : "+ex.getMessage());
        }
}

public void outputSearch(int  newid){
    try{
            String sql = "SELECT * FROM total WHERE id_daerah='"+newid+"'";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                System.out.println("Output Search");
                System.out.println("ID ="+result.getInt(1));
                System.out.println("Nama ="+result.getString(2));
                System.out.println("ODP ="+result.getInt(3));
                System.out.println("PDP ="+result.getInt(4));
                System.out.println("Positif ="+result.getInt(5));
                System.out.println("Status ="+result.getString(6));
            }
        }catch(SQLException e){
            System.out.println("Error sql methode outputsearch: "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error  methode output search : "+ex.getMessage());
        }
}

public void outputSearch(String newnama){
    try{
            String sql = "SELECT * FROM total WHERE nama_daerah='"+newnama+"'";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                System.out.println("Output Search");
                System.out.println("ID ="+result.getInt(1));
                System.out.println("Nama ="+result.getString(2));
                System.out.println("ODP ="+result.getInt(3));
                System.out.println("PDP ="+result.getInt(4));
                System.out.println("Positif ="+result.getInt(5));
                System.out.println("Status ="+result.getString(6));
            }
        }catch(SQLException e){
            System.out.println("Error sql methode outputsearch: "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error  methode output search : "+ex.getMessage());
        }
}
public void outputTotal(){
    try{
            String sql = "SELECT sum(odp), sum(pdp), sum(positif) FROM total";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                System.out.println("Total Data Covid di Indonesia ");
                System.out.println("ODP ="+result.getInt(1));
                System.out.println("PDP ="+result.getInt(2));
                System.out.println("Positif ="+result.getInt(3));
            }
        }catch(SQLException e){
            System.out.println("Error sql methode outputtotal: "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error methode outputtotal : "+ex.getMessage());
        }
}

public void tampilDaerah(){
    
 try{
            String sql = "SELECT id_daerah, nama_daerah FROM total";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                System.out.println(" "+result.getInt(1)+".  "+result.getString(2));
            }
        }catch(SQLException e){
            System.out.println("Error sql methode tampildata: "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error methode tampil data : "+ex.getMessage());
        }

}
}
