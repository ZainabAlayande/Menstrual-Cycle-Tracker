import controller.CycleController;
import controller.UserController;
import dtos.requests.CycleRequest;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import javax.swing.*;

public class Main {

    private static UserController userController = new UserController();
    private static CycleController cycleController = new CycleController();

    public static void main(String[] args) {
        runApplication();
    }

    private static void runApplication() {
        String userInput = input("""
                SHE-CYCLE MENSTRUAL TRACKER
                ...............................................................
                1. Register
                2. Login
                3. Close
                """);
        try {
            validateEmptyStringCanNotBreakCode(userInput);
        } catch (Exception exception) {
            display(exception.getMessage());
            runApplication();
        }
        switch (userInput) {
            case "1" -> register();
            case "2" -> login();
            case "3" -> closeApp();
            default -> runApplication();
        }
    }

    private static void displayAppFunctionalities() {
        String userInput = input("""
                ...............................................................
                1. Ovulation Date
                2. Next Period Date
                3. Safe Date
                4. Dangerous Date
                5. Home Page
                6. Logout
                """);
        try {
            validateEmptyStringCanNotBreakCode(userInput);
        } catch (Exception exception) {
            display(exception.getMessage());
            runApplication();
        }

        switch (userInput) {
            case "1" -> ovulationDate();
            case "2" -> nextPeriodDate();
            case "3" -> safeDates();
            case "4" -> dangerousDates();
            case "7" -> closeApp();
            default -> runApplication();

        }
    }

    private static void dangerousDates() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setStartDay(input("Period Start Date in (dd/MM/yyyy)::: "));
        var result = cycleController.dangerousDates(cycleRequest);
        display("Dangerous Dates: " + result.toString());
        displayAppFunctionalities();
    }

    private static void safeDates() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setStartDay(input("Period Start Date in (dd/MM/yyyy)::: "));
        String flowDays = input("Number of Flow Days:::");
        cycleRequest.setFlowDays(Integer.parseInt(flowDays));
        cycleRequest.setCircularDays(input("Number of Circular Days:::"));
        var result = cycleController.safeDates(cycleRequest);
        display("Safe Dates: " + result.toString());
        displayAppFunctionalities();
    }

    private static void nextPeriodDate() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setStartDay(input("Period Start Date in (dd/MM/yyyy)::: "));
        cycleRequest.setCircularDays(input("Number of Circular Days::: "));
        var result = cycleController.nextPeriodDate(cycleRequest);
        display("Next Period Date: " + result.toString());
        displayAppFunctionalities();
    }

    private static void ovulationDate() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setStartDay(input("Period Start Date in (dd/MM/yyyy):::"));
        cycleRequest.setCircularDays(input("Circular Days::: "));
        String flowDays = input("Number of Flow Days:::");
        cycleRequest.setFlowDays(Integer.parseInt(flowDays));
        var result = cycleController.ovulationDate(cycleRequest);
        display("Ovulation Date: " + result.toString());
        displayAppFunctionalities();
    }

    private static void closeApp() {
        System.exit(1);
    }

    private static void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailAddress(input("Enter Email Address::: "));
        loginRequest.setPassword(input("Enter Password::: "));
        var result = userController.login(loginRequest);
        display(result.toString());
        displayAppFunctionalities();
    }

    private static void register() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName(input("Enter First Name::: "));
        registerRequest.setLastName(input("Enter Last Name::: "));
        registerRequest.setEmail(input("Enter Email Address::: "));
        registerRequest.setPassword(input("Enter Password::: "));
        var result = userController.register(registerRequest);
        display(result.toString());
        runApplication();
    }

    private static void display(String prompt) {
        JOptionPane.showMessageDialog(null, prompt);
    }

    private static String input(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    private static String input(int prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    private static void validateEmptyStringCanNotBreakCode(String empty) {
        String emptyString = "";
        if (empty.equals(emptyString)) {
            throw new IllegalArgumentException("Invalid Argument");
        }
    }
}
