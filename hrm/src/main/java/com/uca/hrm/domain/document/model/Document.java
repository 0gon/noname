package com.uca.hrm.domain.document.model;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.uca.hrm.domain.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "document_type")
@Getter(AccessLevel.PUBLIC)
@ToString
public class Document {

    @Id
    private String id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn
    private Employee author; // 작성자
    private String status; // 예: draft, published, archived
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    @OneToMany(mappedBy = "document")
    private List<Approve> approves = new ArrayList<>(); // 결재자 목록

    protected Document() {
    }

    Document(String id, String title, String content, Employee author, String status,
            LocalDateTime createdDateTime, LocalDateTime modifiedDateTime, List<Approve> approves) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.status = status;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
        this.approves = approves;
    }

    // public static Document generate(String id, String title, String content, Employee author, List<Approve> approves,
    //         Clock clock) {
    //     return new Document(
    //             id,
    //             title,
    //             content,
    //             author,
    //             "대기",
    //             LocalDateTime.now(clock),
    //             null,
    //             approves);
    // }
}
