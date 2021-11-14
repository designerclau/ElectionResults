/*
 * This class will store temporary the election results
 */
package electionresults;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Claudinea de Almeida
 */

public class Election {
    private SimpleStringProperty ConstituencyName;
    private SimpleStringProperty CandidateSurname;
    private SimpleStringProperty CandidateFirstName;
    private SimpleStringProperty Result;
    private SimpleIntegerProperty CountNumber;
    private SimpleIntegerProperty NonTransferable;
    private SimpleIntegerProperty OccurredOnCount;
    private SimpleIntegerProperty RequiredToReachQuota;
    private SimpleIntegerProperty RequiredToSaveDeposit;
    private SimpleIntegerProperty Transfers;
    private SimpleIntegerProperty Votes;
    private SimpleIntegerProperty TotalVotes;
    private SimpleIntegerProperty ConstituencyNumber;
    private SimpleIntegerProperty CandidateId;

    public Election(SimpleStringProperty ConstituencyName, SimpleStringProperty CandidateSurname, SimpleStringProperty CandidateFirstName, SimpleStringProperty Result, SimpleIntegerProperty Votes, SimpleIntegerProperty TotalVotes) {
        this.ConstituencyName = ConstituencyName;
        this.CandidateSurname = CandidateSurname;
        this.CandidateFirstName = CandidateFirstName;
        this.Result = Result;
        this.Votes = Votes;
        this.TotalVotes = TotalVotes;
    }

    public Election() {
    }

    public Election(SimpleStringProperty ConstituencyName, SimpleStringProperty CandidateSurname, SimpleStringProperty CandidateFirstName, SimpleStringProperty Result, SimpleIntegerProperty CountNumber, SimpleIntegerProperty NonTransferable, SimpleIntegerProperty OccurredOnCount, SimpleIntegerProperty RequiredToReachQuota, SimpleIntegerProperty RequiredToSaveDeposit, SimpleIntegerProperty Transfers, SimpleIntegerProperty Votes, SimpleIntegerProperty TotalVotes, SimpleIntegerProperty ConstituencyNumber, SimpleIntegerProperty CandidateId) {
        this.ConstituencyName = ConstituencyName;
        this.CandidateSurname = CandidateSurname;
        this.CandidateFirstName = CandidateFirstName;
        this.Result = Result;
        this.CountNumber = CountNumber;
        this.NonTransferable = NonTransferable;
        this.OccurredOnCount = OccurredOnCount;
        this.RequiredToReachQuota = RequiredToReachQuota;
        this.RequiredToSaveDeposit = RequiredToSaveDeposit;
        this.Transfers = Transfers;
        this.Votes = Votes;
        this.TotalVotes = TotalVotes;
        this.ConstituencyNumber = ConstituencyNumber;
        this.CandidateId = CandidateId;
    }

    public SimpleStringProperty getConstituencyName() {
        return ConstituencyName;
    }

    public void setConstituencyName(String ConstituencyName) {
        this.ConstituencyName = new SimpleStringProperty (ConstituencyName);
    }

    public SimpleStringProperty getCandidateSurname() {
        return CandidateSurname;
    }

    public void setCandidateSurname(String CandidateSurname) {
        this.CandidateSurname = new SimpleStringProperty(CandidateSurname);
    }

    public SimpleStringProperty getCandidateFirstName() {
        return CandidateFirstName;
    }

    public void setCandidateFirstName(String CandidateFirstName) {
        this.CandidateFirstName = new SimpleStringProperty(CandidateFirstName);
    }

    public SimpleStringProperty getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = new SimpleStringProperty(Result);
    }

    public SimpleIntegerProperty getCountNumber() {
        return CountNumber;
    }

    public void setCountNumber(Integer CountNumber) {
        this.CountNumber = new SimpleIntegerProperty(CountNumber);
    }

    public SimpleIntegerProperty getNonTransferable() {
        return NonTransferable;
    }

    public void setNonTransferable(Integer NonTransferable) {
        this.NonTransferable = new SimpleIntegerProperty(NonTransferable);
    }

    public SimpleIntegerProperty getOccurredOnCount() {
        return OccurredOnCount;
    }

    public void setOccurredOnCount(Integer OccurredOnCount) {
        this.OccurredOnCount = new SimpleIntegerProperty(OccurredOnCount);
    }

    public SimpleIntegerProperty getRequiredToReachQuota() {
        return RequiredToReachQuota;
    }

    public void setRequiredToReachQuota(Integer RequiredToReachQuota) {
        this.RequiredToReachQuota = new SimpleIntegerProperty(RequiredToReachQuota);
    }

    public SimpleIntegerProperty getRequiredToSaveDeposit() {
        return RequiredToSaveDeposit;
    }

    public void setRequiredToSaveDeposit(Integer RequiredToSaveDeposit) {
        this.RequiredToSaveDeposit = new SimpleIntegerProperty(RequiredToSaveDeposit);
    }

    public SimpleIntegerProperty getTransfers() {
        return Transfers;
    }

    public void setTransfers(Integer Transfers) {
        this.Transfers = new SimpleIntegerProperty(Transfers);
    }

    public SimpleIntegerProperty getVotes() {
        return Votes;
    }

    public void setVotes(Integer Votes) {
        this.Votes = new SimpleIntegerProperty(Votes);
    }

    public SimpleIntegerProperty getTotalVotes() {
        return TotalVotes;
    }

    public void setTotalVotes(Integer TotalVotes) {
        this.TotalVotes = new SimpleIntegerProperty(TotalVotes);
    }

    public SimpleIntegerProperty getConstituencyNumber() {
        return ConstituencyNumber;
    }

    public void setConstituencyNumber(Integer ConstituencyNumber) {
        this.ConstituencyNumber = new SimpleIntegerProperty(ConstituencyNumber);
    }

    public SimpleIntegerProperty getCandidateId() {
        return CandidateId;
    }

    public void setCandidateId(Integer CandidateId) {
        this.CandidateId = new SimpleIntegerProperty(CandidateId);
    }

   
    
}
