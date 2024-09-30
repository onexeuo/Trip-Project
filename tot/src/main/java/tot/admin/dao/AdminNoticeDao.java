package tot.admin.dao;

import java.util.List;

import tot.common.page.PageDTO;
import tot.domain.NoticeVO;

public interface AdminNoticeDao {

	List<NoticeVO> noticeList();

	NoticeVO getNoticeById(int noid);

	void insertNotice(NoticeVO notice);

	int deleteNotice(int noid);

	void updateNotice(NoticeVO notice);

	List<NoticeVO> noticeListWithPaging(PageDTO pageDTO);

	int selectNoticeTotalCount(PageDTO pageDTO);

}
