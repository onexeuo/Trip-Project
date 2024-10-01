package tot.domain;

import java.sql.Timestamp;

import tot.common.enums.Flag;
import tot.util.MemberUtil;

public class CommentVO {

	private int commentId; // 댓글 아이디
	private int postId; // 게시물 아이디
	private int topParentId; // 최상위 부모 댓글 아이디
	private int parentId; // 부모 댓글 아이디
	private String parentNickname; // 부모 댓글 닉네임
	private String memId; // 댓글 작성자 아이디
	private String memNick; // 댓글 작성자 이름
	private String content; // 내용
	private int depth; // 댓글 깊이
	private Flag status; // 활성화 코드
	private Timestamp regDate; // 작성일시
	private Timestamp update; // 수정일시

	public CommentVO() {
	}

	public CommentVO(int postId, CommentReqDTO commentReqDTO) {
		this.postId = postId;
		this.parentId = commentReqDTO.getParentId();
		this.parentNickname = commentReqDTO.getParentNickname();
		this.memId = MemberUtil.getAuthenticatedMember().getMemId();
		this.memNick = MemberUtil.getAuthenticatedMember().getMemNick();
		this.content = commentReqDTO.getContent();
	}

	public CommentVO(int commentId, int postId, int topParentId, int parentId, String parentNickname, String memId,
			String memNick, String content, int depth, Flag status, Timestamp regDate, Timestamp update) {
		this.commentId = commentId;
		this.postId = postId;
		this.topParentId = topParentId;
		this.parentId = parentId;
		this.parentNickname = parentNickname;
		this.memId = memId;
		this.memNick = memNick;
		this.content = content;
		this.depth = depth;
		this.status = status;
		this.regDate = regDate;
		this.update = update;
	}

	public int getCommentId() {
		return commentId;
	}

	public int getPostId() {
		return postId;
	}

	public int getTopParentId() {
		return topParentId;
	}

	public int getParentId() {
		return parentId;
	}

	public String getParentNickname() {
		return parentNickname;
	}

	public String getMemId() {
		return memId;
	}

	public String getMemNick() {
		return memNick;
	}

	public String getContent() {
		return content;
	}

	public int getDepth() {
		return depth;
	}

	public Flag getStatus() {
		return status;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getUpdate() {
		return update;
	}

	// 최상위 댓글 ID, 댓글 depth 설정
	public void setCommentInfo(int topParentId, int depth) {
		this.topParentId = topParentId;
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "CommentVO [commentId=" + commentId + ", postId=" + postId + ", topParentId=" + topParentId
				+ ", parentId=" + parentId + ", parentNickname=" + parentNickname + ", memId=" + memId + ", memNick="
				+ memNick + ", content=" + content + ", depth=" + depth + ", status=" + status + ", regDate=" + regDate
				+ ", update=" + update + "]";
	}

}
