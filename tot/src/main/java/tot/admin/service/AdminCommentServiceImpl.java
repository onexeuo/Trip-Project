package tot.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import tot.admin.dao.AdminCommentDAO;
import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.CommentVO;
import tot.exception.ServerException;

@Service
public class AdminCommentServiceImpl implements AdminCommentService {

	@Autowired
	private AdminCommentDAO adminCommentDAO;

	@Override
	public List<CommentVO> getCommentsByReviewId(int trevId) {
		try {
			List<CommentVO> comments = adminCommentDAO.getCommentsByReviewId(trevId);

			return comments;
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 목록 가져오던 중 오류 발생", e);
		}
	}

	@Override
	public void updateCommentStatus(String status, List<Integer> trevcIds) {
		try {
			// 상위 댓글이 비활성화 상태인지 확인
			List<String> inactiveParentComments = adminCommentDAO.findInactiveParentComments(trevcIds);

			// 상위 댓글이 비활성화된 경우 예외 처리
			if (!inactiveParentComments.isEmpty() && "active".equals(status)) {
				throw new IllegalStateException("상위 댓글이 비활성화되어 있어 하위 댓글을 활성화할 수 없습니다.");
			}

			// 검증 통과 시 상태 업데이트
			adminCommentDAO.updateCommentStatus(status, trevcIds);
		} catch (DataAccessException e) {
			throw new ServerException("여행 댓글 상태 업데이트 중 오류 발생", e);
		}

	}

	@Override
	public PageResDTO<CommentVO> findCommentListWithPaging(PageReqDTO pageReqDTO, String boardId) {
		try {
			PageDTO pageDTO = new PageDTO(pageReqDTO, boardId);
			int totalTReviewCount = adminCommentDAO.selectTotalCommentCount(pageDTO);

			List<CommentVO> commentList = adminCommentDAO.selectCommentListWithPaging(pageDTO);

			return new PageResDTO<>(totalTReviewCount, pageReqDTO.getPage(), commentList);
		} catch (DataAccessException e) {
			throw new ServerException("여행 게시물 목록 정보 가져오던 중 데이터베이스 오류 발생", e);
		}
	}

}
