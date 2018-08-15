package com.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by anons on 6/27/16.
 */
public class Report {
    private final int visitId;
    private final Date visitDate;
    private final Time visitTime;
    private final String name;
    private final String contact;
    private final String type;
    private final String visitPurpose;

    public Report(ReportBuilder builder) {
        this.visitId = builder.visitId;
        this.visitDate = builder.visitDate;
        this.visitTime = builder.visitTime;
        this.name = builder.name;
        this.contact = builder.contact;
        this.type = builder.type;
        this.visitPurpose = builder.visitPurpose;
    }


    public int getVisitId() {
        return visitId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public Time getVisitTime() {
        return visitTime;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public static class ReportBuilder{
        private final int visitId;
        private final Date visitDate;
        private final Time visitTime;
        private final String name;
        private final String contact;
        private final String type;
        private final String visitPurpose;

        public ReportBuilder(int visitId, Date visitDate, Time visitTime, String name, String contact, String type, String visitPurpose) {
            this.visitId = visitId;
            this.visitDate = visitDate;
            this.visitTime = visitTime;
            this.name = name;
            this.contact = contact;
            this.type = type;
            this.visitPurpose = visitPurpose;
        }

        public Report build(){
            return new Report(this);
        }
    }

    public static Report getReport(int visitId, Date visitDate, Time visitTime, String name, String contact, String type, String visitPurpose) {
        return new ReportBuilder(visitId,visitDate,visitTime,name,contact,type,visitPurpose).build();
    }
}
