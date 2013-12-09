/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communties.bean
 * @created Jun 25, 2013
 * @description This class will represent the Choice Liferay Model
 */
package org.ieeecs.communties.bean;

public class ChoiceBean {
	private String choiceId;
	private String name;
	private String description;
	private String questionId;
	private String isSelected;
	private int numberOfVotes = 0;
	
	public int getNumberOfVotes() {
		return this.numberOfVotes;
	}
	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	public String getChoiceId() {
		return this.choiceId;
	}
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuestionId() {
		return this.questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getIsSelected() {
		return this.isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
