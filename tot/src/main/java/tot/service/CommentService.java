package tot.service;

import java.util.List;

import tot.domain.CommentReqDTO;
import tot.domain.CommentVO;

public interface CommentService {

	List<CommentVO> getCommentsByReviewId(int trevid);

	void insertComment(String boardId, int postId, CommentReqDTO commentReqDTO);

	void editComment(int commentId, String content);

	void deleteComment(int commentId);

	void reportComment(int commentId, String reportedContentType, String reportReason);

	String getUpdateDate(int commentId);

}
