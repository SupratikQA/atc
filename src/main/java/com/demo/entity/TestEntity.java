package com.demo.entity;

public class TestEntity {

	public String testId;
	public String testCase;
	public String keyWord;
	public String actionName;
	public String identifier;
	public String additionalIdentifier;
	public String testData;
	public String expectedResult;
	public String successMessage;
	public String failureMessage;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestCase() {
		return testCase;
	}

	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAdditionalIdentifier() {
		return additionalIdentifier;
	}

	public void setAdditionalIdentifier(String additionalIdentifier) {
		this.additionalIdentifier = additionalIdentifier;
	}

	public String getTestData() {
		return testData;
	}

	public void setTestData(String testData) {
		this.testData = testData;
	}
	
	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public TestEntity(String testId, String testCase, String keyWord, String actionName, String identifier,
			String additionalIdentifier, String testData, String expectedResult, String successMessage,
			String failureMessage) {
		super();
		this.testId = testId;
		this.testCase = testCase;
		this.keyWord = keyWord;
		this.actionName = actionName;
		this.identifier = identifier;
		this.additionalIdentifier = additionalIdentifier;
		this.testData = testData;
		this.expectedResult = expectedResult;
		this.successMessage = successMessage;
		this.failureMessage = failureMessage;
	}

	public TestEntity() {
		super();
	}

}
