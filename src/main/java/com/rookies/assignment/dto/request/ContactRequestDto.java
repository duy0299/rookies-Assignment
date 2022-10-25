package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDto {
    @NotEmpty
    @NotNull
    private Integer id;

    @NotEmpty
    @NotNull
    private String address;
    @NotEmpty
    @NotNull
    private String hotline;
    @NotEmpty
    @NotNull
    private String email;
    @NotEmpty
    @NotNull
    private String facebook;
    @NotEmpty
    @NotNull
    private long viewPage;
    @NotEmpty
    @NotNull
    private String contactContent;
    @NotEmpty
    @NotNull
    private String brandStory;
    @NotEmpty
    @NotNull
    private Time timeOpen;
    @NotEmpty
    @NotNull
    private Time timeClose;

    public Contact setContact(){
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        Contact contact = new Contact();
        contact.setId(id);
        contact.setContactContent(contactContent);
        contact.setAddress(address);
        contact.setEmail(email);
        contact.setFacebook(facebook);
        contact.setHotline(hotline);
        contact.setBrandStory(brandStory);
        contact.setTimeClose(timeClose);
        contact.setTimeOpen(timeOpen);
        contact.setTimeUpdate(now);

        return contact;
    }


}