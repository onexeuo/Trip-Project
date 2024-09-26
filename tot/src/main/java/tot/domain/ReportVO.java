package tot.domain;

import java.sql.Timestamp;

import tot.common.enums.ProcessStatus;

public class ReportVO {

	private int reportId; // 신고 아이디
	private String reporterId; // 신고한 사용자 아이디
	private int reportedTrevId; // 신고된 게시물 아이디
	private int reportedTrevcId; // 신고된 댓글 아이디
	private String reportedContentType; // 신고된 콘텐츠 유형
	private String reportReason; // 신고 사유
	private Timestamp reportDate; // 신고 날짜
	private Timestamp processDate; // 신고 처리 날짜
	private ProcessStatus reportStatus; // 신고 처리 상태

	public ReportVO() {
	}

	public ReportVO(int reportId, String reporterId, int reportedTrevId, int reportedTrevcId,
			String reportedContentType, String reportReason, Timestamp reportDate, Timestamp processDate,
			ProcessStatus reportStatus) {
		this.reportId = reportId;
		this.reporterId = reporterId;
		this.reportedTrevId = reportedTrevId;
		this.reportedTrevcId = reportedTrevcId;
		this.reportedContentType = reportedContentType;
		this.reportReason = reportReason;
		this.reportDate = reportDate;
		this.processDate = processDate;
		this.reportStatus = reportStatus;
	}

	public ReportVO(ReportCommentDTO dto) {
		this.reporterId = dto.getReporterId();
		this.reportedTrevcId = dto.getReportedTrevcId();
		this.reportedContentType = dto.getReportedContentType();
		this.reportReason = dto.getReportReason();
		this.reportStatus = ProcessStatus.RECEIVED;
	}

	public ReportVO(ReportTReviewDTO dto) {
		this.reporterId = dto.getReporterId();
		this.reportedTrevId = dto.getReportedTrevId();
		this.reportedContentType = dto.getReportedContentType();
		this.reportReason = dto.getReportReason();
		this.reportStatus = ProcessStatus.RECEIVED;
	}

	public int getReportId() {
		return reportId;
	}

	public String getReporterId() {
		return reporterId;
	}

	public int getReportedTrevId() {
		return reportedTrevId;
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

	public Timestamp getReportDate() {
		return reportDate;
	}

	public Timestamp getProcessDate() {
		return processDate;
	}

	public ProcessStatus getReportStatus() {
		return reportStatus;
	}

	@Override
	public String toString() {
		return "ReportVO [reportId=" + reportId + ", reporterId=" + reporterId + ", reportedTrevId=" + reportedTrevId
				+ ", reportedTrevcId=" + reportedTrevcId + ", reportedContentType=" + reportedContentType
				+ ", reportReason=" + reportReason + ", reportDate=" + reportDate + ", processDate=" + processDate
				+ ", reportStatus=" + reportStatus + "]";
	}

}
