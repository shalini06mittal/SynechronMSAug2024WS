package com.web.SpringBootRestDemo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@Entity
//@Table(name = "fictional_character")
public class FictionalCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150, unique = true)
    private String name;
    @Column(length = 150, nullable = false)
    private String house;
    @Column(length = 150, nullable = false)
    private String bio;
    private String imageurl;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="wandid")
    //@JsonIgnore() // REST API endpoints
   
    private Wand wand;

	@Override
	public String toString() {
		return "FictionalCharacter [id=" + id + ", name=" + name + ", house=" + house + ", bio=" + bio + ", imageurl="
				+ imageurl + "]";
	}
    

    

}
