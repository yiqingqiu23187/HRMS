package edu.fudan.hrms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long noticeID;
    public String notice;
    public Long userID;
    public Long time = System.currentTimeMillis();

    public Notice(String notice, Long userID) {
        this.notice = notice;
        this.userID = userID;
    }
}
