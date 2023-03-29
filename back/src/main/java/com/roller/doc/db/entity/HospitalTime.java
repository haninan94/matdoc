package com.roller.doc.db.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Table(name="hospital_time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalTime {
	@Id
	@Column(name="hospital_time_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hospital_time_id;

	@JoinColumn(name="hospital_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Hospital hospital;
//	@Column(name="hospital_id", columnDefinition = "INT")
//	private long hospital_id;

	@Column(name="hospital_time_mon_s",columnDefinition = "VARCHAR(50)")
	private String hospital_time_mon_s;

	@Column(name="hospital_time_mon_e",columnDefinition = "VARCHAR(50)")
	private String hospital_time_mon_e;

	@Column(name="hospital_time_tue_s",columnDefinition = "VARCHAR(50)")
	private String hospital_time_tue_s;

	@Column(name="hospital_time_tue_e",columnDefinition = "VARCHAR(50)")
	private String hospital_time_tue_e;

	@Column(name="hospital_time_wed_s",columnDefinition = "VARCHAR(50)")
	private String hospital_time_wed_s;

	@Column(name="hospital_time_wed_e",columnDefinition = "VARCHAR(50)")
	private String hospital_time_wed_e;

	@Column(name="hospital_time_thu_s",columnDefinition = "VARCHAR(50)")
	private String hospital_time_thu_s;

	@Column(name="hospital_time_thu_e",columnDefinition = "VARCHAR(50)")
	private String hospital_time_thu_e;

	@Column(name="hospital_time_fri_s",columnDefinition = "VARCHAR(50)")
	private String hospital_time_fri_s;

	@Column(name="hospital_time_fri_e",columnDefinition = "VARCHAR(50)")
	private String hospital_time_fri_e;

	@Column(name="hospital_time_sat_s",columnDefinition = "VARCHAR(50)")
	private String hospital_time_sat_s;

	@Column(name="hospital_time_sat_e",columnDefinition = "VARCHAR(50)")
	private String hospital_time_sat_e;

	@Column(name="hospital_time_sun_s",columnDefinition = "VARCHAR(50)")
	private String hospital_time_sun_s;

	@Column(name="hospital_time_sun_e",columnDefinition = "VARCHAR(50)")
	private String hospital_time_sun_e;

	@Column(name = "hospital_time_etc",columnDefinition = "text")
	private String hospital_time_etc;

}
