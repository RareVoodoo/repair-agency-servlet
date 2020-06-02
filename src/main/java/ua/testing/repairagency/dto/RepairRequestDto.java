package ua.testing.repairagency.dto;

public class RepairRequestDto {
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
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
}
