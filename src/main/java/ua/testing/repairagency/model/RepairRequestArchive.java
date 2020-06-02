package ua.testing.repairagency.model;

import ua.testing.repairagency.dao.Identified;

public class RepairRequestArchive implements Identified<Long> {
    private Long id;
    private String description;
    private boolean accepted;
    private boolean performed;
    private String cancellationReason;
    private Long uahPrice;
    private Long usdPrice;
    private String address;
    private String userPhoneNumber;
    private String userComment;


    @Override
    public Long getId() {
        return null;
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

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }


    public static class Builder {
        private RepairRequestArchive requestArchive;
        public Builder() {
            requestArchive = new RepairRequestArchive();
        }

        public Builder id(Long id){
            requestArchive.id = id;
            return this;
        }

        public Builder description(String description){
            requestArchive.description = description;
            return this;
        }

        public Builder accepted(boolean accepted){
            requestArchive.accepted = accepted;
            return this;
        }

        public Builder performed(boolean performed){
            requestArchive.performed = performed;
            return this;
        }

        public Builder cancellationReason(String cancellationReason){
            requestArchive.cancellationReason = cancellationReason;
            return this;
        }

        public Builder uahPrice(Long uahPrice){
            requestArchive.uahPrice = uahPrice;
            return this;
        }

        public Builder usdPrice(Long usdPrice){
            requestArchive.usdPrice = usdPrice;
            return this;
        }

        public Builder address(String address){
            requestArchive.address =address;
            return this;
        }

        public Builder userPhoneNumber(String userPhoneNumber){
            requestArchive.userPhoneNumber = userPhoneNumber;
            return this;
        }

        public Builder userComment(String userComment){
            requestArchive.userComment = userComment;
            return this;
        }

        public RepairRequestArchive build(){
            return requestArchive;
        }

    }

}
