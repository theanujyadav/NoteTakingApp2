package com.example.notetakingapp2.Utils;

import com.example.notetakingapp2.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvider {
    public static final String SAMPLE_TEXT1="A sample data";
    public static final String SAMPLE_TEXT2="A sample data1 ";
    public static final String SAMPLE_TEXT3="A sample data 2";
    public static final String SAMPLE_TEXT4="A sample data 3";

    public static Date getDate(int diffAmount){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.MILLISECOND,diffAmount);
        return gregorianCalendar.getTime();
    }

    public static List<NoteEntity> getSampleData(){

        List<NoteEntity> noteList = new ArrayList<>();
        noteList.add(new NoteEntity(1,getDate(0),SAMPLE_TEXT1));
        noteList.add(new NoteEntity(2,getDate(-1),SAMPLE_TEXT2));
        noteList.add(new NoteEntity(3,getDate(+1),SAMPLE_TEXT3));
        return noteList;
    }

}
