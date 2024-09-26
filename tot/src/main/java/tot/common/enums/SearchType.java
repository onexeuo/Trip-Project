package tot.common.enums;

public enum SearchType {
	ALL("전체"), TITLE("제목"), CONTENT("내용"), POST("게시물"), MEMBER("사용자"), COMMENT("댓글");

	private final String description;

	SearchType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
