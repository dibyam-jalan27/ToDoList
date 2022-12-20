package com.svnit.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Filehelper {
    public static final String Filename = "listinfo.dat";
    public static final String Filename2 = "cheaklist.dat";

    public static void writeData(ArrayList<String> item, ArrayList<Boolean> check, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(Filename,context.MODE_PRIVATE);
            FileOutputStream fos2 = context.openFileOutput(Filename2,context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            ObjectOutputStream oas1 = new ObjectOutputStream(fos2);
            oas.writeObject(item);
            oas1.writeObject(check);
            oas.close();
            oas1.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<String> readText (Context context){
        ArrayList<String> itemlist = null;
        try {
            FileInputStream fis = context.openFileInput(Filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemlist = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemlist = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemlist;

    }
    public static ArrayList<Boolean> readCheck (Context context){
        ArrayList<Boolean> itemlist = null;
        try {
            FileInputStream fis = context.openFileInput(Filename2);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemlist = (ArrayList<Boolean>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemlist = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemlist;

    }
}
