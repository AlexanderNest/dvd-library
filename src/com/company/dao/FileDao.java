package com.company.dao;

import com.company.dao.exceptions.UnableToLoadListOfDvdException;
import com.company.dao.exceptions.UnableToSaveListOfDvdException;
import com.company.dto.interfaces.Disk;

import java.io.*;
import java.util.ArrayList;

public class FileDao {
    private final String filePath;

    public FileDao(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Disk> listOfDvd) throws UnableToSaveListOfDvdException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
        {
            oos.writeObject(listOfDvd);
        }
        catch(IOException ex){
            throw new UnableToSaveListOfDvdException(ex);
        }
    }

    public ArrayList<Disk> load() throws UnableToLoadListOfDvdException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath)))
        {
            return (ArrayList<Disk>) ois.readObject();
        }
        catch(Exception ex){
            throw new UnableToLoadListOfDvdException(ex);
        }
    }
}
