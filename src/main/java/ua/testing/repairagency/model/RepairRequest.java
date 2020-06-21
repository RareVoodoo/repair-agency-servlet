package ua.testing.repairagency.model;

import ua.testing.repairagency.dao.Identified;
import ua.testing.repairagency.util.Constants;

import javax.validation.constraints.Pattern;

public class RepairRequest implements Identified<Long> {

    private Long id;
    private String description;
    private boolean accepted;
    private boolean performed;
    private String cancellationReason;
    private Long uahPrice;
    private Long usdPrice;
    private String address;
    private String userPhoneNumber;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isPerformed() {
        return performed;
    }

    public void setPerformed(boolean performed) {
        this.performed = performed;
    }


    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Long getUahPrice() {
        return uahPrice;
    }

    public void setUahPrice(Long uahPrice) {
        this.uahPrice = uahPrice;
    }


    public Long getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(Long usdPrice) {
        this.usdPrice = usdPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static class Builder {
        private RepairRequest repairRequest;

        public Builder() {
            repairRequest = new RepairRequest();
        }

        public Builder id(Long id){
            repairRequest.id = id;
            return this;
        }

        public Builder description(String description){
            repairRequest.description = description;
            return this;
        }

        public Builder accepted(boolean accepted){
            repairRequest.accepted = accepted;
            return this;
        }

        public Builder performed( boolean performed){
            repairRequest.performed = performed;
            return this;
        }

        public Builder cancellationReason(String cancellationReason){
            repairRequest.cancellationReason = cancellationReason;
            return this;
        }

        public Builder uahPrice(Long uahPrice){
            repairRequest.uahPrice = uahPrice;
            return this;
        }

        public Builder usdPrice(Long usdPrice){
            repairRequest.usdPrice = usdPrice;
            return this;
        }

        public Builder address(String address){
            repairRequest.address =address;
            return this;
        }

        public Builder userPhoneNumber(String userPhoneNumber){
            repairRequest.userPhoneNumber = userPhoneNumber;
            return this;
        }

        public Builder userId(Long userId){
            repairRequest.userId = userId;
            return this;
        }

        public RepairRequest build(){
            return repairRequest;
        }
    }

}
