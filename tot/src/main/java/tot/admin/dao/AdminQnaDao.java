package tot.admin.dao;

import java.util.List;

import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.domain.QnaCommentVO;
import tot.domain.QnaDTO;

public interface AdminQnaDao {

	List<QnaDTO> qnaList();

	QnaDTO getQnaDetail(int QNAID);

	QnaDTO getQna(int QNAID);

	List<QnaDTO> qnaListWithPaging(PageDTO pageDTO);

	List<QnaDTO> qnaListWithPagingByMemId(PageReqDTO dto, String memId);

	int selectQnaTotalCount(PageDTO pageDTO);

	List<QnaDTO> getMyQnaList(String memId);

	List<QnaCommentVO> getCommentsByQnaId(int qnaId);

	int insertQnaComment(QnaCommentVO qnaComment);

	int updateQnaStatus(int qnaId, String newStatus);

}