package ua.testing.repairagency.model;

import ua.testing.repairagency.dao.Identified;

public class Comment implements Identified<Long> {
    private Long id;
    private String comment;
    private Long repairRequestId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static class Builder{
        private Comment newComment;

        public Builder(){
            this.newComment = new Comment();
        }

        public Builder id(Long id){
            newComment.id = id;
            return this;
        }

        public Builder comment(String comment){
            newComment.comment = comment;
            return this;
        }

        public Builder repairRequestId(Long repairRequestId){
            newComment.repairRequestId = repairRequestId;
            return this;
        }

        public Comment build(){
            return newComment;
        }
    }
}
