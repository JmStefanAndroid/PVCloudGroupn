package pv.com.pvcloudgo.model.bean;

/**
 * Created by Administrator on 2017/1/12.
 */
public class Pager {

    int currentPage;
    int currentPageRowCount;
    int nextPage;
    int pageSize;
    int startRow;
    int totalPages;
    int totalRows;
    String url_end;

    public int getCurrentPage() {
        return currentPage;
    }

    public int getCurrentPageRowCount() {
        return currentPageRowCount;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public String getUrl_end() {
        return url_end;
    }
}
