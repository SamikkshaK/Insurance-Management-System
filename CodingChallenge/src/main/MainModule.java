package main;

import entity.*;
import myexceptions.PolicyNotFoundException;
import dao.InsuranceServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) throws PolicyNotFoundException {
        Scanner sc = new Scanner(System.in);
        InsuranceServiceImpl service = new InsuranceServiceImpl();

        try {
            if (service.loginUser("admin", "admin123") == null) {
                service.registerUser(new User(0, "admin", "admin123", "admin"));
                service.registerUser(new User(0, "sam", "sam123", "client"));
            }

            if (service.getAllPolicies().isEmpty()) {
                service.addPolicy(new Policy(1, "Health Plus", 500000, 1500));
                service.addPolicy(new Policy(2, "Life Secure", 1000000, 3000));
                service.addPolicy(new Policy(3, "Vehicle Shield", 200000, 1000));
            }

            if (service.getAllClients().isEmpty()) {
                service.addClient(new Client(0, "John Doe", "john@example.com", 1));
                service.addClient(new Client(0, "Alice Smith", "alice@example.com", 2));
            }

            if (service.getAllClaims().isEmpty()) {
                service.addClaim(new Claim(0, "CLM001", Date.valueOf("2024-03-01"), 40000, "Pending", 1, 1));
                service.addClaim(new Claim(0, "CLM002", Date.valueOf("2024-03-05"), 100000, "Approved", 2, 2));
            }

            service.addPayment(new Payment(0, Date.valueOf("2024-03-10"), 1500, 1));
            service.addPayment(new Payment(0, Date.valueOf("2024-03-12"), 3000, 2));
        } catch (Exception e) {
            System.out.println("Startup error: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n=== Insurance Management System ===");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. Add Policy");
            System.out.println("4. View All Policies");
            System.out.println("5. Add Client");
            System.out.println("6. File Claim");
            System.out.println("7. Make Payment");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    System.out.print("Role: ");
                    String role = sc.nextLine();
                    User u = new User(0, uname, pass, role);
                    boolean added = service.registerUser(u);
                    System.out.println(added ? "User registered." : "Registration failed.");
                }

                case 2 -> {
                    System.out.print("Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    User user = service.loginUser(uname, pass);
                    System.out.println(user != null ? "Welcome " + user.getUsername() : "Login failed.");
                }

                case 3 -> {
                    System.out.print("Policy ID: ");
                    int policyId = sc.nextInt();
                    sc.nextLine();

                    if (policyId != 1 && policyId != 2 && policyId != 3) {
                        throw new PolicyNotFoundException("Invalid Policy ID: " + policyId);
                    }

                    System.out.print("Policy Name: ");
                    String name = sc.nextLine();
                    System.out.print("Coverage Amount: ");
                    double coverage = sc.nextDouble();
                    System.out.print("Premium: ");
                    double premium = sc.nextDouble();

                    Policy p = new Policy(policyId, name, coverage, premium);
                    if (!service.addPolicy(p)) {
                        throw new PolicyNotFoundException("Failed to insert policy: " + name);
                    }

                    System.out.println("Policy added successfully.");
                }

                case 4 -> {
                    List<Policy> policies = service.getAllPolicies();
                    policies.forEach(System.out::println);
                }

                case 5 -> {
                    System.out.print("Client Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Contact Info: ");
                    String contact = sc.nextLine();
                    System.out.print("Policy ID: ");
                    int pid = sc.nextInt();
                    Client client = new Client(0, cname, contact, pid);
                    boolean added = service.addClient(client);
                    System.out.println(added ? "Client added successfully." : "Failed to add client.");
                }

                case 6 -> {
                    System.out.print("Claim Number: ");
                    String cno = sc.nextLine();
                    System.out.print("Claim Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Status: ");
                    String status = sc.nextLine();
                    System.out.print("Policy ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Client ID: ");
                    int cid = sc.nextInt();
                    Claim claim = new Claim(0, cno, new Date(System.currentTimeMillis()), amount, status, pid, cid);
                    boolean added = service.addClaim(claim);
                    System.out.println(added ? "Claim filed." : "Failed to file claim.");
                }

                case 7 -> {
                    System.out.print("Payment Amount: ");
                    double amt = sc.nextDouble();
                    System.out.print("Client ID: ");
                    int cid = sc.nextInt();
                    Payment payment = new Payment(0, new Date(System.currentTimeMillis()), amt, cid);
                    boolean added = service.addPayment(payment);
                    System.out.println(added ? "Payment successful." : "Failed to add payment.");
                }

                case 8 -> {
                    System.out.println("Exiting system.");
                    sc.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }
}
