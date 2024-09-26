package tot.admin.dao;

import java.util.List;

import tot.common.page.PageDTO;
import tot.domain.CommentVO;

public interface AdminCommentDAO {

	List<CommentVO> getCommentsByReviewId(int trevid);

	List<String> findInactiveParentComments(List<Integer> trevcIds);

	void updateCommentStatus(String status, List<Integer> trevcIds);

	int selectTotalCommentCount(PageDTO pageDTO);

	List<CommentVO> selectCommentListWithPaging(PageDTO pageDTO);

}
