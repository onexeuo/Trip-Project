package tot.admin.service;

import java.util.List;

import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.QnaCommentVO;
import tot.domain.QnaDTO;

public interface AdminQnaService {

	List<QnaDTO> qnaList();

	QnaDTO getQnaDetail(int QNAID);

	QnaDTO getQna(int QNAID);

	PageResDTO<QnaDTO> findQnaListWithPaging(PageReqDTO pageReqDTO, int boardId);

	List<QnaDTO> findQnaListWithPagingByMemId(PageReqDTO dto, String memId); // 추가된 메소드

	List<QnaDTO> findMyQnaList(String memId);

	int insertQnaComment(QnaCommentVO qnaComment);

	List<QnaCommentVO> getCommentsByQnaId(int qnaId);

	int updateQnaStatus(int qnaId, String newStatus);

}