package coe528.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

/**
 * @author Sukhjot Saggu
 */
public class Login extends Application implements EventHandler<ActionEvent> {


Bank bank;
Customer customer;
String[] data = new String[3];

Stage window;
Scene loginPage, managerPage, customerPage;
    
Button btnSighInM, btnSighInC, bthAddCustomer, bthRemoveCustomer, bthLogoutM, bthLogoutC, bthDeposit, bthWithdraw, bthBalance, btnOnlinePurchase;
TextField userTextField;
TextArea output;
PasswordField pwBox;

TextInputDialog customerUsername, customerPassword, customerBalance, depositAmount, withdrawAmount, purchaseAmount;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        primaryStage.setTitle("Login");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

loginPage = new Scene(grid, 350, 275);

Text scenetitle = new Text("Welcome");
scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
grid.add(scenetitle, 0, 0, 2, 1);

Label userName = new Label("User Name:");
grid.add(userName, 0, 1);

userTextField = new TextField();
grid.add(userTextField, 1, 1);

Label pw = new Label("Password:");
grid.add(pw, 0, 2);

pwBox = new PasswordField();
grid.add(pwBox, 1, 2);

btnSighInM = new Button("MANAGER LOGIN ");
btnSighInC = new Button("CUSTOMER LOGIN");
VBox hbBtn = new VBox(10);
hbBtn.setAlignment(Pos.BOTTOM_LEFT);
hbBtn.getChildren().addAll(btnSighInM, btnSighInC);
grid.add(hbBtn, 1, 5);

btnSighInM.setOnAction(this);
btnSighInC.setOnAction(this);

//Manager Page
bthAddCustomer = new Button("Create Customer");
bthAddCustomer.setOnAction(this);
bthRemoveCustomer = new Button("Remove Customer");
bthRemoveCustomer.setOnAction(this);
bthLogoutM = new Button("Logout");
bthLogoutM.setOnAction(this);
//output = new TextArea();
//output.setPrefHeight(5);
//output.setPrefWidth(400);
GridPane grid2 = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid2.setHgap(2);
        grid2.setVgap(2);
        grid2.setPadding(new Insets(25, 25, 25, 25));
    
managerPage = new Scene(grid2, 200, 200);
grid2.add(bthAddCustomer, 0,10);
grid2.add(bthRemoveCustomer, 0,20);
grid2.add(bthLogoutM, 0,30);

//Customer Page
bthDeposit = new Button("Deposit Money");
bthDeposit.setOnAction(this);
bthWithdraw = new Button("Withdraw Money");
bthWithdraw.setOnAction(this);
bthBalance = new Button("View Balance");
bthBalance.setOnAction(this);
btnOnlinePurchase = new Button("Online Purchase");
btnOnlinePurchase.setOnAction(this);
bthLogoutC = new Button("Logout");
bthLogoutC.setOnAction(this);
output = new TextArea();
output.setPrefHeight(16);
output.setPrefWidth(400);
GridPane grid3 = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid3.setHgap(2);
        grid3.setVgap(2);
        grid3.setPadding(new Insets(25, 25, 25, 25));
    

customerPage = new Scene(grid3, 400, 400);
grid3.add(bthDeposit, 0,20);
grid3.add(bthWithdraw, 0,30);
grid3.add(bthBalance, 0,40);
grid3.add(btnOnlinePurchase, 0,50);
grid3.add(bthLogoutC, 0,60);
grid3.add(output, 0,0);

//endCustomer
        
primaryStage.setScene(loginPage);
        
        primaryStage.show();       
    }

    @Override
    public void handle(ActionEvent e){
        if(e.getSource() == btnSighInM){
            System.out.println(userTextField.getText());
            System.out.println(pwBox.getText());
            try{
                Manager manager = new Manager(userTextField.getText(), pwBox.getText());
                boolean out = manager.validFile(manager.getUsername(), manager.getPassword());
                System.out.println("the output is: " + out);
                if(out){
                    bank = new Bank(manager);
                    window.setScene(managerPage);
                }
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }    
        }
        
        if(e.getSource() == bthAddCustomer){
            try{
                Manager manager = new Manager("admin", "admin");
                customerUsername = new TextInputDialog();
                customerPassword = new TextInputDialog();
                customerBalance = new TextInputDialog();
                
                customerUsername.setTitle("Username");
                customerUsername.getDialogPane().setContentText("Enter Username:");
                customerUsername.showAndWait();
                TextField inputUsername = customerUsername.getEditor();

                customerPassword.setTitle("Password");
                customerPassword.getDialogPane().setContentText("Enter Password:");
                customerPassword.showAndWait();
                TextField inputPassword = customerPassword.getEditor();
                System.out.println(inputUsername.getText());
                System.out.println(inputPassword.getText());
                
                customerBalance.setTitle("Account Deposit");
                customerBalance.getDialogPane().setContentText("Enter The Deposit Acmount:");
                customerBalance.getEditor().setText("100");
                customerBalance.showAndWait();
                
                TextField inputBalance = customerBalance.getEditor();
                System.out.println(inputBalance.getText());

                Customer newCustomer = new SilverCustomer(inputUsername.getText(), inputPassword.getText(), Double.parseDouble(inputBalance.getText())); 
                bank.addCustomer(manager, newCustomer);
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }
        }
        
        if(e.getSource() == bthRemoveCustomer){
            customerUsername = new TextInputDialog();
            customerUsername.setTitle("Remove User");
            customerUsername.getDialogPane().setContentText("Enter Username Of Customer That Needs To Be Removed");
            customerUsername.showAndWait();
            TextField inputUsername = customerUsername.getEditor();
            
            try{
                File remove = new File(inputUsername.getText() + ".txt");
                if(remove.delete()){ 
                    OutputBox.display("Message", "User deleted successfully"); 
                } 
                else{ 
                    OutputBox.display("Message", "User not found"); 
                } 
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }    
        }
        
        if(e.getSource() == btnSighInC){
            System.out.println(userTextField.getText());
            System.out.println(pwBox.getText());
            try{
                data = customerInfo(userTextField.getText());
            customer = new SilverCustomer(userTextField.getText(), pwBox.getText(), Double.parseDouble(data[1]));
            boolean out = customer.validCustomer(customer.getUsername(), customer.getPassword());
            //boolean out = true;
            System.out.println("the output is: " + out);
            if(out){
                
                //account = new Account(customer, Double.parseDouble(data[1]));
                window.setScene(customerPage);
                output.setText(customer.getUsername() + "\n" +customer.balanceToString());
            }
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }    
        }
        
        if(e.getSource() == bthDeposit){
            depositAmount = new TextInputDialog();
            depositAmount.setTitle("Deposit");
            depositAmount.getDialogPane().setContentText("Enter The Deposit Amount: $");
            depositAmount.showAndWait();
            TextField deposit = depositAmount.getEditor();
            try{
                
                customer.deposit(Double.parseDouble(deposit.getText()));
                customer.updateInfo();
                customer = customer.setLevel();
                output.setText(customer.getUsername() + "\n" +customer.balanceToString());
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }    
        }
        
        if(e.getSource() == bthWithdraw){
            withdrawAmount = new TextInputDialog();
            withdrawAmount.setTitle("Withdraw");
            withdrawAmount.getDialogPane().setContentText("Enter The Withdraw Amount: $");
            withdrawAmount.showAndWait();
            TextField withdraw = withdrawAmount.getEditor();
            try{
                
                customer.withdraw(Double.parseDouble(withdraw.getText()));
                customer.updateInfo();
                customer = customer.setLevel();
                output.setText(customer.getUsername() + "\n" +customer.balanceToString());
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }    
        }
        
        if(e.getSource() == btnOnlinePurchase){
            purchaseAmount = new TextInputDialog();
            purchaseAmount.setTitle("Online Purchase");
            purchaseAmount.getDialogPane().setContentText("Enter The Purchase Amount: $");
            purchaseAmount.showAndWait();
            TextField withdraw = purchaseAmount.getEditor();
            try{
                customer = customer.setLevel();
                OutputBox.display("Total" ,"Your Total: $" + String.valueOf(customer.purchase(Double.parseDouble(withdraw.getText()))));
                customer.updateInfo();
                output.setText(customer.getUsername() + "\n" +customer.balanceToString());
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }    
        }
        
        if(e.getSource() == bthBalance){
            OutputBox.display("",customer.balanceToString());
        }
        
        if(e.getSource() == bthLogoutM){
            window.setScene(loginPage);
            userTextField.setText("");
            pwBox.setText("");
        }
        
        if(e.getSource() == bthLogoutC){
            window.setScene(loginPage);
            userTextField.setText("");
            pwBox.setText("");
                
        }
    }
    
        public String[] customerInfo(String username) throws Exception{
        
        String data = "", line;
        String[] info = new String[3];

        BufferedReader in = new BufferedReader(new FileReader(username + ".txt"));

        while((line = in.readLine()) != null){
            data = data + line;
        }
        in.close();

        StringTokenizer text = new StringTokenizer(data);
        int i = 0;
        while (text.hasMoreElements()) {
            info[i] = text.nextToken();
            i++;
        }
        for(int j=0;j<3;j++){
            System.out.println(info[j]);
        }
        return info;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
