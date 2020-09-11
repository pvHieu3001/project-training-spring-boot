package com.smartosc.training.objectoutputstream;

import com.smartosc.training.domain.order.OrderDto;

import java.io.*;

public class ObjectOutputStreamExample {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("/home/pvhieu/Documents/github/data/testout.txt"));
            // create student
            OrderDto student = new OrderDto(1L,"hha");
            // write student
            oos.writeObject(student);
            System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            oos.close();
        }

        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream("/home/pvhieu/Documents/github/data/testout.txt"));
            // read student
            OrderDto student = (OrderDto) ois.readObject();
            // show student
            System.out.println(student.toString());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            ois.close();
        }
    }
}
