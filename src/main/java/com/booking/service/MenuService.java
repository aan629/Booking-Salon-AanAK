package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import com.booking.Abstracts.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> listAllPerson = PersonRepository.getAllPerson();
    private static List<Service> listAllService = ServiceRepository.getAllService();
    private static List<Reservation> listAllReservation = new ArrayList<>();

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Show History Reservation","Back to main menu"};

        String regexNumber = "^[0-9]+$";
        int numberReservID = 1;
    
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = true;
        boolean backToSubMenu = true;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            System.out.println();
            optionMainMenu = ValidationService.validationOfNumberWithMin("Masukkan pilihan menu (0 s.d 3) : ", "Mohon maaf, inputan harus berupa angka", regexNumber, 0);
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        System.out.println();
                        optionSubMenu = ValidationService.validationOfNumberWithMin("Masukkan pilihan sub menu 1 (0 s.d 4) : ", "Mohon maaf, inputan harus berupa angka", regexNumber, 0);
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan recent reservation
                                PrintService print = new PrintService();
                                print.showRecentReservation(listAllReservation);
                                break;
                            case 2:
                                // panggil fitur tampilkan semua customer
                                PrintService.showAllCustomer(listAllPerson);
                                break;
                            case 3:
                                // panggil fitur tampilkan semua employee
                                PrintService.showAllEmployee(listAllPerson);
                                break;
                            case 4:
                                // panggil fitur tampilkan history reservation + total keuntungan
                                PrintService print2 = new PrintService();
                                print2.showHistoryReservation(listAllReservation);
                                break;
                            case 0:
                                backToSubMenu = false;
                                break;
                            default:
                                System.out.println("Mohon maaf, pilihan SubMenu ini hanya tersedia pilihan 0 s.d 4!!!");
                                System.out.println("Input ulang yang mulia!!!.");
                                break;
                        }

                    } while (backToSubMenu);
                    break;
                case 2:
                    // panggil fitur menambahkan reservation
                    List<Service> serviceList = new ArrayList<>(listAllService); //Memperbaharui kembali service list
                    listAllReservation.addAll(ReservationService.createReservation(listAllPerson, serviceList, numberReservID));
                    numberReservID++;
                    break;
                case 3:
                    // panggil fitur mengubah workstage menjadi finish/cancel
                    ReservationService.editReservationWorkstage(listAllReservation);
                    break;
                case 0:
                    backToMainMenu = false;
                    break;
                default:
                    System.out.println("Mohon maaf, pilihan menu hanya 0 s.d 3 !!!.");
                    System.out.println("Input ulang yang mulia!!!.");
                    break;
            }

        } while (backToMainMenu);
		
        System.out.println("Aplikasi sudah terhenti dan akan direset ulang menjadi default");
	}
}
