import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        PathDAO pathDAO = new PathDAO();

        while (true) {

            System.out.println("\n===== JOB RECOMMENDATION SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Email");
            System.out.println("4. Delete Account");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // REGISTER USER
                case 1:

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    if (!EmailValidator.isValid(email)) {

                        System.out.println("Invalid Email Format!");
                        break;

                    }

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    // Default role USER

                    User user = new User(name, email, password, "USER");

                    userDAO.registerUser(user);

                    break;

                // LOGIN USER / ADMIN
                case 2:

                    System.out.print("Enter Email: ");
                    String loginEmail = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPassword = sc.nextLine();

                    String role = userDAO.loginUser(loginEmail, loginPassword);

                    if (role != null) {

                        // ADMIN PANEL

                        if (role.equals("ADMIN")) {

                            System.out.println("\n===== ADMIN PANEL =====");

                            System.out.println("1. Add Recommendation");
                            System.out.println("2. Delete Recommendation");
                            System.out.println("3. View Users");

                            System.out.print("Enter Choice: ");

                            int adminChoice = sc.nextInt();
                            sc.nextLine();

                            switch (adminChoice) {

                                case 1:

                                    System.out.print("Enter Skill: ");
                                    String skill = sc.nextLine();

                                    System.out.print("Enter Job Role: ");
                                    String jobRole = sc.nextLine();

                                    System.out.print("Enter Next Skills: ");
                                    String nextSkills = sc.nextLine();

                                    boolean added = pathDAO.addRecommendation(
                                            skill,
                                            jobRole,
                                            nextSkills);

                                    if (added) {

                                        System.out.println(
                                                "Recommendation Added Successfully!");

                                    } else {

                                        System.out.println(
                                                "Failed to Add Recommendation!");

                                    }
                                    break;

                                case 2:

                                    System.out.print("Enter Recommendation ID: ");

                                    int id = sc.nextInt();

                                    boolean deleted = pathDAO.deleteRecommendation(id);

                                    if (deleted) {

                                        System.out.println(
                                                "Recommendation Deleted Successfully!");

                                    } else {

                                        System.out.println(
                                                "Recommendation Not Found!");

                                    }

                                    break;

                                case 3:

                                    userDAO.getAllUsers();
                                    break;

                                default:

                                    System.out.println(
                                            "Invalid Admin Choice");

                            }

                        }

                        // NORMAL USER

                        else if (role.equals("USER")) {

                            System.out.print(
                                    "\nEnter Your Skill: ");

                            String skill = sc.nextLine();

                            pathDAO.getRecommendation(skill);

                        }

                    } else {

                        System.out.println(
                                "Invalid Email or Password!");

                    }

                    break;

                // UPDATE USER EMAIL

                case 3:

                    System.out.print(
                            "Enter User ID: ");

                    int updateId = sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Enter New Email: ");

                    String newEmail = sc.nextLine();

                    if (EmailValidator.isValid(newEmail)) {

                        userDAO.updateUserEmail(
                                updateId, newEmail);

                    } else {

                        System.out.println(
                                "Invalid Email Format!");

                    }

                    break;

                // DELETE USER

                case 4:

                    System.out.print(
                            "Enter User ID: ");

                    int deleteId = sc.nextInt();

                    userDAO.deleteUser(deleteId);

                    break;

                // EXIT

                case 5:

                    System.out.println(
                            "Thank you for using Job Recommendation System");

                    sc.close();

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid Choice!");

            }

        }

    }

}