package com.company.dto.interfaces;

import java.time.LocalDate;

public interface Disk {
    String getName();

    void setName(String name);

    LocalDate getDateOfRelease();

    void setDateOfRelease(LocalDate dateOfRelease);

    int getMpaaRating();

    void setMpaaRating(int mpaaRating);

    String getNameOfDirector();

    void setNameOfDirector(String nameOfDirector);

    String getNameOfStudio();

    void setNameOfStudio(String nameOfStudio);

    String getUserNote();

    void setUserNote(String userNote);
}
