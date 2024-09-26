package tot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import tot.common.enums.ProcessStatus;
import tot.dao.CommentDAO;
import tot.domain.CommentReqDTO;
import tot.domain.CommentVO;
import tot.domain.HistoryVO;
import tot.domain.MemberVO;
import tot.domain.ReportCommentDTO;
import tot.domain.ReportVO;
import tot.exception.ServerException;
import tot.util.MemberUtil;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private HistoryService historyService;

	@Override
	public List<CommentVO> getCommentsByReviewId(int trevId) {
		try {
			List<CommentVO> comments = commentDAO.getCommentsByReviewId(trevId);

			return comments;
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 목록 가져오던 중 오류 발생", e);
		}
	}

	/*
	 * 댓글 정보 처리 
	 * 최상위 댓글 ID, 댓글 depth 저장 
	 * 최상위 댓글일 경우, 자신의 commentId를 topParentId로 설정
	 */
	@Override
	public void insertComment(String boardId, int postId, CommentReqDTO commentReqDTO) {
		try {
			MemberVO member = MemberUtil.isAuthenticatedMember();

			CommentVO commentVO = new CommentVO(postId, commentReqDTO);
			handleSetCommentInfo(commentVO, postId);

			commentDAO.insertComment(commentVO);

			int insertedCommentId = commentVO.getCommentId();
			if (commentVO.getParentId() == 0) {
				commentVO.setCommentInfo(insertedCommentId, 0);

				updateTopParentId(commentVO);
			}

			saveHistory(insertedCommentId, member.getMemId(), member.getMemNick(), "등록", commentReqDTO.getContent(), null);
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 작성 등록 중 오류 발생", e);
		}
	}

	/*
	 * 댓글 수정 처리 
	 * 새로운 댓글 내용으로 업데이트
	 */
	@Override
	public void editComment(int commentId, String content) {
		try {
			MemberVO member = MemberUtil.isAuthenticatedMember();

			commentDAO.editComment(commentId, content);

			saveHistory(commentId, member.getMemId(), member.getMemNick(), "수정", content, null);
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 수정 중 오류 발생", e);
		}
	}

	/*
	 * 댓글 삭제 처리 
	 * 해당 댓글 삭제 상태 업데이트
	 */
	@Override
	public void deleteComment(int commentId) {
		try {
			CommentVO existingComment = commentDAO.getCommentById(commentId);
			if (existingComment != null) {
				MemberVO member = MemberUtil.isAuthenticatedMember();
				
				commentDAO.deleteComment(commentId);
				
				saveHistory(commentId, member.getMemId(), member.getMemNick(), "삭제", existingComment.getContent(), null);
			}
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 삭제 중 오류 발생", e);
		}
	}

	/*
	 * 댓글 신고 처리 
	 * 해당 댓글 신고 상태 업데이트
	 */
	@Override
	public void reportComment(int commentId, String reportedContentType, String reportReason) {
		try {
			MemberVO member = MemberUtil.isAuthenticatedMember();

			ReportCommentDTO reportCommentDTO = new ReportCommentDTO(member.getMemId(), commentId, reportedContentType,
					reportReason);
			ReportVO reportVO = new ReportVO(reportCommentDTO);

			commentDAO.updateCommentStatus(commentId);
			commentDAO.insertReportComment(reportVO);

			saveHistory(commentId, member.getMemId(), member.getMemNick(), "신고", reportReason, ProcessStatus.RECEIVED);
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 신고 중 오류 발생", e);
		}

	}

	/*
	 * 댓글 수정, 삭제에 대한 업데이트 시간 반환
	 */
	@Override
	public String getUpdateDate(int commentId) {
		try {
			return commentDAO.getUpdateDate(commentId);
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 신고 중 오류 발생", e);
		}
	}

	/*
	 * 추가되는 댓글에 대한 최상위 부모 댓글 ID, depth 설정 
	 * 부모 댓글 ID가 있을 경우, 대댓글로 간주
	 */
	private void handleSetCommentInfo(CommentVO vo, int postId) {
		if (vo.getParentId() != 0) {
			CommentVO parentComment = commentDAO.getCommentById(vo.getParentId());

			if (parentComment != null) {
				vo.setCommentInfo(parentComment.getTopParentId(), parentComment.getDepth() + 1);
			}
		} else {
			vo.setCommentInfo(vo.getCommentId(), 0);
		}
	}

	/*
	 * 최상위 댓글 topParentId 업데이트 처리
	 */
	private void updateTopParentId(CommentVO commentVO) {
		commentDAO.updateTopParentId(commentVO.getCommentId());
	}

	/*
	 * 여행 후기글 등록, 수정, 삭제, 신고 내역 기록 메소드
	 */
	private void saveHistory(int trevcId, String memId, String memNick, String action, String content,
			ProcessStatus status) {
		HistoryVO historyVO = HistoryVO.fromCommentHistoryVO(trevcId, memId, memNick, action, content, status);

		historyService.insertCommentHistory(historyVO);
	}

}
