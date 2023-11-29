package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {

    public static String inputString(String inputName, String condition) {
        String result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(" + condition + "): ");
            result = sc.nextLine().trim();
            if ("".equals(result)) {
                System.out.println("Your " + inputName + " is empty. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputAllowEmpty(String inputName, boolean allowEmpty) {
        String result;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter " + inputName + ": ");
            result = sc.nextLine().trim();
        } while (!allowEmpty && result.isEmpty());
        return result;
    }

    public interface CheckUnique {

        public boolean check(String checkedId);
    }

    public static String inputId(String inputName, String condition, CheckUnique checkUnique) {
        String result;
        while (true) {
            result = inputString(inputName, condition);
            if (!checkUnique.check(result)) {
                System.out.println("Your " + inputName + " is not unique. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputIdWithFormat(String inputName, String condition, CheckUnique checkUnique, String format) {
        String result;
        while (true) {
            result = Util.inputId(inputName, condition, checkUnique);
            if (!result.matches(format)) {
                System.out.println("Your " + inputName + " is wrong format. Try again!");
                continue;
            }
            return result;
        }
    }

    public static int getInputInteger(String inputName, boolean allowEmpty) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            System.out.println("Enter an " + inputName + " : ");
            input = scanner.nextLine();

            if (allowEmpty && input.trim().isEmpty()) {
                return 0;
            }

            try {
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Number must be larger than 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer number.");
            }
        }
    }

    public static Integer inputInteger(String message, Integer minValue, Integer maxValue) {
        Integer inputVal = null;
        do {
            try {
                inputVal = Integer.valueOf(Util.inputStringINT(message, false));
            } catch (NumberFormatException ex) {
                inputVal = null;
            }
        } while (inputVal == null
                || (minValue != null && minValue.compareTo(inputVal) > 0)
                || maxValue != null && maxValue.compareTo(inputVal) < 0);
        return inputVal;
    }

    public static String inputStringINT(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (!allowEmpty && str.isEmpty());
        return str.trim();
    }

    public static String inputCategory(boolean allowEmpty) {
        List<String> choiceCategory = new ArrayList<>();
        choiceCategory.add("dog");
        choiceCategory.add("cat");
        choiceCategory.add("parrot");
        int choice;
        do {
            for (int i = 0; i < choiceCategory.size(); i++) {
                System.out.println(i + 1 + ". " + choiceCategory.get(i));
            }
            System.out.println("Please choice Category");
            Scanner sc = new Scanner(System.in);
            String choiceString = sc.nextLine();
            if (allowEmpty && choiceString.isEmpty()) {
                return null;
            } else {
                choice = Integer.parseInt(choiceString);
                if (choice > 0 && choice <= choiceCategory.size()) {
                    return choiceCategory.get(choice - 1);
                } else {
                    System.out.println("Value is not exist. Please choice again!");
                }
            }
        } while (true);
    }

    public static String inputDate(String msg, boolean allowEmpty) {
        while (true) {
            System.out.println("Enter " + msg + " (xx/xx/20xx): ");
            Scanner sc = new Scanner(System.in);
            String date = sc.nextLine();
            if (!allowEmpty && !date.isEmpty()) {
                boolean checkDate = Check.checkDate(date);
                if (checkDate) {
                    return date;
                } else {
                    System.out.println("The choice is not format");
                    continue;
                }
            }
            return date;
        }
    }

    public static String loginUser(String msg) {
        do {
            System.out.print("Enter " + msg + ": ");
            Scanner sc = new Scanner(System.in);
            String user = sc.nextLine();
            if (user.isEmpty()) {
                System.out.println("Value can not empty!");
                continue;
            }
            return user;
        } while (true);

    }
}
