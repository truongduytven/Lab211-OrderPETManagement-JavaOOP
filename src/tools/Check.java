package tools;

import java.time.YearMonth;
import java.util.Scanner;

public class Check {

    public static boolean checkDate(String date) {
        boolean checkdate;
        if (!date.matches("^[0-9]{2}/[0-9]{1,2}/[0-9]+$")) {
            return false;
        }
        String token[] = date.split("/");
        int day = Integer.parseInt(token[0].trim());
        int month = Integer.parseInt(token[1].trim());
        int year = Integer.parseInt(token[2].trim());
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day <= 31 && day >= 1) {
                    return true;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day <= 30 && day >= 1) {
                    return true;
                }
                break;
            case 2:
                if ((year % 400 == 0) && (day <= 29 && day >= 1)) {
                    return true;
                } else if ((year % 4 == 0) && (day <= 29 && day >= 1)) {
                    return true;
                } else if (year % 4 != 0 && (day <= 28 && day >= 1)) {
                    return true;
                }
                break;

        }
        return false;
    }

    public static boolean checkContinue(String inputname, String codition) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to continue " + inputname + "  more? " + "(" + codition + "): ");
            String result = sc.nextLine().toUpperCase();
            if (result.equalsIgnoreCase("Y") || result.equalsIgnoreCase("YES")) {
                return true;
            } else {
                if (result.equalsIgnoreCase("N") || result.equalsIgnoreCase("NO")) {
                    return false;
                } else {
                    System.out.println("Invaild Value!.Please renter");
                }
            }
        }
    }
}
