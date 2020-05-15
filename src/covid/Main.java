/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author SIDAM
 */
public class Main {
    private static int hari;

    
    private static void getHari(){
        try{
            String sql = "SELECT max(hari) FROM penambahan";
            java.sql.Connection db = (Connection) Koneksi.configDB();
            java.sql.Statement stm;
            stm = db.createStatement();
            java.sql.ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                hari = result.getInt(1);
   
            }
        }catch(SQLException e){
            System.out.println("Error : "+e.getMessage());
       } catch (ClassNotFoundException ex) {
             System.out.println("Error3 : "+ex.getMessage());
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        int pilih,id,odp,pdp,positif;
        String nama;
        Statistik tambah = new Penambahan();
        Statistik kurang = new Pengurangan();
        Data data = new Data();
        Scanner input = new Scanner(System.in);
        Main.getHari();
        do{
            System.out.println("Menu Persebaran Kasus Covid-19 di Indonesia Hari ke-"+hari);
            System.out.println("1. Tambah Daerah");
            System.out.println("2. Update Penambahan Kasus");
            System.out.println("3. Update Pengurangan Kasus");
            System.out.println("4. Tampil Data");
            System.out.println("5. Tampil Statistik Penambahan/Pengurangan setiap hari");
            System.out.println("8. Go next Day");
            System.out.println("9. Exit");
            System.out.print("pilihan = ");
            pilih = input.nextInt();
            if(pilih==1){
                System.out.println("Menu Tambah Daerah");
                System.out.print("Input nama daerah = ");
               nama = input.nextLine();
                nama = input.nextLine();
                data.setDaerah(nama);
                
            }
            else if(pilih==2){
                System.out.println("Menu Update Penambahan Kasus Hari ke-"+hari);
                data.tampilDaerah();
                System.out.print("pilih daerah = ");
                id = input.nextInt();
                
                System.out.print("Penambahan jumlah ODP = ");
                odp = input.nextInt();
                 System.out.print("Penambahan jumlah PDP = ");
                pdp = input.nextInt();
                 System.out.print("Penambahan jumlah Positif = ");
                positif = input.nextInt();
                tambah.setData(odp, pdp, positif, hari, id);
                data.updateStatus(id);
             }
            else if(pilih==3){
                System.out.println("Menu Update Pengurangan Kasus Hari ke-"+hari);
                data.tampilDaerah();
                System.out.print("pilih daerah = ");
                id = input.nextInt();
     
                System.out.print("Pengurangan jumlah ODP = ");
                odp = input.nextInt();
                 System.out.print("Pengurangan jumlah PDP = ");
                pdp = input.nextInt();
                 System.out.print("Pengurangan jumlah Positif = ");
                positif = input.nextInt();
                kurang.setData(odp, pdp, positif, hari, id);
                data.updateStatus(id);
             }
            else if(pilih==4){
                int pilih2;
                System.out.println("1. Tampil Total data Covid di indonesia");
                System.out.println("2. Tampil  Seluruh data Tiap Daerah");
                System.out.println("3. Cari Daerah");
                System.out.print("pilih = ");
                pilih2 = input.nextInt();
                if(pilih2 == 1){
                    data.outputTotal();
                }else if(pilih2==2){
                    data.outputAll();
                }else if(pilih2==3){
                    data.tampilDaerah();
                System.out.print("pilih daerah = ");
                id = input.nextInt();
                    data.outputSearch(id);
                }
            }
            else if(pilih==5){
                System.out.println("Statistik Penambahan/pengurangan setiap hari di Indonesia");
                for(int i=1; i<=hari;i++){
                    System.out.println("Data hari ke-"+i);
                    tambah.tampilHarian(i);
                    kurang.tampilHarian(i);
                }
             }
            else if(pilih==8){
                hari=hari+1;
            }
            
        }while(pilih!=9);
    }
    
}
