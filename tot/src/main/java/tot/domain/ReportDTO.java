package tot.domain;

import java.sql.Timestamp;

import tot.common.enums.ProcessStatus;

public class ReportDTO {

	private int reportId; // 신고 아이디
	private String reporterId; // 신고한 사용자 아이디
	private String reporterNickName; // 신고자 닉네임
	private int reportedTrevId; // 신고된 게시물 아이디
	private String postTitle; // 게시물 제목
	private int reportedTrevcId; // 신고된 댓글 아이디
	private String commentContent; // 댓글 내용
	private String reportedContentType; // 신고된 콘텐츠 유형
	private String reportReason; // 신고 사유
	private Timestamp reportDate; // 신고 날짜
	private Timestamp processDate; // 신고 처리 날짜
	private ProcessStatus reportStatus; // 신고 처리 상태

	public ReportDTO() {
	}

	public ReportDTO(int reportId, String reporterId, String reporterNickName, int reportedTrevId, String postTitle,
			int reportedTrevcId, String commentContent, String reportedContentType, String reportReason,
			Timestamp reportDate, Timestamp processDate, ProcessStatus reportStatus) {
		this.reportId = reportId;
		this.reporterId = reporterId;
		this.reporterNickName = reporterNickName;
		this.reportedTrevId = reportedTrevId;
		this.postTitle = postTitle;
		this.reportedTrevcId = reportedTrevcId;
		this.commentContent = commentContent;
		this.reportedContentType = reportedContentType;
		this.reportReason = reportReason;
		this.reportDate = reportDate;
		this.processDate = processDate;
		this.reportStatus = reportStatus;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getReporterId() {
		return reporterId;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public String getReporterNickName() {
		return reporterNickName;
	}

	public void setReporterNickName(String reporterNickName) {
		this.reporterNickName = reporterNickName;
	}

	public int getReportedTrevId() {
		return reportedTrevId;
	}

	public void setReportedTrevId(int reportedTrevId) {
		this.reportedTrevId = reportedTrevId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public int getReportedTrevcId() {
		return reportedTrevcId;
	}

	public void setReportedTrevcId(int reportedTrevcId) {
		this.reportedTrevcId = reportedTrevcId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getReportedContentType() {
		return reportedContentType;
	}

	public void setReportedContentType(String reportedContentType) {
		this.reportedContentType = reportedContentType;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public Timestamp getReportDate() {
		return reportDate;
	}

	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

	public Timestamp getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	public ProcessStatus getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(ProcessStatus reportStatus) {
		this.reportStatus = reportStatus;
	}

	@Override
	public String toString() {
		return "ReportDTO [reportId=" + reportId + ", reporterId=" + reporterId + ", reporterNickName="
				+ reporterNickName + ", reportedTrevId=" + reportedTrevId + ", postTitle=" + postTitle
				+ ", reportedTrevcId=" + reportedTrevcId + ", commentContent=" + commentContent
				+ ", reportedContentType=" + reportedContentType + ", reportReason=" + reportReason + ", reportDate="
				+ reportDate + ", processDate=" + processDate + ", reportStatus=" + reportStatus + "]";
	}

}
