package com.rhattark.gui;

import com.rhattark.businessLogic.Facade;
import com.rhattark.businessLogic.ProductTypePanel;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private Facade facade;

    public GUI() {
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product Trading and Bidding System");
        facade = Facade.getInstance();

        JPanel test = new JPanel();
        test.setBounds(new GUIOuterRectangle());
        test.setBackground(Color.decode("#e0e0e0"));

        JPanel productTypePanel = getProductTypePanel(test);
        JPanel loginPanel = getLoginPanel(productTypePanel);
        JPanel userTypePanel = getUserTypePanel(loginPanel);

        setContentPane(userTypePanel);
        setLayout(null);
        setVisible(true);
    }

    private JPanel getUserTypePanel(JPanel loginPanel) {
        UserTypePanel userTypePanel = new UserTypePanel(this, loginPanel);
        return userTypePanel;
    }

    private JPanel getLoginPanel(JPanel success) {
        LoginPage loginPanel = new LoginPage(this, success);
        return loginPanel;
    }

    private JPanel getProductTypePanel(JPanel nextPanel) {
        ProductTypePanel productTypePanel = new ProductTypePanel(this, nextPanel);
        return productTypePanel;
    }
}
