package com.company.controller;

import com.company.controller.exceptions.DiskIsNotFoundException;
import com.company.controller.exceptions.DiskIsNotUniqueException;
import com.company.dao.FileDao;
import com.company.dao.exceptions.UnableToLoadListOfDvdException;
import com.company.dao.exceptions.UnableToSaveListOfDvdException;
import com.company.dto.interfaces.Disk;
import com.company.dto.Dvd;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DiskController {
    private final String filePath = "data/dvd.data";
    private ArrayList<Disk> listOfDisks = new ArrayList<>();

    private final FileDao fileDao;

    public DiskController(){
        fileDao = new FileDao(filePath);
    }

    public Disk findDisk(String name){
        Optional<Disk> optional = listOfDisks
                .stream()
                .filter(i -> i.getName().equals(name))
                .findFirst();

        try {
            return optional.get();
        }
        catch (NoSuchElementException ex){
            return null;
        }
    }

    public void editFieldOfDisk(Disk disk, String field, String value){
        switch (field){
            case "1":
                disk.setName(value);
                break;
            case "3":
                disk.setMpaaRating(Integer.parseInt(value));
                break;
            case "4":
                disk.setNameOfDirector(value);
            case "5":
                disk.setNameOfStudio(value);
                break;
            case "6":
                disk.setUserNote(value);
        }
    }

    public void editDateOfReleaseOfDisk(Disk disk, int year, int month, int day){
        disk.setDateOfRelease(LocalDate.of(year, month, day));
    }

    public void removeDisk(String name) throws DiskIsNotFoundException {
        boolean removed = listOfDisks.removeIf(i -> i.getName().equals(name));
        if (!removed){
            throw new DiskIsNotFoundException();
        }
    }

    public void addDisk(String name, int year, int month, int day, int mpaaRating, String nameOfDirector,
                        String nameOfStudio, String userNote) throws DiskIsNotUniqueException, DateTimeException {
        Disk disk;

        disk = new Dvd(name, year, month, day, mpaaRating, nameOfDirector, nameOfStudio, userNote);

        boolean notUnique = listOfDisks
                        .stream()
                        .anyMatch(i -> i.getName().equals(disk.getName()));

        if (!notUnique){
            listOfDisks.add(disk);
        }
        else{
            throw new DiskIsNotUniqueException();
        }
    }

    public void save() throws UnableToSaveListOfDvdException {
        fileDao.save(listOfDisks);
    }

    public void load() throws UnableToLoadListOfDvdException {
        listOfDisks = fileDao.load();
    }

    public ArrayList<Disk> getAllDisks(){
        return listOfDisks;
    }
}
