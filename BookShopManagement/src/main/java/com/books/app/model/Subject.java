package com.books.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@Column(name="subtitle")
	private String subtitle;
	@Column(name="subjectid")
	private long subjectId;
	@Column(name="durationInHours")
	private Integer durationInHours;
	@OneToMany(fetch = FetchType.EAGER,mappedBy="subject",orphanRemoval=true)
	private Set<Book> references;
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Integer getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(Integer durationInHours) {
		this.durationInHours = durationInHours;
	}
	public Set<Book> getReferences() {
		return references;
	}
	public void setReferences(Set<Book> references) {
		this.references = references;
	}
	
	

}
