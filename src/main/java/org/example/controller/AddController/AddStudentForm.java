package org.example.controller.AddController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AddStudentBO;
import org.example.entity.Payment;
import org.example.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddStudentForm {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCno;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtInitialPayment;

    @FXML
    private TextField txtNicNo;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtUserId;


    @FXML
    private TextField txtCourseId;

    AddStudentBO addStudentBO = (AddStudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.Add_Student);

    @FXML
    void txtAddressReleasedOnAction(KeyEvent event) {
        Pattern adressPattern = Pattern.compile( "^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        if(!adressPattern.matcher(txtAddress.getText()).matches()){
            addError(txtAddress);

        }else{
            removeError(txtAddress);
        }

    }

    @FXML
    void txtContactNoReleasedOnAction(KeyEvent event) {
        Pattern phonePattern = Pattern.compile( "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$");
        if(!phonePattern.matcher(txtCno.getText()).matches()){
            addError(txtCno);

        }else{
            removeError(txtCno);
        }

    }

    @FXML
    void txtCourseIdReleasedOnAction(KeyEvent event) {
        Pattern CourseIdPattern = Pattern.compile("^C\\d{3}$");
        if(!CourseIdPattern.matcher(txtCourseId.getText()).matches()){
            addError(txtCourseId);

        }else{
            removeError(txtCourseId);
        }

    }

    @FXML
    void txtInitialPaymentReleasedOnAction(KeyEvent event) {
        Pattern InitialPaymentPattern = Pattern.compile("^(0|[1-9]\\d{0,6}|10000000)$");
        if(!InitialPaymentPattern.matcher(txtInitialPayment.getText()).matches()){
            addError(txtInitialPayment);

        }else {
            removeError(txtInitialPayment);
        }
    }

    @FXML
    void txtNameReleasedOnAction(KeyEvent event) {
        Pattern NamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        if(!NamePattern.matcher(txtName.getText()).matches()){
            addError(txtName);

        }else{
            removeError(txtName);
        }

    }

    @FXML
    void txtNicNoReleasedOnAction(KeyEvent event) {
        Pattern NicNoPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{10})$");
        if(!NicNoPattern.matcher(txtNicNo.getText()).matches()){
            addError(txtNicNo);

        }else{
            removeError(txtNicNo);
        }

    }

    @FXML
    void txtStudentIdReleasedOnAction(KeyEvent event) {
        Pattern StudentIdPattern = Pattern.compile("^S\\d{3}$");
        if(!StudentIdPattern.matcher(txtStudentId.getText()).matches()){
            addError(txtStudentId);

        }else{
            removeError(txtStudentId);
        }

    }

    @FXML
    void txtUserIdReleasedOnAction(KeyEvent event) {
        Pattern IDPattern = Pattern.compile("^U\\d{3}$");
        if(!IDPattern.matcher(txtUserId.getText()).matches()){
            addError(txtUserId);

        }else{
            removeError(txtUserId);
        }

    }

        @FXML
        void btnAddOnAction (ActionEvent event) throws SQLException, ClassNotFoundException {
            String student_id = txtStudentId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String cno = txtCno.getText();
            String nic_no = txtNicNo.getText();
            String user_id = txtUserId.getText();
            String initial_payment = txtInitialPayment.getText();
            String course_id = txtCourseId.getText();
            String date = String.valueOf(LocalDate.now());
            String PaymentId = addStudentBO.generateNewId();

            Pattern adressPattern = Pattern.compile("^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
            Pattern phonePattern = Pattern.compile("^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$");
            Pattern CourseIdPattern = Pattern.compile("^C\\d{3}$");
            Pattern InitialPaymentPattern = Pattern.compile("^(0|[1-9]\\d{0,6}|10000000)$");
            Pattern NamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
            Pattern NicNoPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{10})$");
            Pattern StudentIdPattern = Pattern.compile("^S\\d{3}$");
            Pattern IDPattern = Pattern.compile("^U\\d{3}$");

            if (isValidInput(adressPattern, phonePattern, CourseIdPattern, InitialPaymentPattern, NamePattern, NicNoPattern, StudentIdPattern, IDPattern)) {

                Student student = new Student(student_id, name, cno, address, nic_no, user_id);

                Payment payment = new Payment(PaymentId, initial_payment, date, student_id, user_id, course_id);


                boolean isSaved = addStudentBO.studentRegistration(student, payment);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student saved!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Something happened!").show();
                }

            }

        }

        private boolean isValidInput (Pattern adressPattern, Pattern phonePattern, Pattern courseIdPattern, Pattern
        InitialPaymentPattern, Pattern NamePattern, Pattern NicNoPattern, Pattern StudentIdPattern, Pattern IDPattern){
            boolean isValid = true;
            if (!adressPattern.matcher(txtAddress.getText()).matches()) {
                addError(txtAddress);
                isValid = false;

            } else {
                removeError(txtAddress);
            }

            if (!phonePattern.matcher(txtCno.getText()).matches()) {
                addError(txtCno);
                isValid = false;

            } else {
                removeError(txtCno);
            }
            if (!courseIdPattern.matcher(txtCourseId.getText()).matches()) {
                addError(txtCourseId);
                isValid = false;

            } else {
                removeError(txtCourseId);
            }
            if (!InitialPaymentPattern.matcher(txtInitialPayment.getText()).matches()) {
                addError(txtInitialPayment);
                isValid = false;

            } else {
                removeError(txtInitialPayment);
            }
            if (!NamePattern.matcher(txtName.getText()).matches()) {
                addError(txtName);
                isValid = false;
            } else {
                removeError(txtName);
            }
            if (!StudentIdPattern.matcher(txtStudentId.getText()).matches()) {
                addError(txtStudentId);
                isValid = false;

            } else {
                removeError(txtStudentId);
            }

            if (!courseIdPattern.matcher(txtCourseId.getText()).matches()) {
                addError(txtCourseId);
                isValid = false;

            } else {
                removeError(txtCourseId);
            }
            if (!NicNoPattern.matcher(txtNicNo.getText()).matches()) {
                addError(txtNicNo);
                isValid = false;

            } else {
                removeError(txtNicNo);
            }
            if (!IDPattern.matcher(txtUserId.getText()).matches()) {
                addError(txtUserId);
                isValid = false;

            } else {
                removeError(txtUserId);
            }
            return isValid;
        }


        @FXML
        void btnExitOnAction (ActionEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org.example/student_form.fxml")));
            Stage stage = (Stage) rootNode.getScene().getWindow();

            stage.setScene(new Scene(anchorPane));
            stage.setTitle("Student Form");
            stage.centerOnScreen();

        }

        private void removeError (TextField textField){
            textField.setStyle("-fx-border-color: green");

        }

        private void addError (TextField textField){
            textField.setStyle("-fx-border-color: red");
        }
    }

