package com.rookies.assignment.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="address", nullable = false, length = 100)
    private String address;

    @Column(name="hotline", nullable = false, length = 15)
    private String hotline;

    @Column(name="email", length = 50)
    private String email;

    @Column(name="facebook", length = 150)
    private String facebook;

    @Column(name="viewPage")
    private long viewPage;

    @Column(name="contact_content", nullable = false, length = 250)
    private String contactContent;

    @Column(name="brand_story", nullable = false, length = 350)
    private String brandStory;

    @Column(name="time_open")
    private Time timeOpen;

    @Column(name="time_close")
    private Time timeClose;

    @Column(name="time_update")
    private Timestamp timeUpdate;

}
