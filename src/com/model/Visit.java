package com.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by anons on 5/2/16.
 */
public class Visit {
    private final int visitId;
    private final int personId;
    private final Time visitTime;
    private final Date visitDate;
    private final String visitPurpose;

    public Visit(VisitBuilder builder){
        this.visitId = builder.visitId;
        this.personId = builder.personID;
        this.visitDate = builder.visitDate;
        this.visitTime = builder.visitTime;
        this.visitPurpose = builder.visitPurpose;
    }

    public int getVisitId() {
        return visitId;
    }

    public int getPersonId() {
        return personId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public Time getVisitTime() {
        return visitTime;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public static class VisitBuilder{
        private final int visitId;
        private final int personID;
        private final Date visitDate;
        private final Time visitTime;
        private final String visitPurpose;

        public VisitBuilder(int id, int person, Date visitDate, Time visitTime, String visitPurpose) {
            this.visitId = id;
            this.personID = person;
            this.visitDate = visitDate;
            this.visitTime=visitTime;
            this.visitPurpose = visitPurpose;
        }

        public Visit build(){
            return new Visit(this);
        }
    }

    public static Visit getVisit(int visitId, int person, Date visitDate, Time visitTime, String purpose) {
        return new VisitBuilder(visitId,person,visitDate, visitTime, purpose).build();
    }

    @Override
    public String toString() {
        return "Visit{" +
                "personId=" + personId +
                ", visitDate=" + visitDate +
                ", visitTime=" + visitTime +
                ", visitPurpose='" + visitPurpose + '\'' +
                '}';
    }
}
