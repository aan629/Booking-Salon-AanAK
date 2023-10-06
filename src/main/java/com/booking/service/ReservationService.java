package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import com.booking.Abstracts.Person;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {
    public static List<Reservation> createReservation(List<Person> listAllPerson, List<Service> listAllService, int numberReservID){
        List<Reservation> result = new ArrayList<Reservation>();
        
        String regexCustomerID = "Cust-\\d{2}", regexEmployeeID = "Emp-\\d{2}", regexServiceID = "Serv-\\d{2}";
        boolean isLooping = true;

        //logic input Customer ID
        String newCustomerID = "";
        int indexListCustomer = 0;
        Person newCustomer = new Customer();
        PrintService.showAllCustomer(listAllPerson); //Tampilkan Menu Employee

        do {
            isLooping = true;
            newCustomerID = ValidationService.validationOfID("Silahkan masukkan Customer ID : ", "Maaf, ID Costumer harus sesuai dengan format 'Cust-' diikuti dengan 2 digit angka ID.", regexCustomerID);
            System.out.println();
            int index = 0;
            int indexToFind = -1;

            for (Person listPerson : listAllPerson) {
                if (newCustomerID.contains(listPerson.getId())) {
                    System.out.println("Selamat!!!, Customer ID terverifikasi sehingga Customer yang anda cari tersedia.");
                    System.out.println();
                    indexListCustomer = index;
                    newCustomer = listAllPerson.get(indexListCustomer);
                    indexToFind++;
                    break;
                }

                index++;
            }

            if (indexToFind != -1) {
                isLooping = false;
                break;
            } else {
                System.out.println("Mohon maaf!!!, Customer ID  tidak valid sehingga Customer yang anda cari tidak tersedia.");
                System.out.println("Input ulang yang mulia!!!.");
                System.out.println();
            }

        } while (isLooping);

        System.out.println();
        
        //logic input Employee ID
        String newEmployeeID = "";
        int indexListEmployee = 0;
        Person newEmployee = new Employee();
        PrintService.showAllEmployee(listAllPerson); //Tampilkan Menu Employee

        do {
            isLooping = true;
            newEmployeeID = ValidationService.validationOfID("Silahkan masukkan Employe ID : ", "Maaf, ID Employee harus sesuai dengan format 'Emp-' diikuti dengan 2 digit angka ID.", regexEmployeeID);
            System.out.println();
            int index = 0;
            int indexToFind = -1;

            for (Person listPerson : listAllPerson) {
                if (newEmployeeID.contains(listPerson.getId())) {
                    System.out.println("Selamat!!!, Employe ID terverifikasi sehingga Employee yang anda cari tersedia.");
                    System.out.println();
                    indexListEmployee = index;
                    newEmployee = listAllPerson.get(indexListEmployee);
                    indexToFind++;
                    break;
                }

                index++;
            }

            if (indexToFind != -1) {
                isLooping = false;
                break;
            } else {
                System.out.println("Mohon maaf!!!, Employee ID tidak valid sehingga Employee yang anda cari tidak tersedia");
                System.out.println("Input ulang yang mulia!!!.");
                System.out.println();
            }

        } while (isLooping);

        System.out.println();

        //Logic input Service ID
        String newServiceID = "";
        int indexListService = 0;
        double price = 0;
        List<Service> newService = new ArrayList<>();

        do {
            PrintService.showAllService(listAllService);
            isLooping = true;
            newServiceID = ValidationService.validationOfID("Silahkan masukkan Service ID : ","Maaf, ID Service harus sesuai dengan format 'Serv-' diikuti dengan 2 digit angka ID.", regexServiceID);
            System.out.println();
            int index = 0;
            int indexToFind = -1;
            int indexTofind2 = -1;

            for (Service listService : listAllService) {
                if (newServiceID.contains(listService.getServiceId())) {
                    System.out.println("Selamat!!!, Service ID terverifikasi sehingga layanan Service yang anda inginkan tersedia.");
                    System.out.println();
                    newService.add(listService);
                    indexListService = index;
                    price += listService.getPrice();   
                    boolean isLooping2 = true;
                    indexToFind++;

                    do {
                        isLooping2 = true;
                        String choice = ValidationService.validationOfID("Apakah anda menginginkan Service yang lain juga (Y/T)? : ", "Mohon maaf, Pilihan hanya 'Y' jika ingin menambah layanan service atau 'N' jika sudah cukup.", "Y|T|y|t");
                        System.out.println();

                        if (choice.matches("Y|y")) {    
                            isLooping2 = false;
                            
                        } else {
                            indexTofind2++;
                            isLooping2 = false;
                        }

                    } while (isLooping2);
                }

                index++;
            }

            if (indexToFind != -1 && indexTofind2 != -1) {
                isLooping = false;
                break;
            }else if(indexToFind != -1){
                listAllService.remove(indexListService);
                System.out.println("Service sudah dipilih sehingga pilihan layanan yang dipilih akan tereliminasi");
                System.out.println("Silahkan pilih tambahan layanan service yang anda inginkan yang mulia!!!.");
                System.out.println();
            } else {
               System.out.println("Mohon maaf!!!, Service ID tidak valid sehingga layanan Service yang anda cari tidak tersedia"); 
               System.out.println("Input ulang yang mulia!!!.");
               System.out.println();
            }

        } while (isLooping);

        double reservationPrice = 0;
        if (((Customer)newCustomer).getMember().getMembershipName().equals("none")) {
            reservationPrice = price;
        } else if(((Customer)newCustomer).getMember().getMembershipName().equals("Silver")){
            reservationPrice = price - (price * 5 / 100);
        } else if(((Customer)newCustomer).getMember().getMembershipName().equals("Gold")){
            reservationPrice = price - (price * 10 / 100);
        }

        System.out.println("Booking Berhasil!!!");
        System.out.println("Total biaya booking adalah : RP." + String.format("#,##0", (int)reservationPrice));
        System.out.println();

        result.add(new Reservation("Rsv-" + String.format("%02d", numberReservID), (Customer)newCustomer, (Employee)newEmployee, newService, reservationPrice, "In Process"));
        
        return result;
    }

    public static void editReservationWorkstage(List<Reservation> listAllReservation){
        PrintService print = new PrintService();
        print.showHistoryReservation(listAllReservation);

        String regexReservationID = "Rsv-\\d{2}";
        boolean isLooping = true;
        String newReservationID = "";
        int indexToFind1 = -1;
        int indexToFind2 = -1;

        do {
            isLooping = true;
            newReservationID = ValidationService.validationOfID("Silahkan Masukkan Reservation ID : ","Maaf, ID Reservation harus sesuai dengan format 'Rsv-' diikuti dengan 2 digit angka ID.", regexReservationID);

            for (Reservation reservation : listAllReservation) {
                if (newReservationID.contains(reservation.getReservationId()) && reservation.getWorkstage().equals("In Process")) {
                    System.out.println("Selamat!!!, Reservasi ID terverifikasi.");
                    System.out.println();
                    String workstage = ValidationService.validationOfID("Selesaikan status Reservasi!!!, Silahkan untuk melakukan pengeditan workstage : ", "Mohon maaf, pilihan harus berupa pilihan ini (Cancel/Finish)", "Cancel|Finish|cancel|finish|CANCEL|FINISH");
                    indexToFind1++;

                    if (workstage.equalsIgnoreCase("Finish")) {
                        reservation.setWorkstage("Finish");
                        System.out.println("Reservasi dengan ID " + newReservationID + " sudah finish");
                        break;
                    } else if (workstage.equalsIgnoreCase("Cancel")){
                        reservation.setWorkstage("Cancel");
                        System.out.println("Reservasi dengan ID " + newReservationID + " sudah cancel");
                        break;
                    }
                } else if(newReservationID.contains(reservation.getReservationId()) && !reservation.getWorkstage().equals("In Process")){
                    indexToFind2++;
                    break;
                }
            }

            if (indexToFind1 != -1) {
                isLooping = false;
            } else if(indexToFind2 != -1){
                System.out.println("Mohon maaf, Reservasi yang anda cari sudah selesai dan tidak tersedia lagi!!!.");
                System.out.println("Silahkan input ulang yang mulia!!!.");
            }else {
                System.out.println("ID reservasi tidak ditemukan");
                System.out.println("Silahkan input ulang yang mulia!!!.");
            }
            
        } while (isLooping);
    }
}
