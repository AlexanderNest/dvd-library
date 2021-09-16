package com.company.dto;

import com.company.dto.exceptions.IncorrectDateOfReleaseException;
import com.company.dto.interfaces.Disk;

import java.io.Serializable;
import java.time.LocalDate;


public class Dvd implements Disk, Serializable {
    private String name;
    private LocalDate dateOfRelease;
    private int mpaaRating;
    private String nameOfDirector;
    private String nameOfStudio;
    private String userNote;

    public Dvd(String name, int year, int month, int day, int mpaaRating, String nameOfDirector, String nameOfStudio, String userNote) {
        this.name = name;
        this.dateOfRelease = LocalDate.of(year, month, day);
        this.mpaaRating = mpaaRating;
        this.nameOfDirector = nameOfDirector;
        this.nameOfStudio = nameOfStudio;
        this.userNote = userNote;
    }

    @Override
    public String toString() {
        return "Disk:\n" +
                "1. name='" + name + '\'' + "\n" +
                "2. dateOfRelease=" + dateOfRelease + "\n" +
                "3. mpaaRating=" + mpaaRating + "\n" +
                "4. nameOfDirector='" + nameOfDirector + '\'' + "\n" +
                "5. nameOfStudio='" + nameOfStudio + '\'' + "\n" +
                "6. userNote='" + userNote + '\'' + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        LocalDate now = LocalDate.now();
        if (dateOfRelease.isAfter(now)){
            throw new IncorrectDateOfReleaseException("Incorrect time of dvd release");
        }

        this.dateOfRelease = dateOfRelease;
    }

    public int getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(int mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getNameOfDirector() {
        return nameOfDirector;
    }

    public void setNameOfDirector(String nameOfDirector) {
        this.nameOfDirector = nameOfDirector;
    }

    public String getNameOfStudio() {
        return nameOfStudio;
    }

    public void setNameOfStudio(String nameOfStudio) {
        this.nameOfStudio = nameOfStudio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
