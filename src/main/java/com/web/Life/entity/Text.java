package com.web.Life.entity;



import com.web.utils.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Text extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String textTitle;

	private String textSubTitle;

	private String established;

	private String project;
	private String client;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "image",referencedColumnName = "id")
	private QbImage image ;

}

