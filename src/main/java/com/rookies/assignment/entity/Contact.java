package com.rookies.assignment.entity;

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
    @GeneratedValue
    private UUID id;

    @Column(name="address", nullable = false, length = 100)
    private String address;

    @Column(name="hotline", nullable = false, length = 15)
    private String hotline;

    @Column(name="email", length = 50)
    private String email;

    @Column(name="facebook", length = 150)
    private String facebook;

    @Column(name="view_page")
    private long view_page;

    @Column(name="contact_content", nullable = false, length = 250)
    private String contact_content;

    @Column(name="brand_story", nullable = false, length = 350)
    private String brand_story;

    @Column(name="time_open")
    private Time time_open;

    @Column(name="time_close")
    private Time time_close;

    @Column(name="time_update")
    private Timestamp time_update;

}
