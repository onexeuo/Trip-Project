package tot.admin.service;

import java.util.List;

import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.TReviewResDTO;

public interface AdminTReviewService {

	PageResDTO<TReviewResDTO> findTReviewListWithPaging(PageReqDTO dto, String boardId);

	void updateTReviewStatus(String status, List<Integer> trevIds);

	TReviewResDTO getTReviewById(int trevId);

}
