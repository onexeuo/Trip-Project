package tot.domain;

public class ReportTReviewDTO {

	private String reporterId; // 신고한 사용자 아이디
	private int reportedTrevId; // 신고된 여행후기 아이디
	private String reportedContentType; // 신고된 콘텐츠 유형
	private String reportReason; // 신고 사유

	public ReportTReviewDTO() {
	}

	public ReportTReviewDTO(String reporterId, int reportedTrevId, String reportedContentType, String reportReason) {
		this.reporterId = reporterId;
		this.reportedTrevId = reportedTrevId;
		this.reportedContentType = reportedContentType;
		this.reportReason = reportReason;
	}

	public String getReporterId() {
		return reporterId;
	}

	public int getReportedTrevId() {
		return reportedTrevId;
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

	public void setReportedTrevId(int reportedTrevId) {
		this.reportedTrevId = reportedTrevId;
	}

	public void setReportedContentType(String reportedContentType) {
		this.reportedContentType = reportedContentType;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	@Override
	public String toString() {
		return "ReportTReviewDTO [reporterId=" + reporterId + ", reportedTrevId=" + reportedTrevId
				+ ", reportedContentType=" + reportedContentType + ", reportReason=" + reportReason + "]";
	}

}
