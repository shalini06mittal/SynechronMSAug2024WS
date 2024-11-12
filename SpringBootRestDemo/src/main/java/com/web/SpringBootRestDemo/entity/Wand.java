package com.web.SpringBootRestDemo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "wand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class Wand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String wood;
    private String core;
    private String length;

    @OneToOne(mappedBy = "wand")
//    @JsonIgnore
    private FictionalCharacter character;

//	@Override
//	public String toString() {
//		return "Wand [id=" + id + ", wood=" + wood + ", core=" + core + ", length=" + length + "]";
//	}

     
}

