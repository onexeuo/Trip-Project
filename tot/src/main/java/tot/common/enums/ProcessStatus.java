package tot.common.enums;

public enum ProcessStatus {
	RECEIVED("접수"), INPROGRESS("처리중"), COMPLETED("완료"), REJECTED("기각");

	private final String description;

	ProcessStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
