package ua.testing.repairagency.dto;

import ua.testing.repairagency.util.Constants;

import javax.validation.constraints.*;

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

    @Pattern(message = Constants.DESCRIPTION_VALIDATION_PROPERTY ,regexp = Constants.DESCRIPTION_VALIDATION_REGEX)
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

    @Pattern(message = Constants.CANCELLATION_REASON_VALIDATION_PROPERTY,
            regexp = Constants.CANCELLATION_REASON_VALIDATION_REGEX)
    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Long getUahPrice() {
        return uahPrice;
    }

    public void setUahPrice(long uahPrice) {
        this.uahPrice = uahPrice;
    }



    @PositiveOrZero(message = Constants.POSITIVE_PRICE_VALIDATION_PROPERTY)
    @DecimalMax(value= Constants.PRICE_MAX_VALUE)
    @NotNull(message = Constants.EMPTY_PRICE_VALIDATION_PROPERTY)
    public Long getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(long usdPrice) {
        this.usdPrice = usdPrice;
    }

    @Pattern( message = Constants.ADDRESS_VALIDATION_PROPERTY  ,regexp = Constants.ADDRESS_VALIDATION_REGEX)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Pattern(message = Constants.PHONE_NUMBER_VALIDATION_PROPERTY,regexp = Constants.PHONE_NUMBER_VALIDATION_REGEX)
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
