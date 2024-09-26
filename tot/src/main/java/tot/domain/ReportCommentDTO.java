package tot.domain;

public class ReportCommentDTO {

	private String reporterId; // 신고한 사용자 아이디
	private int reportedTrevcId; // 신고된 댓글 아이디
	private String reportedContentType; // 신고된 콘텐츠 유형
	private String reportReason; // 신고 사유

	public ReportCommentDTO() {
	}

	public ReportCommentDTO(String reporterId, int reportedTrevcId, String reportedContentType, String reportReason) {
		this.reporterId = reporterId;
		this.reportedTrevcId = reportedTrevcId;
		this.reportedContentType = reportedContentType;
		this.reportReason = reportReason;
	}

	public String getReporterId() {
		return reporterId;
	}

	public int getReportedTrevcId() {
		return reportedTrevcId;
	}

	public String getReportedContentType() {
		return reportedContentType;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public void setReportedTrevcId(int reportedTrevcId) {
		this.reportedTrevcId = reportedTrevcId;
	}

	public void setReportedContentType(String reportedContentType) {
		this.reportedContentType = reportedContentType;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	@Override
	public String toString() {
		return "ReportCommentDTO [reporterId=" + reporterId + ", reportedTrevcId=" + reportedTrevcId
				+ ", reportedContentType=" + reportedContentType + ", reportReason=" + reportReason + "]";
	}

}
