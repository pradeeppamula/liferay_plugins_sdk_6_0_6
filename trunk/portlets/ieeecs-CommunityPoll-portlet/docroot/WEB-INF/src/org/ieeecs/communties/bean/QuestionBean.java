/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communties.bean
 * @created Jun 25, 2013
 * @description This class will represent the Question Liferay Model
 */
package org.ieeecs.communties.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionBean implements Comparable<QuestionBean> {
	private String questionId;
	private String question;
	private List<ChoiceBean> choices;
	private int numberOfVotes = 0;
	private Date createdDate;
	private Date expirationDate;
	
	/**
	 * We override the compare to method here to 
	 * sort by created date DESC
	 */
	@Override
	public int compareTo(QuestionBean o) {
	    if (this.getCreatedDate() == null || o.getCreatedDate() == null) {
	      return 0;
	    }
	    return o.getCreatedDate().compareTo(this.getCreatedDate());
	}
	 
	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	public int getNumberOfVotes() {
		return this.numberOfVotes;
	}
	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	public Date getCreatedDate() {
		return this.createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getExpirationDate() {
		return this.expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getQuestion() {
		return this.question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<ChoiceBean> getChoices() {
		if(this.choices == null) {
			this.choices = new ArrayList<ChoiceBean>();
		}
		return this.choices;
	}
	public void setChoices(List<ChoiceBean> choices) {
		this.choices = choices;
	}
}
