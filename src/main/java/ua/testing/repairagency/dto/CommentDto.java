package ua.testing.repairagency.dto;

import ua.testing.repairagency.util.Constants;

import javax.validation.constraints.Pattern;




public class CommentDto {
    private Long id;
    private String comment;
    private Long repairRequestId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Pattern( message = Constants.COMMENT_VALIDATION_PROPERTY,regexp = Constants.COMMENT_VALIDATION_REGEX)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getRepairRequestId() {
        return repairRequestId;
    }

    public void setRepairRequestId(Long repairRequestId) {
        this.repairRequestId = repairRequestId;
    }
}
