package com.mikagorelik.diaryofplases;

import java.io.Serializable;

public class myContact implements Serializable {

    public String contactID;
    public String contactName;
    public String contactComment;
    public String contactPhone;
    public String contactEmail;

    public myContact(String contactID, String contactName, String contactComment, String contactPhone, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactComment = contactComment;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactComment() {
        return contactComment;
    }

    public void setContactComment(String contactComment) {
        this.contactComment = contactComment;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return ""+contactName +"\n" + contactComment +"\n" + contactPhone + "\n" + contactEmail;
    }
}
