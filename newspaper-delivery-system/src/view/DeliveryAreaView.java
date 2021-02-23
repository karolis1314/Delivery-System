//package view;
//
//
//import exceptions.DeliveryAreaException;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DeliveryAreaView extends JFrame {
//    private JPanel DeliveryAreaView;
//    private JTextField areaNameTxt;
//    private JTextField areaIdTxt;
//    private JTextField areaSizeTxt;
//    private JButton newAreaBtn;
//    private JTextArea textArea1;
//    private JLabel areaId;
//    private JLabel areaName;
//    private JLabel areaSize;
//    private JButton updateButton;
//    private JButton delete;
//
//    public DeliveryAreaView(String title){
//        super(title);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setContentPane(DeliveryAreaView);
//        this.pack();
//
//
//        showTable();
//
//        newAreaBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String areaName = areaNameTxt.getText();
//                int areaSize = Integer.parseInt(areaSizeTxt.getText());
//
//                try {
//                    m
//                } catch (DeliveryAreaException e1) {
//
//                }
//                showTable();
//            }
//        });
//        updateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DeliveryAreaController dac = new DeliveryAreaController();
//                try {
//                    dac.updateDeliveryArea(
//                            Integer.parseInt(areaIdTxt.getText()),
//                            areaNameTxt.getText(),
//                            Integer.parseInt(areaSizeTxt.getText()));
//                } catch (DeliveryAreaException deliveryAreaException) {
//                    deliveryAreaException.printStackTrace();
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                } finally {
//                    showTable();
//                }
//            }
//        });
//        delete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DeliveryAreaController dac = new DeliveryAreaController();
//                try {
//                    dac.deleteDeliveryArea(Integer.parseInt(areaIdTxt.getText()));
//                } catch (DeliveryAreaException deliveryAreaException) {
//                    deliveryAreaException.printStackTrace();
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                } finally {
//                    showTable();
//                }
//            }
//        });
//    }
//
//    private void showTable(){
//        DeliveryAreaController n = new DeliveryAreaController();
//
//
//        try {
//            textArea1.setText(n.readAllDeliveryAreas().toString());
//        } catch (DeliveryAreaException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
