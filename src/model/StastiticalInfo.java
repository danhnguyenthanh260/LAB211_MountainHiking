/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import collections.StudentList;
/**
 *
 * @author DANH NGUYEN
 */
public class StastiticalInfo {
    String mountainCode;
    int participant;
    double totalTuitionFee;

    public StastiticalInfo() {
    }

    public StastiticalInfo(String mountainCode, int participant, double totalTuitionFee) {
        this.setMountainCode(mountainCode);
        this.setParticipant(participant);
        this.setTotalTuitionFee(totalTuitionFee);
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }

    public double getTotalTuitionFee() {
        return totalTuitionFee;
    }

    public void setTotalTuitionFee(double totalTuitionFee) {
        this.totalTuitionFee = totalTuitionFee;
    }
    
    @Override
    public String toString() {
        return String.format(" MT%-7s| %-23d| %-13s", mountainCode, participant, StudentList.addCommaToTuitionFee(totalTuitionFee));
    }
}
