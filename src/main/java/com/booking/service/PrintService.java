package com.booking.service;

import java.text.DecimalFormat;
import java.util.List;

import com.booking.Abstracts.Person;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> listAllReservation){
        DecimalFormat df = new DecimalFormat("#,##0");
        
        int num = 1;
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+==================================================================================================+");
        for (Reservation reservation : listAllReservation) {
            if (reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), "Rp." + df.format(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }

        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }

    public static void showAllCustomer(List<Person> listAllPerson){
        DecimalFormat df = new DecimalFormat("#,##0");

        int num = 1;
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("+====================================================================================+");
        for (Person listPerson : listAllPerson) {
            if (listPerson instanceof Customer) {    
            System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s |\n",
            num, ((Customer)listPerson).getId(), ((Customer)listPerson).getName(), ((Customer)listPerson).getAddress(), ((Customer)listPerson).getMember().getMembershipName(), "Rp." + df.format(((Customer)listPerson).getWallet()));
            num++;
            }  
        }

        System.out.println("+------------------------------------------------------------------------------------+");
    }

    public static void showAllEmployee(List<Person> listAllPerson){
        int num = 1;
        System.out.println("+------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("+==================================================================+");
        for (Person listPerson : listAllPerson) {
            if (listPerson instanceof Employee) {    
            System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s |\n",
            num, ((Employee)listPerson).getId(), ((Employee)listPerson).getName(), ((Employee)listPerson).getAddress(), ((Employee)listPerson).getExperience());
            num++;
            }  
        }

        System.out.println("+-----------------------------------------------------------------+");
    }

    public static void showAllService(List<Service> listAllService){
        DecimalFormat df = new DecimalFormat("#,##0");

        int num = 1;
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-4s | %-7s | %-16s | %-15s |\n",
                "No.", "ID", "Nama", "Harga");
        System.out.println("+======================================================+");
        for (Service listService: listAllService) { 
            System.out.printf("| %-4s | %-7s | %-16s | %-15s |\n",
            num, listService.getServiceId(), listService.getServiceName(), "Rp." + df.format(listService.getPrice()));
            num++;
        }

        System.out.println("+------------------------------------------------------+");
    }
    

    public void showHistoryReservation(List<Reservation> listAllReservation){
        DecimalFormat df = new DecimalFormat("#,##0");

        int num = 1;
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+==================================================================================================+");
        
        double totalKeuntungan = 0;
        for (Reservation reservation : listAllReservation) {
            if(reservation.getWorkstage().equalsIgnoreCase("Finish")){
                totalKeuntungan += reservation.getReservationPrice();
            }

            System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
            num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), "Rp." + df.format(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
            num++;
        }

        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.println("| Total Keuntungan                                                           | Rp." + df.format((int)totalKeuntungan) + "      |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }
}
